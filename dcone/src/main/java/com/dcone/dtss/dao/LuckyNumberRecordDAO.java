package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.LuckyNumberRecord;

public class LuckyNumberRecordDAO {

	/**
	 * 插入一条新的红包记录
	 * @param wid   获得红包的钱包id
	 * @param luckymoney   获得红包的金额
	 * @param round   发红包的轮次
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    true:插入成功;false:插入失败
	 */
	public static boolean newLuckyNumberRecord(int wid,int luckymoney,int round,JdbcTemplate jdbcTemplate) {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Timestamp datetime = Timestamp.valueOf(nowTime);
		int i = jdbcTemplate.update("insert into lucky_money_record values(null,?,?,?,?)", new Object[] {wid,luckymoney,round,datetime});
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
	public static List<LuckyNumberRecord> getAllRecords(JdbcTemplate jdbcTemplate) {
		RowMapper<LuckyNumberRecord> LuckyNumberRecord_mapper = new BeanPropertyRowMapper<LuckyNumberRecord>(LuckyNumberRecord.class);
		List<LuckyNumberRecord> luckNumerRecords = jdbcTemplate.query("select * from lucky_money_record",LuckyNumberRecord_mapper);
		return luckNumerRecords;
	}
}
