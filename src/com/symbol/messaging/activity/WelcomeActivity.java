package com.symbol.messaging.activity;

import java.io.IOException;

import com.symbol.messaging.function.DefaultAutore;
import com.symbol.messaging.function.ServiceManage;
import com.symbol.messaging.util.DataBaseUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.welcome);

		// ����splash ����
		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(1500);
		aa.setAnimationListener(new AnimationListener() {

			public void onAnimationEnd(Animation arg0) {
				Intent intent = new Intent(WelcomeActivity.this, TabIndex.class);
				WelcomeActivity.this.startActivity(intent);
				WelcomeActivity.this.finish();
			}

			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

		});
		this.findViewById(R.id.fullscreen_content).startAnimation(aa);
		this.copyDataBaseToPhone();
	}

	private void copyDataBaseToPhone() {
		DataBaseUtil util = new DataBaseUtil(this);
		// �ж����ݿ��Ƿ����
		boolean dbExist = util.checkDataBase();

		if (dbExist) {
			Log.i("tag", "The database is exist.");
		} else {// �����ھͰ�raw������ݿ�д���ֻ�
			try {
				util.copyDataBase();
				//��ӽ�����Զ��ظ��ƻ�
				DefaultAutore dfa=new DefaultAutore(this);
				dfa.festivalAutore();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
		// ������ʱ���ͷ���
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		Boolean autosend = sp.getBoolean("autosend", false);
		if (autosend)
			ServiceManage.opentrueAutosendService(this);
	}

}
