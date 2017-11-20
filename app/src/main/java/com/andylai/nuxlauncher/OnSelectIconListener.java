package com.andylai.nuxlauncher;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.andylai.nuxlauncher.util.NuxUtil;

/**
 * Created by AndyLai on 2017/11/21.
 */

public class OnSelectIconListener implements View.OnTouchListener {

    private final String TAG = "OnSwipeTouchListener";
    private Context mContext;
    private final GestureDetector mGestureDetector;



    public OnSelectIconListener (Context context){
        this.mContext = context;
        mGestureDetector = new GestureDetector(mContext, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 500;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        public GestureListener() {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll : " +
                    "e1(x,y) = " + e1.getX() + ", " + e1.getY() +
                    "e2(x,y) = " + e2.getX() + ", " + e2.getY() +
                    "distanceX = " + distanceX + ", distanceY = " + distanceY);

            int w = NuxUtil.getWindowWidth(mContext);
            int h = NuxUtil.getWindowHeight(mContext);



            try {
                getMovingXY(e2.getX(), e2.getY());
                return true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            Log.d("Andy", "onFling : " +
//                    "e1(x,y) = " + e1.getX() + ", " + e1.getY() + ", " +
//                    "e2(x,y) = " + e2.getX() + ", " + e2.getY() + ", " +
//                    "velocityX = " + velocityX + ", velocityY = " + velocityY);

            return false;
        }
    }

    public void getMovingXY(float x, float y) {}


}
