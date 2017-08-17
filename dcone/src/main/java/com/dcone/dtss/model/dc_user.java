package com.dcone.dtss.model;

public class dc_user {
	int uid;
	String itcode;
	String username;
	String password;
	int locked;
	int inside;
	public dc_user() {}
	public dc_user(int uid, String itcode, String username, String password, int locked, int inside) {
		super();
		this.uid = uid;
		this.itcode = itcode;
		this.username = username;
		this.password = password;
		this.locked = locked;
		this.inside = inside;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getInside() {
		return inside;
	}
	public void setInside(int inside) {
		this.inside = inside;
	}
	@Override
	public String toString() {
		return "dc_user [uid=" + uid + ", itcode=" + itcode + ", username=" + username + ", password=" + password
				+ ", locked=" + locked + ", inside=" + inside + "]";
	}
	
}
