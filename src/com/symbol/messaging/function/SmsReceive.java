package com.symbol.messaging.function;

import java.util.ArrayList;
import java.util.Calendar;

import com.symbol.messaging.DAO.AutoreDAO;
import com.symbol.messaging.DAO.IgnorelistDAO;
import com.symbol.messaging.DAO.LogDAO;
import com.symbol.messaging.DAO.PCacheDAO;
import com.symbol.messaging.sms.IgnoreItem;
import com.symbol.messaging.sms.LogItem;
import com.symbol.messaging.sms.PCacheItem;
import com.symbol.messaging.sms.ReItem;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class SmsReceive extends BroadcastReceiver {
	private Context context;

	@Override
	public void onReceive(Context cont, Intent intent) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(cont);
		Boolean autore = sp.getBoolean("autore", false);
		if (!autore) {
			return;
		} else {
			this.context = cont;
			SmsMessage[] msg = null;

			if (intent.getAction().equals(
					"android.provider.Telephony.SMS_RECEIVED")) {
				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					Object[] pdusObj = (Object[]) bundle.get("pdus");
					msg = new SmsMessage[pdusObj.length];
					for (int i = 0; i < pdusObj.length; i++)
						msg[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
				}

				for (int i = 0; i < msg.length; i++) {
					String msgtxt = msg[i].getMessageBody();
					String msgphone = msg[i].getOriginatingAddress();
					// �鿴�ú����Ƿ��ں����б���
					IgnorelistDAO iiclass = new IgnorelistDAO(context);
					ArrayList<IgnoreItem> ii = new ArrayList<IgnoreItem>();
					ii = iiclass.find(msgphone);
					if (ii.size() != 0)
						return;
					// �鿴�˴λظ�����һ�λظ��೤ʱ��
					PCacheDAO pd = new PCacheDAO(context);
					PCacheItem pi = pd.find(msgphone);
					long currenttime = System.currentTimeMillis();
					if (pi != null) {
						String interval = sp.getString("intervallist", "60");
						long currenttimecache = pi.getcurrenttime();
						if ((currenttime - currenttimecache) > (Integer
								.parseInt(interval) * 1000)) {
							autore(msgtxt, msgphone);
							pi.setcurrenttime(currenttime);
							pd.update(pi);
						}
					} else {
						autore(msgtxt, msgphone);
						pi=new PCacheItem(msgphone,currenttime);
						pd.add(pi);
					}
				}
				return;
			}
		}
	}

	private void autore(String msgtxt, String msgphone) {

		boolean issend = false;
		// ȡ�ý����ʱ��
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		String monthstr;
		String daystr;
		String minutestr;
		String hourstr;
		// ��ȡ�õ�ʱ��������ת��ָ����ʽ
		if (minute < 10)
			minutestr = "0" + minute;
		else
			minutestr = String.valueOf(minute);
		if (hour < 10)
			hourstr = "0" + hour;
		else
			hourstr = String.valueOf(hour);

		if (month < 10)
			monthstr = "0" + month;
		else
			monthstr = String.valueOf(month);
		if (day < 10)
			daystr = "0" + day;
		else
			daystr = String.valueOf(day);

		String date = year + "-" + monthstr + "-" + daystr;
		String time = hourstr + ":" + minutestr;
		String now = date + " " + time;
		// �����ݿ���ȡ�����Ϊ������������Ҫ��ļ�¼
		AutoreDAO siclass = new AutoreDAO(context);
		ArrayList<ReItem> ri = new ArrayList<ReItem>();
		ri = siclass.find(1, now);
		for (int i = 0; !issend && i < ri.size(); i++) {
			String[] keywords = ri.get(i).getkeywords().split("-");
			if (keywords.length == 0) {
				addToLog(now, "�Զ��ظ�", msgphone, ri.get(i).getcontent());
				sendMsg(msgphone, ri.get(i).getcontent());
				issend = true;
				break;
			}
			for (int j = 0; j < keywords.length; j++) {
				if (msgtxt.indexOf(keywords[j]) != -1) {
					addToLog(now, "�Զ��ظ�", msgphone, ri.get(i).getcontent());
					sendMsg(msgphone, ri.get(i).getcontent());
					issend = true;
					break;
				}
			}
		}
	}

	private void addToLog(String now, String operation, String msgphone,
			String content) {
		LogItem li = new LogItem(now, operation, msgphone, content);
		LogDAO ld = new LogDAO(context);
		ld.add(li);
	}

	private void sendMsg(String number, String content) {
		SmsManager smsManager = SmsManager.getDefault();
		if (content.length() > 70) {
			ArrayList<String> sms = smsManager.divideMessage(content);
			smsManager.sendMultipartTextMessage(number, null, sms, null, null);
		} else
			smsManager.sendTextMessage(number, null, content, null, null);
		// �Ѷ�����ӵ�ϵͳ�������ݿ�
		addSmsToDB(number, content);
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