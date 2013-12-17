package com.symbol.messaging.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
//import android.view.KeyEvent;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.Toast;

public class MainActivity extends Activity {

	Button festive = null;
	Button collect = null;
	Button personal = null;
//	private long mExitTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		festive = (Button) findViewById(R.id.festival);
		festive.setOnClickListener(new festvieListener());
		collect = (Button) findViewById(R.id.mycollect);
		collect.setOnClickListener(new collectListener());
		personal = (Button) findViewById(R.id.personal);
		personal.setOnClickListener(new personalListener());		

		
	}

	class festvieListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			if (getIntent().getBooleanExtra("isfinish", false)) {
				intent.putExtra("isfinish", true);
			}
			intent.setClass(MainActivity.this, Festivefilter.class);
			startActivityForResult(intent, 1);
		}
	}

	class collectListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			if (getIntent().getBooleanExtra("isfinish", false)) {
				intent.putExtra("isfinish", true);
			}
			intent.putExtra("title", "Œ“ ’≤ÿµƒ");
			intent.putExtra("by", "collect");
			intent.setClass(MainActivity.this, SmslistActivity.class);
			startActivityForResult(intent, 1);
		}
	}

	class personalListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			if (getIntent().getBooleanExtra("isfinish", false)) {
				intent.putExtra("isfinish", true);
			}
			intent.setClass(MainActivity.this, Personalfilter.class);
			startActivityForResult(intent, 1);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		try {
			if (resultCode == RESULT_OK
					&& intent.getBooleanExtra("isfinish", false)) {
				setResult(RESULT_OK, data);
				finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
