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
<style>
.td {
	width: 100px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="pageContent">
		<div class="showPeople">
			<div class="show1">
				<table border="1" cellspacing="0">
					<tr>
						<th class="td">序号</th>
						<th class="td">司机</th>
						<th class="td">被投诉次数</th>
					</tr>
					<c:forEach items="${compMap }" var="count" varStatus="vs">
						<tr>
							<td class="td">${vs.index+1}</td>
							<td class="td">${count.key}</td>
							<td class="td">${count.value}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>