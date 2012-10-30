<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>
<script>
	function checkFrmChangePassword(){
		var validFlag = true;
		
		var fcp = document.frmChangePassword;
		
		var oldPw = fcp.memberPwOld;
		var newPw = fcp.memberPw;
		var newPw2 = fcp.memberPw2;
		
		var tdOldPw = document.getElementById("tdOldPw");
		var tdNewPw = document.getElementById("tdNewPw");
		var tdNewPw2 = document.getElementById("tdNewPw2");
		
		if(oldPw.value.length < 5){
			tdOldPw.innerHTML = "암호는 5자~12자입니다.";
			validFlag = false;
		}
		else {
			tdOldPw.innerHTML = "";
			validFlag = true;
		}
		
		if(newPw.value.length < 5){
			tdNewPw.innerHTML = "암호는 5자~12자입니다.";
			validFlag = false;
		}
		else {
			tdNewPw.innerHTML = "";
			validFlag = true;
		}
		
		if(newPw2.value.length < 5){
			tdNewPw2.innerHTML = "암호는 5자~12자입니다.";
			validFlag = false;
		}
		else if(newPw.value != newPw2.value){
			tdNewPw2.innerHTML = "암호는 같은 걸로 입력하는걸로";
			validFlag = false;
		}
		else {
			tdOldPw2.innerHTML = "";
			validFlag = true;
		}

		return false;
	}
</script>
</head>
<body>
	<form name="frmChangePassword" action="changePassword.action" method="POST" onsubmit="return checkFrmChangePassword();">
	<table>
		
		<tr>
			<td>현재 비밀번호</td>
			<td><input type="password" name="memberPwOld" maxlength="12" required/></td>
			<td id="tdOldPw"></td>
		</tr>
		<tr>
			<td colspan="3">
				<hr/>
			</td>
		</tr>
		<tr>
			<td>새 비밀번호</td>
			<td><input type="password" name="memberPw" maxlength="12" required/></td>
			<td id="tdNewPw"></td>
		</tr>
		<tr>
			<td>새 비밀번호 확인</td>
			<td><input type="password" name="memberPw2" maxlength="12" required/></td>
			<td id="tdNewPw2"></td>
		</tr>
		<tr>
			<td id="tdCaution" colspan="3">
				${message}
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">변경</button>
				<button type="reset">다시입력</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>