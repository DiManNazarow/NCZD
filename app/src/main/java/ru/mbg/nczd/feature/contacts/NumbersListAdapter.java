package ru.mbg.nczd.feature.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 21.01.2018.
 */

public class NumbersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HOLDERS_COUNT = 8;

    private final int NAME_VIEW_TYPE = 1;
    private final int SCHEDULE_DIAGNOSTIC_TYPE = 2;
    private final int HEAD_DOCTOR_VIEW_TYPE = 3;
    private final int HEAD_DOCTORLDC_VIEW_TYPE = 4;
    private final int SECOND_HEAD_DOCTOR_VIEW_TYPE = 5;
    private final int SECOND_HEAD_SCIENCE_VIEW_TYPE = 6;
    private final int SECOND_HEAD_MINZDRAV_VIEW_TYPE = 7;
    private final int INFO_VIEW_TYPE = 8;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case NAME_VIEW_TYPE: return new NameViewHolder(parent);
            case SCHEDULE_DIAGNOSTIC_TYPE: return new ScheduleDiagnosticViewHolder(parent);
            case HEAD_DOCTOR_VIEW_TYPE: return new HeadDoctorViewHolder(parent);
            case HEAD_DOCTORLDC_VIEW_TYPE: return new HeadDoctorKDCViewHolder(parent);
            case SECOND_HEAD_DOCTOR_VIEW_TYPE: return new SecondHeadDoctorViewHolder(parent);
            case SECOND_HEAD_SCIENCE_VIEW_TYPE: return new SecondHeadScienceViewHolder(parent);
            case SECOND_HEAD_MINZDRAV_VIEW_TYPE: return new SecondHeadMinzdrvViewHolder(parent);
            case INFO_VIEW_TYPE: return new InfoViewHolder(parent);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public int getItemViewType(int position) {
        switch (position){
            case 0: return NAME_VIEW_TYPE;
            case 1: return SCHEDULE_DIAGNOSTIC_TYPE;
            case 2: return HEAD_DOCTOR_VIEW_TYPE;
            case 3: return HEAD_DOCTORLDC_VIEW_TYPE;
            case 4: return SECOND_HEAD_DOCTOR_VIEW_TYPE;
            case 5: return SECOND_HEAD_SCIENCE_VIEW_TYPE;
            case 6: return SECOND_HEAD_MINZDRAV_VIEW_TYPE;
            case 7: return INFO_VIEW_TYPE;
            default: return -1;
        }
    }

    @Override
    public int getItemCount() {
        return HOLDERS_COUNT;
    }

    private class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_name_holder, rootView, false));
        }
    }

    private class ScheduleDiagnosticViewHolder extends RecyclerView.ViewHolder {

        public ScheduleDiagnosticViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_shedule_holder, rootView, false));
        }
    }

    private class HeadDoctorViewHolder extends RecyclerView.ViewHolder {

        public HeadDoctorViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_headdoctor_holder, rootView, false));
        }
    }

    private class HeadDoctorKDCViewHolder extends RecyclerView.ViewHolder {

        public HeadDoctorKDCViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_headdoctorkdc_holder, rootView, false));
        }
    }

    private class SecondHeadDoctorViewHolder extends RecyclerView.ViewHolder {

        public SecondHeadDoctorViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_secondheaddoctor_holder, rootView, false));
        }
    }

    private class SecondHeadScienceViewHolder extends RecyclerView.ViewHolder {

        public SecondHeadScienceViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_second_doctor_science_holder, rootView, false));
        }
    }

    private class SecondHeadMinzdrvViewHolder extends RecyclerView.ViewHolder {

        public SecondHeadMinzdrvViewHolder(ViewGroup rootView) {
            super(LayoutInflater.from(rootView.getContext()).inflate(R.layout.layout_head_minzdrav_holder, rootView, false));
        }
    }

    private class InfoViewHolder extends RecyclerView.ViewHolder {

        public InfoViewHolder(ViewGroup rootVeiw) {
            super(LayoutInflater.from(rootVeiw.getContext()).inflate(R.layout.layout_contact_info_holder, rootVeiw, false));
        }
    }

}
