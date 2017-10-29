package com.andylai.nuxlauncher;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";

    private Button openPieButton;
    private Button closePieButton;

    private float EXPAND_RATIO = 0.8f;
    private int rotateRadius; // unit : px

    private ConstraintLayout mMainPage;
    private PieLayout pieLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "getWindowWidth = " + NuxUtil.getWindowWidth(HomeActivity.this));
        Log.d(TAG, "getWindowHeight = " + NuxUtil.getWindowHeight(HomeActivity.this));
        initPieLayout();
        mMainPage = (ConstraintLayout) findViewById(R.id.main_page);
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
        mMainPage.setOnTouchListener(new OnSwipeTouchListener(HomeActivity.this) {
            @Override
            public void onSwipeBottomRightHalfBound() {
                super.onSwipeBottomRightHalfBound();
                Log.d(TAG, "onSwipeBottomRightHalfBound ");

                pieLayout.closePieMenu();
//                Toast.makeText(MainActivity.this, "onSwipeBottomRightHalfBound",
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSwipeLeftLowerHalfBound() {
                Log.d(TAG, "onSwipeLeftLowerHalfBound ");

                pieLayout.showPieMenu();
//                Toast.makeText(MainActivity.this, "onSwipeLeftLowerHalfBound",
//                        Toast.LENGTH_SHORT).show();

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
        pieLayout.setListener(mPieMenuListener);
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
                    Toast.makeText(HomeActivity.this, "my_button1", Toast.LENGTH_SHORT).show();
                    Log.d("Andy", "my_button1");
                    break;
                case R.id.button2:
                    Toast.makeText(HomeActivity.this, "my_button2", Toast.LENGTH_SHORT).show();
                    Log.d("Andy", "my_button2");
                    break;
                case R.id.button3:
                    Toast.makeText(HomeActivity.this, "my_button3", Toast.LENGTH_SHORT).show();
                    Log.d("Andy", "my_button3");
                    break;
                default:
                    break;
            }
        }
    };

}
