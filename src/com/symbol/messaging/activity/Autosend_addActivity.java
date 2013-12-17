package com.symbol.messaging.activity;

import java.util.Calendar;

import com.symbol.messaging.DAO.AutosendDAO;
import com.symbol.messaging.sms.SendItem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Autosend_addActivity extends Activity {
	
	TextView titletext;
	EditText title;
	EditText name;
	EditText phone;
	Button date;
	Button time;
	EditText smstext;
	Button addok;
	Button reset;
	ImageView addforcon;// 从联系人中获得电话
	ImageView addforbl;// 从短信大全中选取短信

	Intent intent;

	private boolean ismodify;

	private static final int DATE_PICKER_ID = 1;
	private static final int TIME_PICKER_ID = 2;
	private static final int PICK_CONTACT = 2;
	private static final int PICK_MESSAGE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autosend_add);
		titletext=(TextView)findViewById(R.id.autosend_add_title);
		
		title = (EditText) findViewById(R.id.autosend_title_edit);
		name = (EditText) findViewById(R.id.autosend_name_edit);
		phone = (EditText) findViewById(R.id.autosend_phone_edit);
		smstext = (EditText) findViewById(R.id.autosend_sms_edit);

		addok = (Button) findViewById(R.id.autosend_addOK);
		reset = (Button) findViewById(R.id.autosend_reset);
		date = (Button) findViewById(R.id.autosend_add_date);
		time = (Button) findViewById(R.id.autosend_add_time);
		addforcon = (ImageView) findViewById(R.id.autosend_name_addforcontacts);
		addforbl = (ImageView) findViewById(R.id.autosend_sms_addforbleesingsms);

		int year=Calendar.getInstance().get(Calendar.YEAR);
		int month=Calendar.getInstance().get(Calendar.MONTH)+1;
		int day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		date.setText(year+"-"+month+"-"+day);
		
		reset.setOnClickListener(new resetListener());
		addok.setOnClickListener(new addokListener());
		date.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_PICKER_ID);
			}
		});
		time.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(TIME_PICKER_ID);
			}
		});
		addforcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(
						new Intent(
								Intent.ACTION_PICK,
								android.provider.ContactsContract.Contacts.CONTENT_URI),
						PICK_CONTACT);
			}
		});
		addforbl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("isfinish", true);
				intent.setClass(Autosend_addActivity.this, MainActivity.class);
				startActivityForResult(intent, PICK_MESSAGE);
			}
		});
		intent = getIntent();
		if (intent.getStringExtra("id") != null) {
			setItem();
			ismodify = true;
		} else
			ismodify = false;
	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {			
			// TODO Auto-generated method stub
			int month = monthOfYear + 1;
			date.setText(year + "-" + month + "-" + dayOfMonth);
		}
	};
	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			time.setText(hourOfDay + ":" + minute);
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case DATE_PICKER_ID:
			String [] date1=date.getText().toString().split("-");
			int year = Integer.parseInt(date1[0]);
			int month = Integer.parseInt(date1[1]);
			int day = Integer.parseInt(date1[2]);
			return new DatePickerDialog(this, onDateSetListener, year, month, day);
		case TIME_PICKER_ID:
			String []time1=time.getText().toString().split(":");
			int hour = Integer.parseInt(time1[0]);
			int minute = Integer.parseInt(time1[1]);
			return new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
		}
		return null;
	}

	class addokListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if ("".equals(title.getText().toString().trim())) {
				Toast.makeText(Autosend_addActivity.this, "请填写标题",
						Toast.LENGTH_SHORT).show();
			} else if ("".equals(phone.getText().toString().trim())) {
				Toast.makeText(Autosend_addActivity.this, "请填写电话号码",
						Toast.LENGTH_SHORT).show();
			} else if ("".equals(smstext.getText().toString().trim())) {
				Toast.makeText(Autosend_addActivity.this, "请填写短信内容",
						Toast.LENGTH_SHORT).show();
			} else {
				AutosendDAO sc = new AutosendDAO(Autosend_addActivity.this);
				if (!ismodify) {
					SendItem si = new SendItem(title.getText().toString()
							.trim(), name.getText().toString().trim(), phone
							.getText().toString().trim(), date.getText()
							.toString().trim(), time.getText().toString()
							.trim(), smstext.getText().toString().trim());
					sc.add(si);
					Toast.makeText(Autosend_addActivity.this, "添加成功",
							Toast.LENGTH_SHORT).show();
					title.setText("");
					name.setText("");
					phone.setText("");
				}
				else if(ismodify){
					SendItem si=new SendItem(Integer.parseInt(intent.getStringExtra("id")),0,title.getText().toString()
							.trim(), name.getText().toString().trim(), phone
							.getText().toString().trim(), date.getText()
							.toString().trim(), time.getText().toString()
							.trim(), smstext.getText().toString().trim());
					sc.update(si);
					Toast.makeText(Autosend_addActivity.this, "修改成功",
							Toast.LENGTH_SHORT).show();
					finish();
				}
			}
		}
	}

	class resetListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			title.setText("");
			name.setText("");
			phone.setText("");
			smstext.setText("");
		}
	}

	private void setItem() {
		titletext.setText("修改定时发送");
		title.setText(intent.getStringExtra("title"));
		name.setText(intent.getStringExtra("name"));
		phone.setText(intent.getStringExtra("phone"));
		date.setText(intent.getStringExtra("date"));
		time.setText(intent.getStringExtra("time"));
		smstext.setText(intent.getStringExtra("content"));
	}

	private void addphoneedit(Intent data) {
		final Uri uriRet = data.getData();
		if (uriRet != null) {
			try {
				@SuppressWarnings("deprecation")
				Cursor c = managedQuery(uriRet, null, null, null, null);
				/* 将Cursor移到数据最前端 */
				c.moveToFirst();
				/* 取得联系人的姓名 */
				String strName = c
						.getString(c
								.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

				name.setText(strName);
				/* 取得联系人的电话 */
				int ccontactId = c.getInt(c
						.getColumnIndex(ContactsContract.Contacts._ID));
				Cursor phones = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
								+ " = " + ccontactId, null, null);
				StringBuffer sb = new StringBuffer();
				int typePhone, resType;
				String numPhone;
				if (phones.getCount() > 0) {
					phones.moveToFirst();
					typePhone = phones
							.getInt(phones
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
					numPhone = phones
							.getString(phones
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					resType = ContactsContract.CommonDataKinds.Phone
							.getTypeLabelResource(typePhone);
					sb.append(getString(resType) + ": " + numPhone + "\n");

					phone.setText(numPhone);
				} else {
					sb.append("no Phone number found");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addsmstextedit(Intent data) {
		smstext.setText(data.getStringExtra("text"));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			switch (requestCode) {
			case PICK_CONTACT:
				addphoneedit(data);
				break;
			case PICK_MESSAGE:
				addsmstextedit(data);
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
