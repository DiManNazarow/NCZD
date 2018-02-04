package ru.mbg.nczd.views;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.kidz.ChildInfoActivity;
import ru.mbg.nczd.utils.Params;

/**
 * Created by Дмитрий on 04.02.2018.
 */

public class SimpleChildInfo extends ConstraintLayout {

    @BindView(R.id.initials_text_view)
    protected TextView mInitialsTextView;
    @BindView(R.id.omc_text_view)
    protected TextView mOmcTextView;

    private long mChildId;

    public SimpleChildInfo(Context context, long childId) {
        super(context);
        mChildId = childId;
        init();
    }

    public SimpleChildInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleChildInfo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_simple_child_info, this, true);
        ButterKnife.bind(this, this);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), ChildInfoActivity.class);
                intent.putExtra(Params.CHILD_ID_ARG, mChildId);
                getContext().startActivity(intent);
            }
        });
    }

    public void setInitialsText(String text){
        mInitialsTextView.setText(text);
    }

    public void setOmcText(String text){
        mOmcTextView.setText(getContext().getString(R.string.omc, text));
    }

}
