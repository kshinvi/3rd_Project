package com.emn.game.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.content.action.WordAction;
import com.emn.game.model.MazeWord;
import com.emn.game.model.PairingCard;
import com.emn.game.model.PointBean;
import com.emn.member.model.Member;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class MazeAction extends EmnAction implements ModelDriven<PointBean>, Preparable{
	public static final Logger log = Logger.getLogger(WordAction.class);
	// 과 가져올때
	ArrayList<MazeWord> gwaList;
	
	public ArrayList<MazeWord> getGwaList() {
		return gwaList;
	}
	public void setGwaList(ArrayList<MazeWord> gwaList) {
		this.gwaList = gwaList;
	}
	
	//
	private String pointStr;
	private PointBean pointBean;
	//private int point;
	
	public PointBean getPointBean() {
		return pointBean;
	}
	public void setPointBean(PointBean pointBean) {
		this.pointBean = pointBean;
	}
	
	public String getPointStr() {
		return pointStr;
	}
	public void setPointStr(String pointStr) {
		this.pointStr = pointStr;
	}

	//점수 매길때 
	private int mypoint;
	private int mazepoint;
	
	
	
	public int getMypoint() {
		return mypoint;
	}
	public void setMypoint(int mypoint) {
		this.mypoint = mypoint;
	}
	public int getMazepoint() {
		return mazepoint;
	}
	public void setMazepoint(int mazepoint) {
		this.mazepoint = mazepoint;
	}
	public String form() throws Exception {
		
		//포인트 가져오기!
		ActionContext context = ActionContext.getContext();		
		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		int memberPk = sessionMember.getMemberPk();
				
		PointBean pointBean = new PointBean();
		pointBean.setMemberPk(memberPk);
				
		Member resultMem = (Member) SqlMapConfig.selectOneEmn("getPoint", sessionMember);
				
				
		pointStr = Integer.toString(resultMem.getMemberPoint());
				
		//과 리스트 가져오기
		ArrayList<? extends SqlMapUsableObj> list = 
				(ArrayList<? extends SqlMapUsableObj>)SqlMapConfig.selectListEmn("findGwas");
		//log.debug(list);
		gwaList = (ArrayList<MazeWord>)list;
		
		for (MazeWord mazeWord : gwaList) {
			log.debug(mazeWord.toString());
		}
		
		return SUCCESS;
	}
	
	public String score() throws Exception {
		
		/*ArrayList<? extends SqlMapUsableObj> list = 
				(ArrayList<? extends SqlMapUsableObj>)SqlMapConfig.selectListEmn("getGwas");
		//log.debug(list);
		gwaList = (ArrayList<PairingCard>)list;
		
		for (PairingCard pairingCard : gwaList) {
			log.debug(pairingCard.toString());
		}*/
		//log.debug(heart);
		return SUCCESS;
	}
	public String _savePoint() throws Exception{
		ActionContext context = ActionContext.getContext();		
		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		int memberPk = sessionMember.getMemberPk();
		
		pointBean.setMemberPk(memberPk);
		pointBean.setDetail("미로");
		sessionMember.setMemberPoint(pointBean.getPoint());
		
		// sessionMember 에는 현재 포인트값을 저장
		SqlMapConfig.updateEmn("updatePoint", sessionMember);
		// pointBean 에는 내가 변화된 포인트 차이를 저장
		SqlMapConfig.insertEmn("insertPoint", pointBean);
	
//		PrintWriter out = ServletActionContext.getResponse().getWriter();
//		out.print(returnStr.toString());
//	
		return SUCCESS;
	}
	public String findWords() throws Exception{
		return SUCCESS;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public PointBean getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
