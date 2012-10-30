package com.emn.member.action;

import java.util.Map;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;

public class ChangeBasicInfoAction extends EmnAction {
	
	private String memberId;
	private String memberName;
	private String memberEmail;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memeberId) {
		this.memberId = memeberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	@Override
	public String execute() throws Exception{
		Member param = new Member();
		param.setMemberId(memberId);
		param.setMemberName(memberName);
		param.setMemberEmail(memberEmail);
		
		SqlMapConfig.updateEmn("updateMember", param);
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		Member sessionMember = (Member) session.get("member");
		sessionMember.setMemberId(memberId);
		sessionMember.setMemberName(memberName);
		sessionMember.setMemberEmail(memberEmail);
		sessionMember.setMemberPw("");
		
		session.put("member",sessionMember);
		return SUCCESS;
	}
}
