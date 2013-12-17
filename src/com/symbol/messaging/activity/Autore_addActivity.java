package com.symbol.messaging.activity;

import java.util.Calendar;

import com.symbol.messaging.DAO.AutoreDAO;
import com.symbol.messaging.sms.ReItem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Autore_addActivity extends Activity {

	EditText title;
	EditText keywords;
	Button startdate;
	Button starttime;
	Button enddate;
	Button endtime;
	EditText smstext;
	ImageView addforbl;
	Button addok;
	Button reset;
	TextView titletext;

	Intent intent;

	private boolean ismodify;

	private static final int START_DATE_PICKER_ID = 1;
	private static final int START_TIME_PICKER_ID = 2;
	private static final int END_DATE_PICKER_ID = 3;
	private static final int END_TIME_PICKER_ID = 4;
	private static final int PICK_MESSAGE = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autore_add);
		titletext = (TextView) findViewById(R.id.autore_add_title);

		title = (EditText) findViewById(R.id.autore_title_edit);
		keywords = (EditText) findViewById(R.id.autore_keywords_edit);
		smstext = (EditText) findViewById(R.id.autore_sms_edit);

		startdate = (Button) findViewById(R.id.autore_add_startdate);
		starttime = (Button) findViewById(R.id.autore_add_starttime);
		enddate = (Button) findViewById(R.id.autore_add_enddate);
		endtime = (Button) findViewById(R.id.autore_add_endtime);
		addok = (Button) findViewById(R.id.autore_addOK);
		reset = (Button) findViewById(R.id.autore_reset);
		addforbl = (ImageView) findViewById(R.id.autore_sms_addforbleesingsms);

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String monthstr;
		String daystr;
		if (month < 10)
			monthstr = "0" + month;
		else
			monthstr = String.valueOf(month);
		if (day < 10)
			daystr = "0" + day;
		else
			daystr = String.valueOf(day);
		startdate.setText(year + "-" + monthstr + "-" + daystr);
		enddate.setText(year + "-" + monthstr + "-" + daystr);
		starttime.setText("07:00");
		endtime.setText("07:00");

		intent = getIntent();
		if (intent.getStringExtra("id") != null) {
			setItem();
			ismodify = true;
		} else
			ismodify = false;
		
		addok.setOnClickListener(new addokListener());
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				title.setText("");
				keywords.setText("");
				smstext.setText("");
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
				int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				String monthstr;
				String daystr;
				if (month < 10)
					monthstr = "0" + month;
				else
					monthstr = String.valueOf(month);
				if (day < 10)
					daystr = "0" + day;
				else
					daystr = String.valueOf(day);
				startdate.setText(year + "-" + monthstr + "-" + daystr);
				enddate.setText(year + "-" + monthstr + "-" + daystr);
				starttime.setText("07:00");
				endtime.setText("07:00");
			}
		});
		startdate.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(START_DATE_PICKER_ID);
			}
		});
		starttime.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(START_TIME_PICKER_ID);
			}
		});
		enddate.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(END_DATE_PICKER_ID);
			}
		});
		endtime.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(END_TIME_PICKER_ID);
			}
		});
		addforbl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("isfinish", true);
				intent.setClass(Autore_addActivity.this, MainActivity.class);
				startActivityForResult(intent, PICK_MESSAGE);
			}
		});
	}

	private void setItem() {
		// TODO Auto-generated method stub
		titletext.setText("修改自动回复");
		title.setText(intent.getStringExtra("title"));
		keywords.setText(intent.getStringExtra("keywords"));
		String[] start = intent.getStringExtra("start").split(" ");
		System.out.println("start:"+start[0]+" "+start[1]);
		startdate.setText(start[0]);
		starttime.setText(start[1]);
		String[] end = intent.getStringExtra("end").split(" ");
		System.out.println("end:"+end[0]+" "+end[1]);
		enddate.setText(end[0]);
		endtime.setText(end[1]);
		smstext.setText(intent.getStringExtra("content"));
	}

	// {{ 时间窗口的响应事件
	DatePickerDialog.OnDateSetListener startdateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			int month = monthOfYear + 1;
			String monthstr;
			String daystr;
			if (month < 10)
				monthstr = "0" + month;
			else
				monthstr = String.valueOf(month);
			if (dayOfMonth < 10)
				daystr = "0" + dayOfMonth;
			else
				daystr = String.valueOf(dayOfMonth);
			startdate.setText(year + "-" + monthstr + "-" + daystr);
		}
	};
	TimePickerDialog.OnTimeSetListener starttimeListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			String minutestr;
			String hourstr;
			if (minute < 10)
				minutestr = "0" + minute;
			else
				minutestr = String.valueOf(minute);
			if (hourOfDay < 10)
				hourstr = "0" + hourOfDay;
			else
				hourstr = String.valueOf(hourOfDay);
			starttime.setText(hourstr + ":" + minutestr);
		}
	};
	DatePickerDialog.OnDateSetListener enddateListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			int month = monthOfYear + 1;
			String monthstr;
			String daystr;
			if (month < 10)
				monthstr = "0" + month;
			else
				monthstr = String.valueOf(month);
			if (dayOfMonth < 10)
				daystr = "0" + dayOfMonth;
			else
				daystr = String.valueOf(dayOfMonth);
			enddate.setText(year + "-" + monthstr + "-" + daystr);
		}
	};
	TimePickerDialog.OnTimeSetListener endtimeListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			String minutestr;
			String hourstr;
			if (minute < 10)
				minutestr = "0" + minute;
			else
				minutestr = String.valueOf(minute);
			if (hourOfDay < 10)
				hourstr = "0" + hourOfDay;
			else
				hourstr = String.valueOf(hourOfDay);
			endtime.setText(hourstr + ":" + minutestr);
		}
	};

	// }}
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case START_DATE_PICKER_ID:
			String [] date=startdate.getText().toString().split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			return new DatePickerDialog(this, startdateListener, year, month,
					day);
		case START_TIME_PICKER_ID:
			String []time=starttime.getText().toString().split(":");
			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			return new TimePickerDialog(this, starttimeListener, hour, minute, true);
		case END_DATE_PICKER_ID:
			String [] date1=startdate.getText().toString().split("-");
			int year1 = Integer.parseInt(date1[0]);
			int month1 = Integer.parseInt(date1[1]);
			int day1 = Integer.parseInt(date1[2]);
			return new DatePickerDialog(this, enddateListener, year1, month1,
					day1);
		case END_TIME_PICKER_ID:
			String []time1=starttime.getText().toString().split(":");
			int hour1 = Integer.parseInt(time1[0]);
			int minute1 = Integer.parseInt(time1[1]);
			return new TimePickerDialog(this, endtimeListener, hour1, minute1, true);
		}
		return null;
	}

	// 判断输入的起止日期是否正确
	private boolean isDatetrue() {
		// 比较日期
		int compdate = startdate.getText().toString().trim()
				.compareTo(enddate.getText().toString().trim());
		// 如果起始日期大于终止日期，返回false
		if (compdate > 0)
			return false;
		// 如果起止日期相同，则比较时间
		if (compdate == 0) {
			int comptime = starttime.getText().toString().trim()
					.compareTo(endtime.getText().toString().trim());
			if (comptime > 0)
				return false;
		}
		return true;
	}

	class addokListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if ("".equals(title.getText().toString().trim())) {
				Toast.makeText(Autore_addActivity.this, "请填写标题",
						Toast.LENGTH_SHORT).show();
			} else if (!isDatetrue()) {
				Toast.makeText(Autore_addActivity.this, "起止时间不正确",
						Toast.LENGTH_SHORT).show();

			} else if ("".equals(smstext.getText().toString().trim())) {
				Toast.makeText(Autore_addActivity.this, "请填写短信内容",
						Toast.LENGTH_SHORT).show();
			} else {
				AutoreDAO sc = new AutoreDAO(Autore_addActivity.this);
				if (!ismodify) {
					ReItem ri = new ReItem(title.getText().toString().trim(),
							keywords.getText().toString().trim(), startdate
									.getText().toString().trim()
									+ " "
									+ starttime.getText().toString().trim(),
							enddate.getText().toString().trim() + " "
									+ endtime.getText().toString().trim(),
							smstext.getText().toString().trim());
					sc.add(ri);
					Toast.makeText(Autore_addActivity.this, "添加成功",
							Toast.LENGTH_SHORT).show();
					title.setText("");
					keywords.setText("");
					smstext.setText("");
				}
				else if (ismodify){
					ReItem ri = new ReItem(Integer.parseInt(intent.getStringExtra("id")),0,title.getText().toString().trim(),
							keywords.getText().toString().trim(), startdate
									.getText().toString().trim()
									+ " "
									+ starttime.getText().toString().trim(),
							enddate.getText().toString().trim() + " "
									+ endtime.getText().toString().trim(),
							smstext.getText().toString().trim());
					sc.update(ri);
					Toast.makeText(Autore_addActivity.this, "修改成功",
							Toast.LENGTH_SHORT).show();
					title.setText("");
					keywords.setText("");
					smstext.setText("");
					finish();
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			switch (requestCode) {
			case PICK_MESSAGE:
				smstext.setText(data.getStringExtra("text"));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
