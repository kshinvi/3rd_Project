package com.emn.member.model;

import com.emn.common.SqlMapUsableObj;

public class Student extends SqlMapUsableObj {

	private int studentPk;
	private int memberPk;
	private int lecturePk;
	private char userFlag;
	private String cancelDate;
	private String onLecture; // 강의현황-학생배정팝업에서 사용
	private String memberName; // 강의현황-학생배정팝업에서 사용

	public Student() {
		super();
	}

	public Student(int lecturePk) {
		this();
		this.lecturePk = lecturePk;
	}

	public int getStudentPk() {
		return studentPk;
	}

	public void setStudentPk(int studentPk) {
		this.studentPk = studentPk;
	}

	public int getMemberPk() {
		return memberPk;
	}

	public void setMemberPk(int memberPk) {
		this.memberPk = memberPk;
	}

	public int getLecturePk() {
		return lecturePk;
	}

	public void setLecturePk(int lecturePk) {
		this.lecturePk = lecturePk;
	}

	public char getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(char userFlag) {
		this.userFlag = userFlag;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getOnLecture() {
		return onLecture;
	}

	public void setOnLecture(String onLecture) {
		this.onLecture = onLecture;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "Student [studentPk=" + studentPk + ", memberPk=" + memberPk
				+ ", lecturePk=" + lecturePk + ", useFlag=" + userFlag
				+ ", cancelDate=" + cancelDate + ", onLecture=" + onLecture
				+ ", memberName=" + memberName + "]";
	}

}
