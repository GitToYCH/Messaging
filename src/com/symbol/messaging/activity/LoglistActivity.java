package com.symbol.messaging.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.symbol.messaging.DAO.LogDAO;
import com.symbol.messaging.adapter.LoglistAdapter;
import com.symbol.messaging.sms.LogItem;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class LoglistActivity extends ListActivity {

	private ImageView clear;
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		HashMap<String, String> map = (HashMap<String, String>) l
				.getItemAtPosition(position);
		Intent intent = new Intent();
		intent.putExtra("time", map.get("time"));
		intent.putExtra("operation", map.get("operation"));
		intent.putExtra("phone", map.get("phone"));
		intent.putExtra("content", map.get("content"));
		intent.setClass(LoglistActivity.this, LogmoreActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loglist);
		clear=(ImageView)findViewById(R.id.loglist_clear);
		clear.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(LoglistActivity.this).setTitle("确认").setMessage("确定清空日志记录吗？")
				.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						LogDAO ld = new LogDAO(LoglistActivity.this);
						ld.clean();
						refreshList();
					}
				}).setNegativeButton("否", null).show();
			}});
		refreshList();
	}

	private void refreshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		LogDAO ld = new LogDAO(LoglistActivity.this);
		ArrayList<LogItem> log = new ArrayList<LogItem>();
		log = ld.find();
		if (log.size() == 0) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("time", "没有日志记录");
			list.add(map);
		} else
			for (int i = 0; i < log.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", String.valueOf(log.get(i).getid()));
				map.put("time", log.get(i).gettime());
				map.put("operation", log.get(i).getoperation());
				map.put("phone", log.get(i).getphone());
				map.put("content", log.get(i).getcontent());
				list.add(map);
			}
		LoglistAdapter listAdapter = new LoglistAdapter(this, list,
				R.layout.log_list_item, new String[] { "time", "operation", },
				new int[] { R.id.log_time, R.id.log_operation });
		setListAdapter(listAdapter);
	}

}
