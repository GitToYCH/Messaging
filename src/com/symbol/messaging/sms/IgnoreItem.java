package com.symbol.messaging.sms;

public class IgnoreItem {

	private int id;
	private String name;
	private String phone;
	
	public IgnoreItem(int id,String name,String phone){
		this.id=id;
		this.name=name;
		this.phone=phone;
	}
	public IgnoreItem(String name,String phone){
		this.name=name;
		this.phone=phone;
	}
	public int getid(){
		return this.id;
	}
	public String getname(){
		return this.name;
	}
	public String getphone(){
		return this.phone;
	}
	public void setid(int id){
		this.id=id;
	}
	public void setname(String name){
		this.name=name;
	}
	public void setphone(String phone){
		this.phone=phone;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+"\n–’√˚:"+name+"\nµÁª∞:"+phone;
	}
	
}
