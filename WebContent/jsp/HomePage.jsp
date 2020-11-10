<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="UTF-8">
<title>出租车信息平台</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/topAndBottom.css"
	type="text/css">
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/client.css"
	type="text/css">
</head>
<style>
.a {
	display: none;
}

.b {
	display: block;
}
</style>
<body>
	<!-- <div class="pageName">
		<span class="font1"></span>明日之星
	</div> -->
	<div class="pageContent">
		<div class="showPeople">

			<c:if test="${not empty requestScope.driver.getCar_id()}">
				<div class="show1" id="hp2">
					<%-- <c:forEach items="${requestScope.driverList }" var="driver"> --%>
						<table border="1" cellspacing="0">
							<tr>
								<td rowspan="3" class="tableImage"><img
									src="/curriculum_design/img/${driver.getHeadshot()}" alt="加载失败"
									width="145px" height="145px"></td>
								<td>姓名：</td>
								<td width="100px">${driver.getName()}</td>
								<td>性别：</td>
								<td width="100px">${driver.getSex()}</td>
							</tr>
							<tr>
								<td>年龄：</td>
								<td>${driver.getAge()}</td>
								<td>联系电话：</td>
								<td>${driver.getPhonenum()}</td>
							</tr>
							<tr>
								<td>车牌照：</td>
								<td>${driver.getCar_id()}</td>
								<td>驾龄：</td>
								<td>${driver.getCar_age()}</td>
							</tr>
							<tr>
								<td colspan="5">相关评价：</td>
							</tr>
							<c:forEach items="${requestScope.evaList}" var="item">
								<tr>
									<td colspan="5">${item }</td>
								</tr>
							</c:forEach>
						</table>
					<%-- </c:forEach> --%>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>