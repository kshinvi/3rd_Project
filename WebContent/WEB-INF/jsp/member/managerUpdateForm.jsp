<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" HREF="/css/common.css" />
<title>정보수정 화면</title>
<script>
	function formCheck(flag) {
		var Fm1 = document.managerUpdateForm;
		var select = flag;
		if (select == 1) {
			Fm1.memberId.value = "";
			Fm1.memberPw.value = "";
			Fm1.memberPw2.value = "";
			Fm1.memberName.value = "";
			Fm1.memberEmail.value = "";
			Fm1.memberId.focus();
		}
		if (select == 2) {
			document.location.replace('UserManagerForm.action');
		}
	}

	function userRegCheck() {

		var Fm1 = document.managerUpdateForm;
		str1 = Fm1.memberId.value;
		str2 = Fm1.memberPw.value;
		str3 = Fm1.memberEmail.value;
		han = /[ㄱ-힣]/g;
		eng = /[A-Z]/;
		chk_han = str1.match(han);
		chk_han2 = str2.match(han);
		chk_han3 = str3.match(han);
		chk_eng = str1.match(eng);

		if (chk_han2) {
			alert("비밀번호에 한글이 있습니다.");
			return false;
		}
		if (Fm1.memberPw.value == "" || Fm1.memberPw.value.length < "4" || Fm1.memberPw.value.length >= '12') {
			Fm1.memberPw.value == "";
			alert('비밀번호는 4자 이상 12자 이내로 입력해주세요.');
			return false;
		} else if (Fm1.memberPw2.value == "") {
			alert('비밀번호를 한번 더 입력해주세요');
			return false;
		} else if (Fm1.memberPw.value != Fm1.memberPw2.value) {
			alert('비밀번호가 맞지 않습니다.');
			return false;
		} else if (Fm1.memberName.value == "" || Fm1.memberName.value >= 10) {
			alert('이름을 입력해 주세요(최대 10글자)');
			return false;
		} else if (Fm1.memberEmail.value == "") {
			alert('이메일을 입력해 주세요.');
			return false;
		} else if (chk_han3) {
			alert('이메일에 한글이 있으면 안됩니다.');
			return false;
		} else if (Fm1.memberLevel[0].checked == false && Fm1.memberLevel[1].checked == false && Fm1.memberLevel[2].checked == false) {
			alert('권한 여부를 체크해 주세요.');
			return false;
		} else if (Fm1.memberUse[0].checked == false && Fm1.memberUse[1].checked == false) {
			alert('사용여부를 체크해 주세요.');
			return false;
		} 
		else {
			return true;
		}
	}
	
	function initManagerUpdateForm(){
		var memberLevel = '<s:property value="member.memberLevel"/>';
		var memberUse = '<s:property value="member.memberUse"/>';

		var memberForm = document.managerUpdateForm;
		var memberLevelRadio = memberForm.memberLevel;
		var memberUseRadio = memberForm.memberUse;
		
		memberForm.reset();
		
		for (var index = 0; index < memberLevelRadio.length; index++){
			if(memberLevelRadio[index].value == memberLevel){
				memberLevelRadio[index].checked = true;
			}
		}
		
		for (var index = 0; index < memberUseRadio.length; index++){
			if(memberUseRadio[index].value == memberUse){
				memberUseRadio[index].checked = true;
			}
		}
		
	}
	
window.addEventListener("load", initManagerUpdateForm, true);
</script>
</head>
<body>
	
	<s:form name="managerUpdateForm" onsubmit="return userRegCheck();" action="ManagerUpdateAction" validate="true">
		<s:fielderror>
			<s:param>memId</s:param>
		</s:fielderror>
		<input type="hidden" name="memberPk" value="<s:property value="%{member.memberPk }" />" />
		<input type="hidden" name="memberId" value="<s:property value="%{member.memberId }" />" />
		<table>
			<tr>
				<td colspan="2"><s:property value="%{member.memberId }" />님
					<hr />
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="memberName"value="<s:property value="%{member.memberName }" />" />
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="memberEmail"
					value="<s:property value="%{member.memberEmail }" />" /></td>
			</tr>
			<tr>
				<td>권한</td>
				<td>
					<input type="radio" name="memberLevel" value="1" />관리자
					<input type="radio"	name="memberLevel" value="3" />선생 									
					<input type="radio" name="memberLevel" value="5" />학생 
				</td>
			</tr>
			<tr>
				<td>사용여부</td>
				<td>
					<input type="radio" name="memberUse" value="1" />사용 
					<input type="radio"	name="memberUse" value="0" />미사용
				</td>
			</tr>
			<tr align="center">
				<td colspan="2"><hr />회원 탈퇴의 경우 미사용에 체크</td>
			</tr>
		</table>
		<input type="submit" name="bt1" value="저장" class="btn btn-info"/>
		<input type="button" name="btnReset" value="다시 입력" onclick="initManagerUpdateForm();" class="btn btn-info"/>
		<input type="button" name="bt3" value="목록으로" onClick="formCheck(2);" class="btn btn-info"/>
	</s:form>
</body>
</html>