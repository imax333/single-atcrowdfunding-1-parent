<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
<script type="text/javascript">
$(function(){
	$("#addBtn").click(function(){
		// 校验账号是否存在 
		var loginacct=$.trim($("input[name=loginacct]").val());
		if(loginacct == null || loginacct == ""){
			alert("请输入账号信息");
			return false;
		}
		var ajaxresult = $.ajax({
			"url":"admin/check/loginacct.json",
			"data":{"loginacct":loginacct,"random":Math.random()},
			"type":"post",
			"dataType":"json",
			"async":false
		});
		// 通过访问$.ajax()函数的返回值的responseJSON属性获取服务器返回的JSON数据
		var ajaxMessage = ajaxresult.responseJSON;
		if(ajaxMessage.result == "FAILED"){
			//var message = ajaxresult.responseJSON.message;
			alert(ajaxMessage.message);
			return false;
		};
		var userpswd=$.trim($("input[name=userpswd]").val());
		var checkUserpswd=$.trim($("input[name=checkUserpswd]").val());
		if(userpswd != checkUserpswd){
			
			alert("密码和确认密码不一致");
			return false;
		}
		return true;
		
	});
	
});

</script>


</head>
<body>
	<%@ include file="/WEB-INF/common/navigator.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/WEB-INF/common/sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="index.html">首页</a></li>
					<li><a href="query/with/search.html">数据列表</a></li>
					<li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form action="admin/to/add.html" role="form" method="post">
							<div class="form-group">
								<label for="exampleInputPassword1">登陆账号</label> 
								<input
									type="text"
									name="loginacct" 
									class="form-control" 
									id="exampleInputPassword1"
									placeholder="请输入登陆账号" >
							</div>
							<div class="form-group">
								<label for="exampleInputPassword2">登陆密码</label> 
								<input
									type="password"
									name="userpswd" 
									class="form-control" 
									id="exampleInputPassword2"
									placeholder="请输入登陆密码" >
							</div>
							<div class="form-group">
								<label for="exampleInputPassword3">确认密码</label> 
								<input
									type="password"
									name="checkUserpswd"  
									class="form-control" 
									id="exampleInputPassword3"
									placeholder="请确认登陆密码" >
							</div>
							<div class="form-group">
								<label for="exampleInputPassword4">用户名称</label> 
								<input
									type="text"
									name="username" 
									class="form-control" 
									id="exampleInputPassword4"
									placeholder="请输入用户名称">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail5">邮箱地址</label> 
								<input 
									type="email"
									name="email"
									class="form-control" 
									id="exampleInputEmail5"
									placeholder="请输入邮箱地址">
								<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
									xxxx@xxxx.com</p>
							</div>
							<button id="addBtn" type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i> 新增
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>