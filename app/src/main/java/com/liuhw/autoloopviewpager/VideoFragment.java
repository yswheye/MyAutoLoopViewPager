/**
 * VideoFragment
 * 2017-02-20
 */
package com.liuhw.autoloopviewpager;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liuhw.autoloopviewpager.listener.PageCallback;
import com.liuhw.autoloopviewpager.video.MyVideoView;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.io.File;

/**
 * description
 *
 * @author Garry
 * @email xxx@xxx
 * @date 2017-02-20
 */
public class VideoFragment extends Fragment {
    private MyVideoView videoPlayer;
    private OrientationUtils orientationUtils;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_play_layout, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoPlayer = (MyVideoView) view.findViewById(R.id.video_player);
        initPlayer();
    }

    private void initPlayer() {
        String local_url = Environment.getExternalStorageDirectory().getAbsolutePath() + "/m1.mp4";

        videoPlayer.setUp(Uri.parse(local_url).toString());

        //增加封面
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(Uri.fromFile(new File(local_url))).into(imageView);
//        //获取视频第一帧
//        MediaMetadataRetriever media = new MediaMetadataRetriever();
//        media.setDataSource(local_url);
//        Bitmap bitmap = media.getFrameAtTime();
//        imageView.setImageBitmap(bitmap);
        videoPlayer.setThumbImageView(imageView);

        //title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.setLockLand(true);
        //设置旋转
        orientationUtils = new OrientationUtils(getActivity(), videoPlayer);
        //设置全屏按键功能
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(false);

        videoPlayer.setStandardVideoAllCallBack(new StandardVideoAllCallBack() {
            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }

            @Override
            public void onPrepared(String url, Object... objects) {

            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {

            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            @Override
            public void onClickStop(String url, Object... objects) {

            }

            @Override
            public void onClickStopFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickResume(String url, Object... objects) {

            }

            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {

            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                callback.scrollNext();
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {

            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }

            @Override
            public void onPlayError(String url, Object... objects) {
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (videoPlayer != null) {
                videoPlayer.startPlayLogic();
            }
        } else {
            if (videoPlayer != null) {
                videoPlayer.onVideoReset();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.onVideoReset();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
        //释放所有
        videoPlayer.setStandardVideoAllCallBack(null);
        GSYVideoPlayer.releaseAllVideos();
    }

}