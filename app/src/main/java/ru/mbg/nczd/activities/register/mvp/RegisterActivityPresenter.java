package ru.mbg.nczd.activities.register.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;

import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.activities.personalinfo.PersonalInfoActivity;
import ru.mbg.nczd.db.UserManager;
import ru.mbg.nczd.db.models.UserEntity;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.Params;
import ru.mbg.nczd.utils.AppTextUtils;
import ru.mbg.nczd.views.SuccessRegisterAlert;

/**
 * Created by Дмитрий on 18.01.2018.
 */
@InjectViewState
public class RegisterActivityPresenter extends BaseMvpPresenter<RegisterView> {

    private String mEmail;

    private String mLogin;

    private String mPassword;

    private String mConfirmPassword;

    private long mUserId = Long.MIN_VALUE;

    public RegisterActivityPresenter(Activity activity){
        super(activity);
    }

    public void setupViews(AppCompatEditText email, AppCompatEditText login, AppCompatEditText password, AppCompatEditText confirmPassword){
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
        } else if (!mEmail.contains("@")){
            getViewState().onEmailError(getActivity().getString(R.string.error_email_wrong));
            return;
        } else if (App.getAppDatabase().getUserDao().emailExist(mEmail) > 0){
            getViewState().onEmailError(getActivity().getString(R.string.error_email_exist));
            return;
        }
        if (AppTextUtils.isEmpty(mLogin)){
            getViewState().onLoginError(getActivity().getString(R.string.error_empty_login));
            return;
        } else if (App.getAppDatabase().getUserDao().userExits(mLogin) > 0){
            getViewState().onLoginError(getActivity().getString(R.string.error_login_exist));
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
        mUserId = App.getAppDatabase().getUserDao().insert(createUser());
        SuccessRegisterAlert.show(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserId != Long.MIN_VALUE){
                    UserManager.instance().setUserId(mUserId);
                    Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
                    intent.putExtra(Params.USER_ID_ARG, mUserId);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                }
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Params.REGISTER_SUCCESS);
                intent.putExtra(Params.USER_ID_ARG, mUserId);
                UserManager.instance().setUserId(mUserId);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                getActivity().finish();
            }
        });
    }

    public void openSignInScreen(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

    private UserEntity createUser(){
        UserEntity user = new UserEntity();
        user.setLogin(mLogin);
        user.setEmail(mEmail);
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
