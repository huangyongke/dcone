package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dcone.dtss.model.TradeRecords;

public class TradeRecordDAO {

	/**
	 * 获取所有的交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllTradeRecords(JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid;");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一类所有的交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllTradeRecords(String variety,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and memo=?;",variety);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}

	
	/**
	 * 模糊查询获取指定员工号所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   模糊查询指定用户所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimTradeRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ?;","%"+itcode+"%");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 模糊查询获取指定员工号某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   模糊查询指定用户某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimTradeRecordsByItcode(String itcode,String variety,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and memo like ?;",new Object[] {"%"+itcode+"%","%"+variety+"%"});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 模糊查询获取指定姓名所有的交易的TradeRecords对象
	 * @param username   用户员姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   模糊查询指定用户所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimTradeRecordsByUsername(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ?;","%"+username+"%");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 模糊查询获取指定姓名某一类所有的交易的TradeRecords对象
	 * @param username   用户员姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   模糊查询指定用户某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimTradeRecordsByUsername(String username,String memo,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and memo=?;",new Object[] {"%"+username+"%",memo});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 模糊查询获取指定员工号所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param username   用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   模糊查询指定用户所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimTradeRecordsByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ?;",new Object[] {"%"+itcode+"%","%"+username+"%"});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}

	/**
	 * 模糊查询获取指定员工号某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param username   用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   模糊查询指定用户某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimTradeRecordsByItcodeUsername(String itcode,String username,String memo,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and memo=?;",new Object[] {"%"+itcode+"%","%"+username+"%",memo});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("volume")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取红包雨所有的交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllluckymaoneyRecords(JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid;");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户红包雨所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyitcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode=?;",itcode);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户红包雨所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyuseranme(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username=?;",username);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 某一轮红包雨所有的交易的TradeRecords对象
	 * @param round   红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyround(int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and round=?;",round);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 某一轮红包雨所有的交易的TradeRecords对象
	 * @param itcode   员工号
	 * @param username  用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定员工号和姓名红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyitcodeusername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode=? and username =?;",new Object[]{itcode,username});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 某一员工某一轮红包雨所有的交易的TradeRecords对象
	 * @param itcode   员工号
	 * @param round  红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定员工号某一轮红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyitcoderound(String itcode,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode=? and round =?;",new Object[]{itcode,round});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 某一员工某一轮红包雨所有的交易的TradeRecords对象
	 * @param username   员工姓名
	 * @param round  红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定员工号某一轮红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyusernameround(String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username=? and round =?;",new Object[]{username,round});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	
	/**
	 * 某一员工某一轮红包雨所有的交易的TradeRecords对象
	 * @param itcode   员工号
	 * @param username   员工姓名
	 * @param round  红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定员工号某一轮红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getluckymaoneyRecordsbyitcodeusernameround(String itcode,String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode=? and username=? and round =?;",new Object[]{itcode,username,round});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("lucky_money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound(((Integer) recordMap.get("round")).intValue());
			//r.setMemo((String) recordMap.get("memo"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取所有的打赏交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllRewardRecords(JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amount,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid;");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amount")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取所有的打赏交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getRewardRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amount,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode=?;",itcode);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amount")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取所有的打赏交易的TradeRecords对象
	 * @param username   用户名称
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getRewardRecordsByusername(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amount,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and username=?;",username);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amount")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取所有的打赏交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getRewardRecordsByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amount,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode=? and username =?;",new Object[] {itcode,username});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amount")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("tradetime");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取所有抢红包交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllGrabluckyRecords(JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid;");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户抢红包交易的TradeRecords对象
	 * @param itcode  用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode=?;",itcode);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户抢红包交易的TradeRecords对象
	 * @param username  用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByUsername(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username=?;",username);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户抢红包交易的TradeRecords对象
	 * @param round  抢红包开启
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByRound(int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and round=?;",round);
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户抢红包交易的TradeRecords对象
	 * @param itcode  用户员工号
	 * @param username  用户
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode=? and username=?;",new Object[]{itcode,username});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户抢红包交易的TradeRecords对象
	 * @param username  用户姓名
	 * @param round  抢红包的轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByUsernameRound(String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username=? and round=?;",new Object[] {username,round});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户抢红包交易的TradeRecords对象
	 * @param username  用户姓名
	 * @param round  抢红包的轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByItcodeRound(String itcode,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode=? and round=?;",new Object[] {itcode,round});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
	
	/**
	 * 获取某一用户某一轮抢红包交易的TradeRecords对象
	 * @param itcode  用户员工号
	 * @param username  用户姓名
	 * @param round  抢红包的轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    指定用户某一轮的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getGrabluckyRecordsByItcodeUsernameRound(String itcode,String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode=? and username=? and round=?;",new Object[] {itcode,username,round});
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("money")).intValue();
			float amount = (float)money/100;
			r.setAmount(amount);
			Timestamp time = (Timestamp) recordMap.get("trade_time");
			String datatime = String.valueOf(time);
			r.setTradetime(datatime);
			r.setRound((Integer) recordMap.get("round"));
			records.add(r);
		}
		return records;
	}
}
