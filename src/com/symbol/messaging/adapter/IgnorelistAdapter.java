package com.symbol.messaging.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.symbol.messaging.DAO.IgnorelistDAO;
import com.symbol.messaging.activity.R;
import com.symbol.messaging.sms.IgnoreItem;

public class IgnorelistAdapter extends BaseAdapter {
	// 用于存储控件数据的类
	private class buttonViewHolder {
		TextView name;
		TextView phone;
		ImageView delete;
	}

	private ArrayList<HashMap<String, String>> mAppList;
	private LayoutInflater mInflater;
	Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	CheckBox isopenall;

	public IgnorelistAdapter(Context c,
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
			convertView = mInflater.inflate(R.layout.ignorelist_item, null);
			holder = new buttonViewHolder();
			holder.name = (TextView) convertView.findViewById(valueViewID[0]);
			holder.phone = (TextView) convertView
					.findViewById(valueViewID[1]);
			holder.delete=(ImageView)convertView.findViewById(valueViewID[2]);
			convertView.setTag(holder);
		}

		HashMap<String, String> appInfo = mAppList.get(position);
		if (appInfo != null) {
			int id=Integer.parseInt(appInfo.get(keyString[0]));
			String name=appInfo.get(keyString[1]);
			String phone=appInfo.get(keyString[2]);
			holder.name.setText(name);
			holder.phone.setText(phone);
			IgnoreItem ii=new IgnoreItem(id,name,phone);
			holder.delete.setOnClickListener(new deleteListener(ii, position));
		}
		return convertView;
	}
	class deleteListener implements OnClickListener {
		IgnoreItem ii;
		int position;

		public deleteListener(IgnoreItem ii, int position) {
			this.ii = ii;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new AlertDialog.Builder(mContext)
					.setTitle("确认")
					.setMessage("确定删除该号码吗？")
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									IgnorelistDAO ad = new IgnorelistDAO(mContext);
									ad.delete(ii);
									removeItem(position);
								}
							}).setNegativeButton("否", null).show();

		}

	}
}
