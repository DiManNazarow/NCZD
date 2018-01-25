package ru.mbg.nczd.activities.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.auth.mvp.LoginActivityPresenter;
import ru.mbg.nczd.activities.register.mvp.RegisterActivityPresenter;
import ru.mbg.nczd.activities.register.mvp.RegisterView;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @BindView(R.id.email_text_input_layout)
    protected TextInputLayout mEmailInput;
    @BindView(R.id.login_text_input_layout)
    protected TextInputLayout mLoginInput;
    @BindView(R.id.omc_text_input_layout)
    protected TextInputLayout mOmcInput;
    @BindView(R.id.password_text_input_layout)
    protected TextInputLayout mPasswordInput;
    @BindView(R.id.confirm_password_text_input_layout)
    protected TextInputLayout mConfirmPasswordInput;

    @BindView(R.id.email_edit_text)
    protected AppCompatEditText mEmailEditText;
    @BindView(R.id.login_edit_text)
    protected AppCompatEditText mLoginEditText;
    @BindView(R.id.omc_edit_text)
    protected AppCompatEditText mOmcEditText;
    @BindView(R.id.password_edit_text)
    protected AppCompatEditText mPasswordEditText;
    @BindView(R.id.confirm_password_edit_text)
    protected AppCompatEditText mConfirmPasswordEditText;

    @InjectPresenter(type = PresenterType.LOCAL)
    RegisterActivityPresenter mRegisterActivityPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    RegisterActivityPresenter provideRegisterActivityPresenter(){
        return new RegisterActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mRegisterActivityPresenter.setupViews(mEmailEditText, mLoginEditText, mOmcEditText, mPasswordEditText, mConfirmPasswordEditText);
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.registration);
    }

    @OnClick(R.id.register_button)
    public void onRegisterClick(){
        mRegisterActivityPresenter.register();
    }

    @OnClick(R.id.register_text_view)
    public void onAuthTextClick(){
        mRegisterActivityPresenter.openSignInScreen();
    }

    @Override
    public void onEmailError(String text) {
        mEmailInput.setError(text);
    }

    @Override
    public void onLoginError(String text) {
        mLoginInput.setError(text);
    }

    @Override
    public void onOmcError(String text) {
        mOmcInput.setError(text);
    }

    @Override
    public void onPasswordError(String text) {
        mPasswordInput.setError(text);
    }

    @Override
    public void onConfirmPasswordError(String text) {
        mConfirmPasswordInput.setError(text);
    }
}
