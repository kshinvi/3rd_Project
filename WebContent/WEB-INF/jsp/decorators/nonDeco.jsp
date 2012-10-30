<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<head>
	<page:applyDecorator name="head"/>
	<decorator:head/>
</head>
<body>
<div class="container">
	<decorator:body />
</div>
</body>