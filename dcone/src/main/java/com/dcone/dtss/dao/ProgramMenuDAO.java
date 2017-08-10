package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.ProgramMenu;

public class ProgramMenuDAO {

	/**
	 * 根据节目id获取所有节目
	 * @param pid   节目id
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该部门所有的节目对象
	 */
	public static ProgramMenu getProgramByPid(int pid,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		ProgramMenu program = jdbcTemplate.queryForObject("select * from program_menu where pid=?",programMenu_mapper,pid);
		return program;
	}
	
	
	
	/**
	 * 根据节目名称获取节目
	 * @param program   节目名称
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该节目ProgramMenu对象
	 */
	public static ProgramMenu getProgramByProgram(String program,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		ProgramMenu p = jdbcTemplate.queryForObject("select * from program_menu where program=?",programMenu_mapper,program);
		return p;
	}
	
	/**
	 * 根据节目部门获取所有节目
	 * @param department   节目部门
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该部门所有的节目对象
	 */
	public static List<ProgramMenu> getProgramByDepartment(String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where department=?",programMenu_mapper,department);
		return program;
	}
	/**
	 * 获取节目对象列表
	 * @param jdbcTemplate  Spring JdbcTemplate 对象
	 * @return   节目对象ProgramMenu列表
	 */
	public static List<ProgramMenu> getALLProgram(JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> menu = jdbcTemplate.query("select * from program_menu",programMenu_mapper);
		return menu;
	}
	
	public static List<ProgramMenu> getProgramsBySequence(JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
	 	List<ProgramMenu> menu = jdbcTemplate.query("select * from program_menu order by sequence", programMenu_mapper);
		return menu;
	}
	
	public static boolean programReward(int pid,int amount,JdbcTemplate jdbcTemplate){
		int i = jdbcTemplate.update("update program_menu set reward = reward+? where pid = ?", new Object[] {amount,pid});
		if(i>0)
			return true;
		return false;
	}
	
	public static boolean newProgram(String program,String actor,String department,String showtime,int sequence, JdbcTemplate jdbcTemplate) {
		List<ProgramMenu> menu = getProgramsBySequence(jdbcTemplate);
		
		return false;
	}
}
