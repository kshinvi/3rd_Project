package com.emn.member.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;

public class UserLogoutAction extends EmnAction {

	public static final Logger log = Logger.getLogger(UserLoginAction.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute() 세션 설정 해제하기 execute()
	 * session : 세션을 저장할 Map 타입의 변수
	 */
	@Override
	public String execute() throws Exception {
		// 세션 가져오기
		Map<String, Object> session = (Map<String, Object>) ActionContext
				.getContext().getSession();
		log.debug("로그아웃 세션 처리 : " + session.toString());
		// 세션 끊기
		session.remove("member");
		return "success";
	}

	public String withdraw() throws Exception {
		ActionContext context = ActionContext.getContext();

		Map<String, Object> session = context.getSession();
		String memId = (String)session.get("memId");
		Member member = new Member();
		member.setMemberId(memId);
		
		SqlMapConfig.updateEmn("withdrawMember", (SqlMapUsableObj) member);

		return "success";
	}
}
