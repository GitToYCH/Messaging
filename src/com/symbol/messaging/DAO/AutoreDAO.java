package com.symbol.messaging.DAO;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.symbol.messaging.sms.ReItem;
import com.symbol.messaging.util.DatabaseHelper;

public class AutoreDAO {
	private SQLiteDatabase db;
	private DatabaseHelper helper;

	public AutoreDAO(Context context) {
		helper = new DatabaseHelper(context);
	}

	public void add(ReItem ri) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into autore(isopen,title,keywords,start,end,content) values(?,?,?,?,?,?)",
				new Object[] { 0, ri.gettitle(), ri.getkeywords(),
						ri.getstart(), ri.getend(), ri.getcontent() });
	}

	public void update(ReItem ri) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update autore set isopen=?,title=?,keywords=?,start=?,end=?,content=? where id=?",
				new Object[] { ri.getisopen(), ri.gettitle(), ri.getkeywords(),
						ri.getstart(), ri.getend(), ri.getcontent(), ri.getid() });
	}

	public void delete(ReItem ri) {
		db = helper.getWritableDatabase();
		db.execSQL("delete from autore where id=?", new Object[] { ri.getid() });
	}

	public ArrayList<ReItem> find(int isopen, String date) {
		ArrayList<ReItem> li = new ArrayList<ReItem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select id,isopen,title,keywords,start,end,content from autore where isopen=? and start<=? and end>=?",
						new String[] { String.valueOf(isopen), date, date });
		while (cursor.moveToNext()) {
			li.add(new ReItem(cursor.getInt(0), cursor.getInt(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getString(6)));
		}
		//起止时间相同的也取出来，它代表一直有效
		Cursor cursor1 = db
				.rawQuery(
						"select id,isopen,title,keywords,start,end,content from autore where isopen=? and start=end",
						new String[] { String.valueOf(isopen) });
		while (cursor1.moveToNext()) {
			li.add(new ReItem(cursor1.getInt(0), cursor1.getInt(1), cursor1
					.getString(2), cursor1.getString(3), cursor1.getString(4),
					cursor1.getString(5), cursor1.getString(6)));
		}
		return li;
	}

	public ArrayList<ReItem> find() {
		ArrayList<ReItem> li = new ArrayList<ReItem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select id,isopen,title,keywords,start,end,content from autore",
						new String[] {});
		while (cursor.moveToNext()) {
			li.add(new ReItem(cursor.getInt(0), cursor.getInt(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5), cursor.getString(6)));
		}
		return li;
	}
}
