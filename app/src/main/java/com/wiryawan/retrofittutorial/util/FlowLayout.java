package com.wiryawan.retrofittutorial.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wiryawan.retrofittutorial.R;

/**
 * Created by wiryawan on 1/13/17.
 */

public class FlowLayout extends ViewGroup {

    private int paddingHorizontal;
    private int paddingVertical;

    public FlowLayout(Context context) {
        super(context);
        init();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paddingHorizontal = getResources().getDimensionPixelOffset(R.dimen.margin_8dp);
        paddingVertical = getResources().getDimensionPixelOffset(R.dimen.margin_8dp);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int lineHeight = 0;

        int myWidth = resolveSize(100, widthMeasureSpec);
        int wantedHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            // let the child measure itself
            child.measure(getChildMeasureSpec(widthMeasureSpec, 0, child.getLayoutParams().width),
                    getChildMeasureSpec(heightMeasureSpec, 0, child.getLayoutParams().height));
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            // lineheight is the height of current line, should be the height of the  heightest view
            lineHeight = Math.max(childHeight, lineHeight);
            if (childWidth + childLeft + getPaddingRight() > myWidth) {
                // wrap this line
                childLeft = getPaddingLeft();
                childTop += paddingVertical + lineHeight;
                lineHeight = childHeight;
            }
            childLeft += childWidth + paddingHorizontal;
        }

        wantedHeight += childTop + lineHeight + getPaddingBottom();
        setMeasuredDimension(myWidth, resolveSize(wantedHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int lineHeight = 0;
        int myWidth = right - left;

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            lineHeight = Math.max(childHeight, lineHeight);
            if (childWidth + childLeft + getPaddingRight() > myWidth) {
                childLeft = getPaddingLeft();
                childTop += paddingVertical + lineHeight;
                lineHeight = childHeight;
            }
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            childLeft += childWidth + paddingHorizontal;
        }

    }
}
