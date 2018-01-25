package ru.mbg.nczd.activities.register.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;

import com.arellomobile.mvp.InjectViewState;

import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.Actions;
import ru.mbg.nczd.utils.AppTextUtils;

/**
 * Created by Дмитрий on 18.01.2018.
 */
@InjectViewState
public class RegisterActivityPresenter extends BaseMvpPresenter<RegisterView> {

    private String mEmail;
    private String mLogin;
    private String mOmc;
    private String mPassword;
    private String mConfirmPassword;

    public RegisterActivityPresenter(Activity activity){
        super(activity);
    }

    public void setupViews(AppCompatEditText email, AppCompatEditText login, AppCompatEditText omc, AppCompatEditText password, AppCompatEditText confirmPassword){
        email.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEmail = s.toString();
                getViewState().onEmailError(null);
            }
        });
        login.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLogin = s.toString();
                getViewState().onLoginError(null);
            }
        });
        omc.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mOmc = s.toString();
                getViewState().onOmcError(null);
            }
        });
        password.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPassword = s.toString();
                getViewState().onPasswordError(null);
            }
        });
        confirmPassword.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mConfirmPassword = s.toString();
                getViewState().onConfirmPasswordError(null);
            }
        });
    }

    public void register(){
        if (AppTextUtils.isEmpty(mEmail)){
            getViewState().onEmailError(getActivity().getString(R.string.error_email_empty));
            return;
        }
        if (AppTextUtils.isEmpty(mLogin)){
            getViewState().onLoginError(getActivity().getString(R.string.error_empty_login));
            return;
        }
        if (AppTextUtils.isEmpty(mOmc)){
            getViewState().onOmcError(getActivity().getString(R.string.error_empty_omc));
            return;
        }
        if (AppTextUtils.isEmpty(mPassword)){
            getViewState().onPasswordError(getActivity().getString(R.string.error_empty_password));
            return;
        }
        if (AppTextUtils.isEmpty(mConfirmPassword)){
            getViewState().onConfirmPasswordError(getActivity().getString(R.string.error_empty_confirm_password));
            return;
        }
        if (!mPassword.contentEquals(mConfirmPassword)){
            getViewState().onConfirmPasswordError(getActivity().getString(R.string.error_not_equals_confirm_pass));
            return;
        }
        App.getAppDatabase().getUserDao().insert(createUser());
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(Actions.REGISTER_SUCCESS));
        getActivity().finish();
    }

    public void openSignInScreen(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

    private User createUser(){
        User user = new User();
        user.setLogin(mLogin);
        user.setEmail(mEmail);
        user.setOmc(mOmc);
        user.setPassword(mPassword);
        return user;
    }

    private abstract class OnTextChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
