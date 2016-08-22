package com.lpf.tools.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lpf.tools.R;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/17.
 * Description:自定义圆盘，刻度线
 */
public class CustomCircleView extends View {

    private int mBorderColor;
    private float mBorderWidth;

    private Paint mPaint;
    private RectF mBounds;
    private float width;
    private float height;
    float radius;
    float smallLength;
    float largeLength;


    public CustomCircleView(Context context) {
        super(context);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCircleView,0,0);

        try {
            mBorderColor = typedArray.getColor(R.styleable.CustomCircleView_border_color,0xff000000);
            mBorderWidth = typedArray.getDimension(R.styleable.CustomCircleView_border_width,2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBounds = new RectF(getLeft(),getTop(),getRight(),getBottom());
        width = mBounds.right - mBounds.left;
        height = mBounds.bottom - mBounds.top;

        if(width < height){
            radius = width /4;
        }else{
            radius = height/4;
        }

        smallLength = 10;
        largeLength = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff000000);
        mPaint.setColor(0x66555555);
        canvas.drawRoundRect(new RectF(mBounds.centerX()-(float)0.9*width/2, mBounds.centerY() - (float)0.9*height/2, mBounds.centerX() + (float)0.9*width/2, mBounds.centerY() + (float)0.9*height/2), 30, 30, mPaint);

        mPaint.setColor(mBorderColor);
        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),radius,mPaint);

        float start_x,start_y;
        float end_x,end_y;

        //画刻度盘
        for (int i = 0; i < 60; i++) {
            start_x= radius *(float)Math.cos(Math.PI/180 * i * 6);
            start_y= radius *(float)Math.sin(Math.PI/180 * i * 6);
            if(i%5==0){
                end_x = start_x+largeLength*(float)Math.cos(Math.PI / 180 * i * 6);
                end_y = start_y+largeLength*(float)Math.sin(Math.PI/180 * i * 6);
            }else{
                end_x = start_x+smallLength*(float)Math.cos(Math.PI/180 * i * 6);
                end_y = start_y+smallLength*(float)Math.sin(Math.PI/180 * i * 6);
            }
            start_x+=mBounds.centerX();
            end_x+=mBounds.centerX();
            start_y+=mBounds.centerY();
            end_y+=mBounds.centerY();
            System.out.println("centerX:"+mBounds.centerX()+"centerY:"+mBounds.centerY());
            System.out.println("startX:"+start_x+"start_y:"+start_y+"end_x:"+end_x+"end_y:"+end_y);
            canvas.drawLine(start_x, start_y, end_x, end_y, mPaint);
        }

        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),20,mPaint);

        //画布旋转
        canvas.rotate(260,mBounds.centerX(),mBounds.centerY());
        canvas.drawLine(mBounds.centerX(),mBounds.centerY(),mBounds.centerX(),mBounds.centerY()-radius,mPaint);
    }
}
