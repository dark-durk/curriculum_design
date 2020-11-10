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
		<form
			action="/curriculum_design/TextServlet?type=reply&e_id=<%=request.getParameter("e_id")%>"
			method="post">
			<textarea class="txt" maxLength="255" name="reply"
				placeholder="请输入文字"></textarea>
			<br> <input type="submit" name="btn" value="回复">
		</form>
	</div>
s</body>
</html>