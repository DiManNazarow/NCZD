package ru.mbg.nczd.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 30.01.2018.
 */

public class SimpleReceptionInfo extends ConstraintLayout {

    @BindView(R.id.date_text_view)
    protected TextView mDateTextView;
    @BindView(R.id.time_text_view)
    protected TextView mTimeTextView;
    @BindView(R.id.name_text_view)
    protected TextView mNameText;
    @BindView(R.id.person_text_view)
    protected TextView mPersonTextView;

    public SimpleReceptionInfo(Context context) {
        super(context);
        init();
    }

    public SimpleReceptionInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleReceptionInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_simple_reception_info, this, true);
        ButterKnife.bind(this, this);
        mPersonTextView.setText(R.string.you);
    }

    public void setDateText(String text){
        mDateTextView.setText(text);
    }

    public void setTimeText(String text){
        mTimeTextView.setText(text);
    }

    public void setNameText(String text){
        mNameText.setText(text);
    }


}
