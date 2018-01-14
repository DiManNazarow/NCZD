package ru.mbg.nczd.feature.profile;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public class ProfileHeaderView extends ConstraintLayout {

    @BindView(R.id.avatar_image_view)
    protected CircleImageView mAvatarView;
    @BindView(R.id.name_text_view)
    protected TextView mNameTextView;
    @BindView(R.id.omc_text_view)
    protected TextView mOmcTextView;

    private View mRootView;

    public ProfileHeaderView(Context context) {
        super(context);
        init();
    }

    public ProfileHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_profile_header, this, true);
        ButterKnife.bind(this, mRootView);
    }

    public void setNameText(String text){
        mNameTextView.setText(text);
    }

    public void setOmcText(String text){
        mOmcTextView.setText(text);
    }

}
