package com.mob.analysdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;

import com.mob.analysdk.AnalySDK;
import com.mob.analysdk.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends Activity implements View.OnClickListener {
	private View llNews;
	private View llVideos;
	private View llShopping;
	private View llGame;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		//页面统计
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "主界面");
		AnalySDK.trackEvent("visistPage", data);

		llGame = findViewById(R.id.ll_game);
		llNews = findViewById(R.id.ll_news);
		llVideos = findViewById(R.id.ll_videos);
		llShopping = findViewById(R.id.ll_shopping);
		llGame.setOnClickListener(this);
		llNews.setOnClickListener(this);
		llVideos.setOnClickListener(this);
		llShopping.setOnClickListener(this);

		AnalySDK.setLocation(116.383572, 39.926224);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_game:{
				HashMap<String,Object> data = new HashMap<String, Object>();
				data.put("buttonName","games");
				AnalySDK.trackEvent("click",data);
				Intent intent = new Intent(MainActivity.this, GameActivity.class);
				startActivity(intent);
			} break;
			case R.id.ll_news:{
				//打开新闻列表,统计点击事件
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("buttonName", "news");
				AnalySDK.trackEvent("click", data);
				Intent intent = new Intent(MainActivity.this, NewsActivity.class);
				startActivity(intent);
			} break;
			case R.id.ll_videos:{
				//打开视频列表,统计点击事件
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("buttonName", "videos");
				AnalySDK.trackEvent("click", data);
				Intent intent = new Intent(MainActivity.this, VideosActivity.class);
				startActivity(intent);
			} break;
			case R.id.ll_shopping:{
				//打开商品列表,统计点击事件
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("buttonName", "shopping");
				AnalySDK.trackEvent("click", data);
				Intent intent = new Intent(MainActivity.this, ShoppingActivity.class);
				startActivity(intent);
			} break;
		}
	}

}
