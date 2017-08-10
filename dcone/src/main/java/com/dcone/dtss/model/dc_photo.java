package com.dcone.dtss.model;

import java.util.Arrays;

public class dc_photo {

	int uid;
	Byte[] image;
	public dc_photo() {}
	public dc_photo(int uid, Byte[] image) {
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
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "dc_photo [uid=" + uid + ", image=" + Arrays.toString(image) + "]";
	}
	
}
