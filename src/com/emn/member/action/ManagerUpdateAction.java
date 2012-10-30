package com.emn.member.action;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;

public class ManagerUpdateAction extends EmnAction {
	// for logging
	public static final Logger log = Logger.getLogger(UserUpdateAction.class);

	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	private String message;
	
	private int memberPk;
	private String memberId;
	private String memberName;
	private String memberEmail;
	private String memberLevel;
	private String memberUse;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMemberPk() {
		return memberPk;
	}

	public void setMemberPk(int memberPk) {
		this.memberPk = memberPk;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getMemberUse() {
		return memberUse;
	}

	public void setMemberUse(String memberUse) {
		this.memberUse = memberUse;
	}

	@Override
	public String execute() throws Exception {
		member = new Member();
		member.setMemberPk(memberPk);
		member.setMemberName(memberName);
		member.setMemberEmail(memberEmail);
		member.setMemberLevel(memberLevel);
		member.setMemberUse(memberUse);
		
		SqlMapConfig.updateEmn("updateAdmin", (SqlMapUsableObj) member);
		log.debug("Log memberPk = " + member.getMemberPk());
		return SUCCESS;
	}

	public String getUser() throws Exception {
		member = new Member();
		member.setMemberPk(memberPk);

		member = (Member) SqlMapConfig.selectOneEmn("selectUser", member);
		log.debug("관리자 모드 = " + member);
		return SUCCESS;
	}
}