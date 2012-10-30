<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="#session.memId != 'null'">
<s:property value="#session.member.memberId"/>님 안녕하세요.<br />
<a href="./UserLogoutAction.action">로그아웃</a>
<a href="./UserUpdateForm.action">정보수정</a>
</s:if>
<s:else>
	<a href="./UserLoginForm.action">로그인</a>
</s:else>