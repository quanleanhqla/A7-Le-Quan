package com.example.quanla.pomodoro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by QuanLA on 3/4/2017.
 */

public class LSquareLayout extends RelativeLayout {
    public LSquareLayout(Context context) {
        super(context);
    }

    public LSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LSquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = height;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
