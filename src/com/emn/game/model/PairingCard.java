package com.emn.game.model;

import com.emn.common.SqlMapUsableObj;

public class PairingCard extends SqlMapUsableObj {
	private int value1;
	private int value2;
	private int cardpk;
	private int gwa;
	private String word;
	private String difficulty;
	private String nihongo;
	

	public String getNihongo() {
		return nihongo;
	}

	public void setNihongo(String nihongo) {
		this.nihongo = nihongo;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public int getCardpk() {
		return cardpk;
	}

	public void setCardpk(int cardpk) {
		this.cardpk = cardpk;
	}

	public int getGwa() {
		return gwa;
	}

	public void setGwa(int gwa) {
		this.gwa = gwa;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		setValue2(value1);
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return "PairingCard [value1=" + value1 + ", value2=" + value2
				+ ", cardpk=" + cardpk + ", gwa=" + gwa + ", word=" + word
				+ ", difficulty=" + difficulty + "]";
	}

}
