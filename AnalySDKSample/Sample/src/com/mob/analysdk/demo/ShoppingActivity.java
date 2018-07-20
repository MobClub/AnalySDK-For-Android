package com.mob.analysdk.demo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mob.analysdk.AnalySDK;
import com.mob.analysdk.demo.util.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingActivity extends Activity implements View.OnClickListener{
	private Uri data;
	private Dialog dialog;
	private ImageView ivBack;
	private GridView gvGoodsList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_shopping);

		ivBack = (ImageView) findViewById(R.id.iv_back);
		gvGoodsList = (GridView) findViewById(R.id.gv_shopping_list);
		ivBack.setOnClickListener(this);

		MyAdapter adapter = new MyAdapter(this);
		adapter.setGoodsData(CommonUtils.getGoodsData(this));
		gvGoodsList.setAdapter(adapter);
		gvGoodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ShoppingActivity.this, ShoppingDetailActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
		});

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "购物列表页");
		data.put("prevName", "主界面");
		AnalySDK.trackEvent("visistPage", data);
	}

	public void onClick(View v) {
		if (v == ivBack) {
			finish();
		}
	}

	private class MyAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;
		private ArrayList<HashMap<String, Object>> data;
		public MyAdapter(Context c) {
			this.context = c;
			inflater = LayoutInflater.from(c);
		}

		public void setGoodsData(ArrayList<HashMap<String, Object>> data) {
			this.data = data;
		}

		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			if (data != null) {
				data.get(position);
			}
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.layout_shopping_item, null);
				holder.ivGoodsIcon = (ImageView) convertView.findViewById(R.id.iv_goods_icon);
				holder.tvGoodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
				holder.tvGoodsPrice = (TextView) convertView.findViewById(R.id.tv_goods_price);
				holder.tvGoodsSales = (TextView) convertView.findViewById(R.id.tv_goods_sales);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.ivGoodsIcon.setImageResource((Integer) data.get(position).get("goodsIcon"));
			holder.tvGoodsName.setText((String) data.get(position).get("goodsName"));
			holder.tvGoodsPrice.setText((String) data.get(position).get("goodsPrice"));
			holder.tvGoodsSales.setText((String) data.get(position).get("goodsSales"));

			return convertView;
		}

		private class ViewHolder {
			public ImageView ivGoodsIcon;
			public TextView tvGoodsName;
			public TextView tvGoodsPrice;
			public TextView tvGoodsSales;
		}

	}

}
