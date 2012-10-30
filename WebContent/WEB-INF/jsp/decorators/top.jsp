<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="#session.member.memberId != 'null'">
<br/>
<div class='navbar'>
  <div class='navbar-inner'>
    <div class='container-fluid'>
	    <div class="row-fluid">
	    	<ul class='nav'>
			    <li><a href="../member/UserLoginForm.action"><img src = "../img/smile_girl1.png"/><b><font size = "3px">MINNANO</font></b></a></li>
			     <li><font size = "3px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>			  </li>
			      <s:if test="#session.member.memberLevel == 5">
					                                            
					<li><a href="../lecture/lectureStatus.action"><b>강의실</b></a></li>
					<li><a href="../study/quest_list.action"><b>자습실</b></a></li>
					<li><a href="../game/gameMain.action"><b>놀이터</b></a></li>
				</s:if>
				<s:elseif test="#session.member.memberLevel == 3">
					<li><a href="../teacher/LearnStatus.action"><b>강의현황</b></a></li>
					<li><a href="../teacher/exam_list.action"><b>평가현황</b></a><br/></li>
				</s:elseif>
				<s:elseif test="#session.member.memberLevel == 1">
					<li><a href="../content/lecture_list.action"><b>강의관리</b></a></li>
					<li><a href="../content/content_form.action"><b>컨텐츠관리</b></a></li>
					<li><a href="../member/UserManagerForm.action"><b>회원관리</b></a></li>
				</s:elseif>
			</ul>
			<ul class='nav pull-right'>
				<li><a href="#"><s:property value="#session.member.memberName"/>님 안녕하세요.</a></li>
				<li><a href="../member/UserLogoutAction.action">로그아웃</a></li>
				<li><a href="../member/UserUpdateForm.action">정보수정</a></li>				
			</ul>
		</div>
    </div>
  </div>
</div>
</s:if>
<s:else>
<br/>
<div class='navbar'>
	<div class='navbar-inner'>
		<div class='container'>
			<ul class='nav'>
				<li><a href="../member/UserLoginForm.action"><img src = "../img/smile_girl1.png"/><b><font size = "3px">MINNANO</font></b></a></li>
			</ul>
			<ul class='nav pull-right'>
				<!-- <li><a href="../member/UserLoginForm.action">로그인</a></li> -->
				<li><a href="../member/UserRegForm.action">회원가입</a></li>
				<li><a href="../member/UserFinderForm.action">ID/비번찾기</a></li>
			</ul>
		</div>
	</div>
</div>
</s:else>