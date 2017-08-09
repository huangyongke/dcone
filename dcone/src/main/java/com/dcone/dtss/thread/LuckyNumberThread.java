package com.dcone.dtss.thread;

import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dcone.dtss.dao.LuckyNumberDAO;
import com.dcone.dtss.dao.LuckyNumberRecordDAO;
import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.dc_wallet;

public class LuckyNumberThread extends Thread{

	boolean flag = false;
	int round;
	JdbcTemplate jdbcTemplate;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		List<dc_wallet> wallets = WalletDAO.getAllWallets(jdbcTemplate);
		if(wallets.isEmpty())
			return;
		while(flag) {
			for(dc_wallet wallet : wallets) {
				if(flag) {
					Random r = new Random();
					int total = LuckyNumberDAO.getTotalByRound(round, jdbcTemplate);
					int money = 0;
					if(total>0) {
						if(total>5000) {
							money = r.nextInt(5000);
						}
						else 
							money = total;
						boolean i = LuckyNumberDAO.luckyRain(round, money,jdbcTemplate);
						boolean j = LuckyNumberRecordDAO.newLuckyNumberRecord(wallet.getWid(), money, round, jdbcTemplate);
						boolean m = WalletDAO.wallet_add(wallet.getWid(), money, jdbcTemplate);
						boolean n = TradeDAO.createTrade(wallet.getWid(), money, "ºì°üÓê", jdbcTemplate);
						if(i &j & m & n) {
							System.out.println("³É¹¦");
						}
						else
							System.out.println("Ê§°Ü");
					}
					else {
						flag=false;
					}
				} else {
					break;
				}
			}
		}
	}
	
	
}
