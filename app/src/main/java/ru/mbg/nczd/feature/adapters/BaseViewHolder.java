package ru.mbg.nczd.feature.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.mbg.nczd.feature.BaseModel;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public abstract class BaseViewHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(ViewGroup rootView, @LayoutRes int layoutId) {
        super(LayoutInflater.from(rootView.getContext()).inflate(layoutId, rootView, false));
        ButterKnife.bind(this, itemView);
    }

    public abstract void setup(M model);

}
