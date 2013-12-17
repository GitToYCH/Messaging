package com.symbol.messaging.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.symbol.messaging.DAO.AutoreDAO;
import com.symbol.messaging.adapter.AutorelistAdapter;
import com.symbol.messaging.function.ServiceManage;
import com.symbol.messaging.sms.ReItem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

public class AutoreHomeActivity extends ListActivity {
	ImageView add;
	ImageView refresh;
	CheckBox isopenall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autore_home);
		add = (ImageView) findViewById(R.id.addautorebutton);
		isopenall = (CheckBox) findViewById(R.id.isopenallautore);
		refresh = (ImageView) findViewById(R.id.autorerefresh);
		refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				refreshList();
			}
		});
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AutoreHomeActivity.this,
						Autore_addActivity.class);
				startActivity(intent);
			}
		});
		isopenall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((CheckBox)v).isChecked()) {
					ServiceManage.openAllAutoreService(AutoreHomeActivity.this);
				} else {
					ServiceManage
							.closeAllAutoreService(AutoreHomeActivity.this);
				}
				refreshList();
			}
		});
		refreshList();
		
	}

	private void refreshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		AutoreDAO siclass = new AutoreDAO(AutoreHomeActivity.this);
		ArrayList<ReItem> ri = new ArrayList<ReItem>();
		ri = siclass.find();
		for (int i = 0; i < ri.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", String.valueOf(ri.get(i).getid()));
			map.put("isopen", String.valueOf(ri.get(i).getisopen()));
			map.put("title", ri.get(i).gettitle());
			map.put("keywords", ri.get(i).getkeywords());
			map.put("start", ri.get(i).getstart());
			map.put("end", ri.get(i).getend());
			map.put("content", ri.get(i).getcontent());
			list.add(map);
		}
		AutorelistAdapter listAdapter = new AutorelistAdapter(this, list,
				R.layout.autore_list_item, new String[] { "id", "isopen",
						"title", "keywords", "start", "end", "content" },
				new int[] { R.id.isopenautore, R.id.autore_list_starttime,
						R.id.autore_list_endtime, R.id.deleteaotore,
						R.id.autore_list_keywords }, isopenall);
		setListAdapter(listAdapter);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		refreshList();
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		refreshList();
		super.onResume();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		HashMap<String, String> map = (HashMap<String, String>) l
				.getItemAtPosition(position);
		Intent intent = new Intent();
		intent.putExtra("id", String.valueOf(map.get("id")));
		intent.putExtra("title", map.get("title"));
		intent.putExtra("keywords", map.get("keywords"));
		intent.putExtra("start", map.get("start"));
		intent.putExtra("end", map.get("end"));
		intent.putExtra("content", map.get("content"));
		intent.setClass(AutoreHomeActivity.this, Autore_addActivity.class);
		startActivity(intent);
	}

}
