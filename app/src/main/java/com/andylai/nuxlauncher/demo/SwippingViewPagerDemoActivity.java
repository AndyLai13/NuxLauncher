package com.andylai.nuxlauncher.demo;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.andylai.nuxlauncher.CoverFlowViewPager;
import com.andylai.nuxlauncher.OnPageSelectListener;
import com.andylai.nuxlauncher.OnSelectIconListener;
import com.andylai.nuxlauncher.OnSwipeTouchListener;
import com.andylai.nuxlauncher.PieLayout;
import com.andylai.nuxlauncher.R;
import com.andylai.nuxlauncher.util.NuxUtil;

import java.util.ArrayList;

public class SwippingViewPagerDemoActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private CoverFlowViewPager mCover;
    private PieLayout pieLayout;
    private int rotateRadius; // unit : px
    private ConstraintLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipping_view_pager_demo);

        mContainer = (ConstraintLayout) findViewById(R.id.container);

        mCover = (CoverFlowViewPager) findViewById(R.id.cover);
        mCover.setOnPageSelectListener(new OnPageSelectListener() {
            @Override
            public void select(int position) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });

        initPieLayout();
    }

    private void initPieLayout() {
        rotateRadius = (int) (PieLayout.EXPAND_RATIO * NuxUtil.getWindowWidth(this));
        Log.d(TAG, "rotateRadius = " + rotateRadius);

        pieLayout = (PieLayout) findViewById(R.id.my_pie);
        // LayoutParams input dimen: px
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                rotateRadius, rotateRadius);
        params.startToStart = 0;
        params.bottomToBottom = 0;
        pieLayout.setLayoutParams(params);
        //pieLayout.setOnClickListener(mPieMenuListener);
        pieLayout.setOnTouchListener(/*mTouchListener*/new OnSelectIconListener(SwippingViewPagerDemoActivity.this) {
            @Override
            public void getMovingXY(float x, float y){
                Log.d("Andy", "x = " + x);
                Log.d("Andy", "y = " + y);

                y = 576 - y;
                float tan_theta = x/y;
                double angle18 = Math.tan(1 * Math.PI/10);
                double angle36 = Math.tan(2 * Math.PI/10);
                double angle54 = Math.tan(3 * Math.PI/10);
                double angle72 = Math.tan(4 * Math.PI/10);
                double angle90 = Math.tan(5 * Math.PI/10);

                Log.d("Andy", "x = " + x);
                Log.d("Andy", "y = " + y);
                Log.d("Andy", "tan_theta = " + tan_theta);
                Log.d("Andy", "angle18 = " + angle18);
                Log.d("Andy", "angle36 = " + angle36);
                Log.d("Andy", "angle54 = " + angle54);
                Log.d("Andy", "angle72 = " + angle72);
                Log.d("Andy", "angle90 = " + angle90);


                if (tan_theta <= angle18) {
                    Log.d("Andy", "icon 1" );
                    mCover.getViewPager().setCurrentItem(0,true);
                } else if (angle18 <= tan_theta && tan_theta < angle36) {
                    Log.d("Andy", "icon 2" );
                    mCover.getViewPager().setCurrentItem(1,true);
                } else if (angle36 <= tan_theta && tan_theta < angle54) {
                    Log.d("Andy", "icon 3" );
                    mCover.getViewPager().setCurrentItem(2,true);
                } else if (angle54 <= tan_theta && tan_theta < angle72) {
                    Log.d("Andy", "icon 4" );
                    mCover.getViewPager().setCurrentItem(3,true);
                } else {
                    Log.d("Andy", "icon 5" );
                    mCover.getViewPager().setCurrentItem(4,true);
                }
            }


        });
        pieLayout.initPieMenu(null);
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


        mContainer.setOnTouchListener(new OnSwipeTouchListener(SwippingViewPagerDemoActivity.this) {
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

    View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {



                case MotionEvent.ACTION_UP:
                    Log.d("Andy", "ACTION_UP");

                    ArrayList<ImageButton> iconButtons = pieLayout.getImageButtonGroup();

//                    AnimatedIconLocation location = new AnimatedIconLocation(iconButtons.get(0));
//                    int iconX = location.getX();
//                    int iconY = location.getY();
//                    AnimatedIconLocation locationNext = new AnimatedIconLocation(iconButtons.get(0 + 1));
//                    int iconXNext = locationNext.getX();
//                    int iconYNext = locationNext.getY();

//                    Log.d("Andy", "touch x y = " + event.getRawX() + ", " + event.getRawY());
//                    Log.d("Andy", "iconX, iconY = " + iconX + "," + iconY +
//                            "; iconXNext, iconYNext = " + iconXNext + "," + iconYNext );



                        Log.d("Andy", "touch x y = " + event.getRawX() + ", " + event.getRawY());

                        float xxx = event.getRawX();
                        float yyy = 1280 - event.getRawY();
                        float tan_theta = xxx/yyy;
                        double angle18 = Math.tan(1 * Math.PI/10);
                        double angle36 = Math.tan(2 * Math.PI/10);
                        double angle54 = Math.tan(3 * Math.PI/10);
                        double angle72 = Math.tan(4 * Math.PI/10);
                        double angle90 = Math.tan(5 * Math.PI/10);

                        Log.d("Andy", "xxx = " + xxx);
                        Log.d("Andy", "yyy = " + yyy);
                        Log.d("Andy", "tan_theta = " + tan_theta);
                        Log.d("Andy", "angle18 = " + angle18);
                        Log.d("Andy", "angle36 = " + angle36);
                        Log.d("Andy", "angle54 = " + angle54);
                        Log.d("Andy", "angle72 = " + angle72);
                        Log.d("Andy", "angle90 = " + angle90);


                        if (tan_theta <= angle18) {
                            Log.d("Andy", "icon 1" );
                            mCover.getViewPager().setCurrentItem(0,true);
                        } else if (angle18 <= tan_theta && tan_theta < angle36) {
                            Log.d("Andy", "icon 2" );
                            mCover.getViewPager().setCurrentItem(1,true);
                        } else if (angle36 <= tan_theta && tan_theta < angle54) {
                            Log.d("Andy", "icon 3" );
                            mCover.getViewPager().setCurrentItem(2,true);
                        } else if (angle54 <= tan_theta && tan_theta < angle72) {
                            Log.d("Andy", "icon 4" );
                            mCover.getViewPager().setCurrentItem(3,true);
                        } else {
                            Log.d("Andy", "icon 5" );
                            mCover.getViewPager().setCurrentItem(4,true);
                        }







                    break;
            }
            return false;
        }
//        int[] locationOnScreent = new int[2];
//        view.getLocationOnScreen(locationOnScreent);
//        Log.d("Andy", "x =  " + locationOnScreent[0]);
//        Log.d("Andy", "y =  " + locationOnScreent[1]);}
    };
}
