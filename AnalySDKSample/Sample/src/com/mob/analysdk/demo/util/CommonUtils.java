package com.mob.analysdk.demo.util;

import android.content.Context;

import com.mob.analysdk.demo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonUtils {
	/**
	 * 获取新闻列表数据
	 * @param context
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getNewsData(Context context) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		int[] newsImg = {R.drawable.demo_news01, R.drawable.demo_news02, R.drawable.demo_news03,
				R.drawable.demo_news04, R.drawable.demo_news05, R.drawable.demo_news06, R.drawable.demo_news07};
		String[] newsTitle = context.getResources().getStringArray(R.array.news_titles);
		String[] newsCommments = context.getResources().getStringArray(R.array.news_commments);
		String[] newsDetail = context.getResources().getStringArray(R.array.news_details);
		String[] newsTime = context.getResources().getStringArray(R.array.news_time);
		for (int i = 0; i < newsImg.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("img", newsImg[i]);
			item.put("title", newsTitle[i]);
			item.put("comm", newsCommments[i]);
			if (i == 0) {
				item.put("top", true);
				item.put("opic", true);
			} else {
				item.put("top", false);
				item.put("opic", false);
			}
			item.put("time", newsTime[i]);
			item.put("detail", newsDetail[i]);
			list.add(item);
		}
		return list;
	}

	/**
	 * 获取视频列表数据
	 * @param context
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getVideosData(Context context) {
		int[] videoIcon = {R.drawable.demo_video01, R.drawable.demo_video02, R.drawable.demo_video03,
				R.drawable.demo_video04, R.drawable.demo_video05, R.drawable.demo_video06,
				R.drawable.demo_video07, R.drawable.demo_video08, R.drawable.demo_video09,};
		String[] videoName = context.getResources().getStringArray(R.array.video_name);
		String[] videoUrl = context.getResources().getStringArray(R.array.video_url);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < videoIcon.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("videoIcon", videoIcon[i]);
			item.put("videoName", videoName[i]);
			item.put("videoUrl", videoUrl[i]);
			list.add(item);
		}
		return  list;
	}

	/**
	 * 获取商品列表数据
	 * @param context
	 * @return
	 */
	public static ArrayList<HashMap<String, Object>> getGoodsData(Context context) {
		int[] goodsIcon = {R.drawable.demo_ds01, R.drawable.demo_ds02, R.drawable.demo_ds03,
				R.drawable.demo_ds04, R.drawable.demo_ds05, R.drawable.demo_ds06,
				R.drawable.demo_ds07, R.drawable.demo_ds08, R.drawable.demo_ds09,};
		int[] goodsBigIcon = {R.drawable.demo_ds01_big, R.drawable.demo_ds02_big, R.drawable.demo_ds03_big,
				R.drawable.demo_ds04_big, R.drawable.demo_ds05_big, R.drawable.demo_ds06_big,
				R.drawable.demo_ds07_big, R.drawable.demo_ds08_big, R.drawable.demo_ds09_big,};
		String[] goodsName = context.getResources().getStringArray(R.array.goods_name);
		String[] goodsPrice = context.getResources().getStringArray(R.array.goods_price);
		String[] goodsSales = context.getResources().getStringArray(R.array.goods_sales);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < goodsIcon.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("goodsIcon", goodsIcon[i]);
			item.put("goodsBigIcon", goodsBigIcon[i]);
			item.put("goodsName", goodsName[i]);
			item.put("goodsPrice", goodsPrice[i]);
			item.put("goodsSales", goodsSales[i]);
			list.add(item);
		}
		return  list;
	}

}
