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
	
	/**
	 * 查看是否有红包雨正在发放
	 * @param jdbcTemplate
	 * @return true:有红包雨正在发放;false:没有红包雨在发放
	 */
	public static boolean getStatus(JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from lucky_money where status = 1");
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查看某一轮红包雨的状态
	 * @param jdbcTemplate
	 * @return  0:未开始发放;1:正在发放;2:已发放结束
	 */
	public static int getStatusByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select status from lucky_money where round=?",round);
		return i;
	}
	
	/**
	 * 开始红包雨改变状态
	 * @param round   红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    true:更新成功;false:更新失败
	 */
	public static boolean luckyRainstart(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update lucky_money set status = 1 where round = ?", round);
		if(i>0) {
			return true;
		}
		return false;
	}

	/**
	 * 红包雨发红包改变状态
	 * @param round   红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    true:更新成功;false:更新失败
	 */
	public static boolean luckyRainstop(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update lucky_money set status = 2 where round = ?", round);
		if(i>0) {
			return true;
		}
		return false;
	}

}
