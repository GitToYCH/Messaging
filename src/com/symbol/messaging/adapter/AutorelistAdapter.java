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

import com.symbol.messaging.DAO.AutoreDAO;
import com.symbol.messaging.activity.R;
import com.symbol.messaging.function.ServiceManage;
import com.symbol.messaging.sms.ReItem;

public class AutorelistAdapter extends BaseAdapter {
	// 用于存储控件数据的类
	private class buttonViewHolder {
		CheckBox isopen;
		ImageView delete;
		TextView start;
		TextView end;
		TextView keywords;
	}

	private ArrayList<HashMap<String, String>> mAppList;
	private LayoutInflater mInflater;
	Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	CheckBox isopenall;

	public AutorelistAdapter(Context c,
			ArrayList<HashMap<String, String>> appList, int resource,
			String[] from, int[] to, CheckBox isopenall) {
		this.isopenall = isopenall;
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
			convertView = mInflater.inflate(R.layout.autore_list_item, null);
			holder = new buttonViewHolder();
			holder.isopen = (CheckBox) convertView.findViewById(valueViewID[0]);
			holder.start = (TextView) convertView.findViewById(valueViewID[1]);
			holder.end = (TextView) convertView.findViewById(valueViewID[2]);
			holder.delete = (ImageView) convertView.findViewById(valueViewID[3]);
			holder.keywords = (TextView) convertView
					.findViewById(valueViewID[4]);
			convertView.setTag(holder);
		}

		HashMap<String, String> appInfo = mAppList.get(position);
		if (appInfo != null) {
			int id = Integer.parseInt(appInfo.get(keyString[0]));
			int isopen = Integer.parseInt(appInfo.get(keyString[1]));
			String title = appInfo.get(keyString[2]);
			String keywords = appInfo.get(keyString[3]);
			String start = appInfo.get(keyString[4]);
			String end = appInfo.get(keyString[5]);
			String content = appInfo.get(keyString[6]);
			ReItem ri = new ReItem(id, isopen, title, keywords, start, end,
					content);
			if (isopen == 1)
				holder.isopen.setChecked(true);
			else if (isopen == 0)
				holder.isopen.setChecked(false);
			holder.start.setText(start);
			holder.end.setText(end);
			holder.keywords.setText("关键字：" + keywords);
			holder.isopen.setOnClickListener(new isopenListener(ri));
			holder.delete.setOnClickListener(new deleteListener(ri, position));
		}
		return convertView;
	}

	class isopenListener implements OnClickListener {

		private ReItem ri;

		public isopenListener(ReItem ri) {
			this.ri = ri;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (((CheckBox) v).isChecked())
				ServiceManage.openAutoreService(mContext, ri);
			else {
				ServiceManage.closeAutoreService(mContext, ri);
				isopenall.setChecked(false);
			}
		}
	}

	class deleteListener implements OnClickListener {
		ReItem ri;
		int position;

		public deleteListener(ReItem ri, int position) {
			this.ri = ri;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new AlertDialog.Builder(mContext)
					.setTitle("确认")
					.setMessage("确定删除该计划吗？")
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									AutoreDAO ad = new AutoreDAO(mContext);
									ad.delete(ri);
									removeItem(position);
								}
							}).setNegativeButton("否", null).show();

		}
	}
}
