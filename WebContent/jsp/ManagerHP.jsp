<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出租车信息平台</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/client.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/topAndBottom.css"
	type="text/css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
</head>
<body>
	<div class="head">
		<div class="logo">
			<img src="https://s2.ax1x.com/2019/12/09/QwfjHS.jpg" alt="">
		</div>
		<div class="title">出租车信息平台</div>

	</div>
	<!-- 菜单栏 -->
	<div class="daohang">
		<ul class="menu">
			<li class="menuitem selectedli"><a target="myFrame" href="/curriculum_design/ManagerHPServlet?&type=driver">司机信息</a></li>
			<li class="menuitem"><a target="myFrame" href="/curriculum_design/ManagerHPServlet?&type=showcar">车辆信息</a></li>
			<li class="menuitem"><a target="myFrame" href="/curriculum_design/ManagerHPServlet?&type=showeva"">查看评价</a></li>
			<li class="menuitem"><a target="myFrame" href="/curriculum_design/ManagerHPServlet?&type=compnum"">统计</a></li>
		</ul>
	</div>
	<!-- 中间内容部分发生改变的框架 -->
	<div class="content">
		<iframe class="myFrame" scrolling="auto" frameborder="no"
			src="/curriculum_design/ManagerHPServlet?&type=driver" name="myFrame"> </iframe>

	</div>
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
	</script>
</body>
</html>