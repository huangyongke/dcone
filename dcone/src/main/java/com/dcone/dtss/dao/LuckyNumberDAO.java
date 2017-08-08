package com.dcone.dtss.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LuckyNumberDAO {

	/**
	 * 获取某一轮目前红包剩余的总数
	 * @param round  红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    目前该轮剩余红包的总金额
	 */
	public static int getTotalByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select total from lucky_money where round = ?", round);
		return i;
	}
	
	/**
	 * 红包雨发红包后更新剩余金额
	 * @param round   红包雨的轮数
	 * @param money   该次所发金额
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    true:更新成功;false:更新失败
	 */
	public static boolean luckyRain(int round,int money,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update lucky_money set total = total- ? where round = ?", new Object[] {money,round});
		if(i>0) {
			return true;
		}
		return false;
	}
}
