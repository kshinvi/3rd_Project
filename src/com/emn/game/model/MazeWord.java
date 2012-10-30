package com.emn.game.model;

import com.emn.common.SqlMapUsableObj;

public class MazeWord extends SqlMapUsableObj{
	private int value1;
	private int value2;
	private int mazeword_pk;
	private String word;
	private int gwa;
	private int difficulty;
	private String combination;
	
	public int getValue1() {
		return value1;
	}
	public void setValue1(int value1) {
		this.value1 = value1;
	}
	public int getValue2() {
		return value2;
	}
	public void setValue2(int value2) {
		this.value2 = value2;
	}
	public int getMazeword_pk() {
		return mazeword_pk;
	}
	public void setMazeword_pk(int mazeword_pk) {
		this.mazeword_pk = mazeword_pk;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getGwa() {
		return gwa;
	}
	public void setGwa(int gwa) {
		this.gwa = gwa;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getCombination() {
		return combination;
	}
	public void setCombination(String combination) {
		this.combination = combination;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
