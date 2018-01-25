package ru.mbg.nczd.activities.auth.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.recovery.RecoveryActivity;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.AppTextUtils;

/**
 * Created by Дмитрий on 17.01.2018.
 */
@InjectViewState
public class LoginActivityPresenter extends BaseMvpPresenter<LoginView> {

    private String mLogin;

    private String mPassword;

    public LoginActivityPresenter(Activity activity){
        super(activity);
    }

    public void setupViews(TextInputEditText loginEditText, TextInputEditText passwordEditText, Button signInButton){
        loginEditText.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLogin = s.toString();
                getViewState().onLoginError(null);
            }
        });
        passwordEditText.addTextChangedListener(new OnTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPassword = s.toString();
                getViewState().onPasswordError(null);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppTextUtils.isEmpty(mLogin)){
                    getViewState().onLoginError(v.getContext().getString(R.string.error_empty_login));
                    return;
                }
                if (AppTextUtils.isEmpty(mPassword)){
                    getViewState().onPasswordError(v.getContext().getString(R.string.error_empty_password));
                    return;
                }
                if (!AppTextUtils.isEmpty(mLogin) && !AppTextUtils.isEmpty(mPassword)){
                    login();
                }
            }
        });
    }

    public void openRecoverActivity(){
        Intent intent = new Intent(getActivity(), RecoveryActivity.class);
        getActivity().startActivity(intent);
    }

    private void login(){
        User user = App.getAppDatabase().getUserDao().get(mLogin, mPassword);
        if (user != null){
            getViewState().onLogin(user.getId());
        }
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
