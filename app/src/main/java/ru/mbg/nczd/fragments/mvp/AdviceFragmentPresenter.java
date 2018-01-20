package ru.mbg.nczd.fragments.mvp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.advice.models.Advice;
import ru.mbg.nczd.feature.advice.models.AdviceDeserializer;
import ru.mbg.nczd.feature.news.models.News;
import ru.mbg.nczd.feature.news.models.NewsDeserializer;
import ru.mbg.nczd.mvp.FragmentMvpPresenter;
import ru.mbg.nczd.utils.RawUtils;

/**
 * Created by Дмитрий on 20.01.2018.
 */

@InjectViewState
public class AdviceFragmentPresenter extends FragmentMvpPresenter<AdviceView> {

    public AdviceFragmentPresenter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void getAdviceList(){
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<Advice>>(){}.getType(), new AdviceDeserializer()).create();
        List<Advice> advices = gson.fromJson(RawUtils.getStringRaw(getActivity(), R.raw.advice), new TypeToken<List<Advice>>(){}.getType());
        getViewState().onAdvicesGot(advices);
    }

}
