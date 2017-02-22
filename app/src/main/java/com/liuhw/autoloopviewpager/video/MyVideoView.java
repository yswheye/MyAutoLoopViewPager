package com.liuhw.autoloopviewpager.video;

import android.content.Context;
import android.util.AttributeSet;

import com.liuhw.autoloopviewpager.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by gary on 17-2-22.
 */

public class MyVideoView extends StandardGSYVideoPlayer {

    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        initView();
    }

    private void initView() {

    }

    public boolean setUp(String url) {
        return super.setUp(url, false, "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_video_layout;
    }
}
