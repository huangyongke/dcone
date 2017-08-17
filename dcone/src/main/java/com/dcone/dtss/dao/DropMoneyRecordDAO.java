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
	 * ����һ���µĺ����¼
	 * @param wid   ��ú����Ǯ��id
	 * @param money   ��ú���Ľ��
	 * @param round   ��������ִ�
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    true:����ɹ�;false:����ʧ��
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
	 * ��ȡ���еĺ����¼����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    ���к����¼LuckyNumberRecord�����б�
	 */
	public static List<grab_money_record> getAllRecords(JdbcTemplate jdbcTemplate) {
		RowMapper<grab_money_record> grabmoneyrecord_mapper = new BeanPropertyRowMapper<grab_money_record>(grab_money_record.class);
		List<grab_money_record> luckNumerRecords = jdbcTemplate.query("select * from lucky_money_record",grabmoneyrecord_mapper);
		return luckNumerRecords;
	}
	
	/**
	 * ��ȡĳһ�û�������¼
	 * @param wid   �û�Ǯ��ID
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ���û�������¼LuckyNumberRecord�����б�
	 */
	public static List<grab_money_record> getRecordsByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<grab_money_record> grab_money_record_mapper = new BeanPropertyRowMapper<grab_money_record>(grab_money_record.class);
		List<grab_money_record> luckNumerRecords = jdbcTemplate.query("select * from grab_money_record where wid=?",grab_money_record_mapper,wid);
		return luckNumerRecords;
	}
	
	/**
	 * ĳһ�ֺ�����¼
	 * @param round   ����������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    ���ֺ�����¼LuckyNumberRecord�����б�
	 */
	public static List<grab_money_record> getRecordsByRound(int round,JdbcTemplate jdbcTemplate) {
		RowMapper<grab_money_record> grab_money_record_mapper = new BeanPropertyRowMapper<grab_money_record>(grab_money_record.class);
		List<grab_money_record> luckNumerRecords = jdbcTemplate.query("select * from grab_money_record where round=?",grab_money_record_mapper,round);
		return luckNumerRecords;
	}
}
