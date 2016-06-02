package com.lxy.music.activity.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by LXY on 2015/12/20
 */
public class SearchIndex extends View {

    private Paint mPaint;
    private int mCellWidth;
    private float mCellHeight;
    private int mViewHeight;
    private Rect mRect;
    private static final String[] LETTERS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z"};

    //回调接口
    public interface OnLetterChangeListener {
        public void onLetterChange(String letter);
    }

    public OnLetterChangeListener mOnLetterChangeListener;

//    public OnLetterChangeListener getOnLetterChangeListener() {
//        return mOnLetterChangeListener;
//    }

    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener) {
        mOnLetterChangeListener = onLetterChangeListener;
    }


    public SearchIndex(Context context) {
        super(context);
        initData();
    }

    public SearchIndex(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public SearchIndex(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SearchIndex(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData();
    }

    private void initData() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(24);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);//加粗

        mRect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < LETTERS.length; i++) {
            String text = LETTERS[i];
            float textWidth = mPaint.measureText(text);
            mPaint.getTextBounds(text, 0, text.length(), mRect);
            float textHeight = mRect.height();
            float x = mCellWidth * 0.5f - textWidth * 0.5f;
            float y = mCellHeight * 0.5f + textHeight * 0.5f + mCellHeight * i;
            canvas.drawText(text, x, y, mPaint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        mCellWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mCellHeight = mViewHeight * 1.0f / LETTERS.length;

    }

    private int mIndex = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int currentIndex;
        float currentY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentY = event.getY();
                currentIndex = (int) (currentY / mCellHeight);
                String text = LETTERS[currentIndex];
                if (mIndex != currentIndex) {
                    mIndex = currentIndex;
                    if (mOnLetterChangeListener != null) {
                        if(mIndex<LETTERS.length){
                            mOnLetterChangeListener.onLetterChange(LETTERS[mIndex]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = event.getY();
                currentIndex = (int) (currentY / mCellHeight);
                if (mIndex != currentIndex) {
                    mIndex = currentIndex;
                    if (mOnLetterChangeListener != null) {
                        if(mIndex<LETTERS.length){
                            mOnLetterChangeListener.onLetterChange(LETTERS[mIndex]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mIndex = -1;
                break;
        }
        return super.onTouchEvent(event);
    }
}
