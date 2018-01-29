package ru.mbg.nczd.activities.start.mvp;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public interface StartView extends MvpView {

    void openDrawer();

    void onLogin();

    void onRegister(long userId);

    void onReceptionAction();

    void onReceptionAdd();

}
