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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid order by tradetime;");
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
	 * 获取所有的交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllTradeRecordsBytime(String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid order by tradetime;");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and tradetime < ? order by tradetime;",new Object[] {stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and tradetime > ? order by tradetime;",new Object[] {starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {starttime,stoptime});
			}
		}		
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and memo=? order by tradetime;",variety);
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
	public static List<TradeRecords> getAllTradeRecordsBytime(String variety,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and memo=? order by tradetime;",variety);
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and memo=? and tradetime < ? order by tradetime;",new Object[] {variety,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and memo=? and tradetime > ? order by tradetime;",new Object[] {variety,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and memo=? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {variety,starttime,stoptime});
			}
		}
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
	 * 获取指定员工号所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode=? order by tradetime;",itcode);
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
	 * 获取指定员工号某一段时间某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一段时间某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcodestarttime(String itcode,String starttime,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and tradetime >= ? order by tradetime;",new Object[] {itcode,starttime});
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
	 * 获取指定员工号某一段时间某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一段时间某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcodestoptime(String itcode,String stoptime,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and tradetime <= ? order by tradetime order by tradetime;",new Object[] {itcode,stoptime});
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
	 * 获取指定员工号某一段时间某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一段时间某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcodestartstoptime(String itcode,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and tradetime >= ? and tradetime<= ? order by tradetime;",new Object[] {itcode,starttime,stoptime});
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
	public static List<TradeRecords> getDimTradeRecordsByItcodetime(String itcode,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? order by tradetime;","%"+itcode+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and tradetime > ? order by tradetime;",new Object[] {"%"+itcode+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%",starttime,stoptime});
			}
		}
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
	 * 获取指定员工号某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcode(String itcode,String variety,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? order by tradetime;",new Object[] {itcode,variety});
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
	 * 获取指定员工号某一段时间某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一段时间某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcodestarttime(String itcode,String variety,String starttime,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? and tradetime >= ? order by tradetime;",new Object[] {itcode,variety,starttime});
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
	 * 获取指定员工号某一段时间某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一段时间某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcodestoptime(String itcode,String variety,String stoptime,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? and tradetime <= ? order by tradetime;",new Object[] {itcode,variety,stoptime});
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
	 * 获取指定员工号某一段时间某一类所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return   指定用户某一段时间某一类所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getTradeRecordsByItcodestartstoptime(String itcode,String variety,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? and tradetime >= ? and tradetime<= ? order by tradetime;",new Object[] {itcode,variety,starttime,stoptime});
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
	public static List<TradeRecords> getDimTradeRecordsByItcodetime(String itcode,String variety,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and memo = ? order by tradetime;",new Object[] {"%"+itcode+"%",variety});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and memo = ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%",variety,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and memo = ? and tradetime > ? order by tradetime;",new Object[] {"%"+itcode+"%",variety,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and memo = ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%",variety,starttime,stoptime});
			}
		}
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
	public static List<TradeRecords> getDimTradeRecordsByUsernametime(String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? order by tradetime;",new Object[] {"%"+username+"%"});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and tradetime < ? order by tradetime;",new Object[] {"%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and tradetime > ? order by tradetime;",new Object[] {"%"+username+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+username+"%",starttime,stoptime});
			}
		}
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
	public static List<TradeRecords> getDimTradeRecordsByUsernametime(String username,String memo,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and memo = ? order by tradetime;",new Object[] {"%"+username+"%",memo});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and memo = ? and tradetime < ? order by tradetime;",new Object[] {"%"+username+"%",memo,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and memo = ? and tradetime > ? order by tradetime;",new Object[] {"%"+username+"%",memo,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and username like ? and memo = ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+username+"%",memo,starttime,stoptime});
			}
		}
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
	public static List<TradeRecords> getDimTradeRecordsByItcodeUsernametime(String itcode,String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%"});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and tradetime > ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime,stoptime});
			}
		}
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
	public static List<TradeRecords> getDimTradeRecordsByItcodeUsernametime(String itcode,String username,String memo,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and memo = ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",memo});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and memo = ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",memo,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and memo = ? and tradetime > ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",memo,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode like ? and username like ? and memo = ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",memo,starttime,stoptime});
			}
		}
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
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid order by trade_time;");
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
	 * 获取红包雨所有的交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllluckymaoneyRecordsBytime(String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid order by trade_time;");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and trade_time < ? order by trade_time;",stoptime);
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and trade_time > ? order by trade_time;",starttime);
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户红包雨所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcodetime(String itcode,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? order by trade_time;","%"+itcode+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%", starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户红包雨所有的交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyuseranmetime(String username,String starttime ,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? order by trade_time;","%"+username+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and trade_time > ? order by trade_time;",new Object[] {"%"+username+"%", starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",starttime,stoptime});
			}
		}
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
	 * 模糊查询某一轮红包雨所有的交易的TradeRecords对象
	 * @param round   红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyroundtime(int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and round = ? order by trade_time;","%"+round+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and round = ? and trade_time < ? order by trade_time;",new Object[] {round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and round = ? and trade_time > ? order by trade_time;",new Object[] {round, starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {round,starttime,stoptime});
			}
		}
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
	 * 模糊查询某一轮红包雨所有的交易的TradeRecords对象
	 * @param itcode   员工号
	 * @param username  用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定员工号和姓名红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcodeusernametime(String itcode,String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%"});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%", starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime,stoptime});
			}
		}
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
	 * 模糊查询某一员工某一轮红包雨所有的交易的TradeRecords对象
	 * @param itcode   员工号
	 * @param round  红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定员工号某一轮红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcoderoundtime(String itcode,int round,String starttime,String stoptime, JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and round=? order by trade_time;",new Object[] {"%"+itcode+"%",round});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and round = ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and round = ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%",round, starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",round,starttime,stoptime});
			}
		}
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
	 * 模糊查询某一员工某一轮红包雨所有的交易的TradeRecords对象
	 * @param username   员工姓名
	 * @param round  红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定员工号某一轮红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyusernameroundtime(String username,int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and round=? order by trade_time;",new Object[] {"%"+username+"%",round});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and round = ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and round = ? and trade_time > ? order by trade_time;",new Object[] {"%"+username+"%",round, starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",round,starttime,stoptime});
			}
		}
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
	 * 模糊查询某一员工某一轮红包雨所有的交易的TradeRecords对象
	 * @param itcode   员工号
	 * @param username   员工姓名
	 * @param round  红包雨轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定员工号某一轮红包雨所有的交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcodeusernameroundtime(String itcode,String username,int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round=? order by trade_time;",new Object[] { "%"+username+"%",round});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round, starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round,starttime,stoptime});
			}
		}
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
		List record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid order by tradetime;");
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amonut")).intValue();
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
	public static List<TradeRecords> getAllRewardRecordsBytime(String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid order by tradetime;");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and tardetime < ? order by tradetime;",stoptime);
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and tradetime > ? order by tradetime;",starttime);
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {starttime,stoptime});
			}
		}
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amonut")).intValue();
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
	 * 模糊查询获取所有的打赏交易的TradeRecords对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimRewardRecordsByItcodetime(String itcode,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? order by tradetime;","%"+itcode+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and tardetime < ? order by tradetime;",new Object[] {"%"+itcode+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and tradetime > ? order by tradetime;",new Object[] {"%"+itcode+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%",starttime,stoptime});
			}
		}
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amonut")).intValue();
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
	 * 模糊查询获取所有的打赏交易的TradeRecords对象
	 * @param username   用户名称
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimRewardRecordsByusernametime(String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and username like ? order by tradetime;","%"+username+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and username like ? and tardetime < ? order by tradetime;",new Object[] {"%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and username like ? and tradetime > ? order by tradetime;",new Object[] {"%"+username+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and username like ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+username+"%",starttime,stoptime});
			}
		}
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amonut")).intValue();
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
	 * 模糊查询获取所有的打赏交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询所有的打赏交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimRewardRecordsByItcodeUsernametime(String itcode,String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and username like ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%"});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and username like ? and tardetime < ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and username like ? and tradetime > ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and username like ? and tradetime > ? and tradetime < ? order by tradetime;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime,stoptime});
			}
		}
		Iterator it = record.iterator();   
		List<TradeRecords> records = new ArrayList<TradeRecords>();
		while(it.hasNext()) {
			Map recordMap = (Map) it.next();
			TradeRecords r=new TradeRecords();
			r.setItcode((String) recordMap.get("itcode"));
			r.setUsername((String) recordMap.get("username"));
			int money = ((Integer) recordMap.get("amonut")).intValue();
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
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid order by trade_time;");
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
	 * 获取所有抢红包交易的TradeRecords对象
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    所有的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getAllGrabluckyRecordsBytime(String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid order by trade_time;");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and trade_time < ? order by trade_time;",stoptime);
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and trade_time > ? order by trade_time;",starttime);
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户抢红包交易的TradeRecords对象
	 * @param itcode  用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodetime(String itcode,String starttime ,String stoptime, JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? order by trade_time;","%"+itcode+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户抢红包交易的TradeRecords对象
	 * @param username  用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByUsernametime(String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? order by trade_time;","%"+username+"%");
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and trade_time > ? order by trade_time;",new Object[] {"%"+username+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户抢红包交易的TradeRecords对象
	 * @param round  抢红包开启
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByRoundtime(int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and round = ? order by trade_time;",round);
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and round = ? and trade_time < ? order by trade_time;",new Object[] {round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and round = ? and trade_time > ? order by trade_time;",new Object[] {round,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {round,starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户抢红包交易的TradeRecords对象
	 * @param itcode  用户员工号
	 * @param username  用户
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodeUsernametime(String itcode,String username,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? order by trade_time;",new Object[]{"%"+itcode+"%","%"+username+"%"});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户抢红包交易的TradeRecords对象
	 * @param username  用户姓名
	 * @param round  抢红包的轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByUsernameRoundtime(String username,int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and round = ? order by trade_time;",new Object[] {"%"+username+"%",round});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and round = ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and round = ? and trade_time > ? order by trade_time;",new Object[] {"%"+username+"%",round,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+username+"%",round,starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户抢红包交易的TradeRecords对象
	 * @param username  用户姓名
	 * @param round  抢红包的轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodeRoundtime(String itcode,int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and round = ? order by trade_time;",new Object[] {"%"+itcode+"%",round});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and round = ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and round = ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%",round,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%",round,starttime,stoptime});
			}
		}
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
	 * 模糊查询获取某一用户某一轮抢红包交易的TradeRecords对象
	 * @param itcode  用户员工号
	 * @param username  用户姓名
	 * @param round  抢红包的轮数
	 * @param jdbcTemplate   Spring JdbcTemplate对象
	 * @return    模糊查询指定用户某一轮的抢红包交易的TradeRecords对象列表
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodeUsernameRoundtime(String itcode,String username,int round,String starttime,String stoptime,JdbcTemplate jdbcTemplate) {
		List record;
		if(starttime=="") {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round});
			}
			else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round,stoptime});
			}
		} else {
			if(stoptime=="") {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? and trade_time > ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round,starttime});
			} else {
				record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round = ? and trade_time > ? and trade_time < ? order by trade_time;",new Object[] {"%"+itcode+"%","%"+username+"%",round,starttime,stoptime});
			}
		}
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
