package ru.mbg.nczd.fragments.mvp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mbg.nczd.feature.news.NewsListRecyclerAdapter;
import ru.mbg.nczd.feature.news.models.News;
import ru.mbg.nczd.feature.news.models.NewsDeserializer;
import ru.mbg.nczd.feature.news.models.NewsStat;
import ru.mbg.nczd.feature.news.models.NewsStatDeserializer;
import ru.mbg.nczd.feature.news.network.NewsService;
import ru.mbg.nczd.mvp.FragmentMvpPresenter;
import ru.mbg.nczd.network.RetrofitProvider;

/**
 * Created by Дмитрий on 20.01.2018.
 */

@InjectViewState
public class NewsFragmentPresenter extends FragmentMvpPresenter<NewsView> {

    private final String NEWS_REQUEST_TYPE_NAME = "getPageNewsList";

    private final String STAT_REQUEST_TYPE_NAME = "getStat";

    private int DEFAULT_PAGE_SIZE = 20;

    private RecyclerView mRecyclerView;

    private NewsListRecyclerAdapter mRecyclerAdapter;

    private int mPageCount = DEFAULT_PAGE_SIZE;

    private boolean isLastPage = false;
    private boolean isLoading = false;

    private int mCurrentPage = 0;

    private NewsService mNewsService;

    public NewsFragmentPresenter(@NonNull Fragment fragment) {
        super(fragment);
        mNewsService = RetrofitProvider.instance().getService(NewsService.class);
    }

    public void init(RecyclerView recyclerView, NewsListRecyclerAdapter adapter){
        mRecyclerAdapter = adapter;
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(mRecyclerViewScrollListener);
    }

    public NewsStat parseNewsStat(String json){
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<NewsStat>(){}.getType(), new NewsStatDeserializer()).create();
        return gson.fromJson(json, new TypeToken<NewsStat>(){}.getType());
    }

    public List<News> parseNewsList(String json){
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<News>>(){}.getType(), new NewsDeserializer()).create();
        return gson.fromJson(json, new TypeToken<List<News>>(){}.getType());
    }

    public void loadConfig(){
        getViewState().onLoadingStarted();
        mNewsService.getConfiguration(STAT_REQUEST_TYPE_NAME).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mPageCount = parseNewsStat(response.body().string()).getTotalPages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loadMoreItems();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                getViewState().onLoadingEnded();
            }
        });
    }

    private RecyclerView.OnScrollListener mRecyclerViewScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                }
            }
        }
    };

    private Callback<ResponseBody> newsPageCallBack = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            isLoading = false;
            try {
                mRecyclerAdapter.addData(parseNewsList(response.body().string()));
                mRecyclerAdapter.notifyDataSetChanged();
                getViewState().onLoadingEnded();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (mCurrentPage >= mPageCount){
                isLastPage = true;
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            isLoading = false;
            getViewState().onLoadingEnded();
        }
    };

    private void loadMoreItems(){
        if (mCurrentPage < mPageCount) {
            getViewState().onLoadingStarted();
            isLoading = true;
            mNewsService.getNewsList(NEWS_REQUEST_TYPE_NAME, mCurrentPage).enqueue(newsPageCallBack);
            mCurrentPage += 1;
        }
    }

    public void refresh(){
        getViewState().onLoadingStarted();
        isLoading = true;
        mCurrentPage = 0;
        mNewsService.getNewsList(NEWS_REQUEST_TYPE_NAME, mCurrentPage).enqueue(newsPageCallBack);
        mCurrentPage += 1;
    }

}
