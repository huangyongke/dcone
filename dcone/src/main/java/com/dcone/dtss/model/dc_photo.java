package com.dcone.dtss.model;

import java.util.Arrays;

public class dc_photo {

	int uid;
	byte[] image;
	public dc_photo() {}
	public dc_photo(int uid, byte[] image) {
		super();
		this.uid = uid;
		this.image = image;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "dc_photo [uid=" + uid + ", image=" + Arrays.toString(image) + "]";
	}
	
}
