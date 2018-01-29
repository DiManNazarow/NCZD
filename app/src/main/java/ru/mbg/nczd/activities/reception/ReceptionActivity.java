package ru.mbg.nczd.activities.reception;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.activities.reception.mvp.ReceptionActivityPresenter;
import ru.mbg.nczd.activities.reception.mvp.ReceptionView;

public class ReceptionActivity extends BaseActivity implements ReceptionView {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.apply_button)
    protected Button mApplyButton;

    @InjectPresenter(type = PresenterType.LOCAL)
    ReceptionActivityPresenter mReceptionActivityPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    ReceptionActivityPresenter provideReceptionActivityPresenter(){
        return new ReceptionActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reception);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mReceptionActivityPresenter.getTypeFromIntent(getIntent());
        setToolbarTitle(mReceptionActivityPresenter.getReceptionType().getReceptionName(this));
        mReceptionActivityPresenter.prepareRecyclerView(mRecyclerView);
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.reception_reception);
    }

    @OnClick(R.id.apply_button)
    public void onApplyButtonClick(){
        mReceptionActivityPresenter.addReception();
    }

    @Override
    public void onAdapterReady(ReceptionActivityPresenter.ReceptionAdapter adapter) {

    }

}
