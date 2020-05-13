package com.zyyoona7.easypopup.switchpager;

import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.blankj.utilcode.util.LogUtils;
import com.zyyoona7.easypopup.MainActivity;
import com.zyyoona7.easypopup.R;

import java.util.ArrayList;

public class SwitchLiveRoomActivity extends AppCompatActivity {

    public static final String TAG = "SwitchLiveRoomActivity";
    private ViewPager mViewPager;
    private RelativeLayout mRoomContainer;
    private PagerAdapter mPagerAdapter;
    private int mCurrentItem;
    private FrameLayout mFragmentContainer;
    private ArrayList<Long> mRooms = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private long mRoomId = 100L;
    private RoomFragment mRoomFragment;
    private boolean mInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_live_room);
        mViewPager = findViewById(R.id.view_pager);

        if (mRoomFragment == null) {
            mRoomFragment = RoomFragment.newInstance(mRoomId);
        }

        mRoomContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.view_room_container, null);
        mFragmentContainer = (FrameLayout) mRoomContainer.findViewById(R.id.fragment_container);
        mFragmentManager = getSupportFragmentManager();
        generateUrls();
        mPagerAdapter = new PagerAdapter();
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Log.e(TAG, "mCurrentId == " + position + ", positionOffset == " + positionOffset +
                //         ", positionOffsetPixels == " + positionOffsetPixels);
                LogUtils.e(TAG, "onPageScrolled  mCurrentItem old == " + mCurrentItem);
                mCurrentItem = position;
                LogUtils.e(TAG, "onPageScrolled  mCurrentItem == " + mCurrentItem);
            }
        });

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                ViewGroup viewGroup = (ViewGroup) page;
                // Log.e(TAG, "page.id == " + page.getId() + ", position == " + position);

                if ((position < 0 && viewGroup.getId() != mCurrentItem)) {
                    View roomContainer = viewGroup.findViewById(R.id.room_container);
                    if (roomContainer != null && roomContainer.getParent() != null && roomContainer.getParent() instanceof ViewGroup) {
                        ((ViewGroup) (roomContainer.getParent())).removeView(roomContainer);
                        LogUtils.e(TAG, "transformPage removeView(roomContainer)" + "page.id == " + page.getId() + ", position == " + position);
                    }
                }
                // 满足此种条件，表明需要加载直播视频，以及聊天室了
                if (viewGroup.getId() == mCurrentItem && position == 0) {
                    if (mRoomContainer.getParent() != null && mRoomContainer.getParent() instanceof ViewGroup) {
                        ((ViewGroup) (mRoomContainer.getParent())).removeView(mRoomContainer);
                        LogUtils.e(TAG, "transformPage removeView(mRoomContainer)" + "page.id == " + page.getId() + ", position == " + position);
                    }
                    loadVideoAndChatRoom(viewGroup, mCurrentItem);
                }
            }
        });
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void generateUrls() {
        for (int i = 0, size = 10; i < size; i++) {
            mRooms.add(mRoomId + i);
        }
    }


    /**
     * @param viewGroup
     * @param currentItem
     */
    private void loadVideoAndChatRoom(ViewGroup viewGroup, int currentItem) {
        LogUtils.e(TAG, "loadVideoAndChatRoom " + ", currentItem == " + currentItem);
        mRoomId = mRooms.get(currentItem);
        // 聊天室的fragment只加载一次，以后复用
        if (!mInit) {
            mFragmentManager.beginTransaction().add(mFragmentContainer.getId(), mRoomFragment).commitAllowingStateLoss();
            mInit = true;
        } else {
            mRoomFragment.refreshRoomId(mRoomId);
        }
        viewGroup.addView(mRoomContainer);
    }

    class PagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return mRooms.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item, null);
            view.setId(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(container.findViewById(position));
        }
    }
}
