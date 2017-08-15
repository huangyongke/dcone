package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_massage;
import com.dcone.dtss.model.dc_user;

public class MessageDAO {

	/**
	 * ��ȡ���е�������Ϣ
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    ����������Ϣdc_message�����б�
	 */
	public static List<dc_massage> getAllMessage(JdbcTemplate jdbcTemplate){
		RowMapper<dc_massage> message_mapper = new BeanPropertyRowMapper<dc_massage>(dc_massage.class);
		List<dc_massage> messages = jdbcTemplate.query("select * from dc_message", message_mapper);
		return messages;
	}
	
	/**
	 * ��ȡĳһ�û����е�������Ϣ
	 * @param uid �û�ID
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    ���û�����������Ϣdc_message�����б�
	 */
	public static List<dc_massage>  getMessagesByUid(int uid,JdbcTemplate jdbcTemplate){
		RowMapper<dc_massage> message_mapper = new BeanPropertyRowMapper<dc_massage>(dc_massage.class);
		List<dc_massage> messages = jdbcTemplate.query("select * from dc_message where uid=?", message_mapper,uid);
		return messages;
	}
	/**
	 * ͨ��itcode��ȡĳһ�û����е�������Ϣ
	 * @param itcode �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    ���û�����������Ϣdc_message�����б�
	 */
	public static List<dc_massage>  getMessagesByItcode(String itcode,JdbcTemplate jdbcTemplate){
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getMessagesByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * ����һ����Ϣ
	 * @param uid   �û��б�
	 * @param message   ��Ϣ����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:����ɹ�;false:����ʧ��
	 */
	public static boolean createMessageByUid(int uid,String message,JdbcTemplate jdbcTemplate) {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Timestamp datetime = Timestamp.valueOf(nowTime);
		int i = jdbcTemplate.update("insert into dc_message values(null,?,?,?)", new Object[]{uid,datetime,message});
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * ����һ����Ϣ
	 * @param uid   �û��б�
	 * @param message   ��Ϣ����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:����ɹ�;false:����ʧ��
	 */
	public static boolean createMessageByItcode(String itcode,String message,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return createMessageByUid(user.getUid(), message, jdbcTemplate);
	}

}
