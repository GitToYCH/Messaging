package com.symbol.messaging.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.symbol.messaging.DAO.IgnorelistDAO;
import com.symbol.messaging.adapter.IgnorelistAdapter;
import com.symbol.messaging.sms.IgnoreItem;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class IgnorelistActivity extends ListActivity {

	ImageView goback;
	ImageView add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ignorelist);
		goback = (ImageView) findViewById(R.id.ignore_goback);
		add = (ImageView) findViewById(R.id.ignore_add);
		add.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(IgnorelistActivity.this, Ignore_addActivity.class);
				startActivity(intent);
			}			
		});
		goback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				IgnorelistActivity.this.finish();
			}
		});
		refreshList();
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
	protected void onPause() {
		// TODO Auto-generated method stub
		refreshList();
		super.onPause();
	}
	private void refreshList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		IgnorelistDAO iiclass = new IgnorelistDAO(this);
		ArrayList<IgnoreItem> ii = new ArrayList<IgnoreItem>();
		ii = iiclass.find();
		for (int i = 0; i < ii.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", String.valueOf(ii.get(i).getid()));
			map.put("name", ii.get(i).getname());
			map.put("phone", ii.get(i).getphone());
			list.add(map);
		}
		IgnorelistAdapter listadpter = new IgnorelistAdapter(this, list,
				R.layout.ignorelist_item,
				new String[] { "id", "name", "phone" },
				new int[] { R.id.ignore_name, R.id.ignore_phone,
						R.id.ignore_delete });
		setListAdapter(listadpter);
	}
}
