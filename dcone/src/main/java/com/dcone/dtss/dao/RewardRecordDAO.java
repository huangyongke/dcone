package com.dcone.dtss.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.ProgramMenu;
import com.dcone.dtss.model.RewardRecord;
import com.dcone.dtss.model.dc_wallet;

public class RewardRecordDAO {

	/**
	 * ��ȡ���н�Ŀ���ͼ�¼
	 * @param jdbcTemplate   Spring JdbcTemplate
	 * @return   ���н�Ŀ���͵�RewardRecord����
	 */
	public static List<RewardRecord> getAllRewardRecord(JdbcTemplate jdbcTemplate){
		RowMapper<RewardRecord> programMenu_mapper = new BeanPropertyRowMapper<RewardRecord>(RewardRecord.class);
		List<RewardRecord> records = jdbcTemplate.query("select * from reward_trade", programMenu_mapper);
		return records;
	}
	
	/**
	 * ��ȡĳһ�������н�Ŀ���ͼ�¼
	 * @param department   ��������
	 * @param jdbcTemplate   Spring JdbcTemplate
	 * @return   �ò������н�Ŀ���͵�RewardRecord����
	 */
	public static List<RewardRecord> getRewardRecordByDepartment(String department,JdbcTemplate jdbcTemplate){
		List<ProgramMenu> programs = ProgramMenuDAO.getProgramByDepartment(department, jdbcTemplate);
		List<RewardRecord> records = new ArrayList<RewardRecord>();
		for(ProgramMenu temp : programs) {
		List<RewardRecord> r = getProgramByPid(temp.getPid(), jdbcTemplate);
		records.addAll(r);
		}
		return records;
	}
	
	/**
	 * ��ȡ���н�Ŀ���ͼ�¼
	 * @param jdbcTemplate   Spring JdbcTemplate
	 * @return   ���н�Ŀ���͵�RewardRecord����
	 */
	public static List<RewardRecord> getRewardRecordByProgram(String program,JdbcTemplate jdbcTemplate){
		ProgramMenu p = ProgramMenuDAO.getProgramByProgram(program, jdbcTemplate);
		return getProgramByPid(p.getPid(), jdbcTemplate);
	}
	
	/**
	 * ���ݽ�Ŀid��ȡ���ͼ�¼
	 * @param pid   ��Ŀid
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ý�Ŀ��¼RewardRecord����
	 */
	public static List<RewardRecord> getProgramByPid(int pid, JdbcTemplate jdbcTemplate) {
		RowMapper<RewardRecord> RewardRecord_mapper = new BeanPropertyRowMapper<RewardRecord>(RewardRecord.class);
		List<RewardRecord> menu = jdbcTemplate.query("select * from program_menu where pid=?",RewardRecord_mapper,pid);
		return menu;
	}
	
	/**
	 * �����û�Ǯ��id��ȡ���ͼ�¼
	 * @param wid   �û�Ǯ��id
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ���û����͵Ľ�Ŀ��¼RewardRecord����
	 */
	public static List<RewardRecord> getProgramByWid(int wid, JdbcTemplate jdbcTemplate) {
		RowMapper<RewardRecord> RewardRecord_mapper = new BeanPropertyRowMapper<RewardRecord>(RewardRecord.class);
		List<RewardRecord> menu = jdbcTemplate.query("select * from program_menu where wid=?",RewardRecord_mapper,wid);
		return menu;
	}
	public static boolean createRewardRecord(int wid,int pid,int amount,JdbcTemplate jdbcTemplate) {
		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Timestamp datetime = Timestamp.valueOf(nowTime);
		int i = jdbcTemplate.update("insert into reward_trade values(null,?,?,?,?)",new Object[] {wid,pid,amount,datetime});
		if(i>0)
			return true;
		return false;
	}
	
	public static boolean Reward(String itcode,int pid,int amount,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet = WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		if(TradeDAO.createTrade(wallet.getWid(), -amount, "����", jdbcTemplate))
			if(ProgramMenuDAO.programReward(pid, amount, jdbcTemplate))
				if(createRewardRecord(wallet.getWid(), pid,amount, jdbcTemplate))
					if(WalletDAO.wallet_add(wallet.getWid(), -amount, jdbcTemplate))
						return true;
		return false;
	}
}
