package ru.mbg.nczd.feature.profile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public class ProfileContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int UNAUTHORIZED_MODE = 0;
    public static final int AUTHORIZED_MODE = 1;

    public final int AUTH_VIEW_TYPE = 0;
    public final int RECEPTION_VIEW_TYPE = 1;
    public final int CHILD_VIEW_TYPE = 2;

    private int mMode;

    public ProfileContentAdapter(int mode) {
        mMode = mode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case AUTH_VIEW_TYPE: return new UnAuthorizedHolder(parent);
            default: throw new IllegalArgumentException("Unexpected view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mMode == UNAUTHORIZED_MODE){
            return 1;
        }
        if (mMode == AUTHORIZED_MODE){
            return 2;
        }
        throw new IllegalArgumentException("Unexpected mode type");
    }

    public int getItemViewType(int position) {
        if (mMode == UNAUTHORIZED_MODE){
            return AUTH_VIEW_TYPE;
        }
        if (mMode == AUTHORIZED_MODE){
            switch (position){
                case 0: return RECEPTION_VIEW_TYPE;
                case 1: return CHILD_VIEW_TYPE;
                default: throw new IllegalArgumentException("Unexpected position arg");
            }
        }
        throw new IllegalArgumentException("Unexpected mode type");
    }

    protected class UnAuthorizedHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sign_in_button)
        Button mSignInButton;
        @BindView(R.id.register_button)
        Button mRegisterButton;

        UnAuthorizedHolder(ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.layout_sign_in, parentView, false));
            ButterKnife.bind(this, itemView);
            mSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mRegisterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }


}
