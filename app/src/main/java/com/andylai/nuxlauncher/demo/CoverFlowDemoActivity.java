package com.andylai.nuxlauncher.demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andylai.nuxlauncher.CoverFlowViewPager;
import com.andylai.nuxlauncher.OnPageSelectListener;
import com.andylai.nuxlauncher.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoverFlowDemoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_cover_flow_demo);

        List<View> list = new ArrayList<>();
//        for(int i = 0;i < 10; i++){
//            ImageView img = new ImageView(this);
//            img.setBackgroundColor(Color.parseColor("#" + getRandColorCode()));
//            list.add(img);
//        }

        for(int i = 0;i < mImgIds.length; i++){
            ImageView img = new ImageView(this);
            img.setBackgroundColor(Color.parseColor("#" + getRandColorCode()));
            img.setImageResource(mImgIds[i]);
            list.add(img);
        }

        mCover = (CoverFlowViewPager) findViewById(R.id.cover);
        mCover.setViewList(list);
        mCover.setOnPageSelectListener(new OnPageSelectListener() {
            @Override
            public void select(int position) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
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
}
