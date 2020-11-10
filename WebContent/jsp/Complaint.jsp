<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
.txt {
	width: 300px;
	height: 100px;
	/* border:none; */
	resize: none;
}
</style>
</head>
<body>
	<div class="popup">
		<form action="/curriculum_design/TextServlet?type=complaint&d_id=<%=request.getParameter("d_id")%>&c_id=<%=request.getParameter("c_id")%>" method="post">
			<textarea class="txt" maxLength="255" name="complaints"
				placeholder="请输入文字"></textarea>
			<br> 
			<br> <input type="submit" name="btn" value="投诉" >
		</form>
	</div>
	
</body>
</html>