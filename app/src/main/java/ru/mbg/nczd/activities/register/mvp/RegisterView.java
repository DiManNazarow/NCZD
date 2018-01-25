package ru.mbg.nczd.activities.register.mvp;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 18.01.2018.
 */

public interface RegisterView extends MvpView {

    void onEmailError(String text);

    void onLoginError(String text);

    void onOmcError(String text);

    void onPasswordError(String text);

    void onConfirmPasswordError(String text);

}
