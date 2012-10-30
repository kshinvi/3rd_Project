package com.emn.member.action;

import java.util.Map;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.member.model.ChangePasswordBean;
import com.emn.member.model.Member;
import com.emn.member.model.UserDao2;
import com.opensymphony.xwork2.ActionContext;

public class ChangePasswordAction extends EmnAction {
	private String memberPwOld;
	private String memberPw;
	private String message;
	public String getMemberPwOld() {
		return memberPwOld;
	}
	public void setMemberPwOld(String memberPwOld) {
		this.memberPwOld = memberPwOld;
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
		ChangePasswordBean param = new ChangePasswordBean();
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		
		if(sessionMember == null){
			message = "세션이 종료되었습니다. 로그인 후 재시도 해 주세요.";
			return null;
		}
		
		param.setMemberId(sessionMember.getMemberId());
		param.setOldPassword(memberPwOld);
		param.setNewPassword(memberPw);
		
		boolean isCorrectPassword;
		UserDao2 dao = UserDao2.getInstance();
		
		isCorrectPassword = dao.isCorrectPassword(param.getMemberId(), param.getOldPassword());
		
		if(isCorrectPassword){
			SqlMapConfig.updateEmn("updatePassword", param);
			message = "변경되었습니다.";
			return SUCCESS;
		}
		else {
			message = "<font color='red'>비밀번호가 틀립니다.</font>";
			return INPUT;
		}				
		
		
	}

}
