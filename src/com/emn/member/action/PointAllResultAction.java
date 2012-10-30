package com.emn.member.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.emn.common.EmnAction;
import com.emn.common.SqlMapConfig;
import com.emn.member.model.Member;
import com.emn.member.model.Point;
import com.opensymphony.xwork2.ActionContext;

public class PointAllResultAction extends EmnAction {
	
	private static final long serialVersionUID = 44338864691522027L;

	public static final Logger log = Logger.getLogger(RestLogin.class);
	
	private List<Point> list;
	
	
	public List<Point> getList() {
		return list;
	}

	public void setList(List<Point> list) {
		this.list = list;
	}

	public String execute() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		Member sessionMember = (Member) session.get("member");
		Member member = new Member();
		
		member.setMemberPk(sessionMember.getMemberPk());
		log.debug(member.toString());
		list = (List) SqlMapConfig.selectListEmn("getMemberPoint", member);
		
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		
		if(list == null) {
			out.print("null");
		} else {
			for (Point p : list) {
				System.out.println(p.getDetail() + " : " + p.getPoint() + " : " + p.getRegdate());
			}
		}
		log.debug(list.toString());
		return null;
	}
}