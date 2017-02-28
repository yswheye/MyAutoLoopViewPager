package com.liuhw.autoloopviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
    private static final String ARGS_URL = "pic_url";
    private long DELAY_TIME = 8000;
    private Handler handler = new Handler();
    private Runnable nextRunnable = new Runnable() {
        @Override
        public void run() {
            callback.scrollNext(PictureFragment.this);
        }
    };

    private PageCallback callback;

    public static Fragment getInstance(String url) {
        Fragment f = new PictureFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_URL, url);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (PageCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement PageCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String url = getArguments().getString(ARGS_URL);
        final View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
//        TextView textView = (TextView) view.findViewById(R.id.text);
//        textView.setText(url);
        Glide.with(this).load(url).into(imageView);
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

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(nextRunnable);
    }

}
