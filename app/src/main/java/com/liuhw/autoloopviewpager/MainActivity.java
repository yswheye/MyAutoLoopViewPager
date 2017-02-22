package com.liuhw.autoloopviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.liuhw.autoloopviewpager.listener.PageCallback;
import com.liuhw.autoloopviewpager.pagerindicator.AutoLoopViewPager;
import com.liuhw.autoloopviewpager.pagerindicator.CirclePageIndicator;

public class MainActivity extends AppCompatActivity implements PageCallback {

    private AutoLoopViewPager bannerViewPager;
    private CirclePageIndicator circlePagerIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAutoLoopViewPager();
        initCirclePageIndicator();
    }

    private void initCirclePageIndicator() {
        circlePagerIndicator = (CirclePageIndicator) findViewById(R.id.circlePagerIndicator);
        circlePagerIndicator.setViewPager(bannerViewPager);
    }

    private void initAutoLoopViewPager() {
        bannerViewPager = (AutoLoopViewPager) findViewById(R.id.bannerViewPager);
        bannerViewPager.setOffscreenPageLimit(1);
        // viewpager中边界的view不销毁,防止在轮播时闪屏
        bannerViewPager.setBoundaryCaching(true);
        // 是否自动滚动,默认true
        bannerViewPager.setCycle(true);
        // 设置滚动方向,默认向右
        bannerViewPager.setDirection(AutoLoopViewPager.RIGHT);
        // 当滚动到最后一个时,是否添加动画
        bannerViewPager.setBorderAnimation(true);
        // 设置滚动间隔时间,停留时间 默认1500毫秒
        bannerViewPager.setInterval(Long.MAX_VALUE);
        bannerViewPager.setScrollDuration(1 * 1000);

//        BannerAdapter bannerAdapter = new BannerAdapter(this, imageIds);
//        bannerViewPager.setAdapter(bannerAdapter);

        bannerViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        /**
         * ForegroundToBackgroundTransformer
         * AccordionTransformer
         */
        bannerViewPager.setPageTransformer(true, new AccordionTransformer());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void scrollNext() {
        bannerViewPager.scrollOnce();
    }

    /***********************
     * test
     ***********************/
    private class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 2) {
                return new VideoFragment();
            }
            return PictureFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }
    }

}
