<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<s:form name="ManagerForm" method="post" action="FindManagerAction.action">
<h2>회원관리</h2>
	<table class="table table-bordered">
		<tr>
			<td>ID 또는 이름입력</td>
			<td><s:textfield name="memberId" /></td>
			<td><button type="submit" class="btn btn-info">검색</button></td>
		</tr>
	</table>
	<div id="dv1">${message}</div>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>ID</th>
			<th>이름</th>
			<th>E-Mail</th>
			<th>권한</th>
			<th>상태</th>
			<th>기능</th>
		</tr>

		<s:iterator value="memberList">
			<tr>
				<td><s:property value="memberPk" /></td>
				<td><s:property value="memberId" /></td>
				<td><s:property value="memberName" /></td>
				<td><s:property value="memberEmail" /></td>					
				<td>
					<s:if test="memberLevel == 1">관리자</s:if>
					<s:if test="memberLevel == 3">선생</s:if>
					<s:if test="memberLevel == 5">학생</s:if>						
				</td>
				<td>
					<s:if test="memberUse == 0">탈퇴</s:if>
					<s:if test="memberUse == 1"></s:if>
				</td>
				<td>
					<a href="/emn/member/ManagerUpdateForm.action?memberPk=<s:property value="memberPk" />" />수정</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:form>
</body>
</html>