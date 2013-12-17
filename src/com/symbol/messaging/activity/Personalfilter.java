package com.symbol.messaging.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Personalfilter extends Activity{

	Button jiehun=null;
	Button shengri=null;
	Button shengzi=null;
	Button gaokao=null;
	Button ganxie=null;
	Button daoqian=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalfilter);
		jiehun=(Button)findViewById(R.id.jiehun);
		shengri=(Button)findViewById(R.id.shengri);
		gaokao=(Button)findViewById(R.id.gaokao);
		ganxie=(Button)findViewById(R.id.ganxie);
		shengzi=(Button)findViewById(R.id.shengzi);
		daoqian=(Button)findViewById(R.id.daoqian);
		
		jiehun.setOnClickListener(new jiehunListener());
		shengri.setOnClickListener(new shengriListener());
		gaokao.setOnClickListener(new gaokaoListener());
		ganxie.setOnClickListener(new ganxieListener());
		shengzi.setOnClickListener(new shengziListener());
		daoqian.setOnClickListener(new daoqianListener());
	}
	private void startAct(String filter){
		Intent intent=new Intent();
		if (getIntent().getBooleanExtra("isfinish", false)) {
			intent.putExtra("isfinish", true);
		}
		intent.putExtra("title", filter);
		intent.putExtra("by", filter);
		intent.setClass(Personalfilter.this, SmslistActivity.class);
		startActivityForResult(intent,1);		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			if (resultCode == RESULT_OK
					&& getIntent().getBooleanExtra("isfinish", false)) {
				setResult(RESULT_OK, data);
				finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	class jiehunListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("结婚");
		}
	}
	class shengriListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("生日");
		}
	}
	class shengziListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("生子");
		}
	}
	class gaokaoListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("高考");
		}
	}
	class ganxieListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("感谢");
		}
	}
	class daoqianListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("道歉");
		}
	}
}
