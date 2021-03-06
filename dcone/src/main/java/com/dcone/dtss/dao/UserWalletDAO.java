package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_user_wallet;

public class UserWalletDAO {

	/**
	 * 通过用户id获取钱包信息对象
	 * @param uid   用户id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定id用户的钱包信息dc_user_wallet对象
	 */
	public static dc_user_wallet getWalletInfoByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		dc_user_wallet user_wallet = jdbcTemplate.queryForObject("select * from dc_user_wallet where uid=?",user_wallet_mapper ,uid);
		return user_wallet;
	}
	
	/**
	 * 模糊查询通过用户姓名获取钱包信息对象
	 * @param username   用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   模糊查询指定用户的钱包信息dc_user_wallet对象
	 */
	public static List<dc_user_wallet> getDimWalletInfoByUsername(String username,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		List<dc_user_wallet> user_wallets = jdbcTemplate.query("select * from dc_user_wallet where username like ?",user_wallet_mapper ,"%"+username+"%");
		return user_wallets;
	}
	
	/**
	 * 通过用户姓名获取钱包信息对象
	 * @param username   用户姓名
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定用户的钱包信息dc_user_wallet对象
	 */
	public static List<dc_user_wallet> getWalletInfoByUsername(String username,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		List<dc_user_wallet> user_wallets = jdbcTemplate.query("select * from dc_user_wallet where username=?",user_wallet_mapper ,username);
		return user_wallets;
	}
	
	/**
	 * 通过用户对象获取钱包信息对象
	 * @param user   用户对象
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定用户的钱包信息dc_user_wallet对象
	 */
	public static dc_user_wallet getWalletInfoByUser(dc_user user,JdbcTemplate jdbcTemplate) {
		return getWalletInfoByUid(user.getUid(), jdbcTemplate);
	}

	/**
	 * 通过用户员工号获取钱包信息对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定用户员工号的钱包信息dc_user_wallet对象
	 */
	public static dc_user_wallet getWalletInfoByItcode(String itcode,JdbcTemplate jdbcTemplate){
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getWalletInfoByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 模糊查询通过用户员工号获取钱包信息对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   模糊查询指定用户员工号的钱包信息dc_user_wallet对象
	 */
	public static List<dc_user_wallet> getDimWalletInfoByItcode(String itcode,JdbcTemplate jdbcTemplate){
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		List<dc_user_wallet> user_wallets = jdbcTemplate.query("select * from dc_user_wallet where itcode like ?",user_wallet_mapper ,"%"+itcode+"%");
		return user_wallets;
	}
	
	/**
	 * 通过用户员工号获取钱包信息对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定用户员工号的钱包信息dc_user_wallet对象
	 */
	public static dc_user_wallet getWalletInfoByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate){
		dc_user user = UserDAO.getUserByItcodeUsername(itcode, username, jdbcTemplate);
		return getWalletInfoByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 模糊查询通过用户员工号获取钱包信息对象
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   模糊查询指定用户员工号的钱包信息dc_user_wallet对象
	 */
	public static List<dc_user_wallet> getDimWalletInfoByItcodeUsername(String itcode,String username,JdbcTemplate jdbcTemplate){
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		List<dc_user_wallet> user_wallets = jdbcTemplate.query("select * from dc_user_wallet where itcode like ? and username like ?",user_wallet_mapper ,new Object[] {"%"+itcode+"%","%"+username+"%"});
		return user_wallets;
	}
	
	/**
	 * 获取所有的钱包信息对象
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   所有用户的钱包信息dc_user_wallet对象列表
	 */
	public static List<dc_user_wallet> getAllWalletInfoforUser(JdbcTemplate jdbcTemplate){
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		List<dc_user_wallet> user_wallets = jdbcTemplate.query("select * from dc_user_wallet",user_wallet_mapper);
		return user_wallets;
	}
}
