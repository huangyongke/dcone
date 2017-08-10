package com.dcone.dtss.dao;

import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;

public class UserDAO {

	/**
	 * �ж��û��Ƿ����
	 * @param itcode	�û���Ա����
	 * @param username	  �û�������
	 * @param jdbcTemplate	 Spring JdbcTemplate ����
	 * @return   true:�û������ݿ��д��ڣ�false:�û�������
	 */
	public static boolean checkUserInfo(String itcode,String username,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? and username=?", new Object[] {itcode, username});
		if(i>0)	
			return true;
		return false;
	}
	/**
	 * �ж��û���¼�Ƿ���ȷ
	 * @param itcode	�û���Ա����
	 * @param password   �û�������
	 * @param jdbcTemplate	 Spring JdbcTemplate ����
	 * @return   true:�û�������ȷ��false:�û��������
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
	 * ��������
	 * @param itcode   �û�Ա����
	 * @param username    �û�����
	 * @param password    �û���������
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    0:�û�����Ա���Ų�ƥ��;1:���óɹ�;2:����ʧ��;3:���˻��Ѿ���������
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
	 * ͨ���û�id��ȡ�û�����
	 * @param uid	�û���id
	 * @param jdbcTemplate	 Spring JdbcTemplate ����
	 * @return   ָ�� uid ���û�dc_user����
	 */
	public static dc_user getUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where uid = ?", user_mapper,uid);
		
		return user;
	}
	
	/**
	 * ͨ���û�Ա���Ż�ȡ�û�����
	 * @param itcode   �û���Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��itcode �û���dc_user����
	 */
	public static dc_user getUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode = ?", user_mapper,itcode);
		
		return user;
	}
	
	/**
	 * ͨ���û�������ȡ�û�������б�
	 * @param username	 �û�������
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ�� username ���û�dc_user�����б�
	 */
	public static List<dc_user> getUsersByusername(String username, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		List<dc_user> users = jdbcTemplate.query("select * from dc_user where username = ?", user_mapper,username);
		
		return users;
	}
	
	/**
	 * ��ȡ�����û������б�
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ���е��û�dc_user�����б�
	 */
	public static List<dc_user> getAllUsers(JdbcTemplate jdbcTemplate){
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		List<dc_user> users = jdbcTemplate.query("select * from dc_user", user_mapper);
		return users;
	}
	
	/**
	 * �����û�
	 * @param itcode   �û���Ա����
	 * @param username    �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�����û��ɹ�; false:�����û�ʧ��
	 */
	public static boolean createuser(String itcode,String username,String password,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.update("insert into dc_user values(null,?,?,?,0)", new Object[]{itcode,username,password});
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * ͨ���û�id���˻���ס
	 * @param uid   �û�id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�û��˻���ס�ɹ�; false:�û��˻���סʧ��
	 */
	public static boolean lockUserByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.update("update wallet set locked = 1 where uid=?",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * ͨ���û�Ա���Ž��˻���ס
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   true:�û��˻���ס�ɹ�; false:�û��˻���סʧ��
	 */
	public static boolean lockUserByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=getUserByItcode(itcode, jdbcTemplate);
		return lockUserByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * ͨ���û�id���˻�����
	 * @param uid   �û�id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�û��˻������ɹ�; false:�û��˻�����ʧ��
	 */
	public static boolean unlockUserByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.update("update wallet set locked = 0 where uid=?",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * ͨ���û�Ա���Ž��˻�����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�û��˻������ɹ�; false:�û��˻�����ʧ��
	 */
	public static boolean unlockUserByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=getUserByItcode(itcode, jdbcTemplate);
		return unlockUserByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * ͨ���û�id�ж��˻��Ƿ�����
	 * @param uid  �û�id
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   true:�û��˻�������; false:�û��˻�δ����
	 */
	public static boolean islockByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i=jdbcTemplate.queryForInt("select locked from dc_user where uid= ?",uid);
		if(i==1)
			return true;
		return false;
	}
	
	/**
	 * ͨ���û�Ա���Ų鿴�˻��Ƿ�����
	 * @param itcode    �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�û��˻�������; false:�û��˻�δ����
	 */
	public static boolean islockByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=getUserByItcode(itcode, jdbcTemplate);
		return islockByUid(user.getUid(), jdbcTemplate);
	}
}
