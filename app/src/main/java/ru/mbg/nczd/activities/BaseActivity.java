package ru.mbg.nczd.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 16.01.2018.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
//    @BindView(R.id.content_container)
//    protected FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //prepareContentLayout();
        prepareToolbar();
    }

    protected void prepareToolbar(){
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(getToolbarNavButtonClickListener());
        mToolbar.setTitle(getToolbarTitle());
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }

    protected View.OnClickListener getToolbarNavButtonClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    protected String getToolbarTitle(){
        return getString(R.string.app_name);
    }

//    protected void prepareContentLayout(){
//        mFrameLayout.addView(LayoutInflater.from(this).inflate(getContentLayoutId(), mFrameLayout, true));
//    }

    protected abstract int getContentLayoutId();

}
