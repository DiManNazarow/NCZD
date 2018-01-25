package ru.mbg.nczd.fragments;


import android.support.v7.widget.RecyclerView;

import ru.mbg.nczd.feature.contacts.NumbersListAdapter;
import ru.mbg.nczd.feature.recyclerviews.RecyclerFragment;


public class NumbersFragment extends RecyclerFragment {

    public static NumbersFragment newInstance(){
        return new NumbersFragment();
    }

    public NumbersFragment() {
    }


    @Override
    public RecyclerView.Adapter getAdapter() {
        return new NumbersListAdapter();
    }
}
