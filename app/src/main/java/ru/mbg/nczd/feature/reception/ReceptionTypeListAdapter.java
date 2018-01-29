package ru.mbg.nczd.feature.reception;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.profile.ProfileContentView;
import ru.mbg.nczd.feature.recyclerviews.holders.SimpleViewHolder;
import ru.mbg.nczd.utils.Params;

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

    public interface OnReceptionTypeClickListener {
        void onReceptionTypeClick(Params.RECEPTION_TYPE type);
    }

    private OnReceptionTypeClickListener mReceptionTypeClickListener;

    public ReceptionTypeListAdapter(@NonNull OnReceptionTypeClickListener listener){
        mReceptionTypeClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case CONSULTATION_VIEW_TYPE: return new ConsultationHolder(parent);
            case EMERGENCY_HOSPITALISATION_VIEW_TYPE: return new EmergencyHospitalizationHolder(parent);
            case EMERGENCY_SURGERY_VIEW_TYPE: return new EmergencySurgeryHolder(parent);
            case URGENT_PEDIATRIC_CARE_VIEW_TYPE: return new UrgentPediatricCareHolder(parent);
            case RECEPTION_VIEW_TYPE: return new ReceptionHolder(parent, mReceptionTypeClickListener);
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

    public class ReceptionHolder extends SimpleViewHolder {

        @BindView(R.id.cosmetologist_text_view)
        TextView mCosmetologistView;
        @BindView(R.id.neurologist_text_view)
        TextView mNeurologistView;
        @BindView(R.id.psychologist_text_view)
        TextView mPsychologistView;
        @BindView(R.id.mammologist_text_view)
        TextView mMammologistView;
        @BindView(R.id.ct_scan_text_view)
        TextView mCtScanView;
        @BindView(R.id.mri_text_view)
        TextView mMriTextView;
        @BindView(R.id.x_ray_text_view)
        TextView mXrayTextView;

        ReceptionHolder(ViewGroup rootView, @NonNull final OnReceptionTypeClickListener listener) {
            super(rootView, R.layout.layout_reception_holder);
            mCosmetologistView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReceptionTypeClick(Params.RECEPTION_TYPE.COSMETOLOGIST_RECEPTION);
                }
            });
            mNeurologistView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReceptionTypeClick(Params.RECEPTION_TYPE.NEUROLOGIST_RECEPTION);
                }
            });
            mPsychologistView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listener.onReceptionTypeClick(Params.RECEPTION_TYPE.PSYCHOLOGIST_RECEPTION);
                }
            });
            mMammologistView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReceptionTypeClick(Params.RECEPTION_TYPE.MAMMOLOGIST_RECEPTION);
                }
            });
            mCtScanView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReceptionTypeClick(Params.RECEPTION_TYPE.CT_SCAN_RECEPTION);
                }
            });
            mMriTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReceptionTypeClick(Params.RECEPTION_TYPE.MRI_RECEPTION);
                }
            });
            mXrayTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onReceptionTypeClick(Params.RECEPTION_TYPE.X_RAY_RECEPTION);
                }
            });
        }

    }
}
