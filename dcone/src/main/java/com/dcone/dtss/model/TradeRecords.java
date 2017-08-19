package com.dcone.dtss.model;

import java.util.ArrayList;
import java.util.List;

public class TradeRecords {

	String itcode;
	String username;
	float amount;
	String tradetime;
	String memo;
	int round;
	public TradeRecords() {}
	public TradeRecords(String itcode, String username, float amount, String tradetime, String memo, int round) {
		super();
		this.itcode = itcode;
		this.username = username;
		this.amount = amount;
		this.tradetime = tradetime;
		this.memo = memo;
		this.round = round;
	}
	public String getItcode() {
		return itcode;
	}
	public void setItcode(String itcode) {
		this.itcode = itcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getTradetime() {
		return tradetime;
	}
	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	@Override
	public String toString() {
		return "TradeRecords [itcode=" + itcode + ", username=" + username + ", amount=" + amount + ", tradetime="
				+ tradetime + ", memo=" + memo + ", round=" + round + "]";
	}
	
	public static List<TradeRecords>  getwalletinfo(List<dc_user_wallet> wallets){
		List<TradeRecords> trades = new ArrayList<TradeRecords>();
		for(dc_user_wallet wallet : wallets) {
			TradeRecords trade = new TradeRecords();
			trade.setItcode(wallet.getItcode());
			trade.setUsername(wallet.getUsername());
			int amount = wallet.getAmount();
			float money = (float)amount/100;
			trade.setAmount(money);
			trades.add(trade);
		}
		
		return trades;
	}
}
