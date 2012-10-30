<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 성공</title>
<script>
</script>

</head>
<body>
<form name="Fm1" method="get">
<br/>
<br/>
<br/>
<div class='container'>
	<div class="container">
		<div class="span6 offset3">
			<h2>e-Minnano Nihongo</h2>
		</div>
	</div>
	<br/>
	<div class="container">
		<div class="span6 offset3">
			<div class="alert alert-block">
				<h2>회원가입 성공!</h2>
				<font color="#ffffff">
					입력하신 아이디 : ${memberId }<br/>
					입력하신 이름 : ${memberName }<br/>
					입력하신 이메일 : ${memberEmail }
				</font>
				<br/>
				<br/>
				<input type="button" class="btn btn-primary" name="bt2" value="메인화면" onclick="document.location.replace('../');"/>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>