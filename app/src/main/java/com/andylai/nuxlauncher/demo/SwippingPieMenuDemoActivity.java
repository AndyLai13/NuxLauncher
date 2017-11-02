package com.andylai.nuxlauncher.demo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andylai.nuxlauncher.NuxUtil;
import com.andylai.nuxlauncher.OnSwipeTouchListener;
import com.andylai.nuxlauncher.PieLayout;
import com.andylai.nuxlauncher.R;

import java.util.ArrayList;


public class SwippingPieMenuDemoActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private PieLayout pieLayout;
    private Button openPieButton;
    private Button closePieButton;

    private float EXPAND_RATIO = 0.8f;
    private int rotateRadius; // unit : px
    private ConstraintLayout mMainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipping_pie_menu_demo);

        mMainPage = (ConstraintLayout) findViewById(R.id.main_page);
        Log.d(TAG, "getWindowWidth = " + NuxUtil.getWindowWidth(this));
        Log.d(TAG, "getWindowHeight = " + NuxUtil.getWindowHeight(this));
        initPieLayout();
        openPieButton = (Button) findViewById(R.id.open_pie_button);
        closePieButton = (Button) findViewById(R.id.close_pie_button);

        openPieButton.setOnClickListener(mOpenClosePieButtonListener);
        closePieButton.setOnClickListener(mOpenClosePieButtonListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * OnSwipeTouchListener shall be initialized after MainActivity.this created,
         * thus following format is not allowed in this case.
         *
         * View.OnTouchListener mSwipeDetector = new OnSwipeTouchListener(MainActivity.this){};
         *
         */
        mMainPage.setOnTouchListener(new OnSwipeTouchListener(SwippingPieMenuDemoActivity.this) {
            @Override
            public void onSwipeBottomLeftHalfBound() {
                super.onSwipeBottomLeftHalfBound();
                Log.d(TAG, "onSwipeBottomLeftHalfBound ");
                pieLayout.closePieMenu();
            }

            @Override
            public void onSwipeLeftLowerHalfBound() {
                Log.d(TAG, "onSwipeLeftLowerHalfBound ");
                pieLayout.showPieMenu();
            }
        });
    }
    private void initPieLayout() {
        rotateRadius = (int) (EXPAND_RATIO * NuxUtil.getWindowWidth(this));
        Log.d(TAG, "rotateRadius = " + rotateRadius);
        // LayoutParams input dimen: px
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                rotateRadius, rotateRadius);
        params.startToStart = 0;
        params.bottomToBottom = 0;
        pieLayout = (PieLayout) findViewById(R.id.my_pie);
        pieLayout.setLayoutParams(params);
        pieLayout.setOnClickListenerListener(mPieMenuListener);
        pieLayout.initPieMenu(getButtonIcons()/*null*/);
    }

    /**
     * size of mImgBtnRes shall greater than 2, size of mImgBtnRes
     * and ImgBtnResId must be the same.
     * */
    private ArrayList<Integer> getButtonIcons() {
        ArrayList<Integer> buttonIcons = new ArrayList<>();
        buttonIcons.add(R.mipmap.ic_red_sun);
        buttonIcons.add(R.mipmap.ic_green_error);
        buttonIcons.add(R.mipmap.ic_blue_star);
        buttonIcons.add(R.mipmap.ic_purple_moon);
        return buttonIcons;
    }

    View.OnClickListener mOpenClosePieButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.open_pie_button:
                    Log.d(TAG, "open_pie_button");
                    /**
                     * imageButton is default as {@link View.INVISIBLE} and turn to
                     *  {@link View.VISIBLE} after clicking on {@link openPieButton}
                     */
                    pieLayout.showPieMenu();
                    break;
                case R.id.close_pie_button:
                    pieLayout.closePieMenu();
                    break;
                default:
                    break;
            }
        }
    };

    View.OnClickListener mPieMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button1:
                    Toast.makeText(SwippingPieMenuDemoActivity.this,
                            "my_button1", Toast.LENGTH_SHORT).show();
                    Log.d("Andy", "my_button1");
                    break;
                case R.id.button2:
                    Toast.makeText(SwippingPieMenuDemoActivity.this,
                            "my_button2", Toast.LENGTH_SHORT).show();
                    Log.d("Andy", "my_button2");
                    break;
                case R.id.button3:
                    Toast.makeText(SwippingPieMenuDemoActivity.this,
                            "my_button3", Toast.LENGTH_SHORT).show();
                    Log.d("Andy", "my_button3");
                    break;
                default:
                    break;
            }
        }
    };
}
