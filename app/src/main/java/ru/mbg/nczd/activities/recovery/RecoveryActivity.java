package ru.mbg.nczd.activities.recovery;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.auth.mvp.LoginActivityPresenter;
import ru.mbg.nczd.activities.recovery.mvp.RecoverActivityPresenter;
import ru.mbg.nczd.activities.recovery.mvp.RecoverView;

public class RecoveryActivity extends BaseActivity implements RecoverView{

    @BindView(R.id.email_text_input_layout)
    protected TextInputLayout mEmailInput;
    @BindView(R.id.email_edit_text)
    protected AppCompatEditText mEmailEditText;

    @InjectPresenter(type = PresenterType.LOCAL)
    RecoverActivityPresenter mRecoverActivityPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    RecoverActivityPresenter provideRecoverActivityPresenter(){
        return new RecoverActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recovery);
        super.onCreate(savedInstanceState);
    }

    protected String getToolbarTitle(){
        return getString(R.string.password_recover);
    }

    @OnClick(R.id.recover_button)
    public void onRecoverClick(){
        mRecoverActivityPresenter.recover();
    }

}
