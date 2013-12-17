package com.symbol.messaging.activity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingActivity extends PreferenceActivity {
	OnSharedPreferenceChangeListener listener;
	SharedPreferences sp;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting);
		sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		listener=new OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(
					SharedPreferences sharedPreferences, String key) {
				// TODO Auto-generated method stub
				if (key.equals("intervallist")) {
					String interval = sharedPreferences.getString("intervallist", "60");
					if (interval.equals("0")||interval.equals("30")) {
						new AlertDialog.Builder(SettingActivity.this)    
						                .setTitle("注意！！！")  
						                .setMessage("回复间隔设置过小可能导致与对方进行无限回复")  
						                .setPositiveButton("确定", null)  
						                .show();  
					}
				}
			}
		};

		sp.registerOnSharedPreferenceChangeListener(listener);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sp.unregisterOnSharedPreferenceChangeListener(listener);
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		sp.registerOnSharedPreferenceChangeListener(listener);
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		sp.registerOnSharedPreferenceChangeListener(listener);
		super.onResume();
	}
	
}
