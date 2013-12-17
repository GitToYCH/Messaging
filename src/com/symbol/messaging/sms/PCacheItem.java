package com.symbol.messaging.sms;

public class PCacheItem {
	private String phone;
	private long currenttime;

	public PCacheItem(String phone, long currenttime) {
		this.phone = phone;
		this.currenttime = currenttime;
	}

	public String getphone() {
		return this.phone;
	}

	public long getcurrenttime() {
		return this.currenttime;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public void setcurrenttime(long currenttime) {
		this.currenttime = currenttime;
	}
}
