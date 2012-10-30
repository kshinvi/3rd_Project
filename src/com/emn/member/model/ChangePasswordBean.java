package com.emn.member.model;

import com.emn.common.SqlMapUsableObj;

public class ChangePasswordBean extends SqlMapUsableObj {
	private String memberId;
	private String oldPassword;
	private String newPassword;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}	
}
