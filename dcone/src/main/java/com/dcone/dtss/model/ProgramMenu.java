package com.dcone.dtss.model;

public class ProgramMenu {
	int pid;
	String program;
	String actor;
	String department;
	String showtime;
	String stoptime;
	int sequence;
	int reward;
	public ProgramMenu() {}
	
	public ProgramMenu(int pid, String program, String actor, String department, String showtime, String stoptime,
			int sequence, int reward) {
		super();
		this.pid = pid;
		this.program = program;
		this.actor = actor;
		this.department = department;
		this.showtime = showtime;
		this.stoptime = stoptime;
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
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}

	public String getStoptime() {
		return stoptime;
	}

	public void setStoptime(String stoptime) {
		this.stoptime = stoptime;
	}

	@Override
	public String toString() {
		return "ProgramMenu [pid=" + pid + ", program=" + program + ", actor=" + actor + ", department=" + department
				+ ", showtime=" + showtime + ", stoptime=" + stoptime + ", sequence=" + sequence + ", reward=" + reward
				+ "]";
	}
	
	
}
