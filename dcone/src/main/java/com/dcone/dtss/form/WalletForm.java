package com.dcone.dtss.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WalletForm {

	@NotNull
	@Size(min=2,max=8)
	String username;
	@NotNull
	@Size(min=5,max=11)
	String itcode;
	@NotNull
	@Min(100)
	@Max(100000)
	String amount;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getItcode() {
		return itcode;
	}
	public void setItcode(String itcode) {
		this.itcode = itcode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
