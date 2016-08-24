package com.lpf.tools.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lpf on 16/8/24.
 * 自定义音量图
 */
public class VolumeView extends View{

    private int mWidth;
    private int mRectWidth;
    private int mRectHeight;
    private Paint mPaint;
    private int mRectCount;
    private int offset = 8;
    private double mRandom;
    private LinearGradient mLinearGradient;

    private int maxVolume;
    private int currentVolume;

    public VolumeView(Context context) {
        this(context,null);
    }

    public VolumeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mRectCount = 30;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int)(mWidth*0.8/mRectCount);
        mLinearGradient = new LinearGradient(
                0,0,mRectWidth,mRectHeight,Color.YELLOW,Color.RED, Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mRectCount; i++) {
//            mRandom = Math.random();
            mRandom = (float)currentVolume/maxVolume;
//            float currentHeight = (float)(mRectHeight*mRandom);
            float currentHeight = (float)(mRectHeight*mRandom);
            canvas.drawRect(
                    (float) (mWidth * 0.1 + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.1 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
        }

        postInvalidateDelayed(500);
    }

}
