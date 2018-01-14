package ru.mbg.nczd.feature.profile;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public class ProfileContentView extends ConstraintLayout {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    public ProfileContentView(Context context) {
        super(context);
        init();
    }

    public ProfileContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_profile_content, this, true);
        ButterKnife.bind(this, this);
        prepareRecyclerView();
        initSignInContent();
    }

    private void prepareRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initSignInContent(){
        mRecyclerView.setAdapter(new ProfileContentAdapter(ProfileContentAdapter.UNAUTHORIZED_MODE));
    }

}
