package com.dcone.dtss.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class DropMoneyDAO {

	/**
	 * 获取某一轮目前红包剩余的总数
	 * @param round  红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    目前该轮剩余红包的总金额
	 */
	public static int getTotalByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select total from dc_grab_money where round = ?", round);
		return i;
	}
	
	/**
	 * 是否有红包正在被抢
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   true: 目前有红包正在被抢; false:没有红包正在被抢
	 */
	public static boolean getStatus(JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select count(*) from dc_grab_money where status = 1");
		if(i>0)
			return true;
		return false;
	}

	/**
	 * 红包正在被抢的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return   红包正在被抢的轮数
	 */
	public static int getRound(JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select round from dc_grab_money where status = 1");
		return i;
	}
	
	/**
	 * 获取某一轮目前红包的状态
	 * @param round  红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    目前该轮红包的状态
	 */
	public static int getStatusByRound(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.queryForInt("select status from dc_grab_money where round = ?", round);
		return i;
	}
	
	/**
	 * 抢红包后更新剩余金额
	 * @param round   红包雨的轮数
	 * @param money   该次所发金额
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    true:更新成功;false:更新失败
	 */
	public static boolean grabmoney(int round,int money,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_grab_money set total = total- ? where round = ?", new Object[] {money,round});
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 开始红包后更新状态
	 * @param round   红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    true:更新成功;false:更新失败
	 */
	public static boolean grabmoneystart(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_grab_money set status = 1 where round = ?", round);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 抢红包后更新状态
	 * @param round   红包雨的轮数
	 * @param jdbcTemplate    Spring JdbcTemplate 对象
	 * @return    true:更新成功;false:更新失败
	 */
	public static boolean grabmoneystop(int round,JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("update dc_grab_money set status = 2 where round = ?", round);
		if(i>0) {
			return true;
		}
		return false;
	}

}
