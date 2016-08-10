package com.lpf.tools.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/10.
 * Description:
 */
public class SnackbarUtil {

    private static Snackbar mSnackbar;

    public static void show(View view,String msg,int flag){
        if (flag == 0){
            mSnackbar = Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
        }else{
            mSnackbar = Snackbar.make(view,msg,Snackbar.LENGTH_LONG);
        }

        mSnackbar.show();

        mSnackbar.setAction("关闭", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSnackbar.dismiss();
            }
        });
    }
}
