package ru.mbg.nczd.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.feature.profile.ProfileContentView;
import ru.mbg.nczd.feature.profile.ProfileHeaderView;
import ru.mbg.nczd.utils.AppTextUtils;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public class ProfileDrawerView extends LinearLayout {

    @BindView(R.id.profile_content_view)
    protected ProfileContentView mProfileContentView;
    @BindView(R.id.profile_header_view)
    protected ProfileHeaderView mProfileHeaderView;

    private View mRootView;

    public ProfileDrawerView(Context context) {
        super(context);
        init();
    }

    public ProfileDrawerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileDrawerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_profile_drawer, this, true);
        ButterKnife.bind(this, mRootView);
    }

    public void initUserContent(long userId){

        User user = App.getAppDatabase().getUserDao().get(userId);
        if (user != null){
            if (isUserInitialsEmpty(user)){
                mProfileHeaderView.setNameText(getContext().getString(R.string.profile_good_day));
            } else {
                mProfileHeaderView.setNameText(getContext().getString(R.string.profile_initials, user.getFirstName(), user.getSecondName()));
            }
            if (AppTextUtils.isEmpty(user.getOmc())){
                mProfileHeaderView.setOmcText(getContext().getString(R.string.profile_empty_omc));
            } else {
                mProfileHeaderView.setOmcText(getContext().getString(R.string.omc, user.getOmc()));
            }
        }

        mProfileContentView.initUserContent();
    }

    private boolean isUserInitialsEmpty(User user){
        return AppTextUtils.isEmpty(user.getFirstName()) && AppTextUtils.isEmpty(user.getPatronymic());
    }

    public void initSignInContent(){
        mProfileContentView.initSignInContent();
    }

}
