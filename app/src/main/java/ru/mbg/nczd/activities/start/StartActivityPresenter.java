package ru.mbg.nczd.activities.start;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 14.01.2018.
 */

@InjectViewState
public class StartActivityPresenter extends MvpPresenter<StartView> {

    public StartActivityPresenter(){

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

}
