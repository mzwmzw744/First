<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@page import="com.hm.article.entity.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.hm.article.dao.ArticleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	font-size: 14px;
}

div {
	margin-bottom: 15px;
}
</style>
</head>
<%
	ArticleDao articleDao = new ArticleDao();
	List<Role> roles = articleDao.queryAllRoles(null);
%>
<body>
	<div style="padding: 30px">
		<div
			style="border: 1px solid #ccc; padding: 10px; border-radius: 10px; height: 300px; box-shadow: 2px 2px #ddd">
			<div style="padding-left: 30px;">
				<form action="" method="post">
					搜索：<input type="text" name="" value="" /> <input type="submit"
						name="btnSearch" value="开始搜索">
				</form>
			</div>

			<div style="padding-left: 30px;">
				<button name="btnAdd">添加</button>
			</div>

			<div style="padding-left: 30px;">
				<table width="80%" style="border: 1px solid #ddd;" cellspacing="0"
					cellpadding="0">
					<tbody>
						<tr style="height: 30px;">
							<th>编号</th>
							<th>人物名称</th>
							<th>关联人物</th>
							<th>编辑</th>
							<th>删除</th>
						</tr>

						<%
							for (int i = 0; i < roles.size(); i++) {
								Role role = roles.get(i);
						%>

						<tr style="height: 30px; text-align: center">
							<td><%=role.getRoleId()%></td>
							<td><%=role.getRoleName()%></td>
							<td><%=role.getRoles()%></td>
							<td>编辑</td>
							<td>删除</td>
						</tr>
						<%
							}
						%>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>