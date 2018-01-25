package ru.mbg.nczd.feature.news;

import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.recyclerviews.adapters.BaseRecyclerAdapter;
import ru.mbg.nczd.feature.recyclerviews.holders.BaseViewHolder;
import ru.mbg.nczd.feature.news.models.News;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class NewsListRecyclerAdapter extends BaseRecyclerAdapter<NewsListRecyclerAdapter.NewsViewHolder, News>{

    public NewsListRecyclerAdapter(List<News> news){
        super(news);
    }

    public NewsListRecyclerAdapter(){
        super();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent);
    }

    public class NewsViewHolder extends BaseViewHolder<News> {

        @BindView(R.id.date_text_view)
        protected TextView mDateTextView;
        @BindView(R.id.title_text_view)
        protected TextView mTitleTextView;
        @BindView(R.id.content_text_view)
        protected TextView mContentTextView;

        NewsViewHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_news_holder);
        }

        public void setup(News news){
            mDateTextView.setText(news.getData());
            mTitleTextView.setText(news.getTitle());
            mContentTextView.setText(news.getContent());
        }

    }

}
