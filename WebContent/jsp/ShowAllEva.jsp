<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.util.*,bean.Order"%>
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
<style>
.timetr {
	height: 20px;
	font-size: 10px;
}
</style>
</head>
<body>
	<div class="pageContent">
		<div class="showPeople" style="">
			<table border="0" cellspacing="0" style="margin:20px auto;">
				<c:forEach items="${requestScope.detailList }" var="detail">
					<tr>
						<td>${detail.getOrder().getC_id()}</td>
						<td>评价</td>
						<td>${detail.getOrder().getD_id()}:</td>
						<td>${detail.getEva().getEvaluations()}</td>
					</tr>
					<tr class="timetr">
						<td colspan="4">${detail.getEva().getTime()}</td>
					</tr>
					<tr >
						<td colspan="4" style="margin-bottom:15px;"><input type="button" name="btn2" value="回复"
							onclick="popupReply(this)">${detail.getEva().getReply()}</td>
					<input type="hidden" name="e_id" value="${detail.getEva().getE_id()}">
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>
	<script type="text/javascript">
		function popupReply(obj) {
			var x = $(obj).parent().next();
			var e_id = x.val();
			window.open("/curriculum_design/jsp/Reply.jsp?e_id=" + e_id,
					"回复窗口", "width=350,height=140,left=400,top=200");
		}
		
	</script>

</body>
</html>