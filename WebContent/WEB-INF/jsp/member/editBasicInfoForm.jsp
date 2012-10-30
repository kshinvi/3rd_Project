<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기본정보변경</title>
<script>

</script>
</head>
<body>
<center>
	<s:if test="#session.member.memberId != null">
		<form name="frmEditBasicInfo1" action="../member/editBasicInfoForm2.action" method="POST">
			ID : <strong><font color="#3333AA"><s:property value="#session.member.memberId" /></font></strong> 님<br/><br/>
			<input type="hidden" name="memberId" value="<s:property value="#session.member.memberId"/>">
			비밀번호 : <input type="password" name="memberPw"><br/>
			${message}<br/>
			정보를 안전하게 보호하기 위해 비밀번호를 다시 한 번 확인하겠습니다.<br/><br/>
			<button>확인</button>
		</form>
	</s:if>
	<s:else>
		정상적인 연결이 아닙니다.<br/>
		창을 닫고 다시 시도해주세요.<br/>
	</s:else>
</center>	
</body>
</html>