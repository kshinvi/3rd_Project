package com.emn.member.model;

import com.emn.common.SqlMapUsableObj;

public class Point extends SqlMapUsableObj {
	
	private int pointPk;
	private int memberPk;
	private String detail;
	private int point;
	private String regdate;
	
	public int getPointPk() {
		return pointPk;
	}
	public void setPointPk(int pointPk) {
		this.pointPk = pointPk;
	}
	public int getMemberPk() {
		return memberPk;
	}
	public void setMemberPk(int memberPk) {
		this.memberPk = memberPk;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}	
}
