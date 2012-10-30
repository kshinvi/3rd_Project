package com.emn.member.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;

public class UserLoginAction extends EmnAction {

	public static final Logger log = Logger.getLogger(UserLoginAction.class);
	
	private String memberId;
	private String memberPw;
	private String message;

	// getter & setter
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
	public String execute() throws Exception {
		
		Member member = null;
		
		Member param = new Member();
		param.setMemberId(memberId);
		param.setMemberPw(memberPw);
		
		ActionContext context = ActionContext.getContext();
		
		Map<String, Object> session = context.getSession();
		
		member = (Member) SqlMapConfig.selectOneEmn("login", (SqlMapUsableObj) param);
		
		if(member == null){
			this.message = "rest";
			return INPUT;
		}
		else if(member.getMemberUse() == null || member.getMemberUse().equals("0")){
			this.message = "활성 상태의 회원이 아닙니다.";
			return INPUT;
		}
		
		log.debug("Login Result : " + member.toString());
		
		member.setMemberPw("");
		session.put("member", member);		
		context.setSession(session);
		
		return SUCCESS;
	}
}