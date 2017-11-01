package com.andylai.nuxlauncher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TempHomeActivity extends AppCompatActivity {

    private Button mAppList;
    private Button mFloatingPages;
    private ViewPager mViewPager;
    private int[] mImgIds = new int[] {
            R.mipmap.ic_red_sun,
            R.mipmap.ic_green_error,
            R.mipmap.ic_blue_star,
            R.mipmap.ic_purple_moon };
    private List<ImageView> mImageViews = new ArrayList<>();


    private CoverFlowViewPager mCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_home);
//        initData();

//        mAppList = (Button) findViewById(R.id.app_list);
//        mAppList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(TempHomeActivity.this,
//                        AppListActivity.class));
//            }
//        });
//
//        mFloatingPages = (Button) findViewById(R.id.floating_pages);
//        mFloatingPages.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(TempHomeActivity.this,
//                        FloatinPagesActivity.class));
//            }
//        });
//
//        mViewPager = (ViewPager) findViewById(R.id.my_view_pager);
//        mViewPager.setAdapter(mPagerAdapter);
//        mViewPager.setPageTransformer(true, )


        mCover = (CoverFlowViewPager) findViewById(R.id.cover);

        // 初始化数据
        List<View> list = new ArrayList<>();

        for(int i = 0;i < 10; i++){
            ImageView img = new ImageView(this);
            img.setBackgroundColor(Color.parseColor("#" + getRandColorCode()));
            list.add(img);
        }
        //设置显示的数据
        mCover.setViewList(list);
        // 设置滑动的监听，该监听为当前页面滑动到中央时的索引
        mCover.setOnPageSelectListener(new OnPageSelectListener() {
            @Override
            public void select(int position) {
                Toast.makeText(getApplicationContext(), position+ "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static String getRandColorCode(){
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;

        return r + g + b;
    }

    /*private void initData() {
        for (int imgId : mImgIds) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return mImgIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViews.get(position));
            return mImageViews.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView(mImageViews.get(position));
        }


    };*/
}
