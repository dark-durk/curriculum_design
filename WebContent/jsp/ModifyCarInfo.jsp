<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/client.css"
	type="text/css">
<%@page import="java.util.*,dao.*,bean.*"%>
<style type="text/css">
table tr th, td, input {
	width: 100px;
	height: 30px;
}
</style>
</head>
<body>
	<%
		String d_id = String.valueOf(session.getAttribute("d_id"));
		CarDao dao = new CarDao();
		Car c = dao.getCarInfo(d_id);
		request.setAttribute("car", c);
	%>
	<div class="pageContent">
		<div class="showPeople" style="">
			<form
				action="/curriculum_design/DriverHPServlet?type=carinfo&d_id=<%=session.getAttribute("d_id")%>"
				method="post">
				<table border="0" cellspacing="0">
					<tr>
						<th>购买日期</th>
						<th>品牌</th>
						<th>颜色</th>
					</tr>
					<tr>
						<td><input type="text" name="purchaseDate"
							value="${requestScope.car.getPurchaseDate()}"></td>
						<td><input type="text" name="brand"
							value="${requestScope.car.getBrand()}"></td>
						<td><input type="text" name="color"
							value="${requestScope.car.getColor()}"></td>
					</tr>
				</table>
				<br> <br> <input type="submit" name="btn" value="确认修改">
			</form>
		</div>
	</div>
</body>
</html>