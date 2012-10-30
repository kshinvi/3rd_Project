package com.emn.member.model;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.emn.common.SqlMapConfig;

public class UserDao2 {
	// 싱글톤 방식으로 처리하기 위해 static으로 처리함.
	private static UserDao2 instance = new UserDao2();
	
	//for logging
	public static final Logger log = Logger.getLogger(UserDao2.class);	
	
	// 인스턴스를 반환하는 메소드
	public static UserDao2 getInstance(){
		return instance;
	}

	private UserDao2(){
		super();
	}
	
	public boolean isCorrectPassword(String memberId, String memberPw) throws SQLException{
		boolean result = false;
		Member memberResult = null;
		
		Member param = new Member();
		param.setMemberId(memberId);
		param.setMemberPw(memberPw);
		
		memberResult = (Member)SqlMapConfig.selectOneEmn("login", param);
		
		if(memberResult != null && memberResult.getMemberId() != null)result = true;
		else result = false;
		
		return result;
	}

}
