package ru.mbg.nczd.fragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import ru.mbg.nczd.activities.reception.ReceptionActivity;
import ru.mbg.nczd.feature.reception.ReceptionTypeListAdapter;
import ru.mbg.nczd.feature.recyclerviews.RecyclerFragment;
import ru.mbg.nczd.utils.Params;

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
        return new ReceptionTypeListAdapter(new ReceptionTypeListAdapter.OnReceptionTypeClickListener() {
            @Override
            public void onReceptionTypeClick(Params.RECEPTION_TYPE type) {
                openReceptionActivity(type);
            }
        }, getActivity());
    }

    public void openReceptionActivity(Params.RECEPTION_TYPE receptionType){
        Intent intent = new Intent(getActivity(), ReceptionActivity.class);
        intent.putExtra(Params.RECEPTION_TYPE_ARG, receptionType.getReceptionTypeId());
        getActivity().startActivity(intent);
    }

}
