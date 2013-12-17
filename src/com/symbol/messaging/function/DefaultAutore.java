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

	// 添加今年的节日自动回复计划
	public void festivalAutore() {
		SmsContentDAO smscontent = new SmsContentDAO(context);
		ArrayList<Sms> sms = new ArrayList<Sms>();
		AutoreDAO sc = new AutoreDAO(context);
		ReItem ri;

		Random rd = new Random();
		int index;
		int year = Calendar.getInstance().get(Calendar.YEAR);

		sms = smscontent.find("元旦");
		index = rd.nextInt(sms.size());
		ri = new ReItem("元旦快乐", "元旦快乐-快乐元旦", year + "-01-01 00:00", year
				+ "-01-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);

		sms = smscontent.find("春节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("春节快乐", "春节快乐-新春快乐-新年快乐-新年-春节-快乐-愉快",
				DateConvert.sCalendarLundarToSolar(year, 1, 1) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 1, 1) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);

		sms = smscontent.find("愚人节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("愚人节整蛊", "愚人节", year + "-04-01 00:00", year
				+ "-04-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("劳动节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("劳动光荣", "五一-劳动节", year + "-05-01 00:00", year
				+ "-05-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("儿童节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("儿童节", "六一-儿童节", year + "-06-01 00:00", year
				+ "-06-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("端午节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("端午划龙舟", "端午-端阳-粽",
				DateConvert.sCalendarLundarToSolar(year, 5, 5) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 5, 5) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("中秋节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("中秋节快乐", "中秋-思恋",
				DateConvert.sCalendarLundarToSolar(year, 8, 15) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 8, 15) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("国庆节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("国庆长假", "国庆-十一-黄金周", year + "-10-01 00:00", year
				+ "-10-01 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("重阳节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("重阳", "登高-重阳-九九",
				DateConvert.sCalendarLundarToSolar(year, 9, 9) + " 00:00",
				DateConvert.sCalendarLundarToSolar(year, 9, 9) + " 23:59", sms
						.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("光棍节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("11 11", "光棍-孤单-单身-11", year + "-11-11 00:00", year
				+ "-11-11 23:59", sms.get(index).getsmstext());
		sc.add(ri);
		
		sms = smscontent.find("圣诞节");
		index = rd.nextInt(sms.size());
		ri = new ReItem("Christmas", "圣诞-Christmas", year + "-12-25 00:00", year
				+ "-12-25 23:59", sms.get(index).getsmstext());
		sc.add(ri);
	}
}
