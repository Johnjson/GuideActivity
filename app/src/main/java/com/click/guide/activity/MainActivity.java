package com.click.guide.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.click.guide.guide_lib.GuideCustomViews;
import com.click.guide.guide_lib.interfaces.CallBack;

/**
 * Created by jhonjson on 2019/6/15.
 *
 * @Description: 引导页
 */
public class MainActivity extends AppCompatActivity implements CallBack {

    private GuideCustomViews GuideCustomViews;
    private final int[] mPageImages = {
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3,
            R.mipmap.guide_4
    };

    private final int[] mGuidePoint = {
            R.mipmap.icon_guide_point_select,
            R.mipmap.icon_guide_point_unselect
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        GuideCustomViews = findViewById(R.id.guide_CustomView);
        GuideCustomViews.setData(mPageImages, mGuidePoint, this);
    }

    @Override
    public void callSlidingPosition(int position) {
        Log.e("callSlidingPosition", "滑动位置 callSlidingPosition " + position);
    }

    @Override
    public void callSlidingLast() {
        Log.e("callSlidingLast", "滑动到最后一个callSlidingLast");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GuideCustomViews.clear();
    }
}
