package com.symbol.messaging.sms;

public class SendItem {

	private int id;
	private int isopen;
	private String title;
	private String name;
	private String phone;
	private String date;
	private String time;
	private String content;
	public SendItem(){
		super();
	}
	
	public SendItem(String title,String name,String phone,String date,String time,String content){
		this.isopen=0;
		this.title=title;
		this.name=name;
		this.phone=phone;
		this.date=date;
		this.time=time;
		this.content=content;
	}
	
	public SendItem(int id,int isopen,String title,String name,String phone,String date,String time,String content){
		this.id=id;
		this.isopen=isopen;
		this.title=title;
		this.name=name;
		this.phone=phone;
		this.date=date;
		this.time=time;
		this.content=content;
	}
	public int getid(){
		return id;
	}
	public int getisopen(){
		return isopen;
	}
	public String gettitle(){
		return title;
	}
	public String getname(){
		return name;
	}
	public String getphone(){
		return phone;
	}
	public String getdate(){
		return date;
	}
	public String gettime(){
		return time;
	}
	public String getcontent(){
		return content;
	}
	public void setid(int id){
		this.id=id;
	}
	public void setisopen(int isopen){
		this.isopen=isopen;
	}
	public void settitle(String title){
		this.title=title;
	}
	public void setname(String name){
		this.name=name;
	}
	public void setphone(String phone){
		this.phone=phone;
	}
	public void setdate(String date){
		this.date=date;
	}
	public void settime(String time){
		this.time=time;
	}
	public void setcontent(String content){
		this.content=content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str="id: "+id+"\n标题: "+title+"\n电话号码: "+phone+"\n时间: "+date+"\n "+time+"\n短信内容： "+content;
		return str;
	}
	
}
