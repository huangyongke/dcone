package com.dcone.dtss.model;

public class LuckyNumberRecord {

	int rid;
	int wid;
	int lucky_money;
	int round;
	public LuckyNumberRecord() {}
	public LuckyNumberRecord(int rid, int wid, int lucky_money, int round) {
		super();
		this.rid = rid;
		this.wid = wid;
		this.lucky_money = lucky_money;
		this.round = round;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getLucky_money() {
		return lucky_money;
	}
	public void setLucky_money(int lucky_money) {
		this.lucky_money = lucky_money;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	
}
