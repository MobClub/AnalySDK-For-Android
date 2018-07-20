package com.mob.analysdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mob.analysdk.AnalySDK;
import com.mob.analysdk.demo.util.CommonUtils;

import java.util.HashMap;

public class VideosDetailActivity extends Activity implements View.OnClickListener{
	private ImageView ivBack;
	private TextView tvTitle;
	private WebView wvPlayVideo;
	private View videoSug01;
	private View videoSug02;
	private View videoSug03;
	private View videoSug04;

	private int videoIcon;//用于视频分享时的图片
	private int videoID;
	private String mobID;
	private HashMap<Integer, String> mobIdCache; //mobID缓存

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_videos_detail);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		wvPlayVideo = (WebView) findViewById(R.id.videoView);
		videoSug01 = findViewById(R.id.rl_video_sug01);
		videoSug02 = findViewById(R.id.rl_video_sug02);
		videoSug03 = findViewById(R.id.rl_video_sug03);
		videoSug04 = findViewById(R.id.rl_video_sug04);

		ivBack.setOnClickListener(this);
		videoSug01.setOnClickListener(this);
		videoSug02.setOnClickListener(this);
		videoSug03.setOnClickListener(this);
		videoSug04.setOnClickListener(this);

		wvPlayVideo.getSettings().setJavaScriptEnabled(true); // 开启Javascript支持
		wvPlayVideo.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);// 滚动条风格，为0就是不给滚动条留空间，滚动条覆盖在网页上
		wvPlayVideo.getSettings().setLoadsImagesAutomatically(true);// 设置可以自动加载图片
		wvPlayVideo.getSettings().setAppCacheEnabled(true);// 应用可以有缓存
		wvPlayVideo.getSettings().setDomStorageEnabled(true);// 设置可以使用localStorage
		wvPlayVideo.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 优先使用缓存
		wvPlayVideo.getSettings().setAppCacheMaxSize(10 * 1024 * 1024);// 缓存最多可以有10M
		wvPlayVideo.getSettings().setAllowFileAccess(true);// 可以读取文件缓存(manifest生效)
		wvPlayVideo.getSettings().setPluginState(WebSettings.PluginState.ON);
		wvPlayVideo.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		// 加速WebView加载的方法
		wvPlayVideo.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH); // 提高渲染的优先级
		wvPlayVideo.setWebChromeClient(new WebChromeClient());
		wvPlayVideo.setBackgroundColor(0x000000);

		mobIdCache = new HashMap<Integer, String>();
		String prevName = "视频列表页";
		if (getIntent() != null) {
			videoID = getIntent().getIntExtra("position", 0);
			setVideoDetail();
			prevName = "视频详情页";
		}

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "视频详情页");
		data.put("prevName", prevName);
		AnalySDK.trackEvent("visistPage", data);
	}

	protected void onPause() {
		super.onPause();
		try {
			wvPlayVideo.getClass().getMethod("onPause").invoke(wvPlayVideo, (Object[]) null);
		} catch (Exception e) {
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_back: {
				finish();
			} break;
			case R.id.rl_video_sug01: {
				videoID = 100;
				setVideoDetail();
			} break;
			case R.id.rl_video_sug02: {
				videoID = 101;
				setVideoDetail();
			} break;
			case R.id.rl_video_sug03: {
				videoID = 102;
				setVideoDetail();
			} break;
			case R.id.rl_video_sug04: {
				videoID = 103;
				setVideoDetail();
			} break;
		}
	}

	private void setVideoDetail() {
		String videoName = null;
		String videoUrl = null;
		if (videoID < 100) {
			HashMap<String, Object> video = CommonUtils.getVideosData(this).get(videoID);
			videoName = (String) video.get("videoName");
			videoUrl = (String) video.get("videoUrl");
			videoIcon =  (Integer) video.get("videoIcon");
		} else if (videoID == 100) {
			videoIcon = R.drawable.demo_video_sug01;
			videoName = getResources().getString(R.string.video_sug01_name);
			videoUrl = getResources().getString(R.string.video_sug01_play_url);
			videoSug01.setVisibility(View.GONE);
			videoSug02.setVisibility(View.VISIBLE);
			videoSug03.setVisibility(View.VISIBLE);
			videoSug04.setVisibility(View.VISIBLE);
		} else if (videoID == 101) {
			videoIcon = R.drawable.demo_video_sug02;
			videoName = getResources().getString(R.string.video_sug02_name);
			videoUrl = getResources().getString(R.string.video_sug02_play_url);
			videoSug01.setVisibility(View.VISIBLE);
			videoSug02.setVisibility(View.GONE);
			videoSug03.setVisibility(View.VISIBLE);
			videoSug04.setVisibility(View.VISIBLE);
		} else if (videoID == 102) {
			videoIcon = R.drawable.demo_video_sug03;
			videoName = getResources().getString(R.string.video_sug03_name);
			videoUrl = getResources().getString(R.string.video_sug03_play_url);
			videoSug01.setVisibility(View.VISIBLE);
			videoSug02.setVisibility(View.VISIBLE);
			videoSug03.setVisibility(View.GONE);
			videoSug04.setVisibility(View.VISIBLE);
		} else if (videoID == 103) {
			videoIcon = R.drawable.demo_video_sug04;
			videoName = getResources().getString(R.string.video_sug04_name);
			videoUrl = getResources().getString(R.string.video_sug04_play_url);
			videoSug01.setVisibility(View.VISIBLE);
			videoSug02.setVisibility(View.VISIBLE);
			videoSug03.setVisibility(View.VISIBLE);
			videoSug04.setVisibility(View.GONE);
		}
		tvTitle.setText(videoName);
		wvPlayVideo.loadUrl(videoUrl);

		HashMap<String, Object> video = new HashMap<String, Object>();
		video.put("videoName", videoName);
		video.put("videoUrl", videoUrl);
		AnalySDK.trackEvent("news", video);
	}
}
