<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="utf-8">
<title>出租车信息平台</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/client.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/topAndBottom.css"
	type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
</head>
<body>
	<div class="head">
		<div class="logo">
			<img src="https://s2.ax1x.com/2019/12/09/QwfjHS.jpg" alt="">
		</div>
		<div class="title">出租车信息平台</div>
		<!-- 查询 -->
		<div class="search">
			<form action="/curriculum_design/QueryServlet" method="post" onsubmit="return validate();">
				<input type="text" name="id" placeholder="输入司机工号"> 
				<input type="submit" name="search" value="查询">
			</form>
		</div>
	</div>
	<!-- 菜单栏 -->
	<div class="daohang">
		<ul class="menu">
			<!-- <li class="menuitem selectedli"><a target="myFrame"
				href="HomePage.jsp">首页</a></li> -->
			<li class="menuitem selectedli"><a target="myFrame" href="/curriculum_design/ClientHPServlet?type=yueche&theId=<%=session.getAttribute("theId") %>">约车</a></li>
			<li class="menuitem"><a target="myFrame" href="/curriculum_design/ClientHPServlet?type=order&theId=<%=session.getAttribute("theId")%>"">订单</a></li>
			<li class="menuitem"><a target="myFrame" href="/curriculum_design/ClientHPServlet?type=clientInfo&theId=<%=session.getAttribute("theId")%>">个人信息管理</a></li>
		</ul>
	</div>
	<!-- 中间内容部分发生改变的框架 -->
	<div class="content">
		<iframe class="myFrame" scrolling="auto" frameborder="no"
			src="/curriculum_design/ClientHPServlet?type=yueche&theId=<%=session.getAttribute("theId") %>" name="myFrame"> </iframe>
	</div>
	<!-- 尾部 -->
	<div class="bottom">
		Copyright © 2019 出租车协会 版权所有&nbsp;&nbsp;邮编：6100400&nbsp;&nbsp; 技术支持：<a
			href="#" target="_blank">憨憨信息技术有限公司</a>
		<p>
			<a href="http://www.miitbeian.gov.cn" rel="nofollow" target="_blank">蜀ICP备05000152号-1</a><br>
		</p>
	</div>

	<script type="text/javascript">
		$(".menuitem").click(function() {
			$(this).addClass("selectedli");
			$(this).siblings().removeClass("selectedli");
		});
		function validate(){
			if($("input[name='id']").val()==""){
				alert("请输入司机邮箱");				
				return false;
			}
			return true;
		}
		
	</script>
</body>
</html>