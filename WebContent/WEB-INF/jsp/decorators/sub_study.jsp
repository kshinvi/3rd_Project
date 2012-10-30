<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<page:applyDecorator name="head"/>
	<decorator:head/>
</head>
<body>
<div class="container">
	<div><page:applyDecorator name="top"/></div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<h3 class="text-success">자습실</h3>
				<ul class="nav nav-pills nav-stacked">
					<li><a href="../study/quest_list.action">문제지만들기</a></li>
					<li><a href="../study/studyWordbook.action">내 단어장</a></li>
				</ul>
			</div>
			<div class="span10"><decorator:body/></div>
		</div>
	</div>
	<div><page:applyDecorator name="bottom"/></div>
</div>
</body>
</html>
