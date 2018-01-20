package ru.mbg.nczd.fragments;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import ru.mbg.nczd.feature.news.NewsListRecyclerAdapter;
import ru.mbg.nczd.feature.news.models.News;
import ru.mbg.nczd.fragments.mvp.NewsFragmentPresenter;
import ru.mbg.nczd.fragments.mvp.NewsView;

public class NewsFragment extends RecyclerFragment implements NewsView {

    public final String TAG = "NewFragment";

    private NewsListRecyclerAdapter mRecyclerAdapter;

    @InjectPresenter(type = PresenterType.LOCAL)
    NewsFragmentPresenter mNewsFragmentPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    NewsFragmentPresenter provideNewFragmentPresenter(){
        return new NewsFragmentPresenter(this);
    }

    public NewsFragment() {

    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mNewsFragmentPresenter.getNewsList();
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        mRecyclerAdapter = new NewsListRecyclerAdapter();
        return mRecyclerAdapter;
    }

    @Override
    public void onNewsListGot(List<News> news) {
        mRecyclerAdapter.setData(news);
        mRecyclerAdapter.notifyDataSetChanged();
    }
}
