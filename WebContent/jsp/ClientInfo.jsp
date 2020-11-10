<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/client.css"
	type="text/css">
<style>
.tr{
height:50px;text-align:right}
</style>
</head>
<body>
	<% Client client=(Client)request.getAttribute("client");%>
	<div class="pageContent">
		<div class="showPeople" style="">
				<table border="0" cellspacing="0">
					<tr class="tr">
						<td>邮箱：</td>
						<td><%=session.getAttribute("theId") %></td>
					</tr>
					<tr >
						<td class="tr">密码：</td>
						<td> ${client.getPassword()} </td>
					</tr>
					<tr >
						<td class="tr">联系电话：</td>
						<td>${client.getPhonenum()}</td>
					</tr>
					<tr >
						<td class="tr">年龄：</td>
						<td>${client.getAge()}</td>
					</tr>
					<tr >
						<td class="tr">性别：</td>
						<td>${client.getSex()}</td>
					</tr>
				</table>
				<a href="<%=request.getContextPath() %>/jsp/ModifyClientInfo.jsp?theId=<%=session.getAttribute("theId") %>"> <input type="submit" name="changeInfo" value="修改信息"></a>
		</div>
	</div>
</body>
</html>