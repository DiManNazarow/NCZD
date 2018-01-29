package ru.mbg.nczd.activities.reception.mvp;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Дмитрий on 29.01.2018.
 */

public interface ReceptionView extends MvpView {

    void onAdapterReady(ReceptionActivityPresenter.ReceptionAdapter adapter);

}
