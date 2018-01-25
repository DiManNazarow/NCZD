package ru.mbg.nczd.fragments;

import android.support.v7.widget.RecyclerView;

import ru.mbg.nczd.feature.reception.ReceptionTypeListAdapter;
import ru.mbg.nczd.feature.recyclerviews.RecyclerFragment;

/**
 * Created by Дмитрий on 25.01.2018.
 */

public class ReceptionFragment extends RecyclerFragment {

    public final String TAG = "ReceptionFragment";

    public static ReceptionFragment newInstance(){
        return new ReceptionFragment();
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new ReceptionTypeListAdapter();
    }

}
