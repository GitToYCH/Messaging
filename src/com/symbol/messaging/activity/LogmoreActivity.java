package com.symbol.messaging.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LogmoreActivity extends Activity{
	TextView time;
	TextView operation;
	TextView phone;
	TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_more);
		
		time=(TextView)findViewById(R.id.log_more_time);
		operation=(TextView)findViewById(R.id.log_more_operation);
		phone=(TextView)findViewById(R.id.log_more_phone);
		content=(TextView)findViewById(R.id.log_more_content);
		Intent intent=getIntent();
		
		time.setText(intent.getStringExtra("time"));
		operation.setText(intent.getStringExtra("operation"));
		phone.setText(intent.getStringExtra("phone"));
		content.setText(intent.getStringExtra("content"));
	}

}
