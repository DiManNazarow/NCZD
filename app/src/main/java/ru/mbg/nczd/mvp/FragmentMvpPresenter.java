package ru.mbg.nczd.mvp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class FragmentMvpPresenter<T extends MvpView> extends BaseMvpPresenter<T> {

    private Fragment mFragment;

    public FragmentMvpPresenter(@NonNull Fragment fragment) {
        super(fragment.getActivity());
        mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }
}
