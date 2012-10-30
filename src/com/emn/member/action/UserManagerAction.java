package com.emn.member.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;

public class UserManagerAction extends EmnAction {
	// for logging
	public static final Logger log = Logger.getLogger(UserUpdateAction.class);
	
	private List<Member> memberList;
	private Member member;
	private String message;


	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	@Override
	public String execute() throws Exception {
		member = new Member();

		SqlMapConfig.updateEmn("updateMember", (SqlMapUsableObj) member);
		log.debug("Log ManagerUpdate = " + member.getMemberId());
		return "success";
	}

	public String getUser() throws Exception {

		memberList = (List) SqlMapConfig.selectListEmn("getAllMember");
		
		log.debug("Log getAllMember = " + memberList.toString());
		
		return SUCCESS;
	}
}