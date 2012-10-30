package com.emn.member.action;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.emn.common.EmnAction;
import com.emn.common.RestClient;
import com.emn.common.RestMethod;
import com.emn.common.SqlMapConfig;
import com.emn.member.model.Member;
import com.opensymphony.xwork2.ActionContext;

public class RestLogin extends EmnAction{

	private static final long serialVersionUID = 44338864691522027L;
	
	public static final Logger log = Logger.getLogger(RestLogin.class);

	public String execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String method = request.getMethod().toUpperCase();
		
		log.debug("GET메소드만 취급 : " + method );
		
		String requestBody = "";
		String responseStr = "";
		
		if(request.getMethod().toUpperCase().equals("GET")){
			
			requestBody = getBody();
			requestBody.replaceAll("&", "&amp");
			log.debug("requestBody : " + requestBody);
			
			Serializer sr = new Persister();
			Member member = sr.read(Member.class, requestBody);
			member = (Member) SqlMapConfig.selectOneEmn("login", member);
			if(member == null){
				//회원없음
				responseStr = "false";
			}else{
				//회원있음
				responseStr = getSuccess(member);
			}
			//responseStr 을 어떻게 줄까?
			
		}
		return SUCCESS;
	}
	
	
	private String getBody() {		
		String requestBody = "";
		Map parameters = ActionContext.getContext().getParameters();		
		Set<String> keySet = parameters.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			String s1[] = (String[]) parameters.get(key);
			requestBody = s1[0];
		}
		return requestBody;
	}
	
	private String getSuccess(Member member){
		Serializer sr = new Persister();
		StringWriter sw = new StringWriter();
		try {
			sr.write(member, sw);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		StringBuilder sb = new StringBuilder("<?xml version='1.0' encoding='UTF-8' ?>\r\n");
		sb.append(sw.toString());
		
		return sb.toString();
	}
	
	public static boolean isOtherServer(Member member) throws Exception{
		boolean result = false;
		
		//다른 서버에 접속!!
//		RestClient rc = new RestClient("203.233.196.187", "80", "/Chapter02/HelloWorld2.action");
//		rc.execute(RestMethod.GET);
//		String res = rc.getResponse();
//		
//		System.out.println("주완 서버 접속결과 : " + res);
		
		log.debug(member.toString());
		
		RestClient rc = new RestClient("203.233.196.193", "8080", "/with/api/member/");
		rc.execute(RestMethod.POST);
		rc.addParam("name", member.getMemberId());
		rc.addParam("password", member.getMemberPw());
		String res = rc.getResponse();
		
		log.debug("========== 1조서버 접속결과 : " + res);
		
		return result;
		
	}

}
