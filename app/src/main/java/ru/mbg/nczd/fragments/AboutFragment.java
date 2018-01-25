package ru.mbg.nczd.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mbg.nczd.R;

public class AboutFragment extends Fragment {

    public final String TAG = "AboutFragment";

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    public AboutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
