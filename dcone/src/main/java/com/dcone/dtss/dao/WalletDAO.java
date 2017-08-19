package com.dcone.dtss.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class WalletDAO {
	/**
	 * ͨ���û�id��ȡ���û���Ǯ������
	 * @param uid   �û�id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��id���û�Ǯ��dc_wallet����
	 */
	public static dc_wallet getWalletByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		dc_wallet wallet = jdbcTemplate.queryForObject("select * from dc_wallet where uid=?", wallet_mapper,uid);
		return wallet;
	}
	
	/**
	 * ͨ��Ǯ��id��ȡ���û���Ǯ������
	 * @param wid   Ǯ����id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��wid��Ǯ��dc_wallet����
	 */
	public static dc_wallet getWalletByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		dc_wallet wallet = jdbcTemplate.queryForObject("select * from dc_wallet where wid=?", wallet_mapper,wid);
		return wallet;
	}
	
	/**
	 * ͨ���û�Ա���Ż�ȡ���û���Ǯ������
	 * @param itcode    �û���Ա����
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return	ָ�� Ա������ itcode ��Ǯ������
	 */
	public static dc_wallet getWalletByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getWalletByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * �ж��û��Ƿ��Ѿ�����Ǯ���˻�
	 * @param uid   �û�id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�û��Ѽ���Ǯ���˻�;false:�û�û�м���Ǯ���˻�
	 */
	public static boolean isexistByUid(int uid,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_wallet where uid =?",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * �ж��û��Ƿ��Ѿ�����Ǯ���˻�
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:�û��Ѽ���Ǯ���˻�;false:�û�û�м���Ǯ���˻�
	 */
	public static boolean isexistByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return isexistByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * ͨ���û�id����Ǯ��
	 * @param uid   �û�id
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   true:����ɹ�;false:����ʧ��
	 */
	public static boolean initWallet(int uid,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("insert into dc_wallet values(null,?,0)",uid);
		if(i>0)
			return true;
		return false;
	}
	
	/**
	 * ͨ���û�Ա���ż���Ǯ��
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   true:����ɹ�;false:����ʧ��
	 */
	public static boolean initWallet(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return initWallet(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * ��ȡ����Ǯ���Ķ���
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ����Ǯ����dc_wallet�����б�
	 */
	public static List<dc_wallet> getAllWallets(JdbcTemplate jdbcTemplate){
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		List<dc_wallet> wallets = jdbcTemplate.query("select * from dc_wallet", wallet_mapper);
		return wallets;
	}
	
	/**
	 * ��Ǯ����ֵ
	 * @param wid  Ǯ��id
	 * @param amount   ���ӽ��
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   true:Ǯ����ֵ�ɹ�; false:Ǯ����ֵʧ��
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
