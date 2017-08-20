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
	 * 模糊查询根据节目名称获取节目
	 * @param program   节目名称
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该节目ProgramMenu对象
	 */
	public static List<ProgramMenu> getDimProgramByProgram(String program,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> p = jdbcTemplate.query("select * from program_menu where program like ?",programMenu_mapper,"%"+program+"%");
		return p;
	}
	
	/**
	 * 模糊查询根据节目部门获取所有节目
	 * @param department   节目部门
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该部门所有的节目对象
	 */
	public static List<ProgramMenu> getDimProgramByDepartment(String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where department like ?",programMenu_mapper,"%"+department+"%");
		return program;
	}
	
	/**
	 * 模糊查询根据节目表演者获取所有节目
	 * @param  actor   节目表演者
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getDimProgramByActor(String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where actor=?",programMenu_mapper,"%"+actor+"%");
		return program;
	}
	
	/**
	 * 模糊查询获取所有节目
	 * @param  program   节目名称
	 * @param  actor   节目表演者
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getDimProgramByProgramactor(String program,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program like ? and actor like ?",programMenu_mapper,new Object[] {"%"+program+"%","%"+actor+"%"});
		return programs;
	}
	
	/**
	 * 模糊查询根据获取所有节目
	 * @param  program   节目名称
	 * @param  department   节目部门
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getDimProgramByProgramDepartment(String program,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program kike ? and department like ?",programMenu_mapper,new Object[] {"%"+program+"%","%"+department+"%"});
		return programs;
	}
	
	/**
	 * 模糊查询根据节目表演者获取所有节目
	 * @param  department   节目部门
	 * @param  actor   节目表演者
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getDimProgramByDepartmentactor(String department,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where department like ? and actor like ?",programMenu_mapper,new Object[] {"%"+department+"%","%"+actor+"%"});
		return programs;
	}
	
	/**
	 * 模糊查询获取所有节目
	 * @param  program   节目名称
	 * @param  actor   节目表演者
	 * @param  department   节目部门
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   模糊查询该表演者所有的节目对象
	 */
	public static List<ProgramMenu> getDimProgramByProgramactorDepartment(String program,String actor,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program like ? and actor like ? and department like ?",programMenu_mapper,new Object[] {"%"+program+"%","%"+actor+"%","%"+department+"%"});
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
