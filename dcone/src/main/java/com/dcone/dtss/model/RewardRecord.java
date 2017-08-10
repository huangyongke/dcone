package com.dcone.dtss.model;

public class RewardRecord {
	int rid;
	int wid;
	int pid;
	int amount;
	String tradetime;
	public RewardRecord() {}
	public RewardRecord(int rid, int wid, int pid, int amount, String tradetime) {
		super();
		this.rid = rid;
		this.wid = wid;
		this.pid = pid;
		this.amount = amount;
		this.tradetime = tradetime;
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
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTradetime() {
		return tradetime;
	}
	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	@Override
	public String toString() {
		return "RewardRecord [rid=" + rid + ", wid=" + wid + ", pid=" + pid + ", amount=" + amount + ", tradetime="
				+ tradetime + "]";
	}
	
}
