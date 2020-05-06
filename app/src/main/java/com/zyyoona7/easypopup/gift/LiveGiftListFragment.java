package com.zyyoona7.easypopup.gift;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyyoona7.easypopup.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.Arrays;

/**
 * Created by zicen on 2020-04-13.
 */
public class LiveGiftListFragment extends Fragment {
    private WrapContentHeightViewPager viewPager;
    private MagicIndicator magicIndicator;
    public static final String EXTRA_TEXT = "extra_text";
    private static final String[] tags = new String[]{
            "礼物1","礼物2","礼物3"
    };
    private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(Arrays.asList(tags));
    private String mExtraText;

    public static LiveGiftListFragment newInstance(String extra){
        LiveGiftListFragment fragment = new LiveGiftListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXT, extra);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mExtraText = bundle.getString(EXTRA_TEXT);
        }
        viewPager = (WrapContentHeightViewPager) view.findViewById(R.id.view_pager);
        magicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        viewPager.setAdapter(mExamplePagerAdapter);
        CircleNavigator circleNavigator = new CircleNavigator(view.getContext());
        circleNavigator.setCircleCount(3);
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    public String getExtraText() {
        return mExtraText;
    }
}
