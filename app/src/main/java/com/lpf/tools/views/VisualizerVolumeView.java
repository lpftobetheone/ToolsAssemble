package com.lpf.tools.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.media.audiofx.Visualizer;
import android.view.View;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/24.
 * Description:绘制音量能量图
 */
public class VisualizerVolumeView extends View implements Visualizer.OnDataCaptureListener{

    private static final int DN_W = 470; //View宽度与单个音频快占比（480正常）
    private static final int DN_H = 180; //View高度与单个音频块占比

    private static final int DN_SL = 15; //单个音频块宽度
    private static final int DN_SW = 5;  //单个音频块高度

    protected final static int MAX_LEVEL = 25; //音量柱中音量块的最大个数
    protected final static int CYLINDER_NUM = 25; //音量柱个数
    protected byte[] mData = new byte[CYLINDER_NUM];//音量柱数组

    protected Visualizer mVisualizer = null;//频谱器

    protected Paint mPaint;

    private boolean mDataEn = true;

    private int hgap = 0;
    private int vgap = 0;
    private int levelStep = 0;
    private float strokeWidth = 0;
    private float strokeLength = 0;

    public VisualizerVolumeView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

//        mPaint.setStrokeJoin(Paint.Join.ROUND);//频块圆角
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        float w, h, xr, yr;

        w = right - left;
        h = bottom - top;
        xr = w / (float) DN_W;
        yr = h / (float) DN_H;

        strokeWidth = DN_SW * yr;
        strokeLength = DN_SL * xr;
        hgap = (int) ((w - strokeLength * CYLINDER_NUM) / (CYLINDER_NUM + 1));
        vgap = (int) (h / (MAX_LEVEL + 2));

        mPaint.setStrokeWidth(strokeWidth); //设置频谱块宽度
    }

    //绘制频谱块和倒影
    protected void drawCylinder(Canvas canvas, float x, byte value) {
        if (value <= 0) value = 1;//最少有一个频谱块

        for (int i = 0; i < value; i++) { //每个能量柱绘制value个能量块
            float y = (getHeight() - i * vgap - vgap) - 40;//计算y轴坐标

            //绘制频谱块
            mPaint.setColor(Color.WHITE);//画笔颜色

            //渐变颜色设置
            LinearGradient mLinearGradient = new LinearGradient(getWidth()/2, 0, getWidth()/2, getHeight(), new int[]{Color.BLUE, Color.WHITE, Color.RED}, null, Shader.TileMode.MIRROR);
            mPaint.setShader(mLinearGradient);

            canvas.drawLine(x, y, (x + strokeLength), y, mPaint);//绘制频谱块

            //绘制音量柱倒影
            if (i <= 6 && value > 0) {
                mPaint.setColor(Color.WHITE);//画笔颜色
                mPaint.setAlpha(100 - (100 / 6 * i));//倒影颜色
                canvas.drawLine(x, -y + 210, (x + strokeLength), -y + 210, mPaint);//绘制频谱块
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < CYLINDER_NUM; i++) { //绘制25个能量柱
            drawCylinder(canvas, strokeWidth / 2 + hgap + i * (hgap + strokeLength), mData[i]);
        }
    }

    /**
     * It sets the visualizer of the view. DO set the viaulizer to null when exit the program.
     *
     * @parma visualizer It is the visualizer to set.
     */
    public void setVisualizer(Visualizer visualizer) {
        if (visualizer != null) {
            if (!visualizer.getEnabled()) {
                visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);
            }
            levelStep = 128 / MAX_LEVEL;
            visualizer.setDataCaptureListener(this, Visualizer.getMaxCaptureRate() / 2, false, true);

        } else {

            if (mVisualizer != null) {
                mVisualizer.setEnabled(false);
                mVisualizer.release();
            }
        }
        mVisualizer = visualizer;
    }

    //这个回调应该采集的是快速傅里叶变换有关的数据
    @Override
    public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
        byte[] model = new byte[fft.length / 2 + 1];
        if (mDataEn) {
            model[0] = (byte) Math.abs(fft[1]);
            int j = 1;
            for (int i = 2; i < fft.length; ) {
                model[j] = (byte) Math.hypot(fft[i], fft[i + 1]);
                i += 2;
                j++;
            }
        } else {
            for (int i = 0; i < CYLINDER_NUM; i++) {
                model[i] = 0;
            }
        }
        for (int i = 0; i < CYLINDER_NUM; i++) {
            final byte a = (byte) (Math.abs(model[CYLINDER_NUM - i]) / levelStep);

            final byte b = mData[i];
            if (a > b) {
                mData[i] = a;
            } else {
                if (b > 0) {
                    mData[i]--;
                }
            }
        }
        postInvalidate();//刷新界面
    }


    //这个回调应该采集的是波形数据
    @Override
    public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {

    }

}
