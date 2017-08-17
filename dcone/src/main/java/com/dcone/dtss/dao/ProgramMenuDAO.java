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
	public static List<ProgramMenu> getProgramByProgram(String program,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> p = jdbcTemplate.query("select * from program_menu where program=?",programMenu_mapper,program);
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
	 * 根据节目表演者获取所有节目
	 * @param  actor   节目表演者
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getProgramByActor(String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where actor=?",programMenu_mapper,actor);
		return program;
	}
	
	/**
	 * 获取所有节目
	 * @param  program   节目名称
	 * @param  actor   节目表演者
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getProgramByProgramactor(String program,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program=? and actor=?",programMenu_mapper,new Object[] {program,actor});
		return programs;
	}
	
	/**
	 * 根据获取所有节目
	 * @param  program   节目名称
	 * @param  department   节目部门
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getProgramByProgramDepartment(String program,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program=? and department=?",programMenu_mapper,new Object[] {program,department});
		return programs;
	}
	
	/**
	 * 根据节目表演者获取所有节目
	 * @param  department   节目部门
	 * @param  actor   节目表演者
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getProgramByDepartmentactor(String department,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where department=? and actor=?",programMenu_mapper,new Object[] {department,actor});
		return programs;
	}
	
	/**
	 * 获取所有节目
	 * @param  program   节目名称
	 * @param  actor   节目表演者
	 * @param  department   节目部门
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getProgramByProgramactorDepartment(String program,String actor,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program=? and actor=? and department=?",programMenu_mapper,new Object[] {program,actor,department});
		return programs;
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
