package ru.mbg.nczd.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 14.01.2018.
 */

public class ProfileDrawerView extends LinearLayout {

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
    }

}
