package com.symbol.messaging.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.symbol.messaging.DAO.SmsContentDAO;
import com.symbol.messaging.activity.R;
import com.symbol.messaging.sms.Sms;

import android.text.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SmslistAdapter extends BaseAdapter {
	// 用于存储控件数据的类
	private class buttonViewHolder {
		ImageButton send;
		ImageButton collect;
		ImageButton copy;
		TextView smscontent;
		TextView smslength;
	}

	private ArrayList<HashMap<String, String>> mAppList;
	private LayoutInflater mInflater;
	private Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private buttonViewHolder holder;
	private String title;

	public SmslistAdapter(Context c,
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

	public void collectChanged(int position, Sms sms) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("text", sms.getsmstext());
		map.put("id", String.valueOf(sms.getid()));
		map.put("classify", sms.getclassify());
		map.put("collect", String.valueOf(sms.getcollect()));
		map.put("title", title);
		mAppList.set(position, map);
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			holder = (buttonViewHolder) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.liststyle, null);
			holder = new buttonViewHolder();
			holder.send = (ImageButton) convertView
					.findViewById(valueViewID[0]);
			holder.collect = (ImageButton) convertView
					.findViewById(valueViewID[1]);
			holder.copy = (ImageButton) convertView
					.findViewById(valueViewID[2]);
			holder.smscontent = (TextView) convertView
					.findViewById(valueViewID[3]);
			holder.smslength = (TextView) convertView
					.findViewById(valueViewID[4]);
			convertView.setTag(holder);
		}

		HashMap<String, String> appInfo = mAppList.get(position);
		if (appInfo != null) {
			title = (String) appInfo.get(keyString[4]);
			String smsc = (String) appInfo.get(keyString[0]);
			int id = Integer.parseInt(appInfo.get(keyString[1]));
			String classify = (String) appInfo.get(keyString[2]);
			int collect = Integer.parseInt(appInfo.get(keyString[3]));
			Sms sms = new Sms(id, classify, collect, smsc);
			holder.smscontent.setText(smsc);
			holder.smslength.setText("此条短信" + smsc.length() + "字,普通短信70字，分"
					+ ((smsc.length() - 1) / 70 + 1) + "条发送");
			if (collect == 1) {
				holder.collect.setBackgroundResource(R.drawable.collecttrue);
			} else {
				holder.collect.setBackgroundResource(R.drawable.collectfalse);
			}
			holder.send.setOnClickListener(new sendListener(smsc));
			holder.copy.setOnClickListener(new copyListener(smsc));
			holder.collect
					.setOnClickListener(new collectListener(sms, position));
		}
		return convertView;
	}

	class sendListener implements OnClickListener {
		private String smsc;

		public sendListener(String smsc) {
			this.smsc = smsc;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Uri smsToUri = Uri.parse("smsto:");
			Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
			intent.putExtra("sms_body", smsc);
			mContext.startActivity(intent);
		}
	}

	class copyListener implements OnClickListener {

		private String smsc;

		public copyListener(String smsc) {
			this.smsc = smsc;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ClipboardManager cmb = (ClipboardManager) mContext
					.getSystemService(Context.CLIPBOARD_SERVICE);
			cmb.setText(smsc);
			Toast.makeText(mContext, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
		}
	}

	class collectListener implements OnClickListener {
		private Sms sms;
		private int position;

		public collectListener(Sms sms, int position) {
			this.sms = sms;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			SmsContentDAO col = new SmsContentDAO(mContext);
			if (sms.getcollect() == 1) {
				sms.setcollect(0);
				col.update(sms);
				if (!title.equals("collect")) {
					collectChanged(position, sms);
				}
				Toast.makeText(mContext, "已取消收藏！", Toast.LENGTH_SHORT).show();
				if (title.equals("collect")) {
					removeItem(position);
				}
			} else if (sms.getcollect() == 0) {
				sms.setcollect(1);
				col.update(sms);
				collectChanged(position, sms);
				Toast.makeText(mContext, "收藏成功！", Toast.LENGTH_SHORT).show();
			} else
				Toast.makeText(mContext, "收藏失败！", Toast.LENGTH_SHORT).show();
		}
	}
}
