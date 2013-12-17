package com.symbol.messaging.function;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.symbol.messaging.DAO.AutoreDAO;
import com.symbol.messaging.DAO.AutosendDAO;
import com.symbol.messaging.sms.ReItem;
import com.symbol.messaging.sms.SendItem;

public class ServiceManage {

	/**
	 * �������Զ����͵ķ���
	 * 
	 * @param context
	 */
	public static void openAllAutosendService(Context context) {
		AutosendDAO siclass = new AutosendDAO(context);
		ArrayList<SendItem> si = new ArrayList<SendItem>();
		si = siclass.find();
		for (int i = 0; i < si.size(); i++) {
			openAutosendService(context, si.get(i));
		}
	}

	/**
	 * ���ѱ�ǵ��Զ����͵ķ���
	 * @param context
	 */
	public static void opentrueAutosendService(Context context) {
		AutosendDAO siclass = new AutosendDAO(context);
		ArrayList<SendItem> si = new ArrayList<SendItem>();
		si = siclass.find();
		for (int i = 0; i < si.size(); i++) {
			if (si.get(i).getisopen() == 1)
				openAutosendService(context, si.get(i));
		}
	}

	/**
	 * ��ָ���Զ����͵ķ���
	 * 
	 * @param context
	 * @param si
	 */
	public static void openAutosendService(Context context, SendItem si) {
		AlarmManager aManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.putExtra("id", si.getid());
		intent.putExtra(si.gettime(), si.gettime());
		intent.putExtra(si.getdate().substring(5), si.getdate());
		intent.putExtra("phone", si.getphone());
		intent.putExtra("content", si.getcontent());
		intent.setAction("AlarmReceiver");

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
				si.getid(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
		aManager.setRepeating(AlarmManager.RTC, 0, 60 * 1000, pendingIntent);		

		//���Ϊ��
		si.setisopen(1);
		AutosendDAO ad = new AutosendDAO(context);
		ad.update(si);
	}

	/**
	 * �ر������Զ����͵ķ���
	 * 
	 * @param context
	 */
	public static void closeAllAutosendService(Context context) {
		AutosendDAO siclass = new AutosendDAO(context);
		ArrayList<SendItem> si = new ArrayList<SendItem>();
		si = siclass.find();
		for (int i = 0; i < si.size(); i++) {
			closeAutosendService(context, si.get(i));
		}
	}

	/**
	 * �ر�ָ���Զ����͵ķ���
	 * 
	 * @param context
	 * @param si
	 */
	public static void closeAutosendService(Context context, SendItem si) {
		AlarmManager aManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		Intent intent = new Intent(context, AlarmReceiver.class);
		intent.putExtra("id", si.getid());
		intent.putExtra(si.gettime(), si.gettime());
		intent.putExtra(si.getdate().substring(5), si.getdate());
		intent.putExtra("phone", si.getphone());
		intent.putExtra("content", si.getcontent());
		intent.setAction("AlarmReceiver");

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
				si.getid(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
		aManager.cancel(pendingIntent);
		//���Ϊ�ر�
		si.setisopen(0);
		AutosendDAO ad = new AutosendDAO(context);
		ad.update(si);
	}
	/**
	 * �ر������Զ��ظ��ķ���
	 * @param context
	 */
	public static void closeAllAutoreService(Context context){
		AutoreDAO ad=new AutoreDAO(context);
		ArrayList<ReItem> si = new ArrayList<ReItem>();
		si = ad.find();
		for (int i = 0; i < si.size(); i++) {
			closeAutoreService(context, si.get(i));
		}
	}
	/**
	 * �ر�ָ���Զ��ظ��ķ���
	 * @param context
	 * @param ri
	 */
	public static void closeAutoreService(Context context,ReItem ri){
		ri.setisopen(0);
		AutoreDAO ad=new AutoreDAO(context);
		ad.update(ri);
	}
	/**
	 * �������Զ��ظ��ķ���
	 * @param context
	 */
	public static void openAllAutoreService(Context context){
		AutoreDAO ad=new AutoreDAO(context);
		ArrayList<ReItem> si = new ArrayList<ReItem>();
		si = ad.find();
		for (int i = 0; i < si.size(); i++) {
			openAutoreService(context, si.get(i));
		}
	}
	/**
	 * ��ָ���Զ��ظ��ķ���
	 * @param context
	 * @param ri
	 */
	public static void openAutoreService(Context context,ReItem ri){
		ri.setisopen(1);
		AutoreDAO ad=new AutoreDAO(context);
		ad.update(ri);
	}

}
