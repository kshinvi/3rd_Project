package com.emn.game.model;

import com.emn.common.SqlMapUsableObj;

public class MazeScore extends SqlMapUsableObj {
	private int heart;
	private int wrong;
	private int correct;
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart = heart;
	}
	public int getWrong() {
		return wrong;
	}
	public void setWrong(int wrong) {
		this.wrong = wrong;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
