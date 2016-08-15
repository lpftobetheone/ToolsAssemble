package com.lpf.tools.views.animation;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/15.
 * Description:相邻界面滑动时，使页面收缩并褪色，当页面越靠近中心，它将渐渐还原到正常大小并且图像渐入
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer{

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if(position < -1){  //当前页面已经隐藏掉了
            view.setAlpha(0);
        }else if(position <=1){
            float scaleFactor = Math.max(MIN_SCALE,1-Math.abs(position));
            float vertMargin = pageHeight *(1-scaleFactor)/2;
            float horzMargin = pageWidth*(1-scaleFactor)/2;

            if(position <0){
                view.setTranslationX(horzMargin - vertMargin/2);
            }else{
                view.setTranslationX(-horzMargin + vertMargin/2);
            }

            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            view.setAlpha(MIN_ALPHA+(scaleFactor-MIN_SCALE)/(1-MIN_SCALE)*(1-MIN_SCALE));
        }else{              //当前页面已经隐藏掉了
            view.setAlpha(0);
        }
    }
}
