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
		<form action="/curriculum_design/TextServlet?type=evaluation&o_id=<%=request.getParameter("o_id")%>" method="post">
			<textarea class="txt" maxLength="255" name="evaluations"
				placeholder="请输入文字"></textarea>
			<br> <input type="hidden" name="time"
				value="<fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd" />" />
			<br> <input type="submit" name="btn" value="评价" >
		</form>
	</div>
	<!-- <script type="text/javascript">
		/* $("input[name='btn']").click(function(){
			window.parent.close();
		}); */
	</script> -->
</body>
</html>