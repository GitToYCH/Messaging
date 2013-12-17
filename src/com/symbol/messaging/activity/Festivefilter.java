package com.symbol.messaging.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Festivefilter extends Activity
{
	private GridView mGridView = null;

	private String[] filters = new String[]
	{ "元旦", "春节", "情人节", "妇女节", "愚人节", "劳动节", "母亲节", "父亲节", "端午节", "儿童节",
			"感恩节", "中秋节", "国庆节", "重阳节", "七夕节", "教师节", "光棍节", "圣诞节" };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.festivefilter_s);

		mGridView = (GridView) findViewById(R.id.gv_filter);

		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		for (String ss : filters)
		{
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("data", ss);
			data.add(hm);
		}

		mGridView.setAdapter(new SimpleAdapter(this, data,
				R.layout.festivefilter_item, new String[]
				{ "data" }, new int[]
				{ R.id.tv_filter }));
		mGridView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int index,
					long id)
			{
				// TODO Auto-generated method stub
				String fi=((TextView)view.findViewById(R.id.tv_filter)).getText().toString();
				startAct(fi);
			}
		});
	}

	private void startAct(String filter)
	{
		System.out.println(filter);
		Intent intent = new Intent();
		if (getIntent().getBooleanExtra("isfinish", false))
		{
			intent.putExtra("isfinish", true);
		}
		intent.putExtra("title", filter);
		intent.putExtra("by", filter);
		intent.setClass(Festivefilter.this, SmslistActivity.class);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		try
		{
			if (resultCode == RESULT_OK
					&& getIntent().getBooleanExtra("isfinish", false))
			{
				setResult(RESULT_OK, data);
				finish();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
