package com.liuhw.autoloopviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liuhw.autoloopviewpager.listener.PageCallback;

/**
 * Created by gary on 17-2-22.
 */

public class PictureFragment extends Fragment {
    private static final String ARGS_POSITION = "position";
    private static final int[] COLORS = new int[]{0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444};
    private static final String[] imgs = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056312&di=8945fe2fb2b7a89f5695c2aadd31dff4&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F21%2Fb18b14b8c6f441a4ffdefbffcdcb51b2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056312&di=9dffef09a2de1eae7811654e33f02f36&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F21%2F52002d5e8b6b81b67b416297b537b119.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056312&di=4c22a3b2aefc9e781034a10e839f438e&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F21%2F894af5449d6f40de1b5dd4cdf58e6c90.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056311&di=eb5e8181d36ff5b38ddb7daaf4331604&imgtype=0&src=http%3A%2F%2Fimg6.web07.cn%2FUPics%2FBizhi%2F2016%2F0913%2F121474130955191.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487765056311&di=5ffcf198681a9f1a99d91c49ef66f6c9&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F21%2F8268e60ca71e79e9449f5b9ef98c45fb.jpg"
    };
    private long DELAY_TIME = 8000;
    private Handler handler = new Handler();
    private Runnable nextRunnable = new Runnable() {
        @Override
        public void run() {
            callback.scrollNext();
        }
    };

    private PageCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (PageCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement PageCallback");
        }
    }

    @NonNull
    public static Fragment newInstance(int position) {
        Fragment f = new PictureFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_POSITION, position);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int pos = getArguments().getInt(ARGS_POSITION);
        final View view = inflater.inflate(R.layout.fragment_dummy, container, false);
//        view.setBackgroundColor(COLORS[pos % 5]);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        Glide.with(this).load(imgs[pos % 5]).into(imageView);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            handler.removeCallbacks(nextRunnable);
            handler.postDelayed(nextRunnable, DELAY_TIME);
        } else {
            handler.removeCallbacks(nextRunnable);
        }
    }
}
