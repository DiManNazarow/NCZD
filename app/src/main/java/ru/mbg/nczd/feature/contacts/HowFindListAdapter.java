package ru.mbg.nczd.feature.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 21.01.2018.
 */

public class HowFindListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HOLDER_COUNT = 3;

    private final int ADDRESS_VIEW_TYPE = 0;
    private final int PUBLIC_TRANSPORT_VIEW_TYPE = 1;
    private final int PERSONAL_TRANSPORT_VIEW_TYPE = 2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ADDRESS_VIEW_TYPE: return new AddressViewHolder(parent);
            case PUBLIC_TRANSPORT_VIEW_TYPE: return new PublicTransportViewHolder(parent);
            case PERSONAL_TRANSPORT_VIEW_TYPE: return new PersonalTransportViewHolder(parent);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return HOLDER_COUNT;
    }

    public int getItemViewType(int position) {
        switch (position){
            case 0: return ADDRESS_VIEW_TYPE;
            case 1: return PUBLIC_TRANSPORT_VIEW_TYPE;
            case 2: return PERSONAL_TRANSPORT_VIEW_TYPE;
            default: return -1;
        }
    }

    private class AddressViewHolder extends RecyclerView.ViewHolder {

        AddressViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_address_holder, rootView, false));
        }
    }

    private class PublicTransportViewHolder extends RecyclerView.ViewHolder {

        PublicTransportViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_public_transport_holder, rootView, false));
        }
    }

    private class PersonalTransportViewHolder extends RecyclerView.ViewHolder {

        PersonalTransportViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_personal_transport_holder, rootView, false));
        }
    }

}
