<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="UTF-8">
<title>出租车信息平台</title>
<script type="text/javascript" src="jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/topAndBottom.css"
	type="text/css">
<link rel="stylesheet"
	href="http://localhost:9527/curriculum_design/css/client.css"
	type="text/css">
<style type="text/css">
.fontindex{
margin:0px 20px;line-height:40px;font-size:20px
}
.a{
color: inherit;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="head">
		<div class="logo">
			<img src="https://s2.ax1x.com/2019/12/09/QwfjHS.jpg" alt="">
		</div>
		<div class="title">出租车信息平台</div>
		<div class="search">
			<form action="">
				<input type="text" id="search_text" name="search_text"
					placeholder="输入司机工号"> <input type="submit" id="search"
					name="search" value="查询">
			</form>
		</div>
	</div>
	<div class="daohang">
		<a class="a" href="<%=request.getContextPath() %>/html/Login.html"><span class="fontindex">登陆</span></a>
		<a class="a" href="Register.jsp"><span class="fontindex">注册</span></a>
	</div>
	<div class="pageName">
		<span class="font1"></span>明日之星
	</div>
	<div class="pageContent">
		<div class="showPeople">
			<%
				for (int i = 0; i < 3; i++) {
			%>
			<div class="show1">
				<table border="1" cellspacing="0">
					<tr>
						<td rowspan="4" class="tableImage">头像</td>
						<td>姓名：</td>
						<td width="100px">。。。。</td>
						<td>性别：</td>
						<td width="100px">。。。。</td>
					</tr>
					<tr>
						<td>年龄：</td>
						<td></td>
						<td>联系电话：</td>
						<td></td>
					</tr>
					<tr>
						<td>车牌照：</td>
						<td></td>
						<td>驾龄：</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="4">评价：</td>
					</tr>
				</table>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<div class="bottom">
		Copyright © 2019 出租车协会 版权所有&nbsp;&nbsp;邮编：6100400&nbsp;&nbsp; 技术支持：<a
			href="#" target="_blank">憨憨信息技术有限公司</a>
		<p>
			<a href="http://www.miitbeian.gov.cn" rel="nofollow" target="_blank">蜀ICP备05000152号-1</a><br>
		</p>
	</div>
	
</body>
</html>