<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/topAndBottom.css"
	type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/content.css"
	type="text/css">
</head>
<body>
	<div class="head">
			<div class="logo"><img src="https://s2.ax1x.com/2019/12/09/QwfjHS.jpg" alt=""></div>
			<div class="title">出租车信息平台</div>
			<div class="search">
				<form action="">
					<input type="text" id="search_text" name="search_text" placeholder="输入司机工号">
					<input type="submit" id="search" name="search" value="查询">
				</form>
			</div>
		</div>
		<div class="content">
			<div class="pageName">
				<span class="srec"></span>
				注册
			</div>
			<div class="pageContent">
				<div class="formContent">
					<p class="pageNameFont">使用注册邮箱注册</p>
					<!-- 注册表单开始 -->
					<form action="/curriculum_design/RegisterServlet" method="post" onsubmit="return check();">
						<div id="register1" >
							<p>
								选择身份&nbsp;&nbsp;<input type="radio" name="identity" value="用户" checked onclick="chooseIdentity()">用户
								<input type="radio" name="identity" value="司机" onclick="chooseIdentity()">司机
							</p>
							<br>
							<p>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" name="theId" id="theId"></p><br>
							<p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"></p><br>
							<p>确认密码：<input type="password" name="password2"></p><br>
							<!-- <p>验 &nbsp;证 &nbsp;码&nbsp;&nbsp;<input type="text" style="width: 80px;"></p><br> -->
							<p><input class="button_text" type="submit" name="clientRegister" value="注册">
								<input class="button_text" style="margin-left: 50px;" type="reset" name="cancel" value="取消"></p>
							<p><input class="button_text" style="display: none;" type="button" name="next" value="下一步"></p><br>
						</div>
						<!-- 下一步 -->
						<div id="register2" style="display:none;">
							<div style="display: inline-block;">
							<!-- <p>上传头像<input type="file" name="headshot" accept="image/*" onchange="imagePreview()">
 -->							</p><br>
							<p>姓 名：<input type="text" name="name"> </p><br>
							<p>年 龄：<input type="text" name="age"> </p><br>
							<p>电话号码:<input type="text" name="phonenum"> </p><br>
							<p>车牌照：<input type="text" name="car_id"> </p><br>
							<p>驾 龄：<input type="text" name="car_age"> </p><br>
							<p>性 别：<input type="radio" name="sex" value="男" checked>男<input type="radio" name="sex" value="女" >女</p><br>
							<p><input class="button_text" type="submit" name="driverRegister" value="注册"></p><br>
							</div>
							<!-- <img class="headimg" src="" /> -->
						</div>
					</form>
				</div>
			</div>

		</div>
		<div class="bottom">
			Copyright © 2019 出租车协会 版权所有&nbsp;&nbsp;邮编：6100400&nbsp;&nbsp; 技术支持：<a href="#" target="_blank">憨憨信息技术有限公司</a>
			<p>
				<a href="http://www.miitbeian.gov.cn" target="_blank">蜀ICP备05000152号-1</a><br>
			</p>
 		</div>
		<script type="text/javascript">
			function validate() {
				var theId = document.getElementById("theId");
				var password = $("input:password").eq(0);
				var password2 = $("input:password").eq(1);
				if (theId.value == "") {
					alert("邮箱不能为空");
					theId.focus();
					return false;
				}
				if (password.val() == "") {
					alert("密码不能为空");
					password.focus();
					return false;
				}
				if (password2.val() == "" || password.val() != password2.val()) {
					alert("两次密码不一致");
					password.focus();
					return false;
				}
				return true;
			}
			//司机详细信息界面每一步都不能为空
			function checkDriver() {
				if ($("#register2 input").val() == "") {
					alert("请填写完整信息");
					return false;
				}
				return true;
			}

			function check() {
				if ($("input:radio:checked").val() == "用户") {
					return validate();
				} else {
					return validate() && checkDriver();
				}
			}
		</script>
		<script type="text/javascript">
			//获取被选中的单选按钮--选择用户和司机，下方会出现不同的按钮
			function chooseIdentity() {
				if ($("input:radio:checked").val() == "用户") {
					$("input:button").css("display", "none");
					$("input:submit").eq(1).css({
						"display": "block",
						"float":"left",
						"margin-left": "420px"
					});
					$("input:reset").css({
						"display": "block",
						"margin-left": "530px"
					});
				};
				if ($("input:radio:checked").val() == "司机") {
					$("input:button").css({"display":"block","margin-left":"450px"});
					$("input:submit").eq(1).css({"display":"none","margin-left":"420px"});
					$("input:reset").css({"display":"none","margin-left":"530px"});
				};
			}
			//点击下一步时，隐藏之前的表单，表现司机注册的详细信息表单
			$("input:button").click(function() {
				if (validate()) {
					$("#register1").css("display", "none");
					$("#register2").css("display", "block");
				}
			})
			//司机头像图片预览
			function imagePreview(file) {
				let headimg = $(".headimg");
				let formData = new FormData();
				let imgUrl = file.files[0];
				if (imgUrl) {
					formData.append('file', imgUrl);
					imgUrl.src = window.URL.createObjectURL(imgUrl);
				}
			}
		</script>
	<!-- <script type="text/javascript">
	var msg = '${msg}';
	if(msg != ''){
	alert(msg);
	</script> -->
</body>
</html>