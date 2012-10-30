<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" HREF="/css/common.css" />
<title>정보수정 화면</title>
</head>
<script type="text/javascript">
</script>
<body>
	<h1>e-Minnano Nihonngo</h1><br/>
	<s:property value="#session.member.memberId" />님 회원정보 수정<br/>
	<button type="button" class="btn btn-info" onclick="document.location.replace('../member/editBasicInfoForm.action');">기본정보 수정</button><br/><br/>
	<button type="button" class="btn btn-info" onclick="document.location.replace('../member/changePasswordForm.action');">비밀번호 변경</button><br/><br/>
	<button type="button" class="btn btn-info" onclick="document.location.replace('../member/pointManagerForm.action');">포인트</button>
</body>
</html>