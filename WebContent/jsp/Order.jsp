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
table tr th {
	width: 70px;
	font-size: 15px
}
</style>
</head>
<body>
	<div class="pageContent">
		<div class="showPeople" style="">
			<table border="1" cellspacing="0">
				<tr>
					<th>序号</th>
					<th>订单号</th>
					<th>司机</th>
					<th>起点</th>
					<th>终点</th>
					<th>乘车时间</th>
					<th>评价</th>
					<th>投诉</th>
				</tr>
				<c:forEach items="${requestScope.list }" var="order" varStatus="vs">
					<tr>
						<td>${vs.index+1 }</td>
						<td>${order.getO_id()}</td>
						<td>${order.getD_id()}</td>
						<input type="hidden" name="c_id" value="${requestScope.c_id}">
						<td>${order.getStart()}</td>
						<td>${order.getEnd()}</td>
						<td>${order.getTime()}</td>
						<td><input type="button" name="btn1" value="评价"
							onclick="popupEvaluation(this)"></td>
						<td><input type="button" name="btn2" value="投诉" onclick="popupComplaint(this)"></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>
	<script type="text/javascript">
		 function popupEvaluation(obj){
			var x=$(obj).parent().parent().find("td");
			var o_id=x.eq(1).text();
			window.open("/curriculum_design/jsp/evaluation.jsp?o_id="+o_id,"评价窗口","width=350,height=140,left=400,top=200");
		} 
		 function popupComplaint(obj){
			var x=$(obj).parent().parent().find("td");
			var d_id=x.eq(2).text();
			var c_id=$(obj).parent().parent().find("input[type='hidden']").val().toString();
			window.open("/curriculum_design/jsp/Complaint.jsp?d_id="+d_id+"&c_id="+c_id,"投诉窗口","width=350,height=140,left=400,top=200");
		} 
		$("input[name='btn1']").click(function(){
			$(this).val("已评价");
		});
		$("input[name='btn2']").click(function(){
			$(this).val("已投诉");
		}); 
	</script >
	
</body>
</html>