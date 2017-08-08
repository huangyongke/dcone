package com.dcone.dtss.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LuckyNumberDAO {

	/**
	 * ��ȡĳһ��Ŀǰ���ʣ�������
	 * @param round  ����������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    Ŀǰ����ʣ�������ܽ��
	 */
	public static int getTotalByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select total from lucky_money where round = ?", round);
		return i;
	}
	
	/**
	 * ����귢��������ʣ����
	 * @param round   ����������
	 * @param money   �ô��������
	 * @param jdbcTemplate    Spring JdbcTemplate ����
	 * @return    true:���³ɹ�;false:����ʧ��
	 */
	public static boolean luckyRain(int round,int money,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update lucky_money set total = total- ? where round = ?", new Object[] {money,round});
		if(i>0) {
			return true;
		}
		return false;
	}
}
