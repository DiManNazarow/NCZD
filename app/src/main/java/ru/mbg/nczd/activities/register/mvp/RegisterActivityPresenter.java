package ru.mbg.nczd.activities.register.mvp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.arellomobile.mvp.InjectViewState;

import ru.mbg.nczd.activities.auth.LoginActivity;
import ru.mbg.nczd.mvp.BaseMvpPresenter;
import ru.mbg.nczd.utils.Actions;

/**
 * Created by Дмитрий on 18.01.2018.
 */
@InjectViewState
public class RegisterActivityPresenter extends BaseMvpPresenter<RegisterView> {

    public RegisterActivityPresenter(Activity activity){
        super(activity);
    }

    public void register(){
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(Actions.REGISTER_SUCCESS));
        getActivity().finish();
    }

    public void openSignInScreen(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

}
