<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/curriculum_design/js/jquery-3.3.1.js"></script>
</head>
<body>
	<div style="text-align: center;">
			<form action="/curriculum_design/PicUploadServlet?d_id=<%=session.getAttribute("d_id") %>" method="post" enctype="multipart/form-data">
				<p><img id="img" src="/curriculum_design/img/headshot1.jpg" alt="" width="150" height="150"></p>
				<p><span>上传照片</span></p>
				<p><input id="file" type="file" name="headshot" accept="image/*"  /></p>
				<input type="submit" name="save" value="保存" />
			</form>
		</div>
		<script type="text/javascript">
			$("#file").change(function(){  
					 var objUrl = getObjectURL(this.files[0]) ;//获取文件信息  
					 console.log("objUrl = "+objUrl);  
					  if (objUrl) {  
					  $("#img").attr("src", objUrl);  
					 }   
			}) ;  
			function getObjectURL(file) {  
					 var url = null;   
					 if (window.createObjectURL!=undefined) {  
					  url = window.createObjectURL(file) ;  
					 } else if (window.URL!=undefined) { // mozilla(firefox)  
					  url = window.URL.createObjectURL(file) ;  
					 } else if (window.webkitURL!=undefined) { // webkit or chrome  
					  url = window.webkitURL.createObjectURL(file) ;  
					 }  
					 return url ;  
					}  
			
		</script>
</body>
</html>