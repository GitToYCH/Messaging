package com.symbol.messaging.sms;

public class Sms {

	private int id;// ���ŵ�id
	private String classify;// ���ŵķ���
	private int collect;// �Ƿ��ղ�
	private String smstext;// ���ŵ�����

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
		return "id: "+id+"���ࣺ" + this.classify + "\n�ղأ�" + this.collect + "\n���ݣ�"
				+ this.smstext;
	}

}
