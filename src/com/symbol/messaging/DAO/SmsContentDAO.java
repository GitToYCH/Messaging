package com.symbol.messaging.DAO;

import java.util.ArrayList;

import com.symbol.messaging.sms.Sms;
import com.symbol.messaging.util.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SmsContentDAO {
	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public SmsContentDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	public void add(Sms sms) {
		db = helper.getWritableDatabase();
		db.execSQL("insert into sms(classify,collect,text) values(?,?,?)",
				new Object[] {sms.getclassify(),
						sms.getcollect(), sms.getsmstext() });
	}

	public void update(Sms sms) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update sms set classify=?,collect=?,text=? where id=?",
				new Object[] { sms.getclassify(), sms.getcollect(),
						sms.getsmstext(), sms.getid() });
	}

	public ArrayList<Sms> find(int collect){
		ArrayList<Sms> li=new ArrayList<Sms>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select id,classify,collect,text from sms where collect=?",
				new String[] { String.valueOf(collect) });
		while(cursor.moveToNext()){
			li.add(new Sms(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
		}
		return li;
	}
	
	public ArrayList<Sms> find(String classify) {
		ArrayList<Sms> li=new ArrayList<Sms>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select id,classify,collect,text from sms where classify=?",
				new String[] { classify });
		while(cursor.moveToNext()){
			li.add(new Sms(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
		}
		return li;
	}
}
