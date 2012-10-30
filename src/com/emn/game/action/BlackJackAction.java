package com.emn.game.action;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.content.action.ContentAction;
import com.emn.content.model.Gwa;
import com.emn.game.model.PointBean;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BlackJackAction extends EmnAction implements ModelDriven<PointBean>, Preparable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Logger log = Logger.getLogger(BlackJackAction.class);
	
	private String pointStr;
	private PointBean pointBean;
	
	
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
	
	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();		
		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		int memberPk = sessionMember.getMemberPk();
		
		PointBean pointBean = new PointBean();
		pointBean.setMemberPk(memberPk);
		
		Member resultMem = (Member) SqlMapConfig.selectOneEmn("getPoint", sessionMember);
		
		
		pointStr = Integer.toString(resultMem.getMemberPoint());		
		
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
		pointBean.setDetail("블랙잭");
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
	
	@Override
	public void prepare() throws Exception {
		pointBean = new PointBean();
	}
	@Override
	public PointBean getModel() {
		return pointBean;
	}
	
}
