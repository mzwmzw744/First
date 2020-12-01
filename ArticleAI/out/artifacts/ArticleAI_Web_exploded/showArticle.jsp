<%@page import="com.hm.article.entity.Article"%>
<%@page import="com.hm.article.entity.StoryType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章内容</title>
</head>
<body>
	<div style="text-align: center">
		<h1>${article.title}</h1>
		<h3>作者：无名</h3>

		<div>${article.storyTimer.storyTimerName }</div>
		<div>${article.mainRole.roleName}和</div>
		<div>${article.roles[0].roleName}。</div>
		<div>在 ${article.storyLocation.storyLocationName}</div>
		<div>${article.storyEvent.eventName},${article.storyEvent.eventDesc}</div>

		<br />

		<div style="color: #aaa">
			<%
				Article article = (Article) request.getAttribute("article");
				List<StoryType> storyTypes = article.getStoryTypes();
				for (int i = 0; i < storyTypes.size(); i++) {
					StoryType storyType = storyTypes.get(i);
			%>
			<span style="border: 1px solid #FF0"> <%=storyType.getStorytypeName()%>
			</span>
			<%
				}
			%>
		</div>
	</div>

</body>
</html>