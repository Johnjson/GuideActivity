package com.click.guide.guide_lib.interfaces;


/**
 * Created by jhonjson on 2019/6/15.
 *
 * @Description: 引导view
 * @csdn https://blog.csdn.net/github_34402358
 */
public interface CallBack {

    // 滑动位置
    void callSlidingPosition(int position);

    // 滑动到最后一个
    void callSlidingLast();

    // 点击最后一个
    void onClickLastListener();
}
