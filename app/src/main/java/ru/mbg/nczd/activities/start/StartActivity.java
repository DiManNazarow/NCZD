package ru.mbg.nczd.activities.start;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.start.mvp.StartActivityPresenter;
import ru.mbg.nczd.activities.start.mvp.StartView;
import ru.mbg.nczd.utils.Actions;
import ru.mbg.nczd.views.MainBottomNavBar;

public class StartActivity extends MvpAppCompatActivity implements StartView, MainBottomNavBar.OnNavigationButtonClickListener {

    @BindView(R.id.drawer)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.bottom_navigation_bar)
    protected MainBottomNavBar mBottomNavBar;

    @InjectPresenter(type = PresenterType.LOCAL)
    StartActivityPresenter mStartActivityPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    StartActivityPresenter provideStartActivityPresenter(){
        return new StartActivityPresenter(this);
    }

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
    public void onResume(){
        super.onResume();
        mStartActivityPresenter.registerReceivers(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        mStartActivityPresenter.unregisterReceivers(this);
    }

    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.START, true);
    }

    @Override
    public void onLogin() {

    }

    @Override
    public void onRegister() {
        Toast.makeText(this, "OnRegister", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == Actions.LOGIN_REQUEST_CODE){
            Toast.makeText(this, "OnLogin", Toast.LENGTH_SHORT).show();
        }
    }

}
