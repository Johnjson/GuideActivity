package com.click.guide.guide_lib.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by jhonjson on 2019/6/15.
 *
 * @Description: 引导view
 * @csdn https://blog.csdn.net/github_34402358
 */
public class GuideAdapter extends PagerAdapter {
    private ArrayList<View> views;

    //重写构造方法，加图片
    public GuideAdapter(ArrayList<View> views) {
        this.views = views;
    }

    // 必须 1- 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return views.size();
    }


    //必须 2-  来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //必须 3-  PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView(views.get(position));
    }

    //必须 4- // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
    // 我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

}