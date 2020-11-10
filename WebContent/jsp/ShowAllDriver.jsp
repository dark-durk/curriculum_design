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
			<c:forEach items="${requestScope.driverList }" var="driver">
			<div class="show1">
				<form action="/curriculum_design/ManagerHPServlet?type=deleteDriver" method="post" onsubmit="return validate();">
					<table border="0" cellspacing="0">
					<tr>
						<td rowspan="2" class="tableImage">
							<img src="/curriculum_design/img/${driver.getHeadshot()}" alt="failure" width="69px" height="50px">
						</td>
						<td class="drivertd">账号：</td>
						<td width="100px">${driver.getD_id()}</td>
						<td class="drivertd">密码：</td>
						<td width="100px">${driver.getPassword()}</td>
						<td class="drivertd">姓名：</td>
						<td>${driver.getName()}</td>
						<td class="drivertd">性别：</td>
						<td>${driver.getSex()}</td>
					</tr>
					<tr>
						<td class="drivertd">年龄：</td>
						<td>${driver.getAge()}</td>
						<td class="drivertd">车牌号：</td>
						<td>${driver.getCar_id()}</td>
						<td class="drivertd">联系电话：</td>
						<td colspan="2">${driver.getPhonenum()}</td>
						<td><input type="submit" name="delete" value="删除"  onclick="del(this)"></td>
						<input type="hidden" name="id" value="">
					</tr>
				</table>
				</form>
			</div>
			</c:forEach>
		</div>
		<script type="text/javascript">
			function validate(){
					if(confirm("确认删除该司机吗？")){
						return true;
					}
					else 
						return false;
			}
			function del(obj){
					var x=$(obj).parent().parent().parent().children();
					var y=x.eq(0).children();
					var d_id=y.eq(2).text();
					$("input[name=id]").val(d_id);
			}
			
		</script>
</body>
</html>