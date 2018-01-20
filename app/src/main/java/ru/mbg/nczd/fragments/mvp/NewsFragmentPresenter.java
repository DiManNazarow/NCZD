package ru.mbg.nczd.fragments.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.news.models.News;
import ru.mbg.nczd.feature.news.models.NewsDeserializer;
import ru.mbg.nczd.mvp.FragmentMvpPresenter;
import ru.mbg.nczd.utils.RawUtils;

/**
 * Created by Дмитрий on 20.01.2018.
 */

@InjectViewState
public class NewsFragmentPresenter extends FragmentMvpPresenter<NewsView> {

    public NewsFragmentPresenter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public void getNewsList(){
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<News>>(){}.getType(), new NewsDeserializer()).create();
        List<News> news = gson.fromJson(RawUtils.getStringRaw(getActivity(), R.raw.news), new TypeToken<List<News>>(){}.getType());
        getViewState().onNewsListGot(news);
    }

}
