package ru.mbg.nczd.fragments;


import android.support.v7.widget.RecyclerView;

import ru.mbg.nczd.feature.contacts.HowFindListAdapter;
import ru.mbg.nczd.feature.recyclerviews.RecyclerFragment;

public class HowFindFragment extends RecyclerFragment {

    public static HowFindFragment newInstance(){
        return new HowFindFragment();
    }

    public HowFindFragment() {
    }


    @Override
    public RecyclerView.Adapter getAdapter() {
        return new HowFindListAdapter();
    }

}
