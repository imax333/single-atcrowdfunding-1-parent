<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/common/navigator.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/WEB-INF/common/sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!-- 各个页面具体内容 -->
				<ol class="breadcrumb">
					<li><a href="index.html">首页</a></li>
					<li><a href="query/with/search.html">数据列表</a></li>
					<li class="active">修改</li>
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
						<form:form action="admin/update.html" method="post"  modelAttribute="admin">
							<form:hidden path="id"/>
							<input type="hidden" name="pageNo" value="${param.pageNo }"  />
							<div class="form-group">
								<label for="exampleInputPassword1">登陆账号</label>
								<form:input path="loginacct" id="exampleInputPassword1"
									cssClass="form-control" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword2">登陆密码</label>
								<form:input path="userpswd" cssClass="form-control"
									id="exampleInputPassword2" />
							</div>
							<div class="form-group">
								<label for="exampleInputPassword3">确认密码</label>
								<form:input path="userpswd" cssClass="form-control"
									id="exampleInputPassword3" />
							</div>

							<div class="form-group">
								<label for="exampleInputPassword4">用户名称</label>
								<form:input path="username" cssClass="form-control"
									id="exampleInputPassword4" />
							</div>
							<div class="form-group">
								<label for="exampleInputEmail5">邮箱地址</label>
								<form:input path="email" cssClass="form-control"
									id="exampleInputEmail5" />
								<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
									xxxx@xxxx.com</p>
							</div>
							<button id="addBtn" type="submit" class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i> 修改
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>