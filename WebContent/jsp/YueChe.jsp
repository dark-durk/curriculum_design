<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/client.css"
	type="text/css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<style>
.tr {
	height: 50px;
	text-align: right
}
</style>
</head>
<body>
	<%
		Client client = (Client) request.getAttribute("client");
	%>
	<div class="pageContent">
		<div class="showPeople" style="">
			<form
				action="/curriculum_design/ClientHPServlet?type=createOrder&theId=<%=request.getParameter("theId")%>"
				method="post" onsubmit="return validate();">
				<table border="0" cellspacing="0">
					<tr >
						<td class="tr">用户账号：</td>
						<td><%=request.getParameter("theId")%></td>
					</tr>
					<tr class="tr">
						<td>司机账号：</td>
						<td><input type="text" name="d_id"></td>
					</tr>
					<tr class="tr">
						<td>起点：</td>
						<td><input type="text" name="start"></td>
					</tr>
					<tr class="tr">
						<td >终点：</td>
						<td><input type="text" name="end"></td>
					</tr>
					<tr class="tr">
						<td >时间：</td>
						<td><input type="text" name="time"></td>
					</tr>
				</table>
				<input type="submit" name="changeInfo" value="生成订单">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function validate() {
			var time = $("input").eq(3).val();
			var dataPattern = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
			if ($("input:text").val() == "") {
				alert("请完整填写信息");
				return false;
			}
			if (!dataPattern.test(time)) {
				alert("日期格式错误")
				return false;
			}
			return true;
		}
	</script>
</body>
</html>