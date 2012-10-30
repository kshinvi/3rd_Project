package com.emn.member.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;

public class UserWithdraw extends EmnAction {
	// for logging
	public static final Logger log = Logger.getLogger(UserUpdateAction.class);

	private String memId;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = (Map<String, Object>) ActionContext
				.getContext().getSession();
		// 세션 끊기
		session.remove("member");
		Member member = new Member();
		member.setMemberId(memId);

		SqlMapConfig.updateEmn("withdrawMember", (SqlMapUsableObj) member);
		return "success";
	}
}