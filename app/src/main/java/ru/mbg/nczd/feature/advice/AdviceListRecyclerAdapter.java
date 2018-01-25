package ru.mbg.nczd.feature.advice;

import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import ru.mbg.nczd.R;
import ru.mbg.nczd.feature.recyclerviews.adapters.BaseRecyclerAdapter;
import ru.mbg.nczd.feature.recyclerviews.holders.BaseViewHolder;
import ru.mbg.nczd.feature.advice.models.Advice;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class AdviceListRecyclerAdapter extends BaseRecyclerAdapter<AdviceListRecyclerAdapter.AdviceViewHolder, Advice> {

    @Override
    public AdviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdviceViewHolder(parent);
    }

    public class AdviceViewHolder extends BaseViewHolder<Advice> {

        @BindView(R.id.date_text_view)
        TextView mDateTextView;
        @BindView(R.id.title_text_view)
        TextView mTitleTextView;
        @BindView(R.id.source_text_view)
        TextView mSourceTextView;

        AdviceViewHolder(ViewGroup itemView) {
            super(itemView, R.layout.layout_advice_holder);
        }

        @Override
        public void setup(Advice model) {
            mDateTextView.setText(itemView.getContext().getString(R.string.advices_publish, model.getData()));
            mTitleTextView.setText(model.getTitle());
            mSourceTextView.setText(model.getSource());
        }
    }

}
