package com.dcone.dtss.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class DropMoneyDAO {

	/**
	 * ��ȡĳһ��Ŀǰ���ʣ�������
	 * @param round  ����������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    Ŀǰ����ʣ�������ܽ��
	 */
	public static int getTotalByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select total from dc_grab_money where round = ?", round);
		return i;
	}
	
	/**
	 * �Ƿ��к�����ڱ���
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   true: Ŀǰ�к�����ڱ���; false:û�к�����ڱ���
	 */
	public static boolean getStatus(JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_grab_money where status = 1");
		if(i>0)
			return true;
		return false;
	}

	/**
	 * ������ڱ���������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return   ������ڱ���������
	 */
	public static int getRound(JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select round from dc_grab_money where status = 1");
		return i;
	}
	
	/**
	 * ��ȡĳһ��Ŀǰ�����״̬
	 * @param round  ����������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    Ŀǰ���ֺ����״̬
	 */
	public static int getStatusByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select status from dc_grab_money where round = ?", round);
		return i;
	}
	
	/**
	 * ����������ʣ����
	 * @param round   ����������
	 * @param money   �ô��������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    true:���³ɹ�;false:����ʧ��
	 */
	public static boolean grabmoney(int round,int money,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_grab_money set total = total- ? where round = ?", new Object[] {money,round});
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ��ʼ��������״̬
	 * @param round   ����������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    true:���³ɹ�;false:����ʧ��
	 */
	public static boolean grabmoneystart(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_grab_money set status = 1 where round = ?", round);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ����������״̬
	 * @param round   ����������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    true:���³ɹ�;false:����ʧ��
	 */
	public static boolean grabmoneystop(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_grab_money set status = 2 where round = ?", round);
		if(i>0) {
			return true;
		}
		return false;
	}

}
