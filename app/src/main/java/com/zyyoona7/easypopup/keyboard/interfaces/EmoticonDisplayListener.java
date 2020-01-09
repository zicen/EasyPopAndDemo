package com.zyyoona7.easypopup.keyboard.interfaces;

import android.view.ViewGroup;
import com.zyyoona7.easypopup.keyboard.adpater.EmoticonsAdapter;


public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
