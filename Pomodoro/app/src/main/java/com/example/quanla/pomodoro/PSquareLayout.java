package com.example.quanla.pomodoro;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by QuanLA on 3/4/2017.
 */

public class PSquareLayout extends RelativeLayout {
    public PSquareLayout(Context context) {
        super(context);
    }

    public PSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PSquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
