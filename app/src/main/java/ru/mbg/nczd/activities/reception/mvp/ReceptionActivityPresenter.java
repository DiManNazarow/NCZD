package ru.mbg.nczd.activities.reception.mvp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.db.UserManager;
import ru.mbg.nczd.db.models.Reception;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.db.models.UserEntity;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.AppTextUtils;
import ru.mbg.nczd.utils.DateUtils;
import ru.mbg.nczd.utils.GuiUtils;
import ru.mbg.nczd.utils.Params;
import ru.mbg.nczd.views.SpacingItemDecoration;
import ru.mbg.nczd.views.SpinnerItem;

/**
 * Created by Дмитрий on 29.01.2018.
 */

public class ReceptionActivityPresenter extends BaseMvpPresenter<ReceptionView> {

    private User mUser;

    private ReceptionAdapter mReceptionAdapter;

    private Params.RECEPTION_TYPE mReceptionType;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;

    public ReceptionActivityPresenter(@NonNull Activity activity) {
        super(activity);
        mUser = UserManager.instance().getUser();
        mReceptionAdapter = new ReceptionAdapter();
    }

    public void getTypeFromIntent(Intent intent){
        mReceptionType = Params.RECEPTION_TYPE.getById(intent.getIntExtra(Params.RECEPTION_TYPE_ARG, -1));
    }

    public Params.RECEPTION_TYPE getReceptionType() {
        return mReceptionType;
    }

    public void prepareRecyclerView(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new SpacingItemDecoration());
        mRecyclerView.setAdapter(mReceptionAdapter);
    }

    public void setUser(User user){
        mUser = user;
        mReceptionAdapter.notifyDataSetChanged();
    }

    public void addReception(){
        Reception reception = new Reception();
        if (((ReceptionSettingViewHolder)mReceptionAdapter.mHolders.get(ReceptionAdapter.RECEPTION_SETTINGS_VIEW_TYPE)).isHasError()){
            scrollToError(1);
            return;
        }
        if (!((AgreePersonalDateProcessHolder)mReceptionAdapter.mHolders.get(ReceptionAdapter.PERSONAL_INFO_PROCESS_VIEW_TYPE)).isChecked()){
            scrollToError(2);
            GuiUtils.showOkMessage(R.string.reception_personal_data_process_error_title, R.string.reception_personal_data_process_error, getActivity());
            return;
        }
        ReceptionSettingViewHolder.Settings settings = ((ReceptionSettingViewHolder)mReceptionAdapter.mHolders.get(ReceptionAdapter.RECEPTION_SETTINGS_VIEW_TYPE)).getSettings();
        reception.setTypeId(mReceptionType.getReceptionTypeId());
        reception.setType(mReceptionType.getReceptionName(getActivity()));
        reception.setDate(settings.date);
        reception.setTime(settings.time);
        reception.setRepeated(settings.repeated);
        reception.setPurpose(settings.purpose);
        reception.setUserId(mUser.getId());
        App.getAppDatabase().getReceptionDao().insert(reception);
        mUser.getReceptions().add(reception);
        long userId = App.getAppDatabase().getUserDao().insert(mUser);
        UserManager.instance().setUserId(userId);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(Params.RECEPTION_ADD_ACTION));
        getActivity().finish();
    }

    public class ReceptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        static final int PERSONAL_INFO_VIEW_TYPE = 0;
        static final int RECEPTION_SETTINGS_VIEW_TYPE = 1;
        static final int PERSONAL_INFO_PROCESS_VIEW_TYPE = 2;

        private final int ITEMS_COUNT = 3;

        HashMap<Integer, RecyclerView.ViewHolder> mHolders;

        ReceptionAdapter(){
            mHolders = new HashMap<>();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType){
                case PERSONAL_INFO_VIEW_TYPE: {
                    PersonalInfoViewHolder holder = new PersonalInfoViewHolder(parent);
                    mHolders.put(PERSONAL_INFO_VIEW_TYPE, holder);
                    return holder;
                }
                case RECEPTION_SETTINGS_VIEW_TYPE:{
                    ReceptionSettingViewHolder holder = new ReceptionSettingViewHolder(parent);
                    mHolders.put(RECEPTION_SETTINGS_VIEW_TYPE, holder);
                    return holder;
                }
                case PERSONAL_INFO_PROCESS_VIEW_TYPE:{
                    AgreePersonalDateProcessHolder holder = new AgreePersonalDateProcessHolder(parent);
                    mHolders.put(PERSONAL_INFO_PROCESS_VIEW_TYPE, holder);
                    return holder;
                }
                default: return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (position){
                case 0: ((PersonalInfoViewHolder)holder).setup(mUser); break;
                case 1: ((ReceptionSettingViewHolder)holder).setup(); break;
            }
        }

        public int getItemViewType(int position){
            switch (position){
                case 0: return PERSONAL_INFO_VIEW_TYPE;
                case 1: return RECEPTION_SETTINGS_VIEW_TYPE;
                case 2: return PERSONAL_INFO_PROCESS_VIEW_TYPE;
                default: return -1;
            }
        }

        @Override
        public int getItemCount() {
            return ITEMS_COUNT;
        }
    }

    public class PersonalInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_name_input_layout)
        TextInputLayout mFirstNameInput;
        @BindView(R.id.second_name_input_layout)
        TextInputLayout mSecondNameInput;
        @BindView(R.id.patronymic_input_layout)
        TextInputLayout mPatronymicInput;
        @BindView(R.id.number_input_layout)
        TextInputLayout mNumberInput;
        @BindView(R.id.email_input_layout)
        TextInputLayout mEmailInput;

        @BindView(R.id.first_name_edit_text)
        TextInputEditText mFirstNameEditText;
        @BindView(R.id.second_name_edit_text)
        TextInputEditText mSecondNameEditText;
        @BindView(R.id.patronymic_edit_text)
        TextInputEditText mPatronymicEditText;
        @BindView(R.id.number_edit_text)
        TextInputEditText mNumberEditText;
        @BindView(R.id.email_edit_text)
        TextInputEditText mEmailEditText;

        UserEntity mUser;

        public PersonalInfoViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_reception_personal_holder, rootView, false));
            ButterKnife.bind(this, itemView);
            mFirstNameEditText.addTextChangedListener(new AppTextUtils.TextChangeListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mFirstNameInput.setError(null);
                }
            });
            mFirstNameEditText.setOnTouchListener(AppTextUtils.sEmptyTouchListener);
            mSecondNameEditText.addTextChangedListener(new AppTextUtils.TextChangeListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mSecondNameInput.setError(null);
                }
            });
            mSecondNameEditText.setOnTouchListener(AppTextUtils.sEmptyTouchListener);
            mPatronymicEditText.addTextChangedListener(new AppTextUtils.TextChangeListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mPatronymicInput.setError(null);
                }
            });
            mPatronymicEditText.setOnTouchListener(AppTextUtils.sEmptyTouchListener);
            mNumberEditText.setOnTouchListener(AppTextUtils.sEmptyTouchListener);
            mNumberEditText.addTextChangedListener(new AppTextUtils.TextChangeListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mNumberInput.setError(null);
                }
            });
            mEmailEditText.setOnTouchListener(AppTextUtils.sEmptyTouchListener);
            mEmailEditText.addTextChangedListener(new AppTextUtils.TextChangeListener() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mEmailInput.setError(null);
                }
            });
        }

        public void setup(UserEntity user){
            if (user != null) {
                mUser = user;
                if (!AppTextUtils.isEmpty(user.getSecondName())) {
                    mSecondNameEditText.setText(user.getSecondName());
                }
                if (!AppTextUtils.isEmpty(user.getFirstName())) {
                    mFirstNameEditText.setText(user.getFirstName());
                }
                if (!AppTextUtils.isEmpty(user.getPatronymic())) {
                    mPatronymicEditText.setText(user.getPatronymic());
                }
                if (!AppTextUtils.isEmpty(user.getNumber())) {
                    mNumberEditText.setText(user.getNumber());
                }
                if (!AppTextUtils.isEmpty(user.getEmail())) {
                    mEmailEditText.setText(user.getEmail());
                }
            }
        }

        public void getInfo(){
            if (AppTextUtils.isEmpty(mSecondNameEditText.getText())){
                mSecondNameInput.setError(getString(R.string.error_empty_second_name));
                return;
            }
            if (AppTextUtils.isEmpty(mFirstNameEditText.getText())){
                mFirstNameInput.setError(getString(R.string.error_empty_first_name));
                return;
            }
            if (AppTextUtils.isEmpty(mPatronymicEditText.getText())){
                mPatronymicInput.setError(getString(R.string.error_empty_patronymic));
                return;
            }
            if (AppTextUtils.isEmpty(mNumberEditText.getText())){
                mNumberInput.setError(getString(R.string.error_empty_number));
                return;
            }
            if (AppTextUtils.isEmpty(mEmailEditText.getText())){
                mEmailInput.setError(getString(R.string.error_email_empty));
                return;
            }
        }

    }

    public class ReceptionSettingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.reception_type_spinner)
        SpinnerItem mTypeSpinner;
        @BindView(R.id.date_input)
        TextInputLayout mDateInput;
        @BindView(R.id.date_edit_text)
        TextInputEditText mDateEditText;
        @BindView(R.id.time_input)
        TextInputLayout mTimeInput;
        @BindView(R.id.time_edit_text)
        TextInputEditText mTimeEditText;
        @BindView(R.id.purpose_spinner)
        SpinnerItem mPurposeSpinner;

        private String mRepeated = RECEPTION_TYPE.REPEATED.getTypeTitle(getActivity());
        private String mPurpose = PURPOSE_TYPE.CONSULTATION.getTypeTitle(getActivity());
        private String mDate = DateUtils.getStringDate();
        private String mTime = DateUtils.getStringTimeEditText();

        public ReceptionSettingViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_reception_setting_holder, rootView, false));
            ButterKnife.bind(this, itemView);
            mTypeSpinner.setTitleText(R.string.reception_receprion_type);
            mTypeSpinner.setItems(RECEPTION_TYPE.getTypeNameList(itemView.getContext()));
            mPurposeSpinner.setTitleText(R.string.reception_purpose_of_reception);
            mPurposeSpinner.setItems(PURPOSE_TYPE.getTypeNameList(itemView.getContext()));
            mDateEditText.setText(DateUtils.getStringDateEditText());
            mDateEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DateUtils.showDatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            mDateEditText.setText(DateUtils.getStringDateEditText(year, month, dayOfMonth));
                            mDate = DateUtils.getStringDate(year, month, dayOfMonth);
                            mDateInput.setError(null);
                        }
                    });
                }
            });
            mTimeEditText.setText(DateUtils.getStringTimeEditText());
            mTimeEditText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DateUtils.showTimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String time = DateUtils.getStringTimeEditText(hourOfDay, minute);
                            mTimeEditText.setText(time);
                            mTime = time;
                            mTimeInput.setError(null);
                        }
                    });
                }
            });
        }

        public void setup(){

        }

        public boolean isHasError(){
            if (AppTextUtils.isEmpty(mDate)){
                mDateInput.setError(itemView.getContext().getString(R.string.error_empty_date_reception));
                return true;
            }
            if (AppTextUtils.isEmpty(mTime)){
                mTimeInput.setError(itemView.getContext().getString(R.string.error_empty_time_reception));
                return true;
            }
            return false;
        }

        public Settings getSettings(){
            Settings settings = new Settings();
            settings.repeated = mRepeated;
            settings.date = mDate;
            settings.time = mTime;
            settings.purpose = mPurpose;
            return settings;
        }

        public class Settings {

            public String repeated;

            public String date;

            public String time;

            public String purpose;

        }

    }

    public class AgreePersonalDateProcessHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        AppCompatCheckBox mAppCompatCheckBox;

        public AgreePersonalDateProcessHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_alow_personal_data_process_holder, rootView, false));
            ButterKnife.bind(this, itemView);
        }

        public boolean isChecked(){
            return mAppCompatCheckBox.isChecked();
        }

    }

    public String getString(@StringRes int stringRes){
        return getActivity().getString(stringRes);
    }

    public void scrollToError(int position){
        mLinearLayoutManager.scrollToPosition(position);
    }

    public enum RECEPTION_TYPE{
        REPEATED {
            @Override
            public String getTypeTitle(Context context) {
                return context.getString(R.string.reception_type_1);
            }
        };

        public abstract String getTypeTitle(Context context);

        public static List<String> getTypeNameList(Context context){
            List<String> list = new ArrayList<>();
            list.add(context.getString(R.string.reception_type_1));
            return list;
        }
    }

    public enum PURPOSE_TYPE{
        CONSULTATION {
            @Override
            public String getTypeTitle(Context context) {
                return context.getString(R.string.reception_purpose_1);
            }
        };

        public abstract String getTypeTitle(Context context);

        public static List<String> getTypeNameList(Context context){
            List<String> list = new ArrayList<>();
            list.add(context.getString(R.string.reception_purpose_1));
            return list;
        }
    }

}
