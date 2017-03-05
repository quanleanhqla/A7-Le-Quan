package com.example.quanla.pomodoro.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

/**
 * Created by QuanLA on 2/17/2017.
 */

public class Utils {
    public static void setSolidColor(View v, String colorString) {
        GradientDrawable drawable = (GradientDrawable)v.getBackground();
        drawable.setColor(Color.parseColor(colorString));
    }




}
