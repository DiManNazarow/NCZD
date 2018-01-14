package ru.mbg.nczd.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 15.01.2018.
 */

public class MainBottomNavBar extends ConstraintLayout {

    @BindView(R.id.news_button)
    protected Button mNewsButton;
    @BindView(R.id.about_button)
    protected Button mAboutButton;
    @BindView(R.id.reception_button)
    protected Button mReceptionButton;
    @BindView(R.id.contact_button)
    protected Button mContactButton;
    @BindView(R.id.advice_button)
    protected Button mAdviceButton;

    public interface OnNavigationButtonClickListener {
        void onNewsClick();
        void onAboutClick();
        void onReceptionClick();
        void onContactClick();
        void onAdviceClick();
    }

    private OnNavigationButtonClickListener mNavigationButtonClickListener;

    public MainBottomNavBar(Context context) {
        super(context);
        init();
    }

    public MainBottomNavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainBottomNavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_nav, this, true);
        ButterKnife.bind(this, this);
    }

    public void setNavigationButtonClickListener(OnNavigationButtonClickListener navigationButtonClickListener) {
        mNavigationButtonClickListener = navigationButtonClickListener;
    }

    private void setPadding(int news, int about, int reception, int contact, int advice){
        mNewsButton.setPadding(0, news, 0, 0);
        mAboutButton.setPadding(0, about, 0, 0);
        mReceptionButton.setPadding(0, reception, 0, 0);
        mContactButton.setPadding(0, contact, 0, 0);
        mAdviceButton.setPadding(0, advice, 0, 0);
    }

    private int getDimen(){
        return getResources().getDimensionPixelSize(R.dimen.bottom_nav_padding);
    }

    @OnClick(R.id.news_button)
    public void onNewsClick(){
        mNewsButton.setText(R.string.bottom_nav_news);
        mAboutButton.setText(null);
        mReceptionButton.setText(null);
        mContactButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(0, getDimen(), getDimen(), getDimen(), getDimen());
        if (mNavigationButtonClickListener != null){
            mNavigationButtonClickListener.onNewsClick();
        }
    }

    @OnClick(R.id.about_button)
    public void onAboutClick(){
        mAboutButton.setText(R.string.bottom_nav_about);
        mNewsButton.setText(null);
        mReceptionButton.setText(null);
        mContactButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(getDimen(), 0, getDimen(), getDimen(), getDimen());
        if (mNavigationButtonClickListener != null){
            mNavigationButtonClickListener.onAboutClick();
        }
    }

    @OnClick(R.id.reception_button)
    public void onReceptionClick(){
        mReceptionButton.setText(R.string.bottom_nav_reception);
        mNewsButton.setText(null);
        mAboutButton.setText(null);
        mContactButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(getDimen(), getDimen(), 0, getDimen(), getDimen());
        if (mNavigationButtonClickListener != null){
            mNavigationButtonClickListener.onReceptionClick();
        }
    }

    @OnClick(R.id.contact_button)
    public void onContactClick(){
        mContactButton.setText(R.string.bottom_nav_contacts);
        mNewsButton.setText(null);
        mAboutButton.setText(null);
        mReceptionButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(getDimen(), getDimen(), getDimen(), 0, getDimen());
        if (mNavigationButtonClickListener != null){
            mNavigationButtonClickListener.onContactClick();
        }
    }

    @OnClick(R.id.advice_button)
    public void onAdviceClick(){
        mAdviceButton.setText(R.string.bottom_nav_advice);
        mNewsButton.setText(null);
        mAboutButton.setText(null);
        mReceptionButton.setText(null);
        mContactButton.setText(null);
        setPadding(getDimen(), getDimen(), getDimen(), getDimen(), 0);
        if (mNavigationButtonClickListener != null){
            mNavigationButtonClickListener.onAdviceClick();
        }
    }

    public void setNewsSelected(){
        mNewsButton.setText(R.string.bottom_nav_news);
        mAboutButton.setText(null);
        mReceptionButton.setText(null);
        mContactButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(0, getDimen(), getDimen(), getDimen(), getDimen());
    }

    public void setAboutSelected(){
        mAboutButton.setText(R.string.bottom_nav_about);
        mNewsButton.setText(null);
        mReceptionButton.setText(null);
        mContactButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(getDimen(), 0, getDimen(), getDimen(), getDimen());
    }

    public void setReceptionSelected(){
        mReceptionButton.setText(R.string.bottom_nav_reception);
        mNewsButton.setText(null);
        mAboutButton.setText(null);
        mContactButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(getDimen(), getDimen(), 0, getDimen(), getDimen());
    }

    public void setContactSelected(){
        mContactButton.setText(R.string.bottom_nav_contacts);
        mNewsButton.setText(null);
        mAboutButton.setText(null);
        mReceptionButton.setText(null);
        mAdviceButton.setText(null);
        setPadding(getDimen(), getDimen(), getDimen(), 0, getDimen());
    }

    public void setAdviceSelected(){
        mAdviceButton.setText(R.string.bottom_nav_advice);
        mNewsButton.setText(null);
        mAboutButton.setText(null);
        mReceptionButton.setText(null);
        mContactButton.setText(null);
        setPadding(getDimen(), getDimen(), getDimen(), getDimen(), 0);
    }

}
