package ru.mbg.nczd.activities.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.auth.mvp.LoginActivityPresenter;
import ru.mbg.nczd.activities.auth.mvp.LoginView;
import ru.mbg.nczd.utils.Actions;

public class LoginActivity extends BaseActivity implements LoginView {

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
    @BindView(R.id.recover_text_view)
    protected TextView mRecoverTextView;

    @InjectPresenter
    LoginActivityPresenter mLoginActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        mLoginActivityPresenter.setupViews(mLoginEditText, mPasswordEditText, mSignInButton, mRecoverTextView);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.auth);
    }

    @Override
    public void onLogin() {
        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }

    @Override
    public void onRecoverClick() {

    }

    @Override
    public void onLoginError(String error) {
        mLoginInput.setError(error);
    }

    @Override
    public void onPasswordError(String error) {
        mPasswordInput.setError(error);
    }
}
