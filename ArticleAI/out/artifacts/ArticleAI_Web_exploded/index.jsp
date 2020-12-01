<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>AI-写作机器人</title>
</head>
<!-- 交互 -->
<body>
	<div>
		<h1 align="center">酷酷AI写作助手</h1>
	</div>
	<h3>请输入关键字</h3>
	<form action="article">
		关键字:<input type="text" value="" name="articleKey" /> <br /> <input
			type="submit" name="btnStart" value="开始写作">
	</form>
</body>
</html>