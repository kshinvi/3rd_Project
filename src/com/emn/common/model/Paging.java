package com.emn.common.model;

import com.emn.common.SqlMapUsableObj;

public class Paging extends SqlMapUsableObj {

	private int currentPage; // 현재페이지
	private int totalCount; // 전체 게시물 수
	private int totalPage; // 전체 페이지 수
	private int blockCount; // 한 페이지의 게시물의 수
	private int blockPage; // 한 화면에 보여줄 페이지 수
	private int startCount; // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endCount; // 한 페이지에서 보여줄 게시글의 끝 번호
	private int startPage; // 시작 페이지
	private int endPage; // 마지막 페이지
	private String pagingHtml;
	
	public Paging(){		
	}
	
	public Paging(int currentPage, int totalCount, int blockCount,
			int blockPage){
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.blockCount = blockCount;
		this.blockPage = blockPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public String getPagingHtml() {
		return pagingHtml;
	}
	
	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	@Override
	public String toString() {
		return "Paging [currentPage=" + currentPage + ", totalCount="
				+ totalCount + ", totalPage=" + totalPage + ", blockCount="
				+ blockCount + ", blockPage=" + blockPage + ", startCount="
				+ startCount + ", endCount=" + endCount + ", startPage="
				+ startPage + ", endPage=" + endPage + ", pagingHtml="
				+ pagingHtml + "]";
	}
	

	

}
