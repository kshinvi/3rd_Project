package com.emn.member.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.game.model.PointDetail;
import com.emn.game.model.PointRegDate;
import com.emn.member.model.Member;
import com.emn.member.model.Point;
import com.opensymphony.xwork2.ActionContext;

public class PointManagerAction extends EmnAction {

	private static final long serialVersionUID = 44338864691522027L;

	public static final Logger log = Logger.getLogger(RestLogin.class);

	private String regDate;
	private String pointStr;
	private Point point;
	private List<Point> list;
	private List<PointDetail> detailList;
	private List<PointRegDate> regDateList;
	private PointRegDate regDateAjax;
		
	public PointRegDate getRegDateAjax() {
		return regDateAjax;
	}

	public void setRegDateAjax(PointRegDate regDateAjax) {
		this.regDateAjax = regDateAjax;
	}

	public String getPointStr() {
		return pointStr;
	}

	public void setPointStr(String pointStr) {
		this.pointStr = pointStr;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public List<PointRegDate> getRegDateList() {
		return regDateList;
	}

	public void setRegDateList(List<PointRegDate> regDateList) {
		this.regDateList = regDateList;
	}

	public List<PointDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PointDetail> detailList) {
		this.detailList = detailList;
	}

	public List<Point> getList() {
		return list;
	}

	public void setList(List<Point> list) {
		this.list = list;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();

		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		Member member = new Member();
		member.setMemberPk(sessionMember.getMemberPk());
		list = (List) SqlMapConfig.selectListEmn("getMemberPoint", member);
		
		log.debug(list.toString());
		detailList = (List) SqlMapConfig.selectListEmn("getDetail", member);
		regDateList = (List) SqlMapConfig.selectListEmn("getRegDate", member);
		//log.debug("detailList : " +list.toString());
		
		Member resultMem = (Member) SqlMapConfig.selectOneEmn("getPoint", sessionMember);		
		
		pointStr = Integer.toString(resultMem.getMemberPoint());
		return SUCCESS;
	}
	
	public String getDateResult() throws Exception {
		ActionContext context = ActionContext.getContext();

		Map<String, Object> session = context.getSession();
		Member sessionMember = (Member) session.get("member");
		Member member = new Member();
		member.setMemberPk(sessionMember.getMemberPk());
		
		member.setRegdate(regDate);
		log.debug("==========regdate = " + member.getRegdate() + " , memberPk = " + sessionMember.getMemberPk());
		regDateAjax =  (PointRegDate)SqlMapConfig.selectOneEmn("getUserRegDate", member);
		//log.debug("regDateAjax = " + regDateAjax.getSum() + "," + regDateAjax.getRegdate());
		//log.debug("regDateAjax = " + regDateAjax.toString());

		PrintWriter out = ServletActionContext.getResponse().getWriter();
		//log.debug(regDateAjax.toString());
		if(regDateAjax == null){
			out.print("null");
		}else{
			out.print(regDateAjax.getRegdate() + "," + regDateAjax.getSum() + "," + regDateAjax.getCount());	
		}		
		return null;
	}
}