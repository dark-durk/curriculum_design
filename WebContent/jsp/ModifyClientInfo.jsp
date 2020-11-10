<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.tr{
height:50px;text-align:right}
</style>
</head>
<body>
<div class="pageContent">
		<div class="showPeople" style="">
			<form action="/curriculum_design/ClientHPServlet?type=changeClientInfo&theId=<%=request.getParameter("theId") %>" method="post" onsubmit="return validate();">
				<table border="0" cellspacing="0">
					<tr class="tr">
						<td>邮箱：</td>
						<td><%=request.getParameter("theId") %></td>
					</tr>
					<tr class="tr">
						<td>新密码：</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr class="tr">
						<td>联系电话：</td>
						<td><input type="text" name="phonenum"></td>
					</tr>
					<tr class="tr">
						<td>年龄：</td>
						<td><input type="text" name="age"></td>
					</tr>
					<tr >
						<td class="tr">性别：</td>
						<td><input type="radio" name="sex" value="男" checked>男<input type="radio" name="sex" value="女" >女</td>
					</tr>
				</table>
				<input type="submit" name="changeInfo" value="确认修改" >
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function validate(){
			if($("input").val()==""){
				alert("请完整填写信息");
				return false
			}
			return true;
		}
	</script>
</body>
</html>