package ru.mbg.nczd.feature.reception;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.profile.ProfileContentView;
import ru.mbg.nczd.feature.recyclerviews.holders.SimpleViewHolder;

/**
 * Created by Дмитрий on 25.01.2018.
 */

public class ReceptionTypeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ITEMS_COUNT = 5;

    private final int CONSULTATION_VIEW_TYPE = 0;
    private final int EMERGENCY_HOSPITALISATION_VIEW_TYPE = 1;
    private final int EMERGENCY_SURGERY_VIEW_TYPE = 2;
    private final int URGENT_PEDIATRIC_CARE_VIEW_TYPE = 3;
    private final int RECEPTION_VIEW_TYPE = 4;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case CONSULTATION_VIEW_TYPE: return new ConsultationHolder(parent);
            case EMERGENCY_HOSPITALISATION_VIEW_TYPE: return new EmergencyHospitalizationHolder(parent);
            case EMERGENCY_SURGERY_VIEW_TYPE: return new EmergencySurgeryHolder(parent);
            case URGENT_PEDIATRIC_CARE_VIEW_TYPE: return new UrgentPediatricCareHolder(parent);
            case RECEPTION_VIEW_TYPE: return new ReceptionHolder(parent);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public int getItemViewType(int position) {
        switch (position){
            case 0: return CONSULTATION_VIEW_TYPE;
            case 1: return EMERGENCY_HOSPITALISATION_VIEW_TYPE;
            case 2: return EMERGENCY_SURGERY_VIEW_TYPE;
            case 3: return URGENT_PEDIATRIC_CARE_VIEW_TYPE;
            case 4: return RECEPTION_VIEW_TYPE;
            default: return -1;
        }
    }

    @Override
    public int getItemCount() {
        return ITEMS_COUNT;
    }

    private class ConsultationHolder extends SimpleViewHolder {

        ConsultationHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_consultation_holder);
        }
    }

    private class EmergencyHospitalizationHolder extends SimpleViewHolder {

        EmergencyHospitalizationHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_emergency_hospitalization_holder);
        }
    }

    private class EmergencySurgeryHolder extends SimpleViewHolder {

        EmergencySurgeryHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_emergency_surgery_holder);
        }
    }

    private class UrgentPediatricCareHolder extends SimpleViewHolder {

        UrgentPediatricCareHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_urgent_pediatric_care_holder);
        }
    }

    private class ReceptionHolder extends SimpleViewHolder {

        ReceptionHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_reception_holder);
        }
    }

}
