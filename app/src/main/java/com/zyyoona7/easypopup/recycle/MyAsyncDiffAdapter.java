package com.zyyoona7.easypopup.recycle;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zyyoona7.easypopup.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zicen on 2020-04-25.
 */
public class MyAsyncDiffAdapter extends RecyclerView.Adapter<MyAsyncDiffAdapter.MyAsyncDiffViewHolder> {

    private final AsyncListDiffer<TestBean> mDiffer;
    private final LayoutInflater mInflater;

    public MyAsyncDiffAdapter(Context context) {
        mDiffer = new AsyncListDiffer<>(this, new MyDiffUtilItemCallback());
        mInflater = LayoutInflater.from(context);
    }

    public void addData(TestBean mData){
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.add(mData);
        mDiffer.submitList(mList);
    }

    public void setData(List<TestBean> mData){
        // 由于DiffUtil是对比新旧数据，所以需要创建新的集合来存放新数据。
        // 实际情况下，每次都是重新获取的新数据，所以无需这步。
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mData);
        mDiffer.submitList(mList);
    }

    public void removeData(int index){
        List<TestBean> mList = new ArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.remove(index);
        mDiffer.submitList(mList);
    }

    public void clear(){
        mDiffer.submitList(null);
    }



    @NonNull
    @Override
    public MyAsyncDiffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAsyncDiffViewHolder(mInflater.inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAsyncDiffViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            holder.mTxt.setText(bundle.getString("KEY_NAME"));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyAsyncDiffViewHolder holder, int position) {
        holder.mTxt.setText(mDiffer.getCurrentList().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    static class MyAsyncDiffViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxt;

        public MyAsyncDiffViewHolder(View itemView) {
            super(itemView);

            mTxt = itemView.findViewById(R.id.txt);

        }


    }
}
