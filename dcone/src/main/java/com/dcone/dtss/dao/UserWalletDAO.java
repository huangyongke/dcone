package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_user_wallet;

public class UserWalletDAO {

	/**
	 * ͨ���û�id��ȡǮ����Ϣ����
	 * @param uid   �û�id
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ��id�û���Ǯ����Ϣdc_user_wallet����
	 */
	public static dc_user_wallet getWalletInfoByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		dc_user_wallet user_wallet = jdbcTemplate.queryForObject("select * from dc_user_wallet where uid=?",user_wallet_mapper ,uid);
		return user_wallet;
	}
	
	/**
	 * ͨ���û������ȡǮ����Ϣ����
	 * @param user   �û�����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ���û���Ǯ����Ϣdc_user_wallet����
	 */
	public static dc_user_wallet getWalletInfoByUser(dc_user user,JdbcTemplate jdbcTemplate) {
		return getWalletInfoByUid(user.getUid(), jdbcTemplate);
	}

	/**
	 * ͨ���û�Ա���Ż�ȡǮ����Ϣ����
	 * @param itcode   �û�Ա����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   ָ���û�Ա���ŵ�Ǯ����Ϣdc_user_wallet����
	 */
	public static dc_user_wallet getWalletInfoByItcode(String itcode,JdbcTemplate jdbcTemplate){
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getWalletInfoByUid(user.getUid(), jdbcTemplate);
	}
	
	/**
	 * ��ȡ���е�Ǯ����Ϣ����
	 * @param jdbcTemplate   Spring JdbcTemplate ����
	 * @return   �����û���Ǯ����Ϣdc_user_wallet�����б�
	 */
	public static List<dc_user_wallet> getAllWalletInfoByUser(JdbcTemplate jdbcTemplate){
		RowMapper<dc_user_wallet> user_wallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		List<dc_user_wallet> user_wallets = jdbcTemplate.query("select * from dc_user_wallet",user_wallet_mapper);
		return user_wallets;
	}
}
