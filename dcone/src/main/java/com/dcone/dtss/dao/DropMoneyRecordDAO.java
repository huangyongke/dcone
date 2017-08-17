package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.LuckyNumberRecord;
import com.dcone.dtss.model.grab_money_record;

public class DropMoneyRecordDAO {

	/**
	 * 插入一条新的红包记录
	 * @param wid   获得红包的钱包id
	 * @param money   获得红包的金额
	 * @param round   发红包的轮次
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    true:插入成功;false:插入失败
	 */
	public static boolean newgrapmoneyRecord(int wid,int money,int round,JdbcTemplate jdbcTemplate) {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Timestamp datetime = Timestamp.valueOf(nowTime);
		int i = jdbcTemplate.update("insert into grab_money_record values(null,?,?,?,?)", new Object[] {wid,money,round,datetime});
		if(i>=0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取所有的红包记录对象
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    所有红包记录LuckyNumberRecord对象列表
	 */
	public static List<grab_money_record> getAllRecords(JdbcTemplate jdbcTemplate) {
		RowMapper<grab_money_record> grabmoneyrecord_mapper = new BeanPropertyRowMapper<grab_money_record>(grab_money_record.class);
		List<grab_money_record> luckNumerRecords = jdbcTemplate.query("select * from lucky_money_record",grabmoneyrecord_mapper);
		return luckNumerRecords;
	}
	
	/**
	 * 获取某一用户红包雨记录
	 * @param wid   用户钱包ID
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   该用户红包雨记录LuckyNumberRecord对象列表
	 */
	public static List<grab_money_record> getRecordsByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<grab_money_record> grab_money_record_mapper = new BeanPropertyRowMapper<grab_money_record>(grab_money_record.class);
		List<grab_money_record> luckNumerRecords = jdbcTemplate.query("select * from grab_money_record where wid=?",grab_money_record_mapper,wid);
		return luckNumerRecords;
	}
	
	/**
	 * 某一轮红包雨记录
	 * @param round   红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    该轮红包雨记录LuckyNumberRecord对象列表
	 */
	public static List<grab_money_record> getRecordsByRound(int round,JdbcTemplate jdbcTemplate) {
		RowMapper<grab_money_record> grab_money_record_mapper = new BeanPropertyRowMapper<grab_money_record>(grab_money_record.class);
		List<grab_money_record> luckNumerRecords = jdbcTemplate.query("select * from grab_money_record where round=?",grab_money_record_mapper,round);
		return luckNumerRecords;
	}
}
