package com.andylai.nuxlauncher;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collection;


public class PieLayout extends RelativeLayout{

    public static final float EXPAND_RATIO = 0.8f;
    private final String TAG = "PieLayout";

    private Context mContext;
    private ArrayList<Integer> ImgBtnResArray;
    private ArrayList<ImageButton> mImageButtonGroup = new ArrayList<>();
    private ArrayList<Integer> mImgBtnResId = new ArrayList<>();

    final static String ANIMATE_ROTATION = "rotation";
    final static int OPEN_MENU = 0;
    final static int CLOSE_MENU = 1;

    private boolean mIsOpen = false;
    private boolean mIsInitFirstTime = true;
    //private View.OnClickListener mListener;
    private View.OnTouchListener mListener;

    private int[] mPreparedIds = {
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6};
    private int[] mPreparedRes = {
            R.drawable.ic_test1_01, R.mipmap.ic_button2, R.mipmap.ic_button3,
            R.mipmap.ic_button4, R.mipmap.ic_button5, R.mipmap.ic_button6};

    public PieLayout(Context context) {
        super(context);
        Log.d("Andy", "1");
        mContext = context;
    }

    public PieLayout(Context context, AttributeSet attributes) {
        super(context, attributes);
        Log.d("Andy", "2");
        mContext = context;
    }

    public ArrayList<ImageButton> getImageButtonGroup() {
        return mImageButtonGroup;
    }

//    public void setOnClickListenerListener(View.OnClickListener listener) {
//        mListener = listener;
//    }

    public void setOnTouchListener(View.OnTouchListener listener) {
        mListener = listener;
    }

    public void initPieMenu(ArrayList<Integer> buttonIcons) {
        inflate(getContext(), R.layout.pie_layout, this);
        setImageButtonResource(buttonIcons);
        setImgBtnResId(buttonIcons);
        createImageButton();
        addView();
    }

    private void createImageButton() {
        ImageButton imageButton;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT);
        params.topMargin = 0;
        params.leftMargin = 0;

        for (Integer imgBtnRes: ImgBtnResArray) {
            imageButton = new ImageButton(mContext);
            imageButton.setLayoutParams(params);
            imageButton.setVisibility(INVISIBLE);
            imageButton.setImageResource(imgBtnRes);
            imageButton.setBackgroundResource(android.R.color.transparent);
            mImageButtonGroup.add(imageButton);
        }

        for (int i = 0; i < mImageButtonGroup.size(); i++) {
            mImageButtonGroup.get(i).setId(mImgBtnResId.get(i));
             mImageButtonGroup.get(i).setOnTouchListener(mListener);

        }
    }

    public void setImageButtonResource(ArrayList<Integer> buttonIcons) {
        if(null != buttonIcons) {
            ImgBtnResArray = new ArrayList<>(buttonIcons);
        } else {
            ImgBtnResArray = new ArrayList<>();
            for (int i = 0; i < mPreparedRes.length; i++) {
                ImgBtnResArray.add(mPreparedRes[i]);
            }
        }
    }

    public void setImgBtnResId(ArrayList<Integer> buttonIcons) {
        int size = (null != buttonIcons) ? buttonIcons.size() : mPreparedIds.length;
        for (int i = 0; i < size; i++) {
            mImgBtnResId.add(mPreparedIds[i]);
        }
    }

    private ArrayList<Integer> getImgBtnResId() {
        return mImgBtnResId;
    }

    private void addView() {
        for (ImageButton imageButton: mImageButtonGroup) {
            addView(imageButton);
        }
    }

    public void setImageButtonVisibility(int visibility) {
        for (ImageButton imageButton: mImageButtonGroup) {
            imageButton.setVisibility(visibility);
        }
    }

    // unit of angle: degree
    public void operatePieMenu(int operateMenu) {
        AnimatorSet set = new AnimatorSet();
        Collection<Animator> animatorGroup = new ArrayList<>();
        for(int i = 0; i < mImageButtonGroup.size(); i++) {
            int size = mImageButtonGroup.size();
            mImageButtonGroup.get(i).setVisibility(VISIBLE);
            setPivot(mImageButtonGroup.get(i));
            ObjectAnimator animator;

            float startAngle = -30;
            float endAngle = 90;
            float pieceAngle = (endAngle / (size - 1));
            switch (operateMenu) {
                case OPEN_MENU:
                    animator= ObjectAnimator.ofFloat(mImageButtonGroup.get(i),
                            ANIMATE_ROTATION, startAngle, i * pieceAngle);
                    break;
                case CLOSE_MENU:
                    animator = ObjectAnimator.ofFloat(mImageButtonGroup.get(i),
                            ANIMATE_ROTATION, i * pieceAngle, startAngle);
                    break;
                default:
                    Log.d(TAG, "animator = null");
                    animator = null;
                    break;
            }
            animatorGroup.add(animator);
        }
        set.playTogether(animatorGroup);
        set.setDuration(500);
        set.start();
    }

    public void showPieMenu() {
        Log.d(TAG, "openPieMenu");
        if (mIsInitFirstTime) {
            setImageButtonVisibility(VISIBLE);
            mIsInitFirstTime = false;
        }
        openPieMenu();
    }

    public void openPieMenu() {
        if (!mIsOpen) {
            mIsOpen = true;
            operatePieMenu(OPEN_MENU);
        }
    }

    public void closePieMenu() {
        if (mIsOpen) {
            mIsOpen = false;
            operatePieMenu(CLOSE_MENU);
        }
    }

    public boolean getIsOpen (){
        return mIsOpen;
    }

    private void setPivot(ImageButton imageButton) {
        imageButton.setPivotX(imageButton.getWidth() / 2);
        imageButton.setPivotY(getLayoutParams().height -
                imageButton.getWidth() / 2);
        Log.d(TAG, "pivot(X,Y) = ("
                + imageButton.getPivotY()
                + ","
                + imageButton.getPivotX()
                + ")");
    }
}
