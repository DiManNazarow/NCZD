package ru.mbg.nczd.activities.personalinfo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.DatePicker;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.personalinfo.mvp.PersonalInfoActivityPresenter;
import ru.mbg.nczd.activities.personalinfo.mvp.PersonalInfoView;
import ru.mbg.nczd.utils.DateUtils;
import ru.mbg.nczd.utils.Params;

public class PersonalInfoActivity extends BaseActivity implements PersonalInfoView {

    @BindView(R.id.first_name_input_layout)
    protected TextInputLayout mFirstNameInput;
    @BindView(R.id.second_name_input_layout)
    protected TextInputLayout mSecondNameInput;
    @BindView(R.id.patronymic_input_layout)
    protected TextInputLayout mPatronymicInput;
    @BindView(R.id.date_birth_input_layout)
    protected TextInputLayout mDateBirthInput;
    @BindView(R.id.omc_input_layout)
    protected TextInputLayout mOMCInput;
    @BindView(R.id.number_input_layout)
    protected TextInputLayout mNumberInput;

    @BindView(R.id.date_birth_edit_text)
    protected TextInputEditText mDateBirthEditText;
    @BindView(R.id.omc_edit_text)
    protected TextInputEditText mOmcEdit;

    @InjectPresenter(type = PresenterType.LOCAL)
    public PersonalInfoActivityPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    public PersonalInfoActivityPresenter providePersonalActivityPresenter() {
        return new PersonalInfoActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_info);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.personal_info);
    }

    @OnClick(R.id.save_button)
    public void onSaveClick(){
        if (getIntent() != null) {
            mPresenter.save(getIntent().getLongExtra(Params.USER_ID_ARG, Long.MIN_VALUE));
        }
    }

    @OnClick(R.id.date_birth_edit_text)
    protected void onDateBirthClick(){
        DateUtils.showDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = DateUtils.getStringDate(year, month, dayOfMonth);
                mDateBirthEditText.setText(date);
                mPresenter.setDateBirth(date);
                onDateBirthError(null);
            }
        });
    }

    @OnTextChanged(R.id.first_name_edit_text)
    public void onFirstNameChanged(CharSequence text){
        mPresenter.setFirstName(text.toString());
        onFirstNameError(null);
    }

    @OnTextChanged(R.id.second_name_edit_text)
    public void onSecondNameChanged(CharSequence text){
        mPresenter.setSecondName(text.toString());
        onSecondNameError(null);
    }

    @OnTextChanged(R.id.patronymic_edit_text)
    public void onPatronymicChanged(CharSequence text){
        mPresenter.setPatronymic(text.toString());
        onPatronymicError(null);
    }

    @OnTextChanged(R.id.omc_edit_text)
    public void onOMCTextChanged(CharSequence text){
        mPresenter.setOmc(text.toString());
        onOMCError(null);
    }

    @OnTextChanged(R.id.number_edit_text)
    public void onNumberChanged(CharSequence text){
        mPresenter.setNumber(text.toString());
        onNumberError(null);
    }

    @Override
    public void onFirstNameError(String error) {
        mFirstNameInput.setError(error);
    }

    @Override
    public void onSecondNameError(String error) {
        mSecondNameInput.setError(error);
    }

    @Override
    public void onPatronymicError(String error) {
        mPatronymicInput.setError(error);
    }

    @Override
    public void onDateBirthError(String error) {
        mDateBirthInput.setError(error);
    }

    @Override
    public void onOMCError(String error) {
        mOMCInput.setError(error);
    }

    @Override
    public void onNumberError(String error) {
        mNumberInput.setError(error);
    }
}
