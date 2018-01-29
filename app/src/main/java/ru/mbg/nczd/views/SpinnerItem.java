package ru.mbg.nczd.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mbg.nczd.R;

/**
 * Created by Дмитрий on 29.01.2018.
 */

public class SpinnerItem extends ConstraintLayout {

    @BindView(R.id.title_text_view)
    protected TextView mTitleTextView;
    @BindView(R.id.spinner)
    protected Spinner mSpinner;

    private List<String> mItems;

    private SpinnerAdapter mSpinnerAdapter;

    public SpinnerItem(Context context) {
        super(context);
        init();
    }

    public SpinnerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpinnerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_spinner_item, this, true);
        ButterKnife.bind(this, this);
        mItems = new ArrayList<>();
        mSpinnerAdapter = new SpinnerAdapter(getContext(), R.layout.spinner_item, mItems);
        mSpinner.setAdapter(mSpinnerAdapter);
    }

    public void setItems(List<String> items){
        mItems = items;
        mSpinnerAdapter.addAll(mItems);
        mSpinnerAdapter.notifyDataSetChanged();
    }

    public void setSpinnerItemSelectedListener(AdapterView.OnItemSelectedListener listener){
        mSpinner.setOnItemSelectedListener(listener);
    }

    public void setTitleText(String text){
        mTitleTextView.setText(text);
    }

    public void setTitleText(@StringRes int stringRes){
        mTitleTextView.setText(stringRes);
    }

    public class SpinnerAdapter extends ArrayAdapter<String> {

        SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        @NonNull
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        View getCustomView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false);
            } else {
                textView = (TextView)convertView;
            }
            textView.setText(getItem(position));
            return textView;
        }

    }

}
