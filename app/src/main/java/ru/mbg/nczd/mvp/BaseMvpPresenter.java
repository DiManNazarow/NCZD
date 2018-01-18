package ru.mbg.nczd.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 18.01.2018.
 */

public abstract class BaseMvpPresenter<T extends MvpView> extends MvpPresenter<T> {

    private Activity mActivity;

    public BaseMvpPresenter(@NonNull Activity activity){
        mActivity = activity;
    }

    @NonNull
    protected Activity getActivity() {
        return mActivity;
    }
}
