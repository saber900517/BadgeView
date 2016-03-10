package com.example.sunxiaodong.badgeview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 该BadgeView的实现结合了当前已有的实现，实现了更灵活的使用方式，在xml文件中定位红点，使位置更能按用户需求实现
 * by sunxiaodong
 */
public class BadgeView extends TextView {

    private boolean mHideOnNull = true;//为true,则当TextView的值为0或者null时，不显示红点

    private RoundRectShape mRoundRectShape;//圆角方形背景
    private OvalShape mOvalShape;//圆形背景

    private int mDefaultRadius;//默认圆角
    private int mDefaultBgColor;//默认背景色

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDefaultRadius = (int) getResources().getDimension(R.dimen.dimen_1080p_27);
        mDefaultBgColor = Color.parseColor("#CCFF0000");
        setTextColor(Color.WHITE);
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        initRoundRectShape();
        ShapeDrawable bgDrawable = new ShapeDrawable(mRoundRectShape);
        bgDrawable.getPaint().setColor(mDefaultBgColor);
        setBackgroundDrawable(bgDrawable);
    }

    public void setRadius(int radiusPx) {
        mDefaultRadius = radiusPx;
        float[] radiusArray = new float[]{radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx};
        mRoundRectShape = new RoundRectShape(radiusArray, null, null);
        ShapeDrawable bgDrawable = (ShapeDrawable) getBackground();
        bgDrawable.setShape(mRoundRectShape);
        setBackgroundDrawable(bgDrawable);
    }

    public void setBgColor(int bgColor) {
        mDefaultBgColor = bgColor;
        ShapeDrawable bgDrawable = (ShapeDrawable) getBackground();
        bgDrawable.getPaint().setColor(mDefaultBgColor);
        setBackgroundDrawable(bgDrawable);
    }

    private void initRoundRectShape() {
        if (mRoundRectShape == null) {
            float[] radiusArray = new float[]{mDefaultRadius, mDefaultRadius, mDefaultRadius, mDefaultRadius, mDefaultRadius, mDefaultRadius, mDefaultRadius, mDefaultRadius};
            mRoundRectShape = new RoundRectShape(radiusArray, null, null);
        }
    }

    public void setBadgeCount(int count) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        int leftRightPadding = (int) getResources().getDimension(R.dimen.dimen_1080p_15);
        int topBottomPadding = (int) getResources().getDimension(R.dimen.dimen_1080p_3);
        setPadding(leftRightPadding, topBottomPadding, leftRightPadding, topBottomPadding);
        ShapeDrawable bgDrawable = (ShapeDrawable) getBackground();
        initRoundRectShape();
        bgDrawable.setShape(mRoundRectShape);
        bgDrawable.getPaint().setColor(mDefaultBgColor);
        setBackgroundDrawable(bgDrawable);
        setText(String.valueOf(count));
    }

    private void initOvalShape() {
        if (mOvalShape == null) {
            mOvalShape = new OvalShape();
        }
    }

    public void setNoBadgeCount(int sizePx) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = sizePx;
        layoutParams.height = sizePx;
        setPadding(0, 0, 0, 0);
        ShapeDrawable bgDrawable = (ShapeDrawable) getBackground();
        initOvalShape();
        bgDrawable.setShape(mOvalShape);
        bgDrawable.getPaint().setColor(mDefaultBgColor);
        setBackgroundDrawable(bgDrawable);
        setText("");
    }

    public boolean isHideOnNull() {
        return mHideOnNull;
    }

    public void setHideOnNull(boolean hideOnNull) {
        mHideOnNull = hideOnNull;
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isHideOnNull() && (text == null || text.toString().equalsIgnoreCase("0"))) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
        super.setText(text, type);
    }

    public Integer getBadgeCount() {
        if (getText() == null) {
            return null;
        }

        String text = getText().toString();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void incrementBadgeCount(int increment) {
        Integer count = getBadgeCount();
        if (count == null) {
            setBadgeCount(increment);
        } else {
            setBadgeCount(increment + count);
        }
    }

    public void decrementBadgeCount(int decrement) {
        incrementBadgeCount(-decrement);
    }

}
