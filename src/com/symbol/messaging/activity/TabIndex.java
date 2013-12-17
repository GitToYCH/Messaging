package com.symbol.messaging.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TabIndex extends TabActivity implements
		CompoundButton.OnCheckedChangeListener {

	private TabHost tabHost;
	private Intent blessingsms;
	private Intent autore;
	private Intent autosend;
	private Intent more;

	private ImageView mIvSelectMove;
	private RadioButton rb_blessingsms;
	private RadioButton rb_autore;
	private RadioButton rb_autosend;
	private RadioButton rb_more;

	private int iLastLeft;
	private int currentTabID = 0;
	private long mExitTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabindex);

		this.tabHost = getTabHost();
		TabHost.TabSpec spec;

		this.rb_blessingsms = ((RadioButton) findViewById(R.id.rb_blessingsms));
		this.rb_autore = ((RadioButton) findViewById(R.id.rb_autore));
		this.rb_autosend = ((RadioButton) findViewById(R.id.rb_autosend));
		this.rb_more = ((RadioButton) findViewById(R.id.rb_more));

		this.rb_blessingsms.setOnCheckedChangeListener(this);
		this.rb_autore.setOnCheckedChangeListener(this);
		this.rb_autosend.setOnCheckedChangeListener(this);
		this.rb_more.setOnCheckedChangeListener(this);

		this.blessingsms = new Intent(this, MainActivity.class);
		this.autore = new Intent(this, AutoreHomeActivity.class);
		this.autosend = new Intent(this, AutosendHomeActivity.class);
		this.more = new Intent(this, MoreIndex.class);

		spec = tabHost.newTabSpec("短信大全").setIndicator("短信大全")
				.setContent(this.blessingsms);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("自动回复").setIndicator("自动回复")
				.setContent(this.autore);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("定时发送").setIndicator("定时发送")
				.setContent(this.autosend);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("更多").setIndicator("更多")
				.setContent(this.more);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

		// 设置底部栏
		// 初始化选择移动效果
		this.mIvSelectMove = ((ImageView) findViewById(R.id.iv_select));
		this.iLastLeft = mIvSelectMove.getLeft();
		// 延迟半秒初始化
		new Handler().postDelayed(new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (TabIndex.this.currentTabID) {
				default:
				case 0:
					TabIndex.this.MoveSelectTo(TabIndex.this.rb_blessingsms);
					break;
				case 1:
					TabIndex.this.MoveSelectTo(TabIndex.this.rb_autore);
					break;
				case 2:
					TabIndex.this.MoveSelectTo(TabIndex.this.rb_autosend);
					break;
				case 3:
					TabIndex.this.MoveSelectTo(TabIndex.this.rb_more);
					break;

				}
			}
		}), 500L);
	}

	private void MoveSelectTo(View v) {
		LayoutParams layoutParams = (LayoutParams) mIvSelectMove
				.getLayoutParams();
		layoutParams.width = v.getWidth();
		mIvSelectMove.setLayoutParams(layoutParams);

		int iNewLeft = v.getLeft();

		if (iLastLeft != iNewLeft) {
			TranslateAnimation animation = new TranslateAnimation(iLastLeft,
					iNewLeft, 0, 0);
			animation.setDuration(400);
			animation.setFillAfter(true);
			mIvSelectMove.startAnimation(animation);
			iLastLeft = iNewLeft;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			SharedPreferences sp = PreferenceManager
					.getDefaultSharedPreferences(this);
			switch (buttonView.getId()) {
			default:
			case R.id.rb_blessingsms:
				tabHost.setCurrentTabByTag("短信大全");
				this.MoveSelectTo(rb_blessingsms);
				break;
			case R.id.rb_autore:
				Boolean autore = sp.getBoolean("autore", false);
				if (autore) {
					tabHost.setCurrentTabByTag("自动回复");
					TabIndex.this.MoveSelectTo(rb_autore);
				} else {
					Toast.makeText(this, "请在“系统设置”菜单中打开“自动回复”服务",
							Toast.LENGTH_SHORT).show();
				}
				;
				break;
			case R.id.rb_autosend:
				Boolean autosend = sp.getBoolean("autosend", false);
				if (autosend) {
					tabHost.setCurrentTabByTag("定时发送");
					this.MoveSelectTo(rb_autosend);
				} else {
					Toast.makeText(this, "请在“系统设置”菜单中打开“定时发送”服务",
							Toast.LENGTH_SHORT).show();
				}
				;
				break;
			case R.id.rb_more:
				tabHost.setCurrentTabByTag("更多");
				this.MoveSelectTo(rb_more);
				break;
			}
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}
