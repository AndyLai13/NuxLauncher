package com.andylai.nuxlauncher;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by AndyLai on 2017/10/28.
 */

public abstract class OnSwipeTouchListener implements View.OnTouchListener {

    private final String TAG = "OnSwipeTouchListener";
    private Context mContext;
    private final GestureDetector mGesturDector;



    public OnSwipeTouchListener (Context context){
        this.mContext = context;
        mGesturDector = new GestureDetector(mContext, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGesturDector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 500;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        public GestureListener() {

        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("Andy", "onDown");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll : " +
                    "e1(x,y) = " + e1.getX() + ", " + e1.getY() +
                    "e2(x,y) = " + e2.getX() + ", " + e2.getY() +
                    "distanceX = " + distanceX + ", distanceY = " + distanceY);

            int w = NuxUtil.getWindowWidth(mContext);
            int h = NuxUtil.getWindowHeight(mContext);

            boolean condition1 = (e1.getX() <= (0.15 * w))
                    && (e1.getY() >= (h - (0.8 * w)));
            boolean condition2 = ((h - e1.getY()) <= (0.15 * w))
                    && (e1.getX() <= (0.8 * w));

            Log.d(TAG, "condition1 = " + condition1);
            Log.d(TAG, "condition2 = " + condition2);

            try {
                if (condition1) {
                    onSwipeLeftLowerHalfBound();
                } else if (condition2) {
                    onSwipeBottomRightHalfBound();
                }
                return true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("Andy", "onFling : " +
                    "e1(x,y) = " + e1.getX() + ", " + e1.getY() +
                    "e2(x,y) = " + e2.getX() + ", " + e2.getY() +
                    "velocityX = " + velocityX + ", velocityY = " + velocityY);

            return false;
        }
    }

    public void onSwipeRight(){}
    public void onSwipeLeft(){}
    public void onSwipeTop(){}
    public void onSwipeBottom(){}
    public void onSwipeBottomRightHalfBound(){}
    public void onSwipeLeftLowerHalfBound(){}


}
