package com.emn.member.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.emn.common.EmnAction;
import com.emn.common.RestClient;
import com.emn.common.RestMethod;
import com.emn.common.SqlMapConfig;
import com.emn.common.SqlMapUsableObj;
import com.emn.member.model.Member;

public class UserRegAction extends EmnAction {

	// 전달받은 파라메터를 넣을 변수.
	private Member member;
	
	// 전달받은 파라메터가 저장되어 있는 변수.
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	
	// 결과 메시지 전달용 변수
	private String message;

	// for logging
	public static final Logger log = Logger.getLogger(UserRegAction.class);
	
	// getter & setter
	public String getMemberId() {
		return memberId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute() throws Exception {

		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		paramMember.setMemberName(memberName);
		paramMember.setMemberEmail(memberEmail);
		
		List<SqlMapUsableObj> resultMemberList = SqlMapConfig.selectListEmn("selectMemberIdEmail", paramMember);

		log.debug("size=" + resultMemberList.size());
		if(resultMemberList.size() >= 2){
			message = "<font color='red'>이미 사용하고 있는 ID와 e-Mail입니다.</font>";
			return INPUT;
		}
		else if(resultMemberList.size() == 1){
			Member tempMember = (Member) resultMemberList.get(0);
			if(paramMember.getMemberId().equals(tempMember.getMemberId())){
				message = "<font color='red'>이미 사용하고 있는 ID입니다.</font>";
				return INPUT;
			}
			else if(paramMember.getMemberEmail().equals(tempMember.getMemberEmail())){
				message = "<font color='red'>이미 사용하고 있는 ID입니다.</font>";
				return INPUT;
			}
		}
		SqlMapConfig.insertEmn("insertMember", paramMember);
		
		//1조에도 회원가입(not use but don't erase )
		//joinRest(paramMember);
		
		
		return SUCCESS;
	}
	
	public static String joinRest(Member member) throws Exception{
		String res = "";
		
		log.debug(member.toString());
		
		RestClient rc = new RestClient("203.233.196.177", "8281", "/with/api/members");
		rc.addHeader("Content-Type", "application/json");
		rc.addHeader("Accept", "application/json");
		rc.addParam("test", "{\"name\":\"chotestcho\", \"password\":\"chotestcho\", \"email\":\"chotestcho@chotest.com\"}");
//		rc.addParam("name", member.getMemberId());
//		rc.addParam("password", member.getMemberPw());
//		rc.addParam("email", member.getMemberEmail());
		
		rc.execute(RestMethod.POST);
		res = rc.getResponse();

		
//		HttpClient httpClient = new DefaultHttpClient();
//		
//		try { 
//	        HttpPost postRequest = new HttpPost("http://203.233.196.177:8281/with/api/members");
////	        StringBuilder str = new StringBuilder();
////	        str.append("{name:\"" );
////	        str.append(member.getMemberId());
////	        str.append("\",password:\"");
////	        str.append(member.getMemberPw());
////	        str.append("\",email:\"");
////	        str.append(member.getMemberEmail());
////	        str.append("\"}");
////	        StringEntity params = new StringEntity(str.toString());
////	        postRequest.setHeader("Accept", "application/json"); 
////	        postRequest.setHeader("content-type", "application/x-www-form-urlencoded"); 
////	        postRequest.setEntity(params);
//	        
//	        JSONObject jsonObj = new JSONObject(); 
//	        try { 
//	        	jsonObj.put("name", member.getMemberId()); 
//		        jsonObj.put("password", member.getMemberPw());
//		        jsonObj.put("email", member.getMemberEmail()); 
//	        } catch (JSONException e) { 
//	            //Log.e(TAG, "JSONException: " + e);
//	        	log.debug("error:="+e);
//	        } 
//	        
//	        StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8); 
//
//
//	        
////	        List<NameValuePair> postParams = new ArrayList<NameValuePair>(); 
////	        postParams.add(new BasicNameValuePair("name", "{\"name\":\"chotestcho\", \"password\":\"chotestcho\", \"email\":\"chotestcho@chotest.com\"}")); 
//	        
////	        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParams); 
////	        entity.setContentEncoding(HTTP.UTF_8);
//
////	        StringEntity entity = new StringEntity("{\"name\":\"chotestcho\", \"password\":\"chotestcho\", \"email\":\"chotestcho@chotest.com\"}", HTTP.UTF_8);       
//	        
//	        
//	        entity.setContentType("application/json"); 
//	        postRequest.setEntity(entity); 
//	     
//	        //postRequest.setHeader("Content-Type", "application/json");
//	        //postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
//	        //postRequest.setHeader("Accept", "application/json");
//	        
//	        HttpResponse response = httpClient.execute(postRequest);
//	       
//	        log.debug("========== 1조서버 접속결과 : " + response);
//	 
//	    }catch (Exception ex) { 
//	        // handle exception here 
//	    } finally { 
//	        httpClient.getConnectionManager().shutdown(); 
//	    } 
//
		log.debug("========== 1조서버 접속결과 : " + res);
		
		
		return null;
		
	}
	
	
	/**
	 * 1조에 값이 있는 경우 id pw를 세팅한다.
	 * @return
	 * @throws Exception
	 */
	public String restLoginForm() throws Exception {
		log.debug(memberId);
		log.debug(memberPw);
		return SUCCESS;
	}
	
}