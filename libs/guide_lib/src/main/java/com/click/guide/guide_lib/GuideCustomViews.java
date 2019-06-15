package com.click.guide.guide_lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.click.guide.guide_lib.adapter.GuideAdapter;
import com.click.guide.guide_lib.interfaces.CallBack;

import java.util.ArrayList;


/**
 * Created by jhonjson on 2019/6/15.
 *
 * @Description: 引导view
 * @csdn https://blog.csdn.net/github_34402358
 */
public class GuideCustomViews extends FrameLayout implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private ViewPager viewPager;
    private LinearLayout pointContent;
    private View rootView;
    private int pageSize;
    private ArrayList<View> mPageViews;
    private ArrayList<ImageView> mPointView;
    private Bitmap selectPoint, unselectPoint;
    private CallBack callBack;

    public GuideCustomViews(@NonNull Context context) {
        this(context, null);
    }

    public GuideCustomViews(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuideCustomViews(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化布局
     *
     * @param context
     */
    private void initView(Context context) {
        mContext = context;
        mPageViews = new ArrayList<View>();
        mPointView = new ArrayList<ImageView>();
        rootView = LayoutInflater.from(context).inflate(R.layout.view_guild, this, true);
        viewPager = rootView.findViewById(R.id.guide_viewpager);
        pointContent = rootView.findViewById(R.id.point_content);
    }


    public void setData(int[] pageImages, int[] guidePoint, CallBack callBack) {
        this.callBack = callBack;
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        pageSize = pageImages.length;
        for (int i = 0; i < pageSize; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource((pageImages[i]));
            mPageViews.add(iv);
        }
        viewPager.setAdapter(new GuideAdapter(mPageViews));
        viewPager.addOnPageChangeListener(this);
        initPointViews(guidePoint);
    }


    /**
     * 初始化点View
     */
    private void initPointViews(int[] guidePoint) {
        selectPoint = BitmapFactory.decodeResource(getResources(), guidePoint[0]);
        unselectPoint = BitmapFactory.decodeResource(getResources(), guidePoint[1]);
        mPointView = new ArrayList<ImageView>();
        pointContent.removeAllViews();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mParams.setMargins(dip2px(7), 0, 0, 0);
        for (int i = 0; i < pageSize; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageBitmap(unselectPoint);
            mPointView.add(iv);
            pointContent.addView(iv);
        }
        switchHighlightPoint(0);
    }

    private void switchHighlightPoint(int index) {
        if (index < 0 || index > pageSize - 1) {
            return;
        }
        int size = mPointView.size();
        for (int i = 0; i < size; i++) {
            if (index == i) {
                mPointView.get(i).setImageBitmap(selectPoint);
            } else {
                mPointView.get(i).setImageBitmap(unselectPoint);
            }
        }
    }

    /***
     * px 转 dp
     *
     * @return
     */
    public int dip2px(int dipValue) {
        if (dipValue == 0) {
            return 0;
        }
        final float scale = getScreenDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    private float screenDensity;

    /***
     * 获取屏幕密度
     *
     * @return
     */
    public float getScreenDensity() {
        if (screenDensity != 0f) {
            return screenDensity;
        }
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        screenDensity = dm.density;
        return screenDensity;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switchHighlightPoint(i);
        if (callBack != null) {
            callBack.callSlidingPosition(i);
            if (i == pageSize - 1) {
                callBack.callSlidingLast();
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    public void clear() {
        pageSize = 0;
        clearPageViews();
        clearPointView();
        clearBitmap();

    }

    private void clearPageViews() {
        if (null != mPageViews) {
            int size = mPageViews.size();
            for (int i = 0; i < size; i++) {
                mPageViews.get(i).setBackgroundResource(0);
            }
            mPageViews.clear();
        }
        mPageViews = null;
    }

    private void clearPointView() {
        if (null != mPointView) {
            int size = mPointView.size();
            for (int i = 0; i < size; i++) {
                mPointView.get(i).setBackgroundResource(0);
            }
            mPointView.clear();
        }
        mPointView = null;
    }

    private void clearBitmap() {
        if (!selectPoint.isRecycled()) {
            selectPoint.recycle();
            selectPoint = null;
        }
        if (!unselectPoint.isRecycled()) {
            unselectPoint.recycle();
            unselectPoint = null;
        }
    }


}