package ru.mbg.nczd.activities.kidz.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.db.UserManager;
import ru.mbg.nczd.db.models.Child;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.AppTextUtils;
import ru.mbg.nczd.utils.Params;

/**
 * Created by Дмитрий on 04.02.2018.
 */

@InjectViewState
public class AddKidzActivityPresenter extends BaseMvpPresenter<AddKidzView> {

    private String mFirstName;

    private String mSecondName;

    private String mPatronymic;

    private String mDateBirth;

    private String mOMC;

    public AddKidzActivityPresenter(@NonNull Activity activity) {
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

    public void setOMC(String OMC) {
        mOMC = OMC;
    }

    public void save(long childId){
        if (hasError()) return;
        User user = UserManager.instance().getUser();
        Child child = App.getAppDatabase().getChildrenDao().get(childId);
        child.setFirstName(mFirstName);
        child.setSecondName(mSecondName);
        child.setPatronymic(mPatronymic);
        child.setDateBirth(mDateBirth);
        child.setOmc(mOMC);
        child.setUserId(user.getId());
        long childIdNew = App.getAppDatabase().getChildrenDao().insert(child);
        child.setChildId(childIdNew);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(Params.UPDATE_PROFILE_ACTION));
        getViewState().onSave(childId);
    }

    public void save(){
        if (hasError()) return;
        User user = UserManager.instance().getUser();
        Child child = new Child();
        child.setFirstName(mFirstName);
        child.setSecondName(mSecondName);
        child.setPatronymic(mPatronymic);
        child.setDateBirth(mDateBirth);
        child.setOmc(mOMC);
        child.setUserId(user.getId());
        long childId = App.getAppDatabase().getChildrenDao().insert(child);
        child.setChildId(childId);
        user.getChildren().add(child);
        getViewState().onSave(childId);
    }

    private boolean hasError(){
        if (AppTextUtils.isEmpty(mFirstName)){
            getViewState().onFirstNameError(getActivity().getString(R.string.error_empty_first_name));
            return true;
        }
        if (AppTextUtils.isEmpty(mSecondName)){
            getViewState().onSecondNameError(getActivity().getString(R.string.error_empty_second_name));
            return true;
        }
        if (AppTextUtils.isEmpty(mPatronymic)){
            getViewState().onPatronymicError(getActivity().getString(R.string.error_empty_patronymic));
            return true;
        }
        if (AppTextUtils.isEmpty(mDateBirth)){
            getViewState().onDateBirthError(getActivity().getString(R.string.error_empty_date_birth));
            return true;
        }
        if (AppTextUtils.isEmpty(mOMC)){
            getViewState().onOMCError(getActivity().getString(R.string.error_empty_omc));
            return true;
        } else if (mOMC.trim().length() != 16){
            getViewState().onOMCError(getActivity().getString(R.string.error_omc_wrong));
            return true;
        }
        return false;
    }

}

