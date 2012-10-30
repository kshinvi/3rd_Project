package com.emn.game.model;

import com.emn.common.SqlMapUsableObj;

public class PointBean extends SqlMapUsableObj {
	private	int	pointPk; 		// point pk. point_pk_seq.nextval 로 고정.
	private int	memberPk; 		// member pk. Foreign key이므로 member 테이블에 없는 멤버일 경우 입력 안 됨.
	private String Detail; 		// 게임 히스토리(점수변동된 게임) 예: 사천성
	private	int Point; 			// 잃거나 얻은 값. 잃었을땐 –값 통째로 입력
	private	String Regdate; 	// 저장한 날짜. Sysdate를 입력.
	
	// Getter & Setter
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
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public int getPoint() {
		return Point;
	}
	public void setPoint(int point) {
		Point = point;
	}
	public String getRegdate() {
		return Regdate;
	}
	public void setRegdate(String regdate) {
		Regdate = regdate;
	}
	
	
	@Override
	public String toString() {
		return "Point [pointPk=" + pointPk + ", memberPk=" + memberPk
				+ ", Detail=" + Detail + ", Point=" + Point + ", Regdate=" + Regdate
				+ "]";
	}
}
