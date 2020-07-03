package com.zyyoona7.easypopup.switchpager;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.LogUtils;
import com.zyyoona7.easypopup.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zicen on 2020-05-12.
 */
public class MyLiveFragment extends Fragment {
    public static final String TAG = "MyLiveFragment";
    private RelativeLayout mRoomContainer;
    private PagerAdapter mPagerAdapter;
    private int mCurrentItem;
    private FrameLayout mFragmentContainer;
    private ArrayList<Long> mRooms = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private long mRoomId = 100L;
    private RoomFragment mRoomFragment;
    private boolean mInit = false;
    private ViewPager mViewPager;

    public static MyLiveFragment newInstance() {
        MyLiveFragment fragment = new MyLiveFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_switch_live_room, container, false);
        Log.e(TAG, "onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        mViewPager = view.findViewById(R.id.view_pager);
        view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRooms.clear();
                mRooms.add(0, mRoomId);
                for (int i = 0, size = 10; i < size; i++) {
                    mRooms.add(mRoomId * 100 + i);
                }
                mPagerAdapter.notifyDataSetChanged();
            }
        });

        if (mRoomFragment == null) {
            mRoomFragment = RoomFragment.newInstance(mRoomId);
        }

        mRoomContainer = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.view_room_container, null);
        mFragmentContainer = mRoomContainer.findViewById(R.id.fragment_container);
        mFragmentManager = getChildFragmentManager();
        generateUrls();
        mPagerAdapter = new PagerAdapter(mRooms);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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
                    loadChatRoom(viewGroup, mCurrentItem);
                }
            }
        });
        mViewPager.setAdapter(mPagerAdapter);
        int startItem = mPagerAdapter.getStartItem();
        LogUtils.d(TAG, "startItem: " + startItem);
        mViewPager.setCurrentItem(startItem);
    }

    private void generateUrls() {
        for (int i = 0, size = 10; i < size; i++) {
            mRooms.add(mRoomId + i);
        }
    }

    private void loadChatRoom(ViewGroup viewGroup, int currentItem) {
        LogUtils.e(TAG, "loadChatRoom " + ", currentItem == " + currentItem);
        mRoomId = mRooms.get(mPagerAdapter.getRealItemPos(currentItem));
        // 聊天室的fragment只加载一次，以后复用
        if (!mInit) {
            mFragmentManager.beginTransaction().add(mFragmentContainer.getId(), mRoomFragment).commitAllowingStateLoss();
            mInit = true;
        } else {
            mRoomFragment.refreshRoomId(mRoomId);
        }
        viewGroup.addView(mRoomContainer);
    }

    class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
        private List<Long> itemList;
        // 最好不要用Integer.MAX_VALUE这个值，因为在setCurrentItem的时候会出现卡顿，甚至 ANR，可以适当设置小一点
        public static final int mLooperCount = 500;

        public PagerAdapter(List<Long> rooms) {
            this.itemList = rooms;
        }

        @Override
        public int getCount() {
            return getRealCount() * mLooperCount;
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

        // 获取数据项个数
        private int getRealCount() {
            return itemList == null ? 0 : itemList.size();
        }

        private int getStartItem() {
            if(getRealCount() == 0){
                return 0;
            }
            // 我们设置当前选中的位置为 Integer.MAX_VALUE / 2,这样开始就能往左滑动
            // 但是要保证这个值与 getRealPosition 的 余数为 0，因为要从第一页开始显示
            int currentItem = getRealCount() * mLooperCount / 2;
            if(currentItem % getRealCount()  == 0){
                return currentItem;
            }
            // 直到找到从 0 开始的位置
            while (currentItem % getRealCount() != 0){
                currentItem++;
            }
            return currentItem;
        }
        // 2500  0
        // 2499  9   499 10  9
        // 2501  1
        private int getRealItemPos(int position) {
            int realPos = position % mLooperCount;
            return realPos % getRealCount();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }
}
