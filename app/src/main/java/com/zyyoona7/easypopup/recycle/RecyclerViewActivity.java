package com.zyyoona7.easypopup.recycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.zyyoona7.easypopup.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private MyAsyncDiffAdapter mAdapter;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);

        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAsyncDiffAdapter(this);
        recycler_view.setAdapter(mAdapter);
        initData();
    }

    private void addData() {
        mAdapter.addData(new TestBean(count, "Item " + count));
        count ++;
    }

    private List<TestBean> mList = new ArrayList();

    private void initData() {
        mList.clear();
        for (int i = 0; i < 10; i++){
            mList.add(new TestBean(i, "Item " + i));
        }
        mAdapter.setData(mList);
    }

    private void updateData() {
        mList.clear();
        for (int i = 9; i >= 0; i--){
            mList.add(new TestBean(i, "Item " + i));
        }
        mAdapter.setData(mList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private Random mRandom = new Random();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_add) {
            addData();
        } else if (i == R.id.menu_update) {
            updateData();
        } else if (i == R.id.menu_delete) {
            if (mAdapter.getItemCount() > 0){
                mAdapter.removeData(mRandom.nextInt(mAdapter.getItemCount()));
            }
        }else if (i == R.id.menu_clear){
            mAdapter.clear();
        }
        return true;
    }

}
