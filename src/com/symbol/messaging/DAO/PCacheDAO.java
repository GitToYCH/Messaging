package com.symbol.messaging.DAO;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.symbol.messaging.sms.PCacheItem;
import com.symbol.messaging.util.DatabaseHelper;

public class PCacheDAO {
	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public PCacheDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	public void add(PCacheItem pi) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into phonecache(phone,currenttime) values(?,?)",
				new Object[] { pi.getphone(),pi.getcurrenttime() });
	}

	public void delete(PCacheItem pi) {
		db = helper.getWritableDatabase();
		db.execSQL("delete from phonecache where phone=?", new Object[] { pi.getphone() });
	}
	
	public void update(PCacheItem pi) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update phonecache set currenttime=?where phone=?",
				new Object[] { pi.getcurrenttime(),pi.getphone()});
	}

	public void clean() {
		db = helper.getWritableDatabase();
		db.execSQL("delete from phonecache", new Object[] {});
	}

	public PCacheItem find(String phone) {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select phone,currenttime from phonecache where phone=?",
				new String[] {phone});
		while (cursor.moveToNext()) {
			return new PCacheItem(cursor.getString(0),cursor.getLong(1));
		}
		return null;
	}
}
