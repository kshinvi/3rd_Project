<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>유저 관리 화면</title>
</head>
<script>
	function formCheck(cnt) {
		var Fm1 = document.LoginForm;
		if (cnt == 1) {
			if (Fm1.memberId.value == "") {
				alert("아이디를 입력하세요.");
				return false;
			} else if (Fm1.memberPw.value == "") {
				alert('비밀번호를 입력하세요.');
				return false;
			}
			str1 = Fm1.memberId.value;
			str2 = Fm1.memberPw.value;
			han = /[ㄱ-힣]/g;
			chk_han = str1.match(han);
			chk_han2 = str2.match(han);
			if (chk_han) {
				alert("아이디에 한글이 있습니다.");
				return false;
			}
			if (chk_han2) {
				alert("비밀번호에 한글이 있습니다.");
				return false;
			} else {
				return true;
			}
		}
		if (cnt == 2) {
			document.location.replace('UserLoginForm.action');
		}
		if(cnt == 3) {
			document.location.replace('UserFinderForm.action');
		}
	}
</script>
<body>
<s:form name="finderForm" cssClass="form-horizontal" method="post" action="UserFinderAction">
	<legend>ID찾기</legend>
	<div class="control-group">
		<label class="control-label" for="inputEmail">이메일 주소</label>
		<div class="controls"><s:textfield name="memEmail" value="" /></div>
	</div>
	<legend>비밀번호 찾기</legend>
	<div class="control-group">
		<label class="control-label" for="inputEmail">아이디 입력</label>
		<div class="controls"><s:textfield name="memId2" value="" /></div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputEmail">이메일 입력</label>
		<div class="controls"><s:textfield name="memEmail2" value="" /></div>
	</div>
	<div class="form-actions">
		<input type="submit" class="btn btn-primary"  value="찾기"/>
		<input type="button" class="btn" name="bt1" value="이전화면" onclick="formCheck(2);"/>
	</div>
</s:form>
<%-- <div id="dv1">
	결과 : ${message}
	<br/>
</div> --%>
<hr/>
</body>
</html>