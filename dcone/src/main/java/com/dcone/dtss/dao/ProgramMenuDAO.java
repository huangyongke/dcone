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
	 * ģ����ѯ���ݽ�Ŀ���ƻ�ȡ��Ŀ
	 * @param program   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ý�ĿProgramMenu����
	 */
	public static List<ProgramMenu> getDimProgramByProgram(String program,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> p = jdbcTemplate.query("select * from program_menu where program like ?",programMenu_mapper,"%"+program+"%");
		return p;
	}
	
	/**
	 * ģ����ѯ���ݽ�Ŀ���Ż�ȡ���н�Ŀ
	 * @param department   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ò������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getDimProgramByDepartment(String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where department like ?",programMenu_mapper,"%"+department+"%");
		return program;
	}
	
	/**
	 * ģ����ѯ���ݽ�Ŀ�����߻�ȡ���н�Ŀ
	 * @param  actor   ��Ŀ������
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getDimProgramByActor(String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> program = jdbcTemplate.query("select * from program_menu where actor=?",programMenu_mapper,"%"+actor+"%");
		return program;
	}
	
	/**
	 * ģ����ѯ��ȡ���н�Ŀ
	 * @param  program   ��Ŀ����
	 * @param  actor   ��Ŀ������
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getDimProgramByProgramactor(String program,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program like ? and actor like ?",programMenu_mapper,new Object[] {"%"+program+"%","%"+actor+"%"});
		return programs;
	}
	
	/**
	 * ģ����ѯ���ݻ�ȡ���н�Ŀ
	 * @param  program   ��Ŀ����
	 * @param  department   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getDimProgramByProgramDepartment(String program,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program kike ? and department like ?",programMenu_mapper,new Object[] {"%"+program+"%","%"+department+"%"});
		return programs;
	}
	
	/**
	 * ģ����ѯ���ݽ�Ŀ�����߻�ȡ���н�Ŀ
	 * @param  department   ��Ŀ����
	 * @param  actor   ��Ŀ������
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getDimProgramByDepartmentactor(String department,String actor,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where department like ? and actor like ?",programMenu_mapper,new Object[] {"%"+department+"%","%"+actor+"%"});
		return programs;
	}
	
	/**
	 * ģ����ѯ��ȡ���н�Ŀ
	 * @param  program   ��Ŀ����
	 * @param  actor   ��Ŀ������
	 * @param  department   ��Ŀ����
	 * @param jdbcTemplate   Spring JdbcTemplate jdbcTemplate
	 * @return   ģ����ѯ�ñ��������еĽ�Ŀ����
	 */
	public static List<ProgramMenu> getDimProgramByProgramactorDepartment(String program,String actor,String department,JdbcTemplate jdbcTemplate) {
		RowMapper<ProgramMenu> programMenu_mapper = new BeanPropertyRowMapper<ProgramMenu>(ProgramMenu.class);
		List<ProgramMenu> programs = jdbcTemplate.query("select * from program_menu where program like ? and actor like ? and department like ?",programMenu_mapper,new Object[] {"%"+program+"%","%"+actor+"%","%"+department+"%"});
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
