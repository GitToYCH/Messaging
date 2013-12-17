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

import com.symbol.messaging.DAO.AutosendDAO;
import com.symbol.messaging.activity.R;
import com.symbol.messaging.function.ServiceManage;
import com.symbol.messaging.sms.SendItem;

public class AutosendlistAdapter extends BaseAdapter {
	// 用于存储控件数据的类
	private class buttonViewHolder {
		CheckBox isopen;
		ImageView delete;
		TextView datetime;
		TextView title;
	}

	private ArrayList<HashMap<String, String>> mAppList;
	private LayoutInflater mInflater;
	Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	CheckBox isopenall;

	public AutosendlistAdapter(Context c,
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
			convertView = mInflater.inflate(R.layout.autosend_list_item, null);
			holder = new buttonViewHolder();
			holder.isopen = (CheckBox) convertView.findViewById(valueViewID[0]);
			holder.title = (TextView) convertView.findViewById(valueViewID[1]);
			holder.datetime = (TextView) convertView
					.findViewById(valueViewID[2]);
			holder.delete = (ImageView) convertView.findViewById(valueViewID[3]);
			convertView.setTag(holder);
		}

		HashMap<String, String> appInfo = mAppList.get(position);
		if (appInfo != null) {
			int id = Integer.parseInt(appInfo.get(keyString[0]));
			int isopen = Integer.parseInt(appInfo.get(keyString[1]));
			String title = appInfo.get(keyString[2]);
			String name = appInfo.get(keyString[3]);
			String phone = appInfo.get(keyString[4]);
			String date = appInfo.get(keyString[5]);
			String time = appInfo.get(keyString[6]);
			String content = appInfo.get(keyString[7]);
			SendItem si = new SendItem(id, isopen, title, name, phone, date,
					time, content);
			if (isopen == 1)
				holder.isopen.setChecked(true);
			else if (isopen == 0)
				holder.isopen.setChecked(false);
			holder.title.setText(title);
			holder.datetime.setText(date + " " + time);
			holder.isopen.setOnClickListener(new isopenListener(si));
			holder.delete.setOnClickListener(new deleteListener(si, position));
		}
		return convertView;
	}

	class isopenListener implements OnClickListener {

		private SendItem si;

		public isopenListener(SendItem si) {
			this.si = si;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (((CheckBox) v).isChecked())
				ServiceManage.openAutosendService(mContext, si);
			else {
				ServiceManage.closeAutosendService(mContext, si);
				isopenall.setChecked(false);
			}
		}
	}

	class deleteListener implements OnClickListener {
		SendItem si;
		int position;

		public deleteListener(SendItem si, int position) {
			this.si = si;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new AlertDialog.Builder(mContext)
					.setTitle("确认删除“"+si.gettitle()+"”")
					.setMessage("确定删除该计划吗？")
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									AutosendDAO ad = new AutosendDAO(mContext);
									ad.delete(si);
									ServiceManage.closeAutosendService(
											mContext, si);
									removeItem(position);
								}
							}).setNegativeButton("否", null).show();

		}

	}
}
