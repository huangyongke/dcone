package com.dcone.dtss.model;

import java.util.ArrayList;
import java.util.List;

public class Program {

	int pid;
	String program;
	String actor;
	String department;
	String showtime;
	int sequence;
	float reward;
	public Program() {}
	public Program(int pid, String program, String actor, String department, String showtime, int sequence,
			float reward) {
		super();
		this.pid = pid;
		this.program = program;
		this.actor = actor;
		this.department = department;
		this.showtime = showtime;
		this.sequence = sequence;
		this.reward = reward;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public float getReward() {
		return reward;
	}
	public void setReward(float reward) {
		this.reward = reward;
	}
	@Override
	public String toString() {
		return "Program [pid=" + pid + ", program=" + program + ", actor=" + actor + ", department=" + department
				+ ", showtime=" + showtime + ", sequence=" + sequence + ", reward=" + reward + "]";
	}
	public static List<Program> getProgram(List<ProgramMenu> menu) {
		List<Program> programs = new ArrayList<Program>();
		for(ProgramMenu m:menu) {
			Program program = new Program();
			program.setPid(m.getPid());
			program.setProgram(m.getProgram());
			program.setShowtime(m.getShowtime());
			program.setDepartment(m.getDepartment());
			program.setActor(m.getActor());
			program.setSequence(m.getReward());
			int i = m.getReward();
			float j = (float)i/100;
			program.setReward(j);
			programs.add(program);
		}
		return programs;
	}
}
