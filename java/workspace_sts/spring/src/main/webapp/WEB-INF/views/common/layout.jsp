<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="side">
		<tiles:insertAttribute name="side"/>
	</div>
	<div id="body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>