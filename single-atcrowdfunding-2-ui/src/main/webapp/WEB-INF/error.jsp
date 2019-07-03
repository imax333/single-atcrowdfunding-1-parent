<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<title>凝聚点滴的力量，成就非凡的伟业</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<div>
				<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
			</div>
		</div>
	</div>
	</nav>

	<div class="container">
		<h2 class="form-signin-heading">
			<i class="glyphicon glyphicon-log-in"></i> 系统消息
		</h2>

		${requestScope.EXCEPTION.message }<br />
		<script type="text/javascript">
			$(function() {
				$("button").click(function() {
					//相当于浏览器的后退按钮
					window.history.back();
				});
			});
		</script>
		<button>返回之前操作</button>
	</div>
</body>
</html>