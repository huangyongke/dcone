package com.dcone.dtss.model;

public class dc_massage {

	int uid;
	String talk_time;
	String massage;
	public dc_massage() {}
	public dc_massage(int uid, String talk_time, String massage) {
		super();
		this.uid = uid;
		this.talk_time = talk_time;
		this.massage = massage;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTalk_time() {
		return talk_time;
	}
	public void setTalk_time(String talk_time) {
		this.talk_time = talk_time;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	@Override
	public String toString() {
		return "dc_massage [uid=" + uid + ", talk_time=" + talk_time + ", massage=" + massage + "]";
	}
	
	
}
