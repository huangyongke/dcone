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
	 * ��ȡĳһ�����еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡָ��Ա�������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û����еĽ��׵�TradeRecords�����б�
	 */
	public static List<TradeRecords> getTradeRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ?;",itcode);
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and tradetime >= ?;",new Object[] {itcode,starttime});
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and tradetime <= ?;",new Object[] {itcode,stoptime});
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and tradetime >= ? and tradetime<= ?;",new Object[] {itcode,starttime,stoptime});
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
	 * ��ȡָ��Ա����ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
	 */
	public static List<TradeRecords> getTradeRecordsByItcode(String itcode,String variety,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ?;",new Object[] {itcode,variety});
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? and tradetime >= ?;",new Object[] {itcode,variety,starttime});
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? and tradetime <= ?;",new Object[] {itcode,variety,stoptime});
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
		List record = jdbcTemplate.queryForList("select itcode,username,volume,tradetime,memo from dc_user natural join dc_wallet,dc_trade where dc_trade.wid=dc_wallet.wid and itcode = ? and memo = ? and tradetime >= ? and tradetime<= ?;",new Object[] {itcode,variety,starttime,stoptime});
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
	 * ģ����ѯ��ȡָ���������еĽ��׵�TradeRecords����
	 * @param username   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ������ĳһ�����еĽ��׵�TradeRecords����
	 * @param username   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ��Ա�������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param username   �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û����еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡָ��Ա����ĳһ�����еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param username   �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return   ģ����ѯָ���û�ĳһ�����еĽ��׵�TradeRecords�����б�
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
	 * ��ȡ��������еĽ��׵�TradeRecords����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ��������еĽ��׵�TradeRecords�����б�
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
	 * ģ����ѯ��ȡĳһ�û���������еĽ��׵�TradeRecords����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û���������еĽ��׵�TradeRecords�����б�
	 */
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ?;","%"+itcode+"%");
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
	public static List<TradeRecords> getDimluckymaoneyRecordsbyuseranme(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ?;","%"+username+"%");
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
	public static List<TradeRecords> getDimluckymaoneyRecordsbyround(int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and round like ?;","%"+round+"%");
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
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcodeusername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and like ?;",new Object[]{"%"+itcode+"%","%"+username+"%"});
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
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcoderound(String itcode,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and round like ?;",new Object[]{"%"+itcode+"%","%"+round+"%"});
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
	public static List<TradeRecords> getDimluckymaoneyRecordsbyusernameround(String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and username like ? and round like ?;",new Object[]{"%"+username+"%","%"+round+"%"});
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
	public static List<TradeRecords> getDimluckymaoneyRecordsbyitcodeusernameround(String itcode,String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,lucky_money,trade_time,round from dc_user natural join dc_wallet,lucky_money_record where lucky_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round like ?;",new Object[]{"%"+itcode+"%","%"+username+"%","%"+round+"%"});
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
		List record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid;");
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
	public static List<TradeRecords> getDimRewardRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ?;","%"+itcode+"%");
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
	public static List<TradeRecords> getDimRewardRecordsByusername(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and username like ?;","%"+username+"%");
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
	public static List<TradeRecords> getDimRewardRecordsByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,amonut,tradetime from dc_user natural join dc_wallet,reward_trade where reward_trade.wid=dc_wallet.wid and itcode like ? and username like ?;",new Object[] {"%"+itcode,"%"+username+"%"});
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
	 * ģ����ѯ��ȡĳһ�û���������׵�TradeRecords����
	 * @param itcode  �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate����
	 * @return    ģ����ѯָ���û�����������׵�TradeRecords�����б�
	 */
	public static List<TradeRecords> getDimGrabluckyRecordsByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ?;","%"+itcode+"%");
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
	public static List<TradeRecords> getDimGrabluckyRecordsByUsername(String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ?;","%"+username+"%");
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
	public static List<TradeRecords> getDimGrabluckyRecordsByRound(int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and round like ?;","%"+round+"%");
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
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ?;",new Object[]{"%"+itcode+"%","%"+username+"%"});
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
	public static List<TradeRecords> getDimGrabluckyRecordsByUsernameRound(String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and username like ? and round like ?;",new Object[] {"%"+username+"%","%"+round+"%"});
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
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodeRound(String itcode,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and round like ?;",new Object[] {"%"+itcode+"%","%"+round+"%"});
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
	public static List<TradeRecords> getDimGrabluckyRecordsByItcodeUsernameRound(String itcode,String username,int round,JdbcTemplate jdbcTemplate) {
		List record = jdbcTemplate.queryForList("select itcode,username,money,trade_time,round from dc_user natural join dc_wallet,grab_money_record where grab_money_record.wid=dc_wallet.wid and itcode like ? and username like ? and round like ?;",new Object[] {"%"+itcode+"%","%"+username+"%","%"+round+"%"});
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
