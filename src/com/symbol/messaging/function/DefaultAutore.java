package com.symbol.messaging.function;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.content.Context;

import com.symbol.messaging.DAO.AutoreDAO;
import com.symbol.messaging.DAO.SmsContentDAO;
import com.symbol.messaging.sms.ReItem;
import com.symbol.messaging.sms.Sms;
import com.symbol.messaging.util.DateConvert;

public class DefaultAutore {
	Context context;

	public DefaultAutore(Context context) {
		this.context = context;
	}

	// ��ӽ���Ľ����Զ��ظ��ƻ�
	public void festivalAutore() {
		SmsContentDAO smscontent = new SmsContentDAO(context);
		ArrayList<Sms> sms = new ArrayList<Sms>();
		AutoreDAO sc = new AutoreDAO(context);
		ReItem ri;

		Random rd = new Random();
		int index;
		int year = Calendar.getInstance().get(Calendar.YEAR);

		sms = smscontent.find("Ԫ��");
		index = rd.nextInt(sms.size());
		ri = new ReItem("Ԫ������", "Ԫ������-����Ԫ��", year + "-01-01 00:00", year
				+ "-01-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);

		sms = smscontent.find("����");
		index = rd.nextInt(sms.size());
		ri = new ReItem("���ڿ���", "���ڿ���-�´�����-�������-����-����-����-���",
				DateConvert.sCalendarLundarToSolar(year, 1, 1) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 1, 1) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);

		sms = smscontent.find("���˽�");
		index = rd.nextInt(sms.size());
		ri = new ReItem("���˽�����", "���˽�", year + "-04-01 00:00", year
				+ "-04-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("�Ͷ���");
		index = rd.nextInt(sms.size());
		ri = new ReItem("�Ͷ�����", "��һ-�Ͷ���", year + "-05-01 00:00", year
				+ "-05-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("��ͯ��");
		index = rd.nextInt(sms.size());
		ri = new ReItem("��ͯ��", "��һ-��ͯ��", year + "-06-01 00:00", year
				+ "-06-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("�����");
		index = rd.nextInt(sms.size());
		ri = new ReItem("���绮����", "����-����-��",
				DateConvert.sCalendarLundarToSolar(year, 5, 5) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 5, 5) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("�����");
		index = rd.nextInt(sms.size());
		ri = new ReItem("����ڿ���", "����-˼��",
				DateConvert.sCalendarLundarToSolar(year, 8, 15) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 8, 15) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("�����");
		index = rd.nextInt(sms.size());
		ri = new ReItem("���쳤��", "����-ʮһ-�ƽ���", year + "-10-01 00:00", year
				+ "-10-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("������");
		index = rd.nextInt(sms.size());
		ri = new ReItem("����", "�Ǹ�-����-�ž�",
				DateConvert.sCalendarLundarToSolar(year, 9, 9) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 9, 9) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("�����");
		index = rd.nextInt(sms.size());
		ri = new ReItem("11 11", "���-�µ�-����-11", year + "-11-11 00:00", year
				+ "-11-11 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("ʥ����");
		index = rd.nextInt(sms.size());
		ri = new ReItem("Christmas", "ʥ��-Christmas", year + "-12-25 00:00", year
				+ "-12-25 23:59", sms.get(index).getsmstext());
		sc.add(ri);
	}
}
