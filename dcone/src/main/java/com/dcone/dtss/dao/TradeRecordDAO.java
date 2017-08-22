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
	 * ��ȡ���еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ���еĽ��׵�TradeRecords�����б�
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
	 * ��ȡ���еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ���еĽ��׵�TradeRecords�����б�
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
	 * ��ȡĳһ�����еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡĳһ�����еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա�������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ��Ա�������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա����ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ��ʱ��ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ��Ա����ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ���������еĽ��׵�TradeRecords����
	 * @param username   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ������ĳһ�����еĽ��׵�TradeRecords����
	 * @param username   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ��Ա�������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param username   �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ��Ա����ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param username   �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡ��������еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ��������еĽ��׵�TradeRecords�����б�
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
	 * ��ȡ��������еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ��������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û���������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û���������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯĳһ�ֺ�������еĽ��׵�TradeRecords����
	 * @param round   ���������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ����������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯĳһ�ֺ�������еĽ��׵�TradeRecords����
	 * @param itcode   Ա����
	 * @param username  �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ��Ա���ź�������������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯĳһԱ��ĳһ�ֺ�������еĽ��׵�TradeRecords����
	 * @param itcode   Ա����
	 * @param round  ���������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ��Ա����ĳһ�ֺ�������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯĳһԱ��ĳһ�ֺ�������еĽ��׵�TradeRecords����
	 * @param username   Ա������
	 * @param round  ���������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ��Ա����ĳһ�ֺ�������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯĳһԱ��ĳһ�ֺ�������еĽ��׵�TradeRecords����
	 * @param itcode   Ա����
	 * @param username   Ա������
	 * @param round  ���������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ��Ա����ĳһ�ֺ�������еĽ��׵�TradeRecords�����б�
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
	 * ��ȡ���еĴ��ͽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ���еĴ��ͽ��׵�TradeRecords�����б�
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
	 * ��ȡ���еĴ��ͽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ���еĴ��ͽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡ���еĴ��ͽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯ���еĴ��ͽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡ���еĴ��ͽ��׵�TradeRecords����
	 * @param username   �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯ���еĴ��ͽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡ���еĴ��ͽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯ���еĴ��ͽ��׵�TradeRecords�����б�
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
	 * ��ȡ������������׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ���е���������׵�TradeRecords�����б�
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
	 * ��ȡ������������׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ���е���������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param itcode  �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param username  �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param round  ���������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param itcode  �û�Ա����
	 * @param username  �û�
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param username  �û�����
	 * @param round  �����������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param username  �û�����
	 * @param round  �����������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û�ĳһ����������׵�TradeRecords����
	 * @param itcode  �û�Ա����
	 * @param username  �û�����
	 * @param round  �����������
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�ĳһ�ֵ���������׵�TradeRecords�����б�
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
