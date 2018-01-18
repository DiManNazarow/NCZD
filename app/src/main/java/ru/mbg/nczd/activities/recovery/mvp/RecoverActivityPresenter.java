package ru.mbg.nczd.activities.recovery.mvp;

import android.app.Activity;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.mvp.BaseMvpPresenter;

/**
 * Created by Дмитрий on 18.01.2018.
 */
@InjectViewState
public class RecoverActivityPresenter extends BaseMvpPresenter<RecoverView> {

    public RecoverActivityPresenter(Activity activity) {
        super(activity);
    }

    public void recover(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

}
