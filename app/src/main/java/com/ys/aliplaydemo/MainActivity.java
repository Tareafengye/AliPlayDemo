package com.ys.aliplaydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout appBar;
    /**
     * 大布局背景，遮罩层
     */
    private View bgContent;
    /**
     * 展开状态下toolbar显示的内容
     */
    private View toolbarOpen;
    /**
     * 展开状态下toolbar的遮罩层
     */
    private View bgToolbarOpen;
    /**
     * 收缩状态下toolbar显示的内容
     */
    private View toolbarClose;
    /**
     * 收缩状态下toolbar的遮罩层
     */
    private View bgToolbarClose;

    private int mMaskColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appBar = findViewById(R.id.app_bar);
        bgContent = findViewById(R.id.bg_content);
        toolbarOpen = findViewById(R.id.include_toolbar_open);
        bgToolbarOpen = findViewById(R.id.bg_toolbar_open);
        toolbarClose = findViewById(R.id.include_toolbar_close);
        bgToolbarClose = findViewById(R.id.bg_toolbar_close);
        mMaskColor = getResources().getColor(R.color.light_blue_600);
        appBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {


        int offset = Math.abs(verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        int alphaIn = offset;
        int alphaOut = (200 - offset) < 0 ? 0 : 200 - offset;
        int maskColorIn = Color.argb(alphaIn, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorInDouble = Color.argb(alphaIn * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorOut = Color.argb(alphaOut * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));


        if (offset <= total / 2) {
            toolbarOpen.setVisibility(View.VISIBLE);
            toolbarClose.setVisibility(View.GONE);
            bgContent.setBackgroundColor(maskColorInDouble);


        } else {
            toolbarClose.setVisibility(View.VISIBLE);
            toolbarOpen.setVisibility(View.GONE);
            bgContent.setBackgroundColor(maskColorOut);
        }
            bgContent.setBackgroundColor(maskColorIn);


//        //垂直方向偏移量
//        int offset = Math.abs(i);
//        //最大偏移距离
//        int scrollRange = appBarLayout.getTotalScrollRange();
//        if (offset <= scrollRange / 2) {//当滑动没超过一半，展开状态下toolbar显示内容，根据收缩位置，改变透明值
//            toolbarOpen.setVisibility(View.VISIBLE);
//            toolbarClose.setVisibility(View.GONE);
//            //根据偏移百分比 计算透明值
//            float scale2 = (float) offset / (scrollRange / 2);
//            int alpha2 = (int) (255 * scale2);
////            bgToolbarOpen.setBackgroundColor(Color.argb(alpha2, 25, 131, 131));
//        } else {//当滑动超过一半，收缩状态下toolbar显示内容，根据收缩位置，改变透明值
//            toolbarClose.setVisibility(View.VISIBLE);
//            toolbarOpen.setVisibility(View.GONE);
//            float scale3 = (float) (scrollRange  - offset) / (scrollRange / 2);
//            int alpha3 = (int) (255 * scale3);
////            bgToolbarClose.setBackgroundColor(Color.argb(alpha3, 25, 131, 131));
//        }
//        //根据偏移百分比计算扫一扫布局的透明度值
//        float scale = (float) offset / scrollRange;
//        int alpha = (int) (255 * scale);
////        bgContent.setBackgroundColor(Color.argb(alpha, 25, 131, 131));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appBar.removeOnOffsetChangedListener(this);
    }
}
