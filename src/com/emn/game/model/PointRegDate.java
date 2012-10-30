package com.emn.game.model;

import com.emn.common.SqlMapUsableObj;

public class PointRegDate extends SqlMapUsableObj{
	private String regdate;
	private String point;
	private String detail;
	private String sum;
	private String count;

	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "PointRegDate [regdate=" + regdate + ", point=" + point
				+ ", detail=" + detail + ", sum=" + sum + ", count=" + count
				+ "]";
	}	
	
}
