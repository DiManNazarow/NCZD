package ru.mbg.nczd.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.fragments.mvp.AdviceFragmentPresenter;
import ru.mbg.nczd.fragments.mvp.ContactFragmentPresenter;
import ru.mbg.nczd.fragments.mvp.ContactView;

/**
 * Created by Дмитрий on 21.01.2018.
 */

public class ContactFragment extends MvpAppCompatFragment implements ContactView{

    public final String TAG = "ContactFragment";

    @BindView(R.id.view_pager)
    protected ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    protected TabLayout mTabLayout;

    @InjectPresenter(type = PresenterType.LOCAL)
    ContactFragmentPresenter mContactFragmentPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    ContactFragmentPresenter provideContactFragmentPresenter(){
        return new ContactFragmentPresenter(this);
    }

    public static ContactFragment newInstance(){
        return new ContactFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mContactFragmentPresenter.setup(mTabLayout, mViewPager);
    }

}
