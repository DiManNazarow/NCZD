package ru.mbg.nczd.activities.auth.mvp;

import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 17.01.2018.
 */

public interface LoginView extends MvpView {

    void onLogin();

    void onLoginError(@Nullable String error);

    void onPasswordError(@Nullable String error);

}
