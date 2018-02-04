package ru.mbg.nczd.activities.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.auth.mvp.LoginActivityPresenter;
import ru.mbg.nczd.activities.auth.mvp.LoginView;
import ru.mbg.nczd.utils.GuiUtils;

public class LoginActivity extends BaseActivity implements LoginView {

    public static final String USER_ID_ARG = "user_id_arg";

    @BindView(R.id.login_text_input_layout)
    protected TextInputLayout mLoginInput;
    @BindView(R.id.password_text_input_layout)
    protected TextInputLayout mPasswordInput;

    @BindView(R.id.login_edit_text)
    protected TextInputEditText mLoginEditText;
    @BindView(R.id.password_edit_text)
    protected TextInputEditText mPasswordEditText;
    @BindView(R.id.sign_in_button)
    protected Button mSignInButton;

    @InjectPresenter(type = PresenterType.LOCAL)
    public LoginActivityPresenter mPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    public LoginActivityPresenter provideLoginActivityPresenter() {
        return new LoginActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        mPresenter.setupViews(mLoginEditText, mPasswordEditText, mSignInButton);
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.sign_in);
    }

    @Override
    public void onLogin(long userId) {
        Intent intent = new Intent();
        intent.putExtra(USER_ID_ARG, userId);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onLoginError(String error) {
        mLoginInput.setError(error);
    }

    @Override
    public void onPasswordError(String error) {
        mPasswordInput.setError(error);
    }

    @Override
    public void onAuthError() {
        GuiUtils.showOkMessage(R.string.error_auth_failed_title, R.string.error_auth_failed_message, this);
    }

    @OnClick(R.id.recover_text_view)
    public void onRecoverClick() {
        mPresenter.openRecoverActivity();
    }
}
