<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<%@include file="/WEB-INF/common/head.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/common/navigator.jsp"%>
	<div class="container-fluid">
		<div class="row">
		<%@include file="/WEB-INF/common/sidebar.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">控制面板</h1>
				<!-- 使用SpringSecurity提供的标签可以详细对页面元素进行权限控制。 -->
				<!-- 要引入security标签库 -->
				<!-- 只有项目经理权限才可以访问此页面 -->
				<security:authorize access="hasRole('PM - 项目经理')">
					
				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/sky" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/vine" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/sky" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img data-src="holder.js/200x200/auto/vine" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
				</div>
				
				</security:authorize>
			</div>
		</div>
	</div>


</body>
</html>
