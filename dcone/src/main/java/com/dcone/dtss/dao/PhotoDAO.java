package com.dcone.dtss.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_photo;
import com.dcone.dtss.model.dc_user;

public class PhotoDAO {

	/**
	 * ���û����ͷ��
	 * @param uid	�û�ID
	 * @param image   �û�ͷ��	
	 * @param jdbcTemplate	  Spring JdbcTemplate ����
	 * @return   true:����ͷ��ɹ�;false:����ͷ��ʧ��
	 */
	public static boolean createPhotoByUid(int uid,Byte[] image,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_photo set image=? where uid=?",new Object[] {uid,image});
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * ͨ��itcode���û����ͷ��
	 * @param itcode	�û�Ա����
	 * @param image		�û��ϴ���ͷ��
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return    true:����ͷ��ɹ�;false:����ͷ��ʧ��
	 */
	public static boolean createPhotoByItcode(String itcode,Byte[] image,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return createPhotoByUid(user.getUid(), image, jdbcTemplate);
	}
	
	/**
	 * ��ȡĳһ�û���ͷ��
	 * @param uid   �û�ID
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   ���û���ͷ��
	 */
	public static Byte[] getPhotoByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_photo> user_mapper = new BeanPropertyRowMapper<dc_photo>(dc_photo.class);
		dc_photo image = jdbcTemplate.queryForObject("select * from dc_photo where uid=?", user_mapper,uid);
		return image.getImage();
	}
	
	
	/**
	 * ͨ��itcode��ȡĳһ�û���ͷ��
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   ���û���ͷ��
	 */
	public static Byte[] getPhotoByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getPhotoByUid(user.getUid(), jdbcTemplate);
	}
}
