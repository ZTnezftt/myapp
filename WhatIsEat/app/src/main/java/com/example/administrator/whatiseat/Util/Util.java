package com.example.administrator.whatiseat.Util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class Util {
    public static Toast toast;
    public static void Toast(String str, Context context){
        if (toast==null){
            toast=Toast.makeText(context,str,Toast.LENGTH_SHORT);
        }else{
            toast.setText(str);
        }
        toast.show();
    }
    public static int dpToPx(Context context,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
