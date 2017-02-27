package com.liuhw.autoloopviewpager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.liuhw.autoloopviewpager.listener.PageCallback;
import com.liuhw.autoloopviewpager.model.ADEntity;
import com.liuhw.autoloopviewpager.pagerindicator.AutoLoopViewPager;
import com.liuhw.autoloopviewpager.pagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PageCallback {

    private AutoLoopViewPager bannerViewPager;
    private CirclePageIndicator circlePagerIndicator;
    private ArrayList<ADEntity> adEntities = new ArrayList<>();

    private boolean isRealResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAutoLoopViewPager();
        initCirclePageIndicator();
    }

    private void initData() {
        adEntities.add(new ADEntity(Environment.getExternalStorageDirectory().getAbsolutePath() + "/atest_video/m1.mp4", ADEntity.ADType.VIDEO));
        adEntities.add(new ADEntity("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056312&di=8945fe2fb2b7a89f5695c2aadd31dff4&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F21%2Fb18b14b8c6f441a4ffdefbffcdcb51b2.jpg", ADEntity.ADType.PIC));
        adEntities.add(new ADEntity("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056312&di=9dffef09a2de1eae7811654e33f02f36&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F21%2F52002d5e8b6b81b67b416297b537b119.jpg", ADEntity.ADType.PIC));
    }

    private void initCirclePageIndicator() {
        circlePagerIndicator = (CirclePageIndicator) findViewById(R.id.circlePagerIndicator);
        circlePagerIndicator.setViewPager(bannerViewPager);
    }

    private void initAutoLoopViewPager() {
        bannerViewPager = (AutoLoopViewPager) findViewById(R.id.bannerViewPager);
        bannerViewPager.setOffscreenPageLimit(0);
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
        bannerViewPager.setScrollDuration(1500);

//        BannerAdapter bannerAdapter = new BannerAdapter(this, imageIds);
//        bannerViewPager.setAdapter(bannerAdapter);

        bannerViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        /**
         * ForegroundToBackgroundTransformer
         * AccordionTransformer
         */
        bannerViewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bannerViewPager != null && isRealResume) {
            bannerViewPager.scrollOnce();
        }
        isRealResume = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRealResume = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void scrollNext(Fragment fragment) {
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
            Log.d("_haha", "getItem = " + position);
            int realPos = position - 1;
            if (realPos < 0 || realPos >= 5) {
                realPos = 0;
            }
            if (realPos == 1) {
                return new VideoFragment();
            }
            return PictureFragment.getInstance(realPos);
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
