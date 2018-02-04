package ru.mbg.nczd.feature.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.recyclerviews.adapters.BaseRecyclerAdapter;
import ru.mbg.nczd.feature.recyclerviews.holders.BaseViewHolder;
import ru.mbg.nczd.feature.news.models.News;
import ru.mbg.nczd.network.NetworkUtils;
import ru.mbg.nczd.utils.AppTextUtils;

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

    public class NewsViewHolder extends BaseViewHolder<News> implements View.OnClickListener {

        @BindView(R.id.date_text_view)
        protected TextView mDateTextView;
        @BindView(R.id.title_text_view)
        protected TextView mTitleTextView;
        @BindView(R.id.content_text_view)
        protected TextView mContentTextView;
        @BindView(R.id.image_view)
        protected ImageView mImageView;
        @BindView(R.id.read_more_text_view)
        protected TextView mReadMoreButton;

        private News mNews;

        NewsViewHolder(ViewGroup rootView) {
            super(rootView, R.layout.layout_news_holder);
            mReadMoreButton.setOnClickListener(this);
        }

        public void setup(News news){
            mNews = news;
            mImageView.setVisibility(View.GONE);
            mDateTextView.setText("12 hours ago");
            mTitleTextView.setText(news.getTitle());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mContentTextView.setText(Html.fromHtml(news.getText(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                mContentTextView.setText(Html.escapeHtml(news.getText()));
            }
            if (AppTextUtils.isEmpty(news.getPageLink())){
                mReadMoreButton.setVisibility(View.GONE);
            }
            if (news.getImages() != null && !news.getImages().isEmpty()){
                mImageView.setVisibility(View.VISIBLE);
                Picasso.with(itemView.getContext()).load(NetworkUtils.buildNewsImageLink(news.images.get(0))).into(mImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        mImageView.setVisibility(View.GONE);
                    }
                });
            }
        }

        @Override
        public void onClick(View v) {
            if (!AppTextUtils.isEmpty(mNews.getPageLink())){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(NetworkUtils.buildNewsDetailsLink(mNews.getPageLink())));
                itemView.getContext().startActivity(intent);
            }
        }
    }

}
