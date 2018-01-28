package ru.mbg.nczd.activities.personalinfo.mvp;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 28.01.2018.
 */

public interface PersonalInfoView extends MvpView {

    void onFirstNameError(String error);

    void onSecondNameError(String error);

    void onPatronymicError(String error);

    void onDateBirthError(String error);

    void onOMCError(String error);

    void onNumberError(String error);

}
