package com.symbol.messaging.DAO;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.symbol.messaging.sms.IgnoreItem;
import com.symbol.messaging.util.DatabaseHelper;

public class IgnorelistDAO {

	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public IgnorelistDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	public void add(IgnoreItem ii) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into ignorelist(name,phone) values(?,?)",
				new Object[] { ii.getname(),ii.getphone()});
	}

	public void delete(IgnoreItem ii) {
		db = helper.getWritableDatabase();
		db.execSQL("delete from ignorelist where id=?", new Object[] { ii.getid() });
	}

	public void clean() {
		db = helper.getWritableDatabase();
		db.execSQL("delete from ignorelist", new Object[] {});
	}

	public ArrayList<IgnoreItem> find() {
		ArrayList<IgnoreItem> li = new ArrayList<IgnoreItem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select id,name,phone from ignorelist",
				new String[] {});
		while (cursor.moveToNext()) {
			li.add(new IgnoreItem(cursor.getInt(0), cursor.getString(1), cursor
					.getString(2)));
		}
		return li;
	}
	
	public ArrayList<IgnoreItem> find(String phone) {
		ArrayList<IgnoreItem> li = new ArrayList<IgnoreItem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select id,name,phone from ignorelist where phone=?",
				new String[] {phone});
		while (cursor.moveToNext()) {
			li.add(new IgnoreItem(cursor.getInt(0), cursor.getString(1), cursor
					.getString(2)));
		}
		return li;
	}
}
