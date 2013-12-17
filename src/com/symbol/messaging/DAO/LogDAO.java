package com.symbol.messaging.DAO;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.symbol.messaging.sms.LogItem;
import com.symbol.messaging.util.DatabaseHelper;

public class LogDAO {

	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public LogDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	public void add(LogItem si) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into log(time,operation,phone,content) values(?,?,?,?)",
				new Object[] { si.gettime(), si.getoperation(), si.getphone(),
						si.getcontent() });
	}

	public void delete(LogItem si) {
		db = helper.getWritableDatabase();
		db.execSQL("delete from log where id=?", new Object[] { si.getid() });
	}

	public void clean() {
		db = helper.getWritableDatabase();
		db.execSQL("delete from log", new Object[] {});
	}

	public ArrayList<LogItem> find() {
		ArrayList<LogItem> li = new ArrayList<LogItem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select id,time,operation,phone,content from log",
				new String[] {});
		while (cursor.moveToNext()) {
			li.add(new LogItem(cursor.getInt(0), cursor.getString(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4)));
		}
		return li;
	}

}
