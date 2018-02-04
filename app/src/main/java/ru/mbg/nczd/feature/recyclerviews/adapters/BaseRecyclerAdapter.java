package ru.mbg.nczd.feature.recyclerviews.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mbg.nczd.feature.BaseModel;
import ru.mbg.nczd.feature.recyclerviews.holders.BaseViewHolder;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public abstract class BaseRecyclerAdapter<T extends BaseViewHolder, M extends BaseModel> extends RecyclerView.Adapter<T> {

    private List<M> mData;

    public BaseRecyclerAdapter(List<M> data) {
        mData = data;
    }

    public BaseRecyclerAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        if (holder != null && mData.get(position) != null){
            holder.setup(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<M> getData() {
        return mData;
    }

    public void setData(List<M> data) {
        mData = data;
    }

    public void addData(List<M> data){
        if (mData != null){
            mData.addAll(data);
        } else {
            mData = data;
        }
    }
}
