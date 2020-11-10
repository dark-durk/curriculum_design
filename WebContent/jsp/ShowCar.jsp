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
<style>
table tr th {
	width: 70px;
	font-size: 15px
}
</head>
<body>
	<div class="pageContent">
		<div class="showPeople" style="">
			<table border="1" cellspacing="0">
				<tr>
					<th>序号</th>
					<th>车牌号</th>
					<th>拥有者</th>
					<th>购买日期</th>
					<th>品牌</th>
					<th>颜色</th>
				</tr>
				<c:forEach items="${requestScope.list }" var="car" varStatus="vs">
					<tr>
						<td>${vs.index+1 }</td>
						<td>${car.getCar_id()}</td>
						<td>${car.getD_id()}</td>
						<td>${car.getPurchaseDate()}</td>
						<td>${car.getBrand()}</td>
						<td>${car.getColor()}</td>
						<td><input type="button" name="btn1" value="修改"
							onclick="popupModify(this)"></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>
	<script type="text/javascript">
		 function popupModify(obj){
			var x=$(obj).parent().parent().find("td");
			var o_id=x.eq(1).text();
			window.open("/curriculum_design/jsp/evaluation.jsp?o_id="+o_id,"评价窗口","width=350,height=140,left=400,top=200");
		} 
		 
		/* $("input[name='btn1']").click(function(){
			$(this).val("已评价");
		}); */
	</script >
</body>
</html>