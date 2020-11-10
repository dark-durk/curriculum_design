<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,dao.*,bean.*"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/client.css">
<script type="text/javascript" src="/curriculum_design/js/jquery-3.3.1.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr th {
	width: 70px;
	font-size: 15px;
}

table {
	margin: 20 auto;
	margin-top: 100px;
}
</style>
</head>
<body>
	<%
		String d_id = String.valueOf(session.getAttribute("d_id"));
		DriverDao dao = new DriverDao();
		Driver d = dao.getDriver(d_id);
		request.setAttribute("d", d);
	%>
	<div class="pageContent">
		<div class="pageName">
			<span class="srec"></span> 填写报修信息
		</div>
		<form method="post"
			action="/curriculum_design/DriverHPServlet?type=maint&d_id=<%=session.getAttribute("d_id")%>"
			onsubmit="return validate();">
			<table border="1" cellspacing="0">
				<tr>
					<td>车牌号</td>
					<td><input type="text" name="car_id"
						value="${requestScope.d.getCar_id()}" readonly></td>
				</tr>
				<tr>
					<td>故障原因</td>
					<td><input type="text" name="cause"></td>
				</tr>
				<tr>
					<td>时间</td>
					<td><input type="text" name="time"></td>
				</tr>
			</table>
			<input type="submit" name="btn" value="报修"
				style="width: 50px; margin-left: 500px">
		</form>
	</div>
	<script>
		function validate() {
			var time = $("input[name='time']").val();
			var dataPattern = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
			if ($("input[name='cause']").val() == "") {
				alert("请完整报修原因");
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