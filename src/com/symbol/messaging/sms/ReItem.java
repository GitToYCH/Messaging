package com.symbol.messaging.sms;

public class ReItem {
	private int id;
	private int isopen;
	private String title;
	private String keywords;
	private String start;
	private String end;
	private String content;

	public ReItem() {
		super();
	}

	public ReItem(String title, String keywords, String start, String end,
			String content) {
		this.isopen = 0;
		this.title = title;
		this.keywords = keywords;
		this.start = start;
		this.end = end;
		this.content = content;
	}

	public ReItem(int id, int isopen, String title, String keywords,
			String start, String end, String content) {
		this.id = id;
		this.isopen = isopen;
		this.title = title;
		this.keywords = keywords;
		this.start = start;
		this.end = end;
		this.content = content;
	}

	public int getid() {
		return this.id;
	}

	public int getisopen() {
		return this.isopen;
	}

	public String gettitle() {
		return this.title;
	}

	public String getkeywords() {
		return this.keywords;
	}

	public String getstart() {
		return this.start;
	}

	public String getend() {
		return this.end;
	}

	public String getcontent() {
		return this.content;
	}

	public void setid(int id) {
		this.id = id;
	}

	public void setisopen(int isopen) {
		this.isopen = isopen;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public void setkeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setstart(String start) {
		this.start = start;
	}

	public void setend(String end) {
		this.end = end;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "id: " + id + "\n标题: " + title + "关键字：" + keywords
				+ "\n起止时间: " + start + "\n至“: " + end + "\n短信内容： " + content;
		return str;
	}
}
