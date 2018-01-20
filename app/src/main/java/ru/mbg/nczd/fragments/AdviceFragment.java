package ru.mbg.nczd.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.advice.AdviceListRecyclerAdapter;
import ru.mbg.nczd.feature.advice.models.Advice;
import ru.mbg.nczd.fragments.mvp.AdviceFragmentPresenter;
import ru.mbg.nczd.fragments.mvp.AdviceView;

public class AdviceFragment extends RecyclerFragment implements AdviceView {

    public final String TAG = "AdviceFragment";

    private AdviceListRecyclerAdapter mRecyclerAdapter;

    @InjectPresenter(type = PresenterType.LOCAL)
    AdviceFragmentPresenter mAdviceFragmentPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    AdviceFragmentPresenter provideAdviceFragmentPresenter(){
        return new AdviceFragmentPresenter(this);
    }

    public AdviceFragment() {

    }

    public static AdviceFragment newInstance() {
        AdviceFragment fragment = new AdviceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mAdviceFragmentPresenter.getAdviceList();
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        mRecyclerAdapter = new AdviceListRecyclerAdapter();
        return mRecyclerAdapter;
    }

    @Override
    public void onAdvicesGot(List<Advice> advices) {
        mRecyclerAdapter.setData(advices);
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
