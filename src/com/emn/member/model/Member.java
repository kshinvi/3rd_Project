package com.emn.member.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.emn.common.SqlMapUsableObj;

@Root(name = "member")
public class Member extends SqlMapUsableObj {

	private int memberPk;
	
	@Element(name="id")
	private String memberId;
	
	@Element(name = "pass", required = false)
	private String memberPw;
	
	@Element(name="name", required=false)
	private String memberName;
	
	@Element(name="email", required=false)
	private String memberEmail;
	
	private String memberLevel;
	private String memberUse;
	private String memberRegdate;
	private int memberPoint;
	private String regdate;
	
	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public Member() {

	}

	public Member(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public int getMemberPk() {
		return memberPk;
	}

	public void setMemberPk(int memberPk) {
		this.memberPk = memberPk;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getMemberUse() {
		return memberUse;
	}

	public void setMemberUse(String memberUse) {
		this.memberUse = memberUse;
	}

	public String getMemberRegdate() {
		return memberRegdate;
	}

	public void setMemberRegdate(String memberRegdate) {
		this.memberRegdate = memberRegdate;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	@Override
	public String toString() {
		return "Member [memberPk=" + memberPk + ", memberId=" + memberId
				+ ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberLevel="
				+ memberLevel + ", memberUse=" + memberUse + ", memberRegdate="
				+ memberRegdate + ", memberPoint=" + memberPoint + "]";
	}

}