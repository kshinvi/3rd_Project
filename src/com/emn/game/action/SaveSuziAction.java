package com.emn.game.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.content.action.WordAction;
import com.emn.game.model.PairingCard;
import com.emn.game.model.PointBean;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class SaveSuziAction extends EmnAction implements ModelDriven<PointBean>, Preparable{
	private String pointStr;
	private PointBean pointBean;
	
	public String getPointStr() {
		return pointStr;
	}
	public void setPointStr(String pointStr) {
		this.pointStr = pointStr;
	}

	public PointBean getPointBean() {
		return pointBean;
	}
	public void setPointBean(PointBean pointBean) {
		this.pointBean = pointBean;
	}

	ArrayList<PairingCard> gwaList;
	/*public void setGwaList(ArrayList<PairingCard> gwaList) {
		this.gwaList = gwaList;
	}*/
	public ArrayList<PairingCard> getGwaList() {
		return gwaList;
	}
	
	ArrayList<PairingCard> cardsList;
	/*public void setGwaList(ArrayList<PairingCard> gwaList) {
		this.gwaList = gwaList;
	}*/
	public ArrayList<PairingCard> getCardsList() {
		return cardsList;
	}
	
	PairingCard pairingCard;
	public PairingCard getPairingCard() {
		return pairingCard;
	}
	/*public void setPairingCard(PairingCard pairingCard) {
		this.pairingCard = pairingCard;
	}*/
	
	public static final Logger log = Logger.getLogger(SaveSuziAction.class);
	
	/**
	 * 과 셀렉트 박스 선택하는 폼 부르기
	 * @return
	 * @throws Exception
	 */
	public String form() throws Exception {		
		
		ArrayList<? extends SqlMapUsableObj> list = 
				(ArrayList<? extends SqlMapUsableObj>)SqlMapConfig.selectListEmn("getGwas");
		//log.debug(list);
		gwaList = (ArrayList<PairingCard>)list;
		
		/*for (PairingCard pairingCard : gwaList) {
			log.debug(pairingCard.toString());
		}*/
		
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();		
		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		int memberPk = sessionMember.getMemberPk();
		log.debug("memberPk = " + memberPk);
		PointBean pointBean = new PointBean();
		pointBean.setMemberPk(memberPk);
		
		Member resultMem = (Member) SqlMapConfig.selectOneEmn("getPoint", sessionMember);		
		
		pointStr = Integer.toString(resultMem.getMemberPoint());
		
		ArrayList<? extends SqlMapUsableObj> list = (ArrayList<? extends SqlMapUsableObj>)SqlMapConfig.selectListEmn("getAllWords");
		cardsList = (ArrayList<PairingCard>)list;
		session.put("pairingList", cardsList);
		context.setSession(session);
		log.debug("excute액션 성공");
		return SUCCESS;
	}
	
	/**
	 * 포인트 저장하기(ajax)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String _savePoint() throws Exception {
		log.debug(pointBean.toString());
		
		ActionContext context = ActionContext.getContext();		
		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		int memberPk = sessionMember.getMemberPk();
		
		pointBean.setMemberPk(memberPk);
		pointBean.setDetail("수지 구하기");
		sessionMember.setMemberPoint(pointBean.getPoint());
		
		// sessionMember 에는 현재 포인트값을 저장
		SqlMapConfig.updateEmn("updatePoint", sessionMember);
		// pointBean 에는 내가 변화된 포인트 차이를 저장
		SqlMapConfig.insertEmn("insertPoint", pointBean);
	
//		PrintWriter out = ServletActionContext.getResponse().getWriter();
//		out.print(returnStr.toString());
//		
		return null;
	}

	/*
	 * 모든 단어 가져오기
	 */
	public String getAllWords() throws Exception {
		ArrayList<? extends SqlMapUsableObj> list = (ArrayList<? extends SqlMapUsableObj>)SqlMapConfig.selectListEmn("getAllWords");
		cardsList = (ArrayList<PairingCard>)list;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.put("pairingList", cardsList);
		context.setSession(session);
/*		for(PairingCard a : cardsList) {
			System.out.println(a.getNihongo());
		}*/
		return SUCCESS;
	}
	
	public String refreshGame() throws Exception {		
		return SUCCESS;		
	}
	
	@Override
	public void prepare() throws Exception {
		//pairingCard = new PairingCard();
		pointBean = new PointBean();
	}

	@Override
	public PointBean getModel() {
		return pointBean;
	}
}