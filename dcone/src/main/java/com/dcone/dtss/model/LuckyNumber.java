package com.dcone.dtss.model;

public class LuckyNumber {

	int lid;
	int round;
	int total;
	public LuckyNumber() {}
	public LuckyNumber(int lid, int round, int total) {
		super();
		this.lid = lid;
		this.round = round;
		this.total = total;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
	
}
