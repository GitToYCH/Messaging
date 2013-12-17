package com.symbol.messaging.DAO;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.symbol.messaging.sms.SendItem;
import com.symbol.messaging.util.DatabaseHelper;

public class AutosendDAO {

	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public AutosendDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	public void add(SendItem si) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into autosend(isopen,title,name,phone,date,time,content) values(?,?,?,?,?,?,?)",
				new Object[] { 0,si.gettitle(), si.getname(), si.getphone(),
						si.getdate(), si.gettime(), si.getcontent() });
	}

	public void update(SendItem si) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update autosend set isopen=?,title=?,name=?,phone=?,date=?,time=?,content=? where id=?",
				new Object[] { si.getisopen(),si.gettitle(), si.getname(), si.getphone(),
						si.getdate(), si.gettime(), si.getcontent(), si.getid() });
	}

	public void delete(SendItem si) {
		db = helper.getWritableDatabase();
		db.execSQL("delete from autosend where id=?",
				new Object[] { si.getid() });
	}

	public ArrayList<SendItem> find() {
		ArrayList<SendItem> li = new ArrayList<SendItem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select id,isopen,title,name,phone,date,time,content from autosend",
				new String[] {});
		while (cursor.moveToNext()) {
			li.add(new SendItem(cursor.getInt(0), cursor.getInt(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getString(6),cursor.getString(7)));
		}
		return li;
	}

}
