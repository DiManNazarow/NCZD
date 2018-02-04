package ru.mbg.nczd.activities.kidz;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.DatePicker;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.kidz.mvp.AddKidzActivityPresenter;
import ru.mbg.nczd.activities.kidz.mvp.AddKidzView;
import ru.mbg.nczd.db.models.Child;
import ru.mbg.nczd.utils.DateUtils;
import ru.mbg.nczd.utils.Params;

public class AddKidzActivity extends BaseActivity implements AddKidzView {

    @BindView(R.id.first_name_input_layout)
    protected TextInputLayout mFirstNameInput;
    @BindView(R.id.second_name_input_layout)
    protected TextInputLayout mSecondNameInput;
    @BindView(R.id.patronymic_input_layout)
    protected TextInputLayout mPatronymicInput;
    @BindView(R.id.date_birth_input_layout)
    protected TextInputLayout mDateBirthInput;
    @BindView(R.id.omc_input_layout)
    protected TextInputLayout mOmcInput;

    @BindView(R.id.first_name_edit_text)
    protected TextInputEditText mFirstNameEdit;
    @BindView(R.id.second_name_edit_text)
    protected TextInputEditText mSecondNameEdit;
    @BindView(R.id.patronymic_edit_text)
    protected TextInputEditText mPatronymicEdit;
    @BindView(R.id.omc_edit_text)
    protected TextInputEditText mOMCEdit;
    @BindView(R.id.date_birth_edit_text)
    protected TextInputEditText mDateBirthEditText;

    private long mChildId;

    @InjectPresenter(type = PresenterType.LOCAL)
    public AddKidzActivityPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    public AddKidzActivityPresenter provideLoginActivityPresenter() {
        return new AddKidzActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_kidz);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mChildId = getIntent().getLongExtra(Params.CHILD_ID_ARG, -1);
        editChild();
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.kidz_child_info);
    }

    @OnClick(R.id.date_birth_edit_text)
    protected void onDateBirthEditClick(){
        DateUtils.showDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = DateUtils.getStringDate(year, month, dayOfMonth);
                mPresenter.setDateBirth(date);
                mDateBirthEditText.setText(date);
                onDateBirthError(null);
            }
        });
    }

    @OnClick(R.id.save_button)
    protected void onSaveClick(){
        if (mChildId != -1){
            mPresenter.save(mChildId);
        } else {
            mPresenter.save();
        }
    }

    @OnTextChanged(R.id.first_name_edit_text)
    protected void onFirstNameChanged(CharSequence text){
        mPresenter.setFirstName(text.toString());
        onFirstNameError(null);
    }

    @OnTextChanged(R.id.second_name_edit_text)
    protected void onSecondNameChanged(CharSequence text){
        mPresenter.setSecondName(text.toString());
        onSecondNameError(null);
    }

    @OnTextChanged(R.id.patronymic_edit_text)
    protected void onPatronymicChanged(CharSequence text){
        mPresenter.setPatronymic(text.toString());
        onPatronymicError(null);
    }

    @OnTextChanged(R.id.omc_edit_text)
    protected void onOmcChanged(CharSequence text){
        mPresenter.setOMC(text.toString());
    }

    @Override
    public void onFirstNameError(String text) {
        mFirstNameInput.setError(text);
    }

    @Override
    public void onSecondNameError(String text) {
        mSecondNameInput.setError(text);
    }

    @Override
    public void onPatronymicError(String text) {
        mPatronymicInput.setError(text);
    }

    @Override
    public void onDateBirthError(String text) {
        mDateBirthInput.setError(text);
    }

    @Override
    public void onOMCError(String text) {
        mOmcInput.setError(text);
    }

    @Override
    public void onSave(long childId) {
        Intent intent = new Intent();
        intent.putExtra(Params.CHILD_ID_ARG, childId);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void editChild(){
        if (mChildId != -1){
            Child child = App.getAppDatabase().getChildrenDao().get(mChildId);
            if (child != null){
                mFirstNameEdit.setText(child.getFirstName());
                mPresenter.setFirstName(child.getFirstName());
                mSecondNameEdit.setText(child.getSecondName());
                mPresenter.setSecondName(child.getSecondName());
                mPatronymicEdit.setText(child.getPatronymic());
                mPresenter.setPatronymic(child.getPatronymic());
                mDateBirthEditText.setText(child.getDateBirth());
                mPresenter.setDateBirth(child.getDateBirth());
                mOMCEdit.setText(child.getOmc());
                mPresenter.setOMC(child.getOmc());
            }
        }
    }

}
