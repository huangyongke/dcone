package com.dcone.dtss.model;

public class dc_grab_money {

	int gid;
	int round;
	int total;
	int status;
	public dc_grab_money() {}
	public dc_grab_money(int gid, int round, int total, int status) {
		super();
		this.gid = gid;
		this.round = round;
		this.total = total;
		this.status = status;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "dc_grab_money [gid=" + gid + ", round=" + round + ", total=" + total + ", status=" + status + "]";
	}
	
}
