package com.zyyoona7.easypopup.gift;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */

public class ExampleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<String> mDataList;
    private List<Fragment> mFragments = new ArrayList<>();

    public ExampleFragmentPagerAdapter(FragmentManager fm, List<String> dataList) {
        super(fm);
        mDataList = dataList;
        for (int i = 0, size = mDataList.size(); i < size; i++) {
            mFragments.add(LiveGiftListFragment.newInstance(mDataList.get(i)));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }
}
