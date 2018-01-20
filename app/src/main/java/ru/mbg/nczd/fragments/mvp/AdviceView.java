package ru.mbg.nczd.fragments.mvp;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.mbg.nczd.feature.advice.models.Advice;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public interface AdviceView extends MvpView {

    public void onAdvicesGot(List<Advice> advices);

}
