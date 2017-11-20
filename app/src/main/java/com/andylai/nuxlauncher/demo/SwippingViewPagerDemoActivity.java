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
        pieLayout.setOnTouchListener(mTouchListener);
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


//    View.OnClickListener mOpenClosePieButtonListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.open_pie_button:
//                    Log.d(TAG, "open_pie_button");
//                    pieLayout.showPieMenu();
//                    break;
//                case R.id.close_pie_button:
//                    pieLayout.closePieMenu();
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

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

                    for (int i = 0; i < iconButtons.size() - 1; i++) {

                        AnimatedIconLocation location = new AnimatedIconLocation(iconButtons.get(i));
                        int iconX = location.getX();
                        int iconY = location.getY();
                        AnimatedIconLocation locationNext = new AnimatedIconLocation(iconButtons.get(i + 1));
                        int iconXNext = locationNext.getX();
                        int iconYNext = locationNext.getY();


                        Log.d("Andy", "touch x y = " + event.getRawX() + ", " + event.getRawY());
                        Log.d("Andy", "iconX, iconY = " + iconX + "," + iconY +
                            "; iconXNext, iconYNext = " + iconXNext + "," + iconYNext );
                        if ( iconX < event.getRawX() && event.getRawX() < iconXNext &&
                                iconY < event.getRawY() && event.getRawY() < iconYNext ) {
                            Log.d("Andy", " i = " + i);
                        }
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

    public class AnimatedIconLocation {
        public AnimatedIconLocation(View view) {
            int[] locationOnScreen = new int[2];
            view.getLocationOnScreen(locationOnScreen);
            X = locationOnScreen[0];
            Y = locationOnScreen[1];
        }
        public AnimatedIconLocation(ImageButton imageButton) {
            int[] locationOnScreen = new int[2];
            imageButton.getLocationOnScreen(locationOnScreen);
            X = locationOnScreen[0];
            Y = locationOnScreen[1];
        }

        private int X;
        private int Y;
        public int getX() {return X;}
        public int getY() {return Y;}

    }
}
