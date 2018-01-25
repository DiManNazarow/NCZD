package ru.mbg.nczd.fragments.mvp;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.InjectViewState;

import ru.mbg.nczd.R;
import ru.mbg.nczd.fragments.HowFindFragment;
import ru.mbg.nczd.fragments.NumbersFragment;
import ru.mbg.nczd.mvp.FragmentMvpPresenter;

/**
 * Created by Дмитрий on 21.01.2018.
 */

@InjectViewState
public class ContactFragmentPresenter extends FragmentMvpPresenter<ContactView> {

    public ContactFragmentPresenter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void setup(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.white), ContextCompat.getColor(getActivity(), R.color.white));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getFragment().getChildFragmentManager()));
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final int FRAGMENT_COUNT = 2;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return NumbersFragment.newInstance();
                case 1: return HowFindFragment.newInstance();
                default: return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return getActivity().getString(R.string.contacts_contacts);
                case 1: return getActivity().getString(R.string.contact_how_find);
                default: return "";
            }
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }
    }

}
