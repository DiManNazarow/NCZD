package ru.mbg.nczd.activities.start.mvp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;

import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.activities.register.RegisterActivity;
import ru.mbg.nczd.fragments.AboutFragment;
import ru.mbg.nczd.fragments.AdviceFragment;
import ru.mbg.nczd.fragments.ContactFragment;
import ru.mbg.nczd.fragments.NewsFragment;
import ru.mbg.nczd.fragments.ReceptionFragment;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.Params;

/**
 * Created by Дмитрий on 14.01.2018.
 */

@InjectViewState
public class StartActivityPresenter extends BaseMvpPresenter<StartView> {

    private NewsFragment mNewsFragment;
    private AboutFragment mAboutFragment;
    private AdviceFragment mAdviceFragment;
    private ContactFragment mContactFragment;
    private ReceptionFragment mReceptionFragment;

    public StartActivityPresenter(Activity activity){
        super(activity);
        initFragments();
    }

    public void setupToolbar(Toolbar toolbar){
        toolbar.setTitle(R.string.start_activity_title);
        toolbar.setTitleTextColor(ContextCompat.getColor(toolbar.getContext(), R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_account);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewState().openDrawer();
            }
        });
    }

    private void initFragments(){
        mNewsFragment = NewsFragment.newInstance();
        mAboutFragment = AboutFragment.newInstance();
        mAdviceFragment = AdviceFragment.newInstance();
        mContactFragment = ContactFragment.newInstance();
        mReceptionFragment = ReceptionFragment.newInstance();
    }

    @NonNull
    public NewsFragment getNewsFragment() {
        return mNewsFragment;
    }

    @NonNull
    public AboutFragment getAboutFragment() {
        return mAboutFragment;
    }

    @NonNull
    public AdviceFragment getAdviceFragment() {
        return mAdviceFragment;
    }

    @NonNull
    public ContactFragment getContactFragment() {
        return mContactFragment;
    }

    @NonNull
    public ReceptionFragment getReceptionFragment() {
        return mReceptionFragment;
    }

    public void registerReceivers(Context context){
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.registerReceiver(mLoginSuccessReceiver, new IntentFilter(Params.LOGIN_SUCCESS));
        manager.registerReceiver(mRegisterSuccessReceiver, new IntentFilter(Params.REGISTER_SUCCESS));
        manager.registerReceiver(mLoginActionReceiver, new IntentFilter(Params.LOGIN_ACTION));
        manager.registerReceiver(mRegisterActionReceiver, new IntentFilter(Params.REGISTER_ACTION));
        manager.registerReceiver(mPersonalInfoSuccessUpdateReceiver, new IntentFilter(Params.PERSONAL_INFO_SUCCESS_UPDATE));
    }

    public void unregisterReceivers(Context context){
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.unregisterReceiver(mLoginSuccessReceiver);
        manager.unregisterReceiver(mRegisterSuccessReceiver);
        manager.unregisterReceiver(mLoginActionReceiver);
        manager.unregisterReceiver(mRegisterActionReceiver);
        manager.unregisterReceiver(mPersonalInfoSuccessUpdateReceiver);
    }

    private BroadcastReceiver mLoginSuccessReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getViewState().onLogin();
        }
    };

    private BroadcastReceiver mRegisterSuccessReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long userId = intent.getLongExtra(Params.USER_ID_ARG, Long.MIN_VALUE);
            if (userId != Long.MIN_VALUE) {
                getViewState().onRegister(userId);
            }
        }
    };

    private BroadcastReceiver mPersonalInfoSuccessUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long userId = intent.getLongExtra(Params.USER_ID_ARG, Long.MIN_VALUE);
            if (userId != Long.MIN_VALUE) {
                getViewState().onRegister(userId);
            }
        }
    };

    private BroadcastReceiver mLoginActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent openLoginActivity = new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivityForResult(openLoginActivity, Params.LOGIN_REQUEST_CODE);
        }
    };

    private BroadcastReceiver mRegisterActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent openRegisterActivity = new Intent(getActivity(), RegisterActivity.class);
            getActivity().startActivityForResult(openRegisterActivity, Params.REGISTER_REQUEST_CODE);
        }
    };

}
