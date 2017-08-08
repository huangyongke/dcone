package com.dcone.dtss.model;

public class LuckyNumberRecord {

	int rid;
	int wid;
	int lucky_money;
	int round;
	String trade_time;
	public LuckyNumberRecord() {}
	public LuckyNumberRecord(int rid, int wid, int lucky_money, int round, String trade_time) {
		super();
		this.rid = rid;
		this.wid = wid;
		this.lucky_money = lucky_money;
		this.round = round;
		this.trade_time = trade_time;
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
	public String getTrade_time() {
		return trade_time;
	}
	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}
	
}
