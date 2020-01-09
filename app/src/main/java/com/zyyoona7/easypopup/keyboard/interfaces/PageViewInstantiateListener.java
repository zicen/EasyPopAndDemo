package com.zyyoona7.easypopup.keyboard.interfaces;

import android.view.View;
import android.view.ViewGroup;
import com.zyyoona7.easypopup.keyboard.data.PageEntity;


public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
