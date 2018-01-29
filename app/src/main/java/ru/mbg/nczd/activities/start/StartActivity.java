package ru.mbg.nczd.activities.start;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.activities.start.mvp.StartActivityPresenter;
import ru.mbg.nczd.activities.start.mvp.StartView;
import ru.mbg.nczd.db.UserManager;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.utils.Params;
import ru.mbg.nczd.views.MainBottomNavBar;
import ru.mbg.nczd.views.ProfileDrawerView;

public class StartActivity extends BaseActivity implements StartView, MainBottomNavBar.OnNavigationButtonClickListener {

    @BindView(R.id.drawer)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @BindView(R.id.bottom_navigation_bar)
    protected MainBottomNavBar mBottomNavBar;
    @BindView(R.id.profile_drawer_view)
    protected ProfileDrawerView mProfileDrawerView;

    @InjectPresenter(type = PresenterType.LOCAL)
    StartActivityPresenter mStartActivityPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    StartActivityPresenter provideStartActivityPresenter(){
        return new StartActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mStartActivityPresenter.setupToolbar(mToolbar);
        mBottomNavBar.setNavigationButtonClickListener(this);
        mBottomNavBar.setNewsSelected();
        showFragment(mStartActivityPresenter.getNewsFragment(), mStartActivityPresenter.getNewsFragment().TAG);
        setToolbarTitle(R.string.news);
        mStartActivityPresenter.registerReceivers(this);
    }

    @Override
    protected void prepareToolbar(){
        mStartActivityPresenter.setupToolbar(mToolbar);
    }

    @Override
    public void onResume(){
        super.onResume();
        //mStartActivityPresenter.registerReceivers(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        //mStartActivityPresenter.unregisterReceivers(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
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
    public void onRegister(long userId) {
        mProfileDrawerView.initUserContent(userId);
    }

    @Override
    public void onReceptionAction() {
        mDrawerLayout.closeDrawer(Gravity.START);
        mBottomNavBar.setReceptionSelected();
        showFragment(mStartActivityPresenter.getReceptionFragment(), mStartActivityPresenter.getReceptionFragment().TAG);
        setToolbarTitle(R.string.reception_reception);
    }

    @Override
    public void onReceptionAdd() {
        mProfileDrawerView.initUserContent(UserManager.instance().getUserId());
    }

    @Override
    public void onNewsClick() {
        showFragment(mStartActivityPresenter.getNewsFragment(), mStartActivityPresenter.getNewsFragment().TAG);
        setToolbarTitle(R.string.news);
    }

    @Override
    public void onAboutClick() {
        showFragment(mStartActivityPresenter.getAboutFragment(), mStartActivityPresenter.getAboutFragment().TAG);
        setToolbarTitle(R.string.about);
    }

    @Override
    public void onReceptionClick() {
        showFragment(mStartActivityPresenter.getReceptionFragment(), mStartActivityPresenter.getReceptionFragment().TAG);
        setToolbarTitle(R.string.reception_reception);
    }

    @Override
    public void onContactClick() {
        showFragment(mStartActivityPresenter.getContactFragment(), mStartActivityPresenter.getContactFragment().TAG);
        setToolbarTitle(R.string.contacts_contacts);
    }

    @Override
    public void onAdviceClick() {
        showFragment(mStartActivityPresenter.getAdviceFragment(), mStartActivityPresenter.getAdviceFragment().TAG);
        setToolbarTitle(R.string.advices);
    }

    private void showFragment(Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, tag).commit();
    }

    private void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == Params.LOGIN_REQUEST_CODE){
            long id = data.getLongExtra(LoginActivity.USER_ID_ARG, -1);
            mProfileDrawerView.initUserContent(id);
        }
    }

}
