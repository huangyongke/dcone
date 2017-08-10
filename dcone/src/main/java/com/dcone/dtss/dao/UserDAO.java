package com.dcone.dtss.dao;

import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;

public class UserDAO {

	/**
	 * 判断用户是否存在
	 * @param itcode	用户的员工号
	 * @param username	  用户的姓名
	 * @param jdbcTemplate	 Spring JdbcTemplate 对象
	 * @return   true:用户在数据库中存在；false:用户不存在
	 */
	public static boolean checkUserInfo(String itcode,String username,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? and username=?", new Object[] {itcode, username});
		if(i>0)	
			return true;
		return false;
	}
	/**
	 * 判断用户登录是否正确
	 * @param itcode	用户的员工号
	 * @param password   用户的密码
	 * @param jdbcTemplate	 Spring JdbcTemplate 对象
	 * @return   true:用户密码正确；false:用户密码错误
	 */
	public static boolean checkUser(String itcode,String password,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? and password=?", new Object[] {itcode,password});
		if(i>0)	
			return true;
		return false;
	}
	
	public static boolean checkPassword(String itcode,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode=? ",user_mapper,itcode);
		if(user.getPassword()==null)
			return false;
		return true;
	}
	
	/**
	 * 设置密码
	 * @param itcode   用户员工号
	 * @param username    用户姓名
	 * @param password    用户设置密码
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    0:用户名和员工号不匹配;1:设置成功;2:设置失败;3:该账户已经设置密码
	 */
	public static int setPassword(String itcode,String username,String password,JdbcTemplate jdbcTemplate) {
		if(checkUserInfo(itcode, username, jdbcTemplate))
			if(!checkPassword(itcode, jdbcTemplate)) {
				int i = jdbcTemplate.update("update dc_user set password = ? where itcode=?",new Object[] {password,itcode});
				if(i>0)
					return 1;
				else
					return 2;
			} else
				return 3;
		else
			return 0;
	}
	/**
	 * 通过用户id获取用户对象
	 * @param uid	用户的id
	 * @param jdbcTemplate	 Spring JdbcTemplate 对象
	 * @return   指定 uid 的用户dc_user对象
	 */
	public static dc_user getUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where uid = ?", user_mapper,uid);
		
		return user;
	}
	
	/**
	 * 通过用户员工号获取用户对象
	 * @param itcode   用户的员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定itcode 用户的dc_user对象
	 */
	public static dc_user getUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode = ?", user_mapper,itcode);
		
		return user;
	}
	
	/**
	 * 通过用户姓名获取用户对象的列表
	 * @param username	 用户的姓名
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定 username 的用户dc_user对象列表
	 */
	public static List<dc_user> getUsersByusername(String username, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		List<dc_user> users = jdbcTemplate.query("select * from dc_user where username = ?", user_mapper,username);
		
		return users;
	}
	
	/**
	 * 获取所有用户对象列表
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   所有的用户dc_user对象列表
	 */
	public static List<dc_user> getAllUsers(JdbcTemplate jdbcTemplate){
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		List<dc_user> users = jdbcTemplate.query("select * from dc_user", user_mapper);
		return users;
	}
	
	/**
	 * 创建用户
	 * @param itcode   用户的员工号
	 * @param username    用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:创建用户成功; false:创建用户失败
	 */
	public static boolean createuser(String itcode,String username,String password,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.update("insert into dc_user values(null,?,?,?,0)", new Object[]{itcode,username,password});
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * 通过用户id将账户锁住
	 * @param uid   用户id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:用户账户锁住成功; false:用户账户锁住失败
	 */
	public static boolean lockUserByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.update("update wallet set locked = 1 where uid=?",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * 通过用户员工号将账户锁住
	 * @param itcode   用户员工号
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   true:用户账户锁住成功; false:用户账户锁住失败
	 */
	public static boolean lockUserByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=getUserByItcode(itcode, jdbcTemplate);
		return lockUserByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 通过用户id将账户解锁
	 * @param uid   用户id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:用户账户解锁成功; false:用户账户解锁失败
	 */
	public static boolean unlockUserByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.update("update wallet set locked = 0 where uid=?",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * 通过用户员工号将账户解锁
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:用户账户解锁成功; false:用户账户解锁失败
	 */
	public static boolean unlockUserByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=getUserByItcode(itcode, jdbcTemplate);
		return unlockUserByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 通过用户id判断账户是否锁定
	 * @param uid  用户id
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   true:用户账户已锁定; false:用户账户未锁定
	 */
	public static boolean islockByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.queryForInt("select locked from dc_user where uid= ?",uid);
		if(i==1)
			return true;
		return false;
	}
	
	/**
	 * 通过用户员工号查看账户是否锁定
	 * @param itcode    用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:用户账户已锁定; false:用户账户未锁定
	 */
	public static boolean islockByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=getUserByItcode(itcode, jdbcTemplate);
		return islockByUid(user.getUid(), jdbcTemplate);
	}
}
