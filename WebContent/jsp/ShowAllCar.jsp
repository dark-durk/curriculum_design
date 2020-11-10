<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,bean.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/client.css"
	type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
</head>
<body>
	<div class="pageContent">
		<div class="showPeople">
			<c:forEach items="${requestScope.carList }" var="car">
			<div class="show1">
				<!-- <form action="/curriculum_design/ManagerHPServlet?type=showcar" method="post"> -->
					<table border="1" cellspacing="0">
					<tr>
						<td class="cartd">车牌号：</td>
						<td class="cartd">拥有者：</td>
						<td class="cartd">购买时间：</td>
						<td class="cartd">品牌：</td>
						<td class="cartd">颜色：</td>
					</tr>
					<tr>
						<td class="cartd">${car.getCar_id()}</td>
						<td class="cartd">${car.getD_id()}</td>
						<td class="cartd">${car.getPurchaseDate()}</td>
						<td class="cartd">${car.getBrand()}</td>
						<td class="cartd">${car.getColor()}</td>
					</tr>
				</table>
				<!-- </form> -->
			</div>
			</c:forEach>
		</div>
</body>
</html>