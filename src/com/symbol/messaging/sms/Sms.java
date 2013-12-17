package com.symbol.messaging.sms;

public class Sms {

	private int id;// 短信的id
	private String classify;// 短信的分类
	private int collect;// 是否收藏
	private String smstext;// 短信的内容

	public Sms() {
		super();
	}

	public Sms(String classify, int collect, String smstext) {
		this.classify = classify;
		this.collect = collect;
		this.smstext = smstext;
	}
	
	public Sms(int id, String classify, int collect, String smstext) {
		this.id = id;
		this.classify = classify;
		this.collect = collect;
		this.smstext = smstext;
	}

	public int getid() {
		return this.id;
	}

	public String getclassify() {
		return this.classify;
	}

	public int getcollect() {
		return this.collect;
	}

	public String getsmstext() {
		return this.smstext;
	}

	public void setid(int id) {
		this.id = id;
	}

	public void setclassify(String classify) {
		this.classify = classify;
	}

	public void setcollect(int collect) {
		this.collect = collect;
	}

	public void setsmstext(String smstext) {
		this.smstext = smstext;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: "+id+"分类：" + this.classify + "\n收藏：" + this.collect + "\n内容："
				+ this.smstext;
	}

}
