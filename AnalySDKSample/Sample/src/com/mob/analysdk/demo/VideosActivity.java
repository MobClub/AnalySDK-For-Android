package com.mob.analysdk.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class VideosActivity extends Activity implements View.OnClickListener{
	private ImageView ivBack;
	private GridView gvVideosList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_videos);

		ivBack = (ImageView) findViewById(R.id.iv_back);
		gvVideosList = (GridView) findViewById(R.id.gv_video_list);
		ivBack.setOnClickListener(this);

		MyAdapter adapter = new MyAdapter(this);
		adapter.setVideoData(CommonUtils.getVideosData(this));
		gvVideosList.setAdapter(adapter);
		gvVideosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(VideosActivity.this, VideosDetailActivity.class);
				i.putExtra("position", position);
				startActivity(i);
			}
		});

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "视频列表页");
		data.put("prevName", "主界面");
		AnalySDK.trackEvent("visistPage", data);
	}

	public void onClick(View v) {
		if(v == ivBack) {
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

		public void setVideoData(ArrayList<HashMap<String, Object>> data) {
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
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.layout_videos_item, null);
				holder.ivVideoIcon = (ImageView) convertView.findViewById(R.id.iv_video_icon);
				holder.tvVideoName = (TextView) convertView.findViewById(R.id.tv_video_name);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.ivVideoIcon.setImageResource((Integer) data.get(position).get("videoIcon"));
			holder.tvVideoName.setText((String) data.get(position).get("videoName"));

			return convertView;
		}

		private class ViewHolder {
			public ImageView ivVideoIcon;
			public TextView tvVideoName;
		}

	}

}
