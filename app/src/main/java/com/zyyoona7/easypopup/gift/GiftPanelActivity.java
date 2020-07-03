package com.zyyoona7.easypopup.gift;

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.youth.banner.view.BannerViewPager;
import com.zyyoona7.easypopup.R;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.Arrays;

public class GiftPanelActivity extends AppCompatActivity {
    private MagicIndicator magicIndicator;
    private BannerViewPager bannerViewPager;
    private static final String[] tags = new String[]{
            "礼物","贵族"
    };
    private ExampleFragmentPagerAdapter mExamplePagerAdapter = new ExampleFragmentPagerAdapter(getSupportFragmentManager(), Arrays.asList(tags));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_panel);
        magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        bannerViewPager = (BannerViewPager) findViewById(R.id.banner_view_pager);

        initViewPager();

    }

    private void initViewPager() {
        bannerViewPager.setAdapter(mExamplePagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return tags.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(tags[index]);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(Color.RED);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bannerViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, bannerViewPager);
    }

    public void showGiftPanel(View view) {

    }
}
