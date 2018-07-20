package com.mob.analysdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.mob.analysdk.AnalySDK;
import com.mob.analysdk.demo.util.CommonUtils;

import java.util.HashMap;

public class NewsDetailActivity extends Activity implements View.OnClickListener{
	private ImageView ivBack;
	private View llNewsDetail ;
	private TextView tvNewsTime;
	private TextView tvNewsTitle;
	private TextView tvNewsDetail;
	private View newsSug01;
	private View newsSug02;
	private View newsSug03;
	private int newsID;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_detail);

		ivBack = (ImageView) findViewById(R.id.iv_back);
		llNewsDetail = findViewById(R.id.ll_news_detail);
		tvNewsTitle = (TextView) llNewsDetail.findViewById(R.id.tv_news_title);
		tvNewsDetail = (TextView) llNewsDetail.findViewById(R.id.tv_news_detail);
		tvNewsTime = (TextView) llNewsDetail.findViewById(R.id.tv_news_time);
		newsSug01 = findViewById(R.id.rl_news_sug01);
		newsSug02 = findViewById(R.id.rl_news_sug02);
		newsSug03 = findViewById(R.id.rl_news_sug03);

		ivBack.setOnClickListener(this);
		newsSug01.setOnClickListener(this);
		newsSug02.setOnClickListener(this);
		newsSug03.setOnClickListener(this);

		String prevName = "新闻列表页";
		if (getIntent() != null) {
			newsID = getIntent().getIntExtra("position", newsID);
			setNewsDetail();
			prevName = "新闻详情页";
		}

		//统计页面访问事件
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "新闻详情页");
		data.put("prevName", prevName);
		AnalySDK.trackEvent("visistPage", data);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_back: {
				finish();
			} break;
			case R.id.rl_news_sug01: {
				newsID = 100;
				setNewsDetail();
			} break;
			case R.id.rl_news_sug02: {
				newsID = 101;
				setNewsDetail();
			} break;
			case R.id.rl_news_sug03: {
				newsID = 102;
				setNewsDetail();
			} break;
			default:{
				finish();
			}break;
		}
	}

	private void setNewsDetail() {
		String title = null;
		String detail = null;
		String time = null;
		if (newsID < 100) {
			HashMap<String, Object> news =  CommonUtils.getNewsData(this).get(newsID);
			title = (String) news.get("title");
			detail = (String) news.get("detail");
			time = (String) news.get("time");
		} else if (newsID == 100) {
			title = getResources().getString(R.string.news_sug01_title);
			detail = getResources().getString(R.string.news_sug01_detail);
			time = getResources().getString(R.string.news_sug01_time);
			newsSug01.setVisibility(View.GONE);
			newsSug02.setVisibility(View.VISIBLE);
			newsSug03.setVisibility(View.VISIBLE);
		} else if (newsID == 101) {
			title = getResources().getString(R.string.news_sug02_title);
			detail = getResources().getString(R.string.news_sug02_detail);
			time = getResources().getString(R.string.news_sug02_time);
			newsSug01.setVisibility(View.VISIBLE);
			newsSug02.setVisibility(View.GONE);
			newsSug03.setVisibility(View.VISIBLE);
		} else if (newsID == 102) {
			title = getResources().getString(R.string.news_sug03_title);
			detail = getResources().getString(R.string.news_sug03_detail);
			time = getResources().getString(R.string.news_sug03_time);
			newsSug01.setVisibility(View.VISIBLE);
			newsSug02.setVisibility(View.VISIBLE);
			newsSug03.setVisibility(View.GONE);
		}
		tvNewsTitle.setText(title);
		tvNewsDetail.setText(detail);
		tvNewsTime.setText(time);
		HashMap<String, Object> news = new HashMap<String, Object>();
		news.put("title", title);
		news.put("detail", detail);
		AnalySDK.trackEvent("news", news);
	}

}
