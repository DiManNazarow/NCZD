package ru.mbg.nczd.activities.start.mvp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.activities.register.RegisterActivity;
import ru.mbg.nczd.utils.Actions;

/**
 * Created by Дмитрий on 14.01.2018.
 */

@InjectViewState
public class StartActivityPresenter extends MvpPresenter<StartView> {

    private Activity mActivity;

    public StartActivityPresenter(Activity activity){
        mActivity = activity;
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

    public void registerReceivers(Context context){
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.registerReceiver(mLoginSuccessReceiver, new IntentFilter(Actions.LOGIN_SUCCESS));
        manager.registerReceiver(mRegisterSuccessReceiver, new IntentFilter(Actions.REGISTER_SUCCESS));
        manager.registerReceiver(mLoginActionReceiver, new IntentFilter(Actions.LOGIN_ACTION));
        manager.registerReceiver(mRegisterActionReceiver, new IntentFilter(Actions.REGISTER_ACTION));
    }

    public void unregisterReceivers(Context context){
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
        manager.unregisterReceiver(mLoginSuccessReceiver);
        manager.unregisterReceiver(mRegisterSuccessReceiver);
        manager.unregisterReceiver(mLoginActionReceiver);
        manager.unregisterReceiver(mRegisterActionReceiver);
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
            getViewState().onRegister();
        }
    };

    private BroadcastReceiver mLoginActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent openLoginActivity = new Intent(mActivity, LoginActivity.class);
            mActivity.startActivityForResult(openLoginActivity, Actions.LOGIN_REQUEST_CODE);
        }
    };

    private BroadcastReceiver mRegisterActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent openRegisterActivity = new Intent(mActivity, RegisterActivity.class);
            mActivity.startActivityForResult(openRegisterActivity, Actions.REGISTER_REQUEST_CODE);
        }
    };

}
