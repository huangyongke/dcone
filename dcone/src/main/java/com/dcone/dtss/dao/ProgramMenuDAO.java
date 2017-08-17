package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.ProgramMenu;

public class ProgramMenuDAO {

	/**
	 * ���ݽ�Ŀid��ȡ���н�Ŀ
	 * @param pid   ��Ŀid
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ò������еĽ�Ŀ����
	 */
	public static ProgramMenu getProgramByPid(int pid,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		ProgramMenu program = jdbcTemplate.queryForObject("select * from program_menu where pid=?",programMenu_mapper,pid);
		return program;
	}
	
	
	
	/**
	 * ���ݽ�Ŀ���ƻ�ȡ��Ŀ
	 * @param program   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ý�ĿProgramMenu����
	 */
	public static List<ProgramMenu> getProgramByProgram(String program,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> p = jdbcTemplate.query("select * from program_menu where program=?",programMenu_mapper,program);
		return p;
	}
	
	/**
	 * ���ݽ�Ŀ���Ż�ȡ���н�Ŀ
	 * @param department   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ò������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getProgramByDepartment(String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where department=?",programMenu_mapper,department);
		return program;
	}
	
	/**
	 * ���ݽ�Ŀ�����߻�ȡ���н�Ŀ
	 * @param  actor   ��Ŀ������
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getProgramByActor(String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where actor=?",programMenu_mapper,actor);
		return program;
	}
	
	/**
	 * ��ȡ���н�Ŀ
	 * @param  program   ��Ŀ����
	 * @param  actor   ��Ŀ������
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getProgramByProgramactor(String program,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program=? and actor=?",programMenu_mapper,new Object[] {program,actor});
		return programs;
	}
	
	/**
	 * ���ݻ�ȡ���н�Ŀ
	 * @param  program   ��Ŀ����
	 * @param  department   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getProgramByProgramDepartment(String program,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program=? and department=?",programMenu_mapper,new Object[] {program,department});
		return programs;
	}
	
	/**
	 * ���ݽ�Ŀ�����߻�ȡ���н�Ŀ
	 * @param  department   ��Ŀ����
	 * @param  actor   ��Ŀ������
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getProgramByDepartmentactor(String department,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where department=? and actor=?",programMenu_mapper,new Object[] {department,actor});
		return programs;
	}
	
	/**
	 * ��ȡ���н�Ŀ
	 * @param  program   ��Ŀ����
	 * @param  actor   ��Ŀ������
	 * @param  department   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   �ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getProgramByProgramactorDepartment(String program,String actor,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program=? and actor=? and department=?",programMenu_mapper,new Object[] {program,actor,department});
		return programs;
	}
	
	/**
	 * ��ȡ��Ŀ�����б�
	 * @param jdbcTemplate  Spring JdbcTemplate ����
	 * @return   ��Ŀ����ProgramMenu�б�
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
