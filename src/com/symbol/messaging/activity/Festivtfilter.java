package com.symbol.messaging.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Festivtfilter extends Activity {

	Button chunjie = null;
	Button qingrenjie = null;
	Button yuandan = null;
	Button laodongjie = null;
	Button muqinjie = null;
	Button fuqinjie = null;
	Button qixi = null;
	Button funvjie = null;
	Button yurenjie = null;
	Button duanwujie = null;
	Button ertongjie = null;
	Button ganenjie = null;
	Button zhongqiujie = null;
	Button guoqingjie = null;
	Button chongyangjie = null;
	Button jiaoshijie = null;
	Button guanggunjie = null;
	Button shengdanjie = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.festivefilter);
		chunjie = (Button) findViewById(R.id.chunjie);
		qingrenjie = (Button) findViewById(R.id.qingrenjie);
		yuandan = (Button) findViewById(R.id.yuandan);
		laodongjie = (Button) findViewById(R.id.laodongjie);
		muqinjie = (Button) findViewById(R.id.muqinjie);
		fuqinjie = (Button) findViewById(R.id.fuqinjie);
		qixi = (Button) findViewById(R.id.qixijie);
		funvjie = (Button) findViewById(R.id.funvjie);
		yurenjie = (Button) findViewById(R.id.yurenjie);
		duanwujie = (Button) findViewById(R.id.duanwujie);
		ertongjie = (Button) findViewById(R.id.ertongjie);
		ganenjie = (Button) findViewById(R.id.ganenjie);
		zhongqiujie = (Button) findViewById(R.id.zhongqiujie);
		guoqingjie = (Button) findViewById(R.id.guoqinjie);
		chongyangjie = (Button) findViewById(R.id.chongyangjie);
		jiaoshijie = (Button) findViewById(R.id.jiaoshijie);
		guanggunjie = (Button) findViewById(R.id.guangunjie);
		shengdanjie = (Button) findViewById(R.id.shengdanjie);

		chunjie.setOnClickListener(new chunjieListener());
		qingrenjie.setOnClickListener(new qingrenjieListener());
		yuandan.setOnClickListener(new yuandanListener());
		laodongjie.setOnClickListener(new laodongjieListener());
		muqinjie.setOnClickListener(new muqinjieListener());
		fuqinjie.setOnClickListener(new fuqinjieListener());
		qixi.setOnClickListener(new qixijieListener());
		funvjie.setOnClickListener(new funvjieListener());
		yurenjie.setOnClickListener(new yurenjieListener());
		duanwujie.setOnClickListener(new duanwujieListener());
		ertongjie.setOnClickListener(new ertongjieListener());
		ganenjie.setOnClickListener(new ganenjieListener());
		zhongqiujie.setOnClickListener(new zhongqiujieListener());
		guoqingjie.setOnClickListener(new guoqingjieListener());
		chongyangjie.setOnClickListener(new chongyangjieListener());
		jiaoshijie.setOnClickListener(new jiaoshijieListener());
		guanggunjie.setOnClickListener(new guanggunjieListener());
		shengdanjie.setOnClickListener(new shengdanjieListener());
	}

	private void startAct(String filter) {
		Intent intent = new Intent();
		if (getIntent().getBooleanExtra("isfinish", false)) {
			intent.putExtra("isfinish", true);
		}
		intent.putExtra("title", filter);
		intent.putExtra("by", filter);
		intent.setClass(Festivtfilter.this, SmslistActivity.class);
		startActivityForResult(intent, 1);
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

	class funvjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("妇女节");
		}
	}

	class yurenjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("愚人节");
		}
	}

	class duanwujieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("端午节");
		}
	}

	class ertongjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("儿童节");
		}
	}

	class ganenjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("感恩节");
		}
	}

	class zhongqiujieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("中秋节");
		}
	}

	class guoqingjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("国庆节");
		}
	}

	class chongyangjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("重阳节");
		}
	}

	class jiaoshijieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("教师节");
		}
	}

	class guanggunjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("光棍节");
		}
	}

	class shengdanjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("圣诞节");
		}
	}

	class chunjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("春节");
		}
	}

	class qingrenjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("情人节");
		}
	}

	class yuandanListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("元旦");
		}
	}

	class laodongjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("劳动节");
		}
	}

	class muqinjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("母亲节");
		}
	}

	class fuqinjieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("父亲节");
		}
	}

	class qixijieListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startAct("七夕节");
		}
	}
}
