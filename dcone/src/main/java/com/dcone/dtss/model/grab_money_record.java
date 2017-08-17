package com.dcone.dtss.model;

public class grab_money_record {

	int rid;
	int wid;
	int money;
	int round;
	String trade_time;
	public grab_money_record() {}
	public grab_money_record(int rid, int wid, int money, int round, String trade_time) {
		super();
		this.rid = rid;
		this.wid = wid;
		this.money = money;
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
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
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
	@Override
	public String toString() {
		return "grab_money_record [rid=" + rid + ", wid=" + wid + ", money=" + money + ", round=" + round
				+ ", trade_time=" + trade_time + "]";
	}
	
}
