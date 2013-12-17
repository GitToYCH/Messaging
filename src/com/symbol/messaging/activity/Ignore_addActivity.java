package com.symbol.messaging.activity;

import com.symbol.messaging.DAO.IgnorelistDAO;
import com.symbol.messaging.sms.IgnoreItem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ignore_addActivity extends Activity {

	EditText name;
	EditText phone;
	Button addok;
	Button reset;
	Button addforcon;// 从联系人中获得电话

	private static final int PICK_CONTACT = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ignore_add);

		name = (EditText) findViewById(R.id.ignore_name_edit);
		phone = (EditText) findViewById(R.id.ignore_phone_edit);

		addok = (Button) findViewById(R.id.ignore_addOK);
		reset = (Button) findViewById(R.id.ignore_reset);
		addforcon = (Button) findViewById(R.id.ignore_name_addforcontacts);

		addok.setOnClickListener(new addokListener());
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name.setText("");
				phone.setText("");
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
	}
	class addokListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if ("".equals(phone.getText().toString().trim())) {
				Toast.makeText(Ignore_addActivity.this, "请填写电话号码",
						Toast.LENGTH_SHORT).show();
			} else {
				IgnorelistDAO ig = new IgnorelistDAO(Ignore_addActivity.this);
					IgnoreItem ii = new IgnoreItem(name.getText().toString().trim(), phone
							.getText().toString().trim());
					ig.add(ii);
					Toast.makeText(Ignore_addActivity.this, "添加成功",
							Toast.LENGTH_SHORT).show();
					name.setText("");
					phone.setText("");
				}
		}
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

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			switch (requestCode) {
			case PICK_CONTACT:
				addphoneedit(data);
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
