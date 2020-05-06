package com.zyyoona7.easypopup.recycle;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;

/**
 * Created by zicen on 2020-04-25.
 */
public class MyDiffUtilItemCallback extends DiffUtil.ItemCallback<TestBean> {
    @Override
    public boolean areItemsTheSame(TestBean oldItem, TestBean newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(TestBean oldItem, TestBean newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public Object getChangePayload(TestBean oldItem, TestBean newItem) {
        Bundle payload = new Bundle();
        if (!oldItem.getName().equals(newItem.getName())) {
            payload.putString("KEY_NAME", newItem.getName());
        }
        if (payload.size() == 0) {
            return null;
        }
        return payload;
    }
}
