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
	 * ͨ�����ױ�Ż�ȡ���׶���
	 * @param tid   ���ױ��
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   ָ��tid�Ľ��׶���dc_trade
	 */
	public static dc_trade getTradeByTid(int tid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		dc_trade trade = jdbcTemplate.queryForObject("select * from dc_trade where tid = ?", trade_mapper,tid);
		return trade;
	}
	
	/**
	 * ͨ��Ǯ��id��ȡ���û��Ľ��׶����б�
	 * @param wid   �û���Ǯ��id
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   ָ��Ǯ��wid�Ľ��׶���dc_trade�б�
	 */
	public static List<dc_trade> getTradesByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades = jdbcTemplate.query("select * from dc_trade where wid = ?", trade_mapper,wid);
		return trades;
	}
	
	/**
	 * ͨ���û�id��ȡ���û��Ľ��׶����б�
	 * @param uid   �û�id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��uid�û��Ľ��׶���dc_trade�б�
	 */
	public static List<dc_trade> getTradesByUid(int uid,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByUid(uid, jdbcTemplate);
		return getTradesByWid(wallet.getWid(), jdbcTemplate);
	}
	
	/**
	 * ͨ���û������ȡ���û��Ľ��׶����б�
	 * @param user   �û�dc_user����
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    ָ���û��Ľ��׶���dc_trade�б�
	 */
	public static List<dc_trade> getTradesByUser(dc_user user,JdbcTemplate jdbcTemplate) {
		return getTradesByUid(user.getUid(),jdbcTemplate);
	}
	
	/**
	 * ͨ���û�Ա���Ż�ȡ���û��Ľ��׶����б�
	 * @param itcode   �û���Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    ָ��itcode�û��Ľ��׶���dc_trade�б�
	 */
	public static List<dc_trade> getTradesByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		return getTradesByWid(wallet.getWid(), jdbcTemplate);
	}
	
	/**
	 * ͨ��Ǯ��id��ȡ���û�ĳһʱ��εĽ��׶����б�
	 * @param wid   �û���Ǯ��id
	 * @param start   ��ʼʱ��
	 * @param end   ��ֹʱ��
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��wid�û��� ָ��ʱ�� �ڽ��׶���dc_trade�б�
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
	 * ͨ���û�id��ȡ���û���ĳһʱ��εĽ��׶����б�
	 * @param uid    �û�id
	 * @param start   ��ʼʱ��
	 * @param end   ��ֹʱ��
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��uid�û��� ָ��ʱ���� ���׶���dc_trade�б�
	 */
	public static List<dc_trade> getTradesByUid(int uid,String start,String end,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByUid(uid, jdbcTemplate);
		return getTradesByWid(wallet.getWid(), start, end, jdbcTemplate);
	}
	
	/**
	 * ͨ���û�Ա���Ż�ȡ���û���ĳһʱ��εĽ��׶����б�
	 * @param itcode    �û���Ա����
	 * @param start   ��ʼʱ��
	 * @param end   ��ֹʱ��
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��itcode�û��� ָ��ʱ���ڽ��׶���dc_trade�б�
	 */
	public static List<dc_trade> getTradesByItcode(String itcode,String start,String end,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		return getTradesByWid(wallet.getWid(),start,end, jdbcTemplate);
	}
	
	/**
	 * ��ȡĳһʱ��������еĽ��׶���
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
	 * ��ȡ���еĽ��׶����б�
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   ���н���dc_trade�����б�
	 */
	public static List<dc_trade> getAllTrades(JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades = jdbcTemplate.query("select * from dc_trade", trade_mapper);
		return trades;
	}
	
	/**
	 * �ж������ܷ����
	 * @param wid   �û�Ǯ��id
	 * @param amount    �û����׽��
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�����ܹ�����;false:���ײ��ܽ���
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
	 * ����һ�ν���
	 * @param wid  Ǯ��ID
	 * @param amount   ���׽��
	 * @param memo   ���ױ�ע
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   true:���׳ɹ�;false:����ʧ��
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
	 * �û��˻���ֵ
	 * @param itcode   �û���Ա����
	 * @param username   �û�������
	 * @param amount   ��ֵ���
	 * @param memo   ���׵ı�ע
	 * @param locale   ����ʱ��
	 * @param jdbcTemplate  Spring JdbcTemplate ����
	 * @return  1:��ֵ�ɹ�; -1:�û�������; 0:����ԭ��
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
