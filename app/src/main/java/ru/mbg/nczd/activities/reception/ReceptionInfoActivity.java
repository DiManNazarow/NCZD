package ru.mbg.nczd.activities.reception;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.db.models.Reception;
import ru.mbg.nczd.db.models.User;
import ru.mbg.nczd.utils.DateUtils;
import ru.mbg.nczd.utils.Params;

public class ReceptionInfoActivity extends BaseActivity {

    @BindView(R.id.reception_type_text_view)
    protected TextView mReceptionTypeTextView;
    @BindView(R.id.repeated_type_text_view)
    protected TextView mRepeatedTextView;
    @BindView(R.id.consultation_type_text_view)
    protected TextView mConsultationType;

    @BindView(R.id.date_text_view)
    protected TextView mDateTextView;
    @BindView(R.id.time_text_view)
    protected TextView mTimeText;

    @BindView(R.id.initials_text_view)
    protected TextView mInitialsTextView;
    @BindView(R.id.omc_text_view)
    protected TextView mOmcTextView;
    @BindView(R.id.number_text_view)
    protected TextView mNumberTextView;
    @BindView(R.id.email_text_view)
    protected TextView mEmailTextView;

    private Reception mReception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reception_info);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setup();
    }

    protected String getToolbarTitle(){
        return getString(R.string.reception_info);
    }

    @OnClick(R.id.cancel_button)
    protected void onCancelClick(){
        if (mReception != null) {
            App.getAppDatabase().getReceptionDao().delete(mReception);
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Params.UPDATE_PROFILE_ACTION));
            finish();
        }
    }

    private void setup(){
        long receptionId = getIntent().getLongExtra(Params.RECEPTION_ID_ARG, -1);
        if (receptionId > -1){
            mReception = App.getAppDatabase().getReceptionDao().get(receptionId);
            if (mReception != null){
                mReceptionTypeTextView.setText(Params.RECEPTION_TYPE.getById(mReception.getTypeId()).getName(this));
                mRepeatedTextView.setText(mReception.getRepeated());
                mConsultationType.setText(mReception.getPurpose());
                mDateTextView.setText(DateUtils.getFullReceptionDate(this, mReception.getDate()));
                mTimeText.setText(mReception.getTime());
                User user = App.getAppDatabase().getUserDao().get(mReception.getUserId());
                if (user != null){
                    mInitialsTextView.setText(String.format(Locale.getDefault(), "%s %s %s", user.getFirstName(), user.getSecondName(), user.getPatronymic()));
                    mOmcTextView.setText(user.getOmc());
                    mNumberTextView.setText(user.getNumber());
                    mEmailTextView.setText(user.getEmail());
                }
            }
        }
    }

}
