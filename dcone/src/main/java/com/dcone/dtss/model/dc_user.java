package com.dcone.dtss.model;

public class dc_user {
	int uid;
	String itcode;
	String username;
	int locked;
	public dc_user() {}
	public dc_user(int uid, String itcode, String username, int locked) {
		super();
		this.uid = uid;
		this.itcode = itcode;
		this.username = username;
		this.locked = locked;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getItcode() {
		return itcode;
	}
	public void setItcode(String itcode) {
		this.itcode = itcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	@Override
	public String toString() {
		return "dc_user [uid=" + uid + ", itcode=" + itcode + ", username=" + username + ", locked=" + locked + "]";
	}
	
}
