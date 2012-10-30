package com.emn.member.action;

import com.emn.common.EmnAction;
import com.emn.member.model.UserDao2;

public class EditBasicInfoFormAction extends EmnAction {
	private String memberId;
	private String memberPw;
	private String message;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String execute() throws Exception{
		boolean result;
		UserDao2 dao = UserDao2.getInstance();
		
		result = dao.isCorrectPassword(memberId, memberPw);
		
		if(result){
			return SUCCESS;
		}
		else {
			message = "비밀번호가 틀립니다.";
			return "input";
		}				
	}
}
