package com.symbol.messaging.function;

import java.util.ArrayList;
import java.util.Calendar;

import com.symbol.messaging.DAO.AutosendDAO;
import com.symbol.messaging.DAO.LogDAO;
import com.symbol.messaging.sms.LogItem;
import com.symbol.messaging.sms.SendItem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;

public class AlarmReceiver extends BroadcastReceiver {

	private Context context;

	/**
	 * ͨ���㲥����ɨ�裬�Ƿ񵽴�ʱ����ٷ��Ͷ���
	 * */

	@Override
	public void onReceive(Context cont, Intent intent) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(cont);
		Boolean autosend = sp.getBoolean("autosend", false);
		if (!autosend) {
			return;
		} else {
			this.context = cont;
			String month = String.valueOf(Calendar.getInstance().get(
					Calendar.MONTH) + 1);

			String day = String.valueOf(Calendar.getInstance().get(
					Calendar.DAY_OF_MONTH));

			String hour = String.valueOf(Calendar.getInstance().get(
					Calendar.HOUR_OF_DAY));

			String minute = String.valueOf(Calendar.getInstance().get(
					Calendar.MINUTE));

			int id = intent.getIntExtra("id", -1);
			System.out.println(id + "AlarmReceiver");
			String date = intent.getStringExtra(month + "-" + day);
			String time = intent.getStringExtra(hour + ":" + minute);// Сʱ��֣�
			String phone = intent.getStringExtra("phone");
			String content = intent.getStringExtra("content");
			if (date != null) {
				if (time != null) {// �ж��Ƿ�Ϊ�գ�Ȼ��ͨ��������
					sendMsg(phone, content);

					// �����־��Ϣ
					String dt = date + " " + time;
					LogItem li = new LogItem(dt, "��ʱ����", phone, content);
					LogDAO ld = new LogDAO(context);
					ld.add(li);

					// ���ٸ��¼�
					PendingIntent pendingIntent = PendingIntent.getBroadcast(
							context, intent.getIntExtra("id", -1), intent,
							PendingIntent.FLAG_CANCEL_CURRENT);
					AlarmManager aManager = (AlarmManager) context
							.getSystemService(Context.ALARM_SERVICE);
					aManager.cancel(pendingIntent);
					// �����ݿ����Ƴ�����
					// �Ƴ��������Ҫid
					if (sp.getBoolean("deleteaftersend", false)) {
						SendItem si = new SendItem(id, 0, "", "", "", "", "",
								"");
						AutosendDAO ad = new AutosendDAO(context);
						ad.delete(si);
					}
				}
			}
		}
	}

	private void sendMsg(String number, String message) {
		SmsManager smsManager = SmsManager.getDefault();
		if (message.length() > 70) {
			ArrayList<String> sms = smsManager.divideMessage(message);
			smsManager.sendMultipartTextMessage(number, null, sms, null, null);
			// while (message.length() > 70) {
			// smsManager.sendTextMessage(number, null,
			// message.substring(0, 70), null, null);
			// message = message.substring(70);
			// }
			// smsManager.sendTextMessage(number, null, message, null, null);
		} else {
			smsManager.sendTextMessage(number, null, message, null, null);
		}
		addSmsToDB(number, message);
	}

	private void addSmsToDB(String number, String content) {
		ContentValues values = new ContentValues();
		values.put("date", System.currentTimeMillis());
		values.put("read", 0);// 0Ϊδ����Ϣ
		values.put("type", 2);// 1Ϊ����Ϣ,2Ϊ����Ϣ
		values.put("address", number);
		values.put("body", content);
		context.getContentResolver().insert(Uri.parse("content://sms"), values);
	}
}