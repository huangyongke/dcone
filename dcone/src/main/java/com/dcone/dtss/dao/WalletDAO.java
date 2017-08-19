package com.dcone.dtss.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class WalletDAO {
	/**
	 * 通过用户id获取该用户的钱包对象
	 * @param uid   用户id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定id的用户钱包dc_wallet对象
	 */
	public static dc_wallet getWalletByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		dc_wallet wallet = jdbcTemplate.queryForObject("select * from dc_wallet where uid=?", wallet_mapper,uid);
		return wallet;
	}
	
	/**
	 * 通过钱包id获取该用户的钱包对象
	 * @param wid   钱包的id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   指定wid的钱包dc_wallet对象
	 */
	public static dc_wallet getWalletByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		dc_wallet wallet = jdbcTemplate.queryForObject("select * from dc_wallet where wid=?", wallet_mapper,wid);
		return wallet;
	}
	
	/**
	 * 通过用户员工号获取该用户的钱包对象
	 * @param itcode    用户的员工号
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return	指定 员工号是 itcode 的钱包对象
	 */
	public static dc_wallet getWalletByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getWalletByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 判断用户是否已经激活钱包账户
	 * @param uid   用户id
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:用户已激活钱包账户;false:用户没有激活钱包账户
	 */
	public static boolean isexistByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_wallet where uid =?",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * 判断用户是否已经激活钱包账户
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:用户已激活钱包账户;false:用户没有激活钱包账户
	 */
	public static boolean isexistByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return isexistByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 通过用户id激活钱包
	 * @param uid   用户id
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   true:激活成功;false:激活失败
	 */
	public static boolean initWallet(int uid,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("insert into dc_wallet values(null,?,0)",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * 通过用户员工号激活钱包
	 * @param itcode   用户员工号
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   true:激活成功;false:激活失败
	 */
	public static boolean initWallet(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return initWallet(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * 获取所有钱包的对象
	 * @param jdbcTemplate   Spring JdbcTemplate 对象
	 * @return   所有钱包的dc_wallet对象列表
	 */
	public static List<dc_wallet> getAllWallets(JdbcTemplate jdbcTemplate){
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		List<dc_wallet> wallets = jdbcTemplate.query("select * from dc_wallet", wallet_mapper);
		return wallets;
	}
	
	/**
	 * 向钱包充值
	 * @param wid  钱包id
	 * @param amount   增加金额
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   true:钱包充值成功; false:钱包充值失败
	 */
	public static boolean wallet_add(int wid,int amount,JdbcTemplate jdbcTemplate) {
		System.out.println(amount);
		int i = jdbcTemplate.update("update dc_wallet set amount = amount + ? where wid=?",new Object[] {amount,wid});
		if(i>0) 
			return true;
		return false;
	}
	
	public static boolean wallet_subtract(int wid,int amount,JdbcTemplate jdbcTemplate) {
		int money = getWalletByWid(wid, jdbcTemplate).getAmount();
		if(money>=amount) {
			int i = jdbcTemplate.update("update dc_wallet set amount = amount - ? where wid=?",new Object[] {amount,wid});
			if(i>0) 
				return true;
			return false;
		}
		else 
			return false;
	}
}
