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
	 * ����һ���µĺ����¼
	 * @param wid   ��ú����Ǯ��id
	 * @param luckymoney   ��ú���Ľ��
	 * @param round   ��������ִ�
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    true:����ɹ�;false:����ʧ��
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
	 * ��ȡ���еĺ����¼����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    ���к����¼LuckyNumberRecord�����б�
	 */
	public static List<LuckyNumberRecord> getAllRecords(JdbcTemplate jdbcTemplate) {
		RowMapper<LuckyNumberRecord> LuckyNumberRecord_mapper = new BeanPropertyRowMapper<LuckyNumberRecord>(LuckyNumberRecord.class);
		List<LuckyNumberRecord> luckNumerRecords = jdbcTemplate.query("select * from lucky_money_record",LuckyNumberRecord_mapper);
		return luckNumerRecords;
	}
}
