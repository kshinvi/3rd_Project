<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 화면</title>
<script>

	//입력폼을 체크하여 모두 true인 경우만 true를 리턴하여 submit하도록 한다.
	function checkFrmRegisterMember(){
		
		if(	checkId() & 
			checkPw() &
			checkPw2() &
			checkEmail()
		){
			//with
			<s:if test="%{memberId != null}">
				frmRegisterMember.submit();
			</s:if>
			<s:else>
				restReg();
			</s:else>
				
		}
	//alert(false);
	//return false;
	}
	
	function restReg(){
		var regForm = document.frmRegisterMember;
		$.ajax({
			type : 'POST',
			url : 'http://203.233.196.177:8281/with/api/members',
			dataType : "json",
			data: {name:regForm.memberId.value, password:regForm.memberPw.value, email:regForm.memberEmail.value},
			success : function(data) {
				if(data.result){
					frmRegisterMember.submit();	
				}else{
					alert("'With' 가입중 문제가 발생하였습니다. \n본 사이트로만 가입합니다.");
					frmRegisterMember.submit();	
				}
			}
		});
	}
	
	// ID가 영문자와 숫자인지 확인하여 아니면 false 리턴하고 에러를 표시하는 함수.
	function checkId(){
		var id = document.frmRegisterMember.memberId.value;		
		var tdCautionId = document.getElementById("tdCautionId");
		
		document.frmRegisterMember.memberId.value = id.toLowerCase();
		
		// 값 길이를 확인하고 0이상이면 내용을 검사
		if(checkLength(id, 5, 12)){
			tdCautionId.innerHTML = "";
			if(checkEngChar(id) == false){
				tdCautionId.innerHTML = "<font color='red'size='1px'>아이디에는 영문자와 숫자만 입력 가능합니다.</font>";
				return false;
			}
		}
		else {
			tdCautionId.innerHTML = "<font color='red'size='1px'>값을 5자~12자 이내로 입력하세요.</font>";
			return false;
		}		
		return true;
	}
	
	// PW가 영문자와 숫자, 특수문자인지 확인하여 아니면 false 리턴하고 에러를 표시하는 함수.
	function checkPw(){
		var pw = document.frmRegisterMember.memberPw.value;		
		var tdCautionPw = document.getElementById("tdCautionPw");
		
		// 값 길이를 확인하고 0이상이면 내용을 검사
		if(checkLength(pw, 8, 12)){
			tdCautionPw.innerHTML = "";
			if(checkEngChar(pw) == false && checkSpChar(pw) == false){
				tdCautionPw.innerHTML = "<font color='red'size='1px'>비밀번호에는 영문자와 숫자만 입력 가능합니다.</font>";
				return false;
			}
		}
		else {
			tdCautionPw.innerHTML = "<font color='red'size='1px'>값을 8자~12자 이내로 입력하세요.</font>";
			return false;
		}		
		return true;
	}
	
	// PW2가 PW와 같은지 검사하여 맞으면 true리턴하고 아니면 false 리턴하고 에러를 표시하는 함수.
	function checkPw2(){
		var pw = document.frmRegisterMember.memberPw.value;
		var pw2 = document.frmRegisterMember.memberPw2.value;		
		var tdCautionPw2 = document.getElementById("tdCautionPw2");
		
		// PW와 같은지만 확인한다.
		if(pw != pw2){
			tdCautionPw2.innerHTML = "<font color='red'size='1px'>비밀번호와 값이 다릅니다.</font>";
				return false;
		}
		else {
			tdCautionPw2.innerHTML = "";
		}		
		return true;
	}
	
	function checkEmail(){
		var getMailStr = frmRegisterMember.memberEmail.value;
		var isMail = getMailStr.indexOf('@');
		if(isMail >= 0){
			isMail = getMailStr.indexOf('.');
		}
		if(isMail >= 0){
			if( getMailStr.indexOf('@') > getMailStr.indexOf('.')){
				isMail = -1;
			}
		}
		
		if(isMail < 0 ){
			tdCautionEmail.innerHTML = "<font color='red'size='1px'>이메일 형식이 아닙니다.</font>";
			return false;
		}else {
			tdCautionEmail.innerHTML = "";
		}
		return true;
	}
	
	window.onload = function() {
		<s:if test="%{memberId != null}">
			tdCautionId.innerHTML = "<font color='blue'size='1px'>제휴사 'With'에 가입되어있는 ID 입니다. 나머지 정보를 입력해주세요.</font>";
		</s:if>
		<s:else>
			tdCautionId.innerHTML = "";
		</s:else>	
	}
	
	
</script>
</head>
<body>
<form name="frmRegisterMember" class="form-horizontal" onsubmit="return false;" action="UserRegAction.action" method="POST">
	<legend>회원가입</legend>
	<div class="control-group">
		<label class="control-label" for="inputEmail">아이디</label>
		<div class="controls">
			<input type="text" name="memberId" onchange="checkId();" maxlength="12" value="${memberId}" <s:if test="%{memberId != null}">readonly</s:if> required autofocus /><p id="tdCautionId"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">비밀번호</label>
		<div class="controls">
			<input type="password" name="memberPw" onchange="checkPw();" maxlength="12" value="${memberPw}" <s:if test="%{memberId != null}">readonly</s:if> required/><p id="tdCautionPw"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">비밀번호 확인</label>
		<div class="controls">
			<input type="password" name="memberPw2" onchange="checkPw2();" maxlength="12" value="${memberPw}" <s:if test="%{memberId != null}">readonly</s:if> required/><p id="tdCautionPw2"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">이름</label>
		<div class="controls">
			<input type="text" name="memberName" maxlength="10 " required/><p id="tdCautionName"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">이메일</label>
		<div class="controls">
			<input type="email" name="memberEmail" onchange="checkEmail()" value="${memberEmail}" required/><p id="tdCautionEmail"/>
		</div>
	</div>
	<div class="form-actions">
		<input type="button" class="btn btn-primary"  value="가입하기" onclick="checkFrmRegisterMember();"/>
		<input type="reset" class="btn" name="bt1" value="새로작성"/>
		<input type="button" class="btn" name="bt1" value="이전화면" onClick="document.location.replace('UserLoginForm.action');"/>
	</div>
</body>
</html>