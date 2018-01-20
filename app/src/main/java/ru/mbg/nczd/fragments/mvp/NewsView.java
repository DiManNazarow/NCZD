package ru.mbg.nczd.fragments.mvp;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.mbg.nczd.feature.news.models.News;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public interface NewsView extends MvpView {

    public void onNewsListGot(List<News> news);

}
