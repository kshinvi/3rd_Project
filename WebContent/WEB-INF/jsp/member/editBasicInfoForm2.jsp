<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기본정보변경</title>
<script>
	function refillValue(){
		var memberForm = document.frmEditBasicInfo;
		var memberName = memberForm.memberName;
		var memberEmail = memberForm.memberEmail;
		
		memberName.value = "<s:property value='#session.member.memberName' />";
		memberEmail.value = "<s:property value='#session.member.memberEmail' />";
	}
</script>
</head>
<body>
	<s:if test="#session.member.memberId != null">
	<table border="1" align="center">
		<form name="frmEditBasicInfo" method="POST" action="changeBasicInfo.action">
		<tr>
			<td>ID :</td>
			<td><s:property value="#session.member.memberId" /><input type="hidden" name="memberId" value="<s:property value='#session.member.memberId' />"></td>
		</tr>
		<tr>
			<td>Name :</td>
			<td><input type="text" name="memberName" value="<s:property value='#session.member.memberName' />" required /></td>
		</tr>
		<tr>
			<td>e-Mail :</td>
			<td><input type="email" name="memberEmail" value="<s:property value='#session.member.memberEmail' />" required/></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" onclick="">확인</button>
				<button type="button" onclick="refillValue();">다시입력</button>
			</td>				
		</tr>
		</form>
	</table>
	</s:if>
</body>
</html>