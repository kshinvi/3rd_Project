package com.emn.game.model;

import com.emn.common.SqlMapUsableObj;

public class PointDetail extends SqlMapUsableObj {
	private int sum;
	private String detail;
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}	
	
}
