package com.symbol.messaging.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.symbol.messaging.DAO.SmsContentDAO;
import com.symbol.messaging.adapter.SmslistAdapter;
import com.symbol.messaging.sms.Sms;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SmslistActivity extends ListActivity {

	TextView title = null;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smslist);

		intent = getIntent();
		
		title = (TextView) findViewById(R.id.title);
		title.setText(intent.getStringExtra("title"));
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		SmsContentDAO smscontent = new SmsContentDAO(SmslistActivity.this);
		ArrayList<Sms> sms = new ArrayList<Sms>();
		String by = intent.getStringExtra("by");
		if (by.equals("collect")) {
			int collect = 1;
			sms = smscontent.find(collect);
		} else
			sms = smscontent.find(by);
		for (int i = 0; i < sms.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("text", sms.get(i).getsmstext());
			map.put("id", String.valueOf(sms.get(i).getid()));
			map.put("classify", sms.get(i).getclassify());
			map.put("collect", String.valueOf(sms.get(i).getcollect()));
			map.put("title", by);
			list.add(map);
		}

		SmslistAdapter listItemAdapter = new SmslistAdapter(this, list,
				R.layout.liststyle, new String[] { "text", "id", "classify",
						"collect", "title" }, new int[] { R.id.send,
						R.id.collect, R.id.copy, R.id.smscontent,
						R.id.smslength });
		setListAdapter(listItemAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (intent.getBooleanExtra("isfinish", false)) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map=(HashMap<String, String>)l.getItemAtPosition(position);
			String str = map.get("text");
			Intent in = new Intent();
			in.putExtra("text", str);
			setResult(RESULT_OK, in);
			finish();
		}
	}
}
