package ru.mbg.nczd.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.views.SpacingItemDecoration;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public abstract class RecyclerFragment extends MvpAppCompatFragment {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecycler();
    }

    public abstract RecyclerView.Adapter getAdapter();

    protected RecyclerView.LayoutManager getRecyclerLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    protected RecyclerView.ItemDecoration getRecyclerItemDecoration(){
        return new SpacingItemDecoration();
    }

    private void setupRecycler(){
        mRecyclerView.setLayoutManager(getRecyclerLayoutManager());
        mRecyclerView.addItemDecoration(getRecyclerItemDecoration());
        mRecyclerView.setAdapter(getAdapter());
    }

}
