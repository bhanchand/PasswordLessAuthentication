package com.okta.prasad.oktaweb2.model;

public class PassCode {
	String passCode;

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ passCode:\"").append(passCode).append("\"}");
		return builder.toString();
	}
}
