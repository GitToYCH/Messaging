package com.symbol.messaging.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.symbol.messaging.DAO.AutosendDAO;
import com.symbol.messaging.adapter.AutosendlistAdapter;
import com.symbol.messaging.activity.R;
import com.symbol.messaging.function.ServiceManage;
import com.symbol.messaging.sms.SendItem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

public class AutosendHomeActivity extends ListActivity {

	ImageView add;
	ImageView refresh;
	CheckBox isopenall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autosend_home);
		add = (ImageView) findViewById(R.id.addautosendbutton);
		isopenall = (CheckBox) findViewById(R.id.isopenallautosend);
		refresh=(ImageView)findViewById(R.id.autosendrefresh);
		refresh.setOnClickListener(new OnClickListener(){
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
				intent.setClass(AutosendHomeActivity.this,
						Autosend_addActivity.class);
				startActivity(intent);
			}
		});
		isopenall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (((CheckBox)v).isChecked()) {
					ServiceManage
							.openAllAutosendService(AutosendHomeActivity.this);
				} else {
					ServiceManage
							.closeAllAutosendService(AutosendHomeActivity.this);
				}
				refreshList();
			}

		});
		refreshList();
	}

	private void refreshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		AutosendDAO siclass = new AutosendDAO(AutosendHomeActivity.this);
		ArrayList<SendItem> si = new ArrayList<SendItem>();
		si = siclass.find();
		for (int i = 0; i < si.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", String.valueOf(si.get(i).getid()));
			map.put("isopen", String.valueOf(si.get(i).getisopen()));
			map.put("title", si.get(i).gettitle());
			map.put("name", si.get(i).getname());
			map.put("phone", si.get(i).getphone());
			map.put("date", si.get(i).getdate());
			map.put("time", si.get(i).gettime());
			map.put("content", si.get(i).getcontent());
			list.add(map);
		}
		AutosendlistAdapter listAdapter = new AutosendlistAdapter(this, list,
				R.layout.autosend_list_item, new String[] { "id", "isopen","title",
						"name", "phone", "date", "time", "content" },
				new int[] { R.id.isopenautosend, R.id.autosend_list_title,
						R.id.autosend_list_datetime, R.id.deleteautosendbutton },isopenall);
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
		intent.putExtra("name", map.get("name"));
		intent.putExtra("phone", map.get("phone"));
		intent.putExtra("date", map.get("date"));
		intent.putExtra("time", map.get("time"));
		intent.putExtra("content", map.get("content"));
		intent.setClass(AutosendHomeActivity.this, Autosend_addActivity.class);
		startActivity(intent);
	}

}
