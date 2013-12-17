package com.symbol.messaging.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.symbol.messaging.activity.R;

public class LoglistAdapter extends BaseAdapter {
	// 用于存储控件数据的类
	private class buttonViewHolder {
		TextView time;
		TextView operation;
	}

	private ArrayList<HashMap<String, String>> mAppList;
	private LayoutInflater mInflater;
	Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	CheckBox isopenall;

	public LoglistAdapter(Context c,
			ArrayList<HashMap<String, String>> appList, int resource,
			String[] from, int[] to) {
		mAppList = appList;
		mContext = c;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
	}

	@Override
	public int getCount() {
		return mAppList.size();
	}

	@Override
	public Object getItem(int position) {
		return mAppList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void removeItem(int position) {
		mAppList.remove(position);
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			holder = (buttonViewHolder) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.log_list_item, null);
			holder = new buttonViewHolder();
			holder.time = (TextView) convertView.findViewById(valueViewID[0]);
			holder.operation = (TextView) convertView
					.findViewById(valueViewID[1]);
			convertView.setTag(holder);
		}

		HashMap<String, String> appInfo = mAppList.get(position);
		if (appInfo != null) {
			String time=appInfo.get(keyString[0]);
			String operation=appInfo.get(keyString[1]);
			holder.time.setText(time);
			holder.operation.setText(operation);
		}
		return convertView;
	}

}
