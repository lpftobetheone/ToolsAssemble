package com.lpf.tools.views.animation;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/15.
 * Description:潜藏动画将page淡出，并且线性缩小
 */
public class DepthPageTransformer implements ViewPager.PageTransformer{
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        if(position < -1){              //[-infinity,-1]
            page.setAlpha(0);
        }else if(position <=0){         //(-1,0]
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleY(1);
            page.setScaleX(1);
        }else if(position <=1){         //(0,1]
            page.setAlpha(1-position);
            page.setTranslationX(pageWidth * -position);

            //Scale the page down (MIN_SCALE ~1)
            float scaleFactor = MIN_SCALE +(1-MIN_SCALE)*(1-Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }else{                          //(1,infinity)
            page.setAlpha(0);
        }
    }
}
