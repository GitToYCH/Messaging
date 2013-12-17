package com.symbol.messaging.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MoreIndex extends ListActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		String[]content=new String[]{" 系统设置"," 发送日志"," 关于软件"};
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,content));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch(position){
		case 0:
			startActivity(new Intent(this,SettingActivity.class));	
			break;
		case 1:
			startActivity(new Intent(this,LoglistActivity.class));			
			break;
		case 2:
			startActivity(new Intent(this,AboutActivity.class));
			break;
		}
	}
}
