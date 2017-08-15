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
	 * 获取所有的聊天信息
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    所有聊天信息dc_message对象列表
	 */
	public static List<dc_massage> getAllMessage(JdbcTemplate jdbcTemplate){
		RowMapper<dc_massage> message_mapper = new BeanPropertyRowMapper<dc_massage>(dc_massage.class);
		List<dc_massage> messages = jdbcTemplate.query("select * from dc_message", message_mapper);
		return messages;
	}
	
	/**
	 * 获取某一用户所有的聊天信息
	 * @param uid 用户ID
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    该用户所有聊天信息dc_message对象列表
	 */
	public static List<dc_massage>  getMessagesByUid(int uid,JdbcTemplate jdbcTemplate){
		RowMapper<dc_massage> message_mapper = new BeanPropertyRowMapper<dc_massage>(dc_massage.class);
		List<dc_massage> messages = jdbcTemplate.query("select * from dc_message where uid=?", message_mapper,uid);
		return messages;
	}
	/**
	 * 通过itcode获取某一用户所有的聊天信息
	 * @param itcode 用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    该用户所有聊天信息dc_message对象列表
	 */
	public static List<dc_massage>  getMessagesByItcode(String itcode,JdbcTemplate jdbcTemplate){
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getMessagesByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 插入一条信息
	 * @param uid   用户列表
	 * @param message   消息内容
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:插入成功;false:插入失败
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
	 * 插入一条信息
	 * @param uid   用户列表
	 * @param message   消息内容
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:插入成功;false:插入失败
	 */
	public static boolean createMessageByItcode(String itcode,String message,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return createMessageByUid(user.getUid(), message, jdbcTemplate);
	}

}
