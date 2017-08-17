package com.dcone.dtss.model;

public class Message {

	String itcode;
	String username;
	String talktime;
	String message;
	public Message() {}
	public Message(String itcode, String username, String talktime, String message) {
		super();
		this.itcode = itcode;
		this.username = username;
		this.talktime = talktime;
		this.message = message;
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
	public String getTalktime() {
		return talktime;
	}
	public void setTalktime(String talktime) {
		this.talktime = talktime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Message [itcode=" + itcode + ", username=" + username + ", talktime=" + talktime + ", message="
				+ message + "]";
	}
	
	
}
