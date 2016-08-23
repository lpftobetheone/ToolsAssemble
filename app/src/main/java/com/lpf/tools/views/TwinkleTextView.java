package com.lpf.tools.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lpf.tools.R;

/**
 * Created by lpf on 16/8/24.
 * 闪烁的文字
 */
public class TwinkleTextView extends TextView {

    private int mViewWidth = 0;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;

    private int mTranslate = 0;

    private int startColor;
    private int middleColor;
    private int endColor;

    public TwinkleTextView(Context context) {
        super(context);
    }

    public TwinkleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TwinkleTextView);
//        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TwinkleTextView,0,0);
        try {
            startColor = ta.getColor(R.styleable.TwinkleTextView_start_color, 0xff0000);
            middleColor = ta.getColor(R.styleable.TwinkleTextView_middle_color, 0xff0000);
            endColor = ta.getColor(R.styleable.TwinkleTextView_end_color, 0xff0000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ta.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mViewWidth = getMeasuredWidth();
        if (mViewWidth > 0) {
            mPaint = getPaint();
            //渐变颜色设置
            mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{startColor, middleColor, endColor}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(mLinearGradient);
            mGradientMatrix = new Matrix();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
