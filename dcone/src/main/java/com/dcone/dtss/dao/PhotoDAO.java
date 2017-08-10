package com.dcone.dtss.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_photo;
import com.dcone.dtss.model.dc_user;

public class PhotoDAO {

	/**
	 * 给用户添加头像
	 * @param uid	用户ID
	 * @param image   用户头像	
	 * @param jdbcTemplate	  Spring JdbcTemplate 对象
	 * @return   true:创建头像成功;false:创建头像失败
	 */
	public static boolean createPhotoByUid(int uid,Byte[] image,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_photo set image=? where uid=?",new Object[] {uid,image});
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * 通过itcode给用户添加头像
	 * @param itcode	用户员工号
	 * @param image		用户上传的头像
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return    true:创建头像成功;false:创建头像失败
	 */
	public static boolean createPhotoByItcode(String itcode,Byte[] image,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return createPhotoByUid(user.getUid(), image, jdbcTemplate);
	}
	
	/**
	 * 获取某一用户的头像
	 * @param uid   用户ID
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   该用户的头像
	 */
	public static Byte[] getPhotoByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_photo> user_mapper = new BeanPropertyRowMapper<dc_photo>(dc_photo.class);
		dc_photo image = jdbcTemplate.queryForObject("select * from dc_photo where uid=?", user_mapper,uid);
		return image.getImage();
	}
	
	
	/**
	 * 通过itcode获取某一用户的头像
	 * @param itcode   用户员工号
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   该用户的头像
	 */
	public static Byte[] getPhotoByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getPhotoByUid(user.getUid(), jdbcTemplate);
	}
}
