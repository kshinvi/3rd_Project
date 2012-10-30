package com.emn.member.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;

public class UserUpdateAction extends EmnAction {

	// for logging
	public static final Logger log = Logger.getLogger(UserUpdateAction.class);

	private List<Member> memberList;

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}

	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	private String message;
	private String memId;
	private String memPw;
	private String memName;
	private String memEmail;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public static Logger getLog() {
		return log;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();

		Map<String, Object> session = context.getSession();

		member = new Member();
		// member.setMemId((String)session.get("memId"));
		member.setMemberId(memId);
		member.setMemberPw(memPw);
		member.setMemberName(memName);
		member.setMemberEmail(memEmail);

		SqlMapConfig.updateEmn("updateMember", (SqlMapUsableObj) member);
		log.debug("Log UserUpdate memId = " + member.getMemberId());
		
		session.put("member", member);
		context.setSession(session);
		
		return "success";
	}

	public String getUser() throws Exception {
		ActionContext context = ActionContext.getContext();

		Map<String, Object> session = context.getSession();
		Member member = new Member();
		member.setMemberId((String) session.get("member.memId"));
		member.setMemberId((String) session.get("member.memPw"));

		// log.debug("Log.Debug값 = " + member.toString());
		member = (Member) SqlMapConfig.selectOneEmn("login", member);

		return "success";
	}
}