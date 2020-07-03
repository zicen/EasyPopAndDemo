package com.zyyoona7.easypopup.gift;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
