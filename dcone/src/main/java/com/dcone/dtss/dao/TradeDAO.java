package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class TradeDAO {

	/**
	 * 通过交易编号获取交易对象
	 * @param tid   交易编号
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   指定tid的交易对象dc_trade
	 */
	public static dc_trade getTradeByTid(int tid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		dc_trade trade = jdbcTemplate.queryForObject("select * from dc_trade where tid = ?", trade_mapper,tid);
		return trade;
	}
	
	/**
	 * 通过钱包id获取该用户的交易对象列表
	 * @param wid   用户的钱包id
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   指定钱包wid的交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades = jdbcTemplate.query("select * from dc_trade where wid = ?", trade_mapper,wid);
		return trades;
	}
	
	/**
	 * 通过用户id获取该用户的交易对象列表
	 * @param uid   用户id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定uid用户的交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByUid(int uid,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByUid(uid, jdbcTemplate);
		return getTradesByWid(wallet.getWid(), jdbcTemplate);
	}
	
	/**
	 * 通过用户对象获取该用户的交易对象列表
	 * @param user   用户dc_user对象
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    指定用户的交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByUser(dc_user user,JdbcTemplate jdbcTemplate) {
		return getTradesByUid(user.getUid(),jdbcTemplate);
	}
	
	/**
	 * 通过用户员工号获取该用户的交易对象列表
	 * @param itcode   用户的员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    指定itcode用户的交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		return getTradesByWid(wallet.getWid(), jdbcTemplate);
	}
	
	/**
	 * 通过钱包id获取该用户某一时间段的交易对象列表
	 * @param wid   用户的钱包id
	 * @param start   开始时间
	 * @param end   截止时间
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定wid用户在 指定时间 内交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByWid(int wid,String start,String end,JdbcTemplate jdbcTemplate) {
		Timestamp starttime = Timestamp.valueOf(start);
		Timestamp endtime = Timestamp.valueOf(end);
		System.out.println(starttime);
		System.out.println(endtime);
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades = jdbcTemplate.query("select * from dc_trade where wid = ? and tradetime > ? && tradetime < ?", trade_mapper,new Object[] {wid,starttime,endtime});
		return trades;
	}
	
	/**
	 * 通过用户id获取该用户在某一时间段的交易对象列表
	 * @param uid    用户id
	 * @param start   开始时间
	 * @param end   截止时间
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定uid用户在 指定时间内 交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByUid(int uid,String start,String end,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByUid(uid, jdbcTemplate);
		return getTradesByWid(wallet.getWid(), start, end, jdbcTemplate);
	}
	
	/**
	 * 通过用户员工号获取该用户在某一时间段的交易对象列表
	 * @param itcode    用户的员工号
	 * @param start   开始时间
	 * @param end   截止时间
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定itcode用户在 指定时间内交易对象dc_trade列表
	 */
	public static List<dc_trade> getTradesByItcode(String itcode,String start,String end,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		return getTradesByWid(wallet.getWid(),start,end, jdbcTemplate);
	}
	
	/**
	 * 获取某一时间段内所有的交易对象
	 * @param start 
	 * @param end
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<dc_trade> getTradesByTime(String start,String end,JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades = jdbcTemplate.query("select * from dc_trade where tradetime > ? & tradetime < ?", trade_mapper,new Object[] {start,end});
		return trades;
	}
	
	/**
	 * 获取所有的交易对象列表
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   所有交易dc_trade对象列表
	 */
	public static List<dc_trade> getAllTrades(JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades = jdbcTemplate.query("select * from dc_trade", trade_mapper);
		return trades;
	}
	
	/**
	 * 判定交易能否进行
	 * @param wid   用户钱包id
	 * @param amount    用户交易金额
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:交易能够进行;false:交易不能进行
	 */
	private static boolean preTrade(int wid,int amount,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet= WalletDAO.getWalletByWid(wid, jdbcTemplate);
		if(!UserDAO.islockByUid(wallet.getUid(), jdbcTemplate)) {
			if(wallet.getAmount()+amount>=0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 进行一次交易
	 * @param wid  钱包ID
	 * @param amount   交易金额
	 * @param memo   交易备注
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   true:交易成功;false:交易失败
	 */
	public static boolean createTrade(int wid,int amount,String memo,JdbcTemplate jdbcTemplate) {
		if(preTrade(wid, amount, jdbcTemplate)) {
			Date date = new Date();
			String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			Timestamp datetime = Timestamp.valueOf(nowTime);
			int i = jdbcTemplate.update("insert into dc_trade values(null,?,?,?,?)",new Object[] {wid,amount,datetime,memo});
			
			if(i>0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 用户账户充值
	 * @param itcode   用户的员工号
	 * @param username   用户的姓名
	 * @param amount   充值金额
	 * @param memo   交易的备注
	 * @param locale   所在时区
	 * @param jdbcTemplate  Spring JdbcTemplate 对象
	 * @return  1:充值成功; -1:用户不存在; 0:其他原因
	 */
	public static boolean balance_add(String itcode,int amount,String memo , JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		if(createTrade(wallet.getWid(), amount, memo, jdbcTemplate)){
			if(WalletDAO.wallet_add(wallet.getWid(), amount, jdbcTemplate)) {
				return true;
			}
		}
		return false;
	}
}
