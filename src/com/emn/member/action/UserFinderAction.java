package com.emn.member.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;

public class UserFinderAction extends EmnAction {

	public static final Logger log = Logger.getLogger(UserFinderAction.class);

	private List<Member> memberList;
	private String memberId;
	private String memberName;
	private String message;


	public List<Member> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String execute() throws Exception {

		Member member = new Member();

		member.setMemberId(memberId);
		member.setMemberName(memberId);

		memberList = (List) SqlMapConfig.selectListEmn("findId", member);

		if (memberList.size() == 0) {
			this.message = "<font color='red'>일치하는 회원이 없습니다.</font>";
			return INPUT;
		} else {
			this.message = "";
			for (Member p : memberList) {
				String memberId = p.getMemberId();
				int cnt = 0;
				cnt = memberId.length();
				memberId = memberId.substring(0, 3);
				for (int i = 0; i < cnt; i++) {
					memberId += "*";
				}
				message += "ID : " + memberId + "<br/>";
			}
			return "success";
		}
	}

	public String getUser() throws Exception {

		Member member = new Member();

		member.setMemberId(memberId);
		member.setMemberName(memberId);
		
		log.debug(member.getMemberId() + member.getMemberName());

		memberList = (List) SqlMapConfig.selectListEmn("findIdPw", member);
		log.debug("List = " + memberList.toString());

		if (memberList.size() == 0) {
			this.message = "<font color='red'>일치하는 회원이 없습니다.</font>";
			return INPUT;
		} /*
		 * else { this.message = ""; for (Member p : list) { message += "ID : "
		 * + memId + "<br/>"; } return "success"; }
		 */
		return "success";
	}
}