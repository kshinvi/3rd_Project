package com.emn.member.model;

import java.sql.SQLException;

import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;

public class UserDao {
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public UserDao() {

	}

	public int loginIdCheckDao(String memId, String memPw) {
		int cnt = 0;
		SqlMapUsableObj result = null;
		member = new Member();
		member.setMemberId(memId);
		member.setMemberPw(memPw);

		try {
			result = SqlMapConfig.selectOneEmn("loginIdCheck",
					(SqlMapUsableObj) member);
			if (result != null) {
				cnt = 1;
				// System.out.println("Success ID Check..");
			}
		} catch (SQLException e) {
			System.out.println("Failed Login Check!!");
			e.printStackTrace();
			cnt = 0;
		}
		return cnt;
	}

	public int loginPwCheckDao(String memId, String memPw) {
		int cnt = 0;
		SqlMapUsableObj result = null;
		member = new Member();
		member.setMemberId(memId);
		member.setMemberPw(memPw);

		try {
			result = SqlMapConfig.selectOneEmn("loginPwCheck",
					(SqlMapUsableObj) member);
			if (result != null) {
				cnt = 1;
				// System.out.println("Success ID Check..");
			}
		} catch (SQLException e) {
			System.out.println("Failed Login Check!!");
			e.printStackTrace();
			cnt = 0;
		}
		return cnt;
	}

	public Member getLoginSession(String memId) throws Exception {
		SqlMapUsableObj result = null;
		member.setMemberId(memId);
		result = SqlMapConfig.selectOneEmn("loginIdCheck",
				(SqlMapUsableObj) member);
		System.out.println("3. result = " + result.toString());
		return (Member) result;
	}
	
}
