package ru.mbg.nczd.feature.profile;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.db.UserManager;
import ru.mbg.nczd.db.models.Reception;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.utils.DateUtils;
import ru.mbg.nczd.utils.Params;
import ru.mbg.nczd.views.SimpleReceptionInfo;

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
            case RECEPTION_VIEW_TYPE: return new ReceptionsHolder(parent);
            case CHILD_VIEW_TYPE: return new ChildsHolder(parent);
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

    class UnAuthorizedHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sign_in_button)
        Button mSignInButton;
        @BindView(R.id.register_button)
        Button mRegisterButton;

        UnAuthorizedHolder(final ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.layout_sign_in, parentView, false));
            ButterKnife.bind(this, itemView);
            mSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Params.LOGIN_ACTION);
                    LocalBroadcastManager.getInstance(parentView.getContext()).sendBroadcast(intent);
                }
            });
            mRegisterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Params.REGISTER_ACTION);
                    LocalBroadcastManager.getInstance(parentView.getContext()).sendBroadcast(intent);
                }
            });
        }
    }

    class ReceptionsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.reception_button)
        Button mReceptionButton;
        @BindView(R.id.add_reception_button)
        ImageButton mAddReceptionButton;
        @BindView(R.id.container)
        LinearLayout mContainer;

        User mUser;

        public ReceptionsHolder(final ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.layout_receptions, parentView, false));
            ButterKnife.bind(this, itemView);
            mReceptionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocalBroadcastManager.getInstance(parentView.getContext()).sendBroadcast(new Intent(Params.RECEPTION_ACTION));
                }
            });
            mAddReceptionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocalBroadcastManager.getInstance(parentView.getContext()).sendBroadcast(new Intent(Params.RECEPTION_ACTION));
                }
            });
            mUser = UserManager.instance().getUser();
            if (mUser.isUserHaveReception()){
                mReceptionButton.setVisibility(View.GONE);
                mAddReceptionButton.setVisibility(View.VISIBLE);
                addReceptionInfo(mContainer);
            }
        }

        void addReceptionInfo(LinearLayout container){
            container.removeAllViews();
            List<Reception> r = App.getAppDatabase().getReceptionDao().getAll();
            for (Reception reception : mUser.getReceptions()){
                SimpleReceptionInfo info = new SimpleReceptionInfo(container.getContext());
                info.setDateText(DateUtils.getSimpleReceptionInfoDate(container.getContext(), reception.getDate()));
                info.setTimeText(reception.getTime());
                info.setNameText(Params.RECEPTION_TYPE.getById(reception.getTypeId()).getName(container.getContext()));
                container.addView(info);
            }
        }
    }

    class ChildsHolder extends RecyclerView.ViewHolder {

        public ChildsHolder(ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.layout_childs, parentView, false));
        }
    }


}
