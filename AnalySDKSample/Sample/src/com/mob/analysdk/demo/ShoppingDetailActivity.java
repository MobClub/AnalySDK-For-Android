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

public class ShoppingDetailActivity extends Activity implements View.OnClickListener {
	private ImageView ivBack;
	private TextView tvTitle;
	private ImageView ivGoodsIcon;
	private TextView tvGoodsDetail;
	private TextView tvGoodsPrice;
	private View goodsSug01;
	private View goodsSug02;
	private View goodsSug03;

	private int goodsID;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shopping_detail);

		ivBack = (ImageView) findViewById(R.id.iv_back);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		ivGoodsIcon = (ImageView) findViewById(R.id.iv_goods_icon);
		tvGoodsDetail = (TextView) findViewById(R.id.tv_goods_detail);
		tvGoodsPrice = (TextView) findViewById(R.id.tv_goods_price);
		goodsSug01 = findViewById(R.id.rl_goods_sug01);
		goodsSug02 = findViewById(R.id.rl_goods_sug02);
		goodsSug03 = findViewById(R.id.rl_goods_sug03);

		ivBack.setOnClickListener(this);
		goodsSug01.setOnClickListener(this);
		goodsSug02.setOnClickListener(this);
		goodsSug03.setOnClickListener(this);

		String prevName = "购物列表页";
		if (getIntent() != null) {
			goodsID = getIntent().getIntExtra("position", goodsID);
			setGoodsDetail();
			prevName = "购物详情页";
		}

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "购物详情页");
		data.put("prevName", prevName);
		AnalySDK.trackEvent("visistPage", data);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_back: {
				finish();
			} break;
			case R.id.rl_goods_sug01: {
				goodsID = 100;
				setGoodsDetail();
			} break;
			case R.id.rl_goods_sug02: {
				goodsID = 101;
				setGoodsDetail();
			} break;
			case R.id.rl_goods_sug03: {
				goodsID = 102;
				setGoodsDetail();
			} break;
		}
	}

	private void setGoodsDetail() {
		String goodsName = null;
		String goodsPrice = null;
		Integer goodsBigIcon = null;
		if (goodsID < 100) {
			HashMap<String, Object> goods = CommonUtils.getGoodsData(this).get(goodsID);
			goodsName = (String) goods.get("goodsName");
			goodsPrice = (String) goods.get("goodsPrice");
			goodsBigIcon = (Integer) goods.get("goodsBigIcon");
			goodsSug01.setVisibility(View.VISIBLE);
			goodsSug02.setVisibility(View.VISIBLE);
		} else if (goodsID == 100) {
			goodsName = getResources().getString(R.string.goods_sug01_name);
			goodsPrice = getResources().getString(R.string.goods_sug01_price);
			goodsBigIcon = R.drawable.demo_dsxg01_big;
			goodsSug01.setVisibility(View.GONE);
			goodsSug02.setVisibility(View.VISIBLE);
			goodsSug03.setVisibility(View.VISIBLE);
		} else if (goodsID == 101) {
			goodsName = getResources().getString(R.string.goods_sug02_name);
			goodsPrice = getResources().getString(R.string.goods_sug02_price);
			goodsBigIcon = R.drawable.demo_dsxg02_big;
			goodsSug01.setVisibility(View.VISIBLE);
			goodsSug02.setVisibility(View.GONE);
			goodsSug03.setVisibility(View.VISIBLE);
		} else if (goodsID == 102) {
			goodsName = getResources().getString(R.string.goods_sug03_name);
			goodsPrice = getResources().getString(R.string.goods_sug03_price);
			goodsBigIcon = R.drawable.demo_dsxg03_big;
			goodsSug01.setVisibility(View.VISIBLE);
			goodsSug02.setVisibility(View.VISIBLE);
			goodsSug03.setVisibility(View.GONE);
		}
		tvGoodsDetail.setText(goodsName);
		tvGoodsPrice.setText(goodsPrice);
		ivGoodsIcon.setImageResource(goodsBigIcon);

		HashMap<String, Object> goods = new HashMap<String, Object>();
		goods.put("goodsName", goodsName);
		goods.put("goodsPrice", Integer.valueOf(goodsPrice.substring(1)));
		AnalySDK.trackEvent("shopping", goods);
	}

}
