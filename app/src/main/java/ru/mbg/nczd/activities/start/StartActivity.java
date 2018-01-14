package ru.mbg.nczd.activities.start;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;

public class StartActivity extends MvpAppCompatActivity implements StartView {

    @BindView(R.id.drawer)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @InjectPresenter
    StartActivityPresenter mStartActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mStartActivityPresenter.setupToolbar(mToolbar);
    }

    @Override
    public void onPostCreate(Bundle savedInstanteState){
        super.onPostCreate(savedInstanteState);
        //mStartActivityPresenter.setupToolbar(mToolbar);
    }

    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.START, true);
    }
}
