package com.example.administrator.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.administrator.game2048.GameData;

public class OVuong extends android.support.v7.widget.AppCompatTextView {
    public OVuong(Context context) {
        super(context);
    }

    public OVuong(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OVuong(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int chieuDaiCanh = getMeasuredWidth();
        setMeasuredDimension(chieuDaiCanh, chieuDaiCanh);//vì là ô vuông nên 2 cạnh bằng nhau
    }

    public void chinhText(int so) {
        if (so < 100) {
            setTextSize(40);
        } else if (so < 1000) {
            setTextSize(35);
        } else {
            setTextSize(30);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) this.getBackground();
        gradientDrawable.setColor(GameData.getGameData().setColor(so));
        setBackground(gradientDrawable);

        if (so == 0) {
            setText(" ");
        } else {
            setText("" + so);
        }

        setTextColor(Color.WHITE);
    }
}
