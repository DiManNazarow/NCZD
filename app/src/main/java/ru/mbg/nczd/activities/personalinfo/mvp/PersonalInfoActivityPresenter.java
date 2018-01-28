package ru.mbg.nczd.activities.personalinfo.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.LocalBroadcastManager;

import com.arellomobile.mvp.InjectViewState;

import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.Params;
import ru.mbg.nczd.utils.AppTextUtils;

/**
 * Created by Дмитрий on 28.01.2018.
 */

@InjectViewState
public class PersonalInfoActivityPresenter extends BaseMvpPresenter<PersonalInfoView> {

    private String mFirstName;

    private String mSecondName;

    private String mPatronymic;

    private String mDateBirth;

    private String mOmc;

    private String mNumber;

    public PersonalInfoActivityPresenter(@NonNull Activity activity) {
        super(activity);
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public void setSecondName(String secondName) {
        mSecondName = secondName;
    }

    public void setPatronymic(String patronymic) {
        mPatronymic = patronymic;
    }

    public void setDateBirth(String dateBirth) {
        mDateBirth = dateBirth;
    }

    public void setOmc(String omc) {
        mOmc = omc;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public void save(long userId){
        if (AppTextUtils.isEmpty(mFirstName)){
            getViewState().onFirstNameError(getString(R.string.error_empty_first_name));
            return;
        }
        if (AppTextUtils.isEmpty(mSecondName)){
            getViewState().onSecondNameError(getString(R.string.error_empty_second_name));
            return;
        }
        if (AppTextUtils.isEmpty(mPatronymic)){
            getViewState().onPatronymicError(getString(R.string.error_empty_patronymic));
            return;
        }
        if (AppTextUtils.isEmpty(mDateBirth)){
            getViewState().onDateBirthError(getString(R.string.error_empty_date_birth));
            return;
        }
        if (AppTextUtils.isEmpty(mOmc)){
            getViewState().onOMCError(getString(R.string.error_empty_omc));
            return;
        }
        if (AppTextUtils.isEmpty(mNumber)){
            getViewState().onNumberError(getString(R.string.error_empty_number));
            return;
        }
        if (userId != Long.MIN_VALUE) {
            User user = App.getAppDatabase().getUserDao().get(userId);
            user.setFirstName(mFirstName);
            user.setSecondName(mSecondName);
            user.setPatronymic(mPatronymic);
            user.setDateBirth(mDateBirth);
            user.setOmc(mOmc);
            user.setNumber(mNumber);
            App.getAppDatabase().getUserDao().insert(user);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(Params.PERSONAL_INFO_SUCCESS_UPDATE));
            getActivity().finish();
        }
    }

    private String getString(@StringRes int stringRes){
        return getActivity().getString(stringRes);
    }

}
