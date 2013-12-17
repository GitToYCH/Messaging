package com.symbol.messaging.sms;

public class LogItem {
	private int id;
	private String time;
	private String operation;
	private String phone;
	private String content;

	public LogItem(int id, String time, String operation, String phone,
			String content) {
		this.id = id;
		this.time = time;
		this.operation = operation;
		this.phone = phone;
		this.content = content;
	}

	public LogItem(String time, String operation, String phone, String content) {
		this.time = time;
		this.operation = operation;
		this.phone = phone;
		this.content = content;

	}

	public int getid() {
		return this.id;
	}

	public String gettime() {
		return this.time;
	}

	public String getoperation() {
		return this.operation;
	}

	public String getphone() {
		return this.phone;
	}

	public String getcontent() {
		return this.content;
	}
}
