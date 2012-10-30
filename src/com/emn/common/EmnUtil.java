package com.emn.common;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.emn.common.model.Paging;
import com.emn.content.model.Gwa;
import com.emn.content.model.Other;
import com.emn.member.model.Member;

public class EmnUtil {
	
	public static final String UploadPath = "D:/contents/";
	
	public static final Logger log = Logger.getLogger(EmnUtil.class);
	
	//UploadPath = sc.getRealPath("/");	//서버루트
	//implements ServletContextAware
	//private ServletContext sc;
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Gwa> getGwaSelect (Gwa paramGwa){
		
		ArrayList<Gwa> gwaList = null;
		
		List<? extends SqlMapUsableObj> resultList = null;
		
		try {
			resultList = SqlMapConfig.selectListEmn("getGwaSelect", paramGwa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		gwaList = (ArrayList<Gwa>) resultList;
		
		return gwaList;
	}
	
	
	/**
	 * 멤버리스트 가져오기(권한을 넣으면 권한에 해당하는 멤머리스트가 리턴)
	 * @param member
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Member> getMemberList (Member member) {
		
		ArrayList<Member> memList = null;
		
		List<? extends SqlMapUsableObj> resultList = null;
		
		try {
			resultList = SqlMapConfig.selectListEmn("getMemList", member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		memList = (ArrayList<Member>) resultList;
		
		return memList;
	}
	
	
	public static ArrayList<String> getWclassSelect(){
		//동사1, 동사2, 동사3, 명사, 대명사, 형용사, 부사,조사,접사,조수사,형용동사 로 고정
		ArrayList<String> returnList = new ArrayList<String>();
/*		returnList.add("동사1");
		returnList.add("동사2");
		returnList.add("동사3");
		returnList.add("명사");
		returnList.add("대명사");
		returnList.add("형용사");
		returnList.add("부사");
		returnList.add("조사");
		returnList.add("접속사");
		returnList.add("조수사");		
		returnList.add("형용동사");
		returnList.add("감탄사");
		//아래는 추가됨
		returnList.add("접미어");
		returnList.add("문장");*/
		
		// 중요한 품사만 사용하기로 한다.
		returnList.add("동사");
		returnList.add("명사");
		returnList.add("형용사");
		returnList.add("그외");
		return returnList;
	}
	
	
	public static void deleteFile(String fileSubRoot, String fileName) {
		File delFile = new File( EmnUtil.UploadPath + fileSubRoot + fileName );
		if(delFile.exists()){
			delFile.delete();
		}	
	}
	
	public static boolean uploadFile(File upFile, String fileSubRoot, String fileName) {
		
		//make directory		
		File path = new File(EmnUtil.UploadPath + fileSubRoot);
		if (!path.exists()) {
			path.mkdirs();
		}
		
		if( upFile != null && upFile.exists()){
			File realFile = new File(EmnUtil.UploadPath + fileSubRoot + fileName);
			try {
				FileUtils.copyFile(upFile, realFile);
			} catch (IOException e) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Paging 객체를 리턴
	 * @param paging
	 * @return
	 */
	public static Paging getPaging(Paging paging) {
		
		// 페이징 후
		int lastCount = paging.getTotalCount();
		
		// 전체 페이지 수
		paging.setTotalPage((int) Math.ceil((double) paging.getTotalCount() / paging.getBlockCount()));
		
		
		if (paging.getTotalPage() == 0) {
			paging.setTotalPage(1);
		}
				
		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (paging.getCurrentPage() > paging.getTotalPage()) {
			paging.setCurrentPage(paging.getTotalPage());
		}
		
		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		paging.setStartCount((paging.getCurrentPage() - 1) * paging.getBlockCount());
		paging.setEndCount(paging.getStartCount() + paging.getBlockCount() - 1);
		// 시작 페이지와 마지막 페이지 값 구하기.
		paging.setStartPage((int) ((paging.getCurrentPage() - 1) / paging.getBlockPage()) * paging.getBlockPage() + 1);
		paging.setEndPage(paging.getStartPage() + paging.getBlockPage() - 1);
		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (paging.getEndPage() > paging.getTotalPage()) {
			paging.setEndPage(paging.getTotalPage());
		}
		
		StringBuilder pagingHtml = new StringBuilder();	
		
		// 이전 block 페이지
		pagingHtml.append("<ul>");
		if (paging.getCurrentPage() > paging.getBlockPage()) {
			pagingHtml.append("<li class=\"active\">");
			pagingHtml.append("<a href=\"#\" onclick=\"javascript:movePage(");
			pagingHtml.append( paging.getStartPage() - 1 );
			pagingHtml.append(");submit();\" >");
			pagingHtml.append("&laquo;");
			pagingHtml.append("</a>");
			pagingHtml.append("</li>");
		}
		
		//페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
		for (int i = paging.getStartPage(); i <= paging.getEndPage(); i++) {
			if (i > paging.getTotalPage()) {
				break;
			}
			if (i == paging.getCurrentPage()) {
				pagingHtml.append("<li class=\"disabled\">");
				pagingHtml.append("<a href=\"#\" >");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
				pagingHtml.append("</li>");
			} else {
				pagingHtml.append("<li class=\"active\">");
				pagingHtml.append("<a href=\"#\" ");
				pagingHtml.append(" onclick=\"javascript:movePage(");
				pagingHtml.append(i);
				pagingHtml.append(");submit();\" >");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
				pagingHtml.append("</li>");
			}
		}
		
		// 다음 block 페이지
		if (paging.getTotalPage() - paging.getStartPage() >= paging.getBlockPage()) {
			pagingHtml.append("<li class=\"active\">");
			pagingHtml.append("<a href=\"#\" ");
			pagingHtml.append(" onclick=\"javascript:movePage(");
			pagingHtml.append( paging.getEndPage() + 1 );
			pagingHtml.append(");submit();\" >");
			pagingHtml.append("&raquo;");
			pagingHtml.append("</a>");
			pagingHtml.append("</li>");
		}
		pagingHtml.append("</ul>");
		paging.setPagingHtml(pagingHtml.toString());
		
		//페이징 만든 후 변경
		
		// 현재 페이지의 마지막 글의 번호가 전체의 마지막 글 번호보다 작으면 lastCount를 +1 번호로 설정.
		if (paging.getEndCount() < lastCount){
			lastCount = paging.getEndCount() + 1;
		}
		
		// 현재 페이지에서 보여줄 마지막 글의 번호 설정.
		paging.setEndCount(lastCount);	
		
		log.debug("paging:" + paging.toString());
		
		return paging;
	}
	
	/**
	 * 컨텐츠에 따른 업로드할 sub path 리턴 예: grammar/xml/
	 * @param other
	 * @return
	 */
	public static String getSubPath(Other other) {
		StringBuilder subPath = new StringBuilder();
		
		if(other.getCategory().equals("grammar"))
			subPath.append("grammar/");
		else if(other.getCategory().equals("dialog"))
			subPath.append("dialog/");
		else if(other.getCategory().equals("reading"))
			subPath.append("reading/");
		
		
		if(other.getExt().equals("xml"))
			subPath.append("xml/");
		else if(other.getExt().equals("mp3"))
			subPath.append("mp3/");
		else if(other.getExt().equals("img"))
			subPath.append("img/");
		
		return subPath.toString();		
		
	}
	
	public static void ftpFileUpload(String subDir, File upFile) {
		
		FtpUtil ftp = new FtpUtil();
 
		String host = "nms171775.cafe24.com";
		String id = "nms171775";
		String password = "cafe241212";
		int port = 21;
		String dir = "contents/" + subDir;
		ftp.init(host, id, password, port);
		 
		
		ftp.upload("www/test/", upFile);//ex."f:\\test.txt"
		//ftp.download(dir, "다운로드할 파일명", "저장할 파일명"); //ex.ftp.download(dir, "test.txt", "f:\\testgood.txt")
		ftp.disconnection();
		log.debug("ftp 업로드 되나?");
 
	}
	
	
	public static String getToday10Str(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

}
