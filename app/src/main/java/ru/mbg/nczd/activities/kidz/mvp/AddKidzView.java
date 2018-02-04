package ru.mbg.nczd.activities.kidz.mvp;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 04.02.2018.
 */

public interface AddKidzView extends MvpView {

    void onFirstNameError(String text);

    void onSecondNameError(String text);

    void onPatronymicError(String text);

    void onDateBirthError(String text);

    void onOMCError(String text);

    void onSave(long childId);

}
