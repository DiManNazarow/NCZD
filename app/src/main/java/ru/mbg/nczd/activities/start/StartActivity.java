package ru.mbg.nczd.activities.start;

import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.views.MainBottomNavBar;

public class StartActivity extends MvpAppCompatActivity implements StartView, MainBottomNavBar.OnNavigationButtonClickListener {

    @BindView(R.id.drawer)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.bottom_navigation_bar)
    protected MainBottomNavBar mBottomNavBar;

    @InjectPresenter
    StartActivityPresenter mStartActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        mStartActivityPresenter.setupToolbar(mToolbar);
        mBottomNavBar.setNavigationButtonClickListener(this);
        mBottomNavBar.setNewsSelected();
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

    @Override
    public void onNewsClick() {

    }

    @Override
    public void onAboutClick() {

    }

    @Override
    public void onReceptionClick() {

    }

    @Override
    public void onContactClick() {

    }

    @Override
    public void onAdviceClick() {

    }
}
