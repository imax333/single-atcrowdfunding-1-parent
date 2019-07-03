<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css" />
<script type="text/javascript" src="script/menu.js"></script>
<script type="text/javascript">
	$(function() {
		// 初始化zTree页面
		initMenu();
		// 添加操作
		$("#menuAddBtn").click(function(){
			
			var name = $.trim($("#menuAddModal [name='name']").val());
			var url = $.trim($("#menuAddModal [name='url']").val());
			var icon = $.trim($("#menuAddModal [name='icon']:checked").val());
			
			$.ajax({
				"url":"menu/to/add.json",
				"data":{
					"pid":window.currentId,
					"url":url,
					"icon":icon,
					"name":name,
					"random":Math.random()},
				"type":"post",
				"dataType":"json",
				"success":function(response){
					if(response.result == "SUCCESS"){
						layer.msg("添加成功");
						initMenu();
					}
					if(response.result == "FAILED"){
						layer.msg(response.message);
					}
				},
				"error":function(response){
					layer.msg(response.message);
				}
			});
			// 后置操作 关闭模态框  让系统去点击重置按钮 清除上一次操作的数据
			$("#menuAddModal").modal("hide");
			$("#menuAddResetBtn").click();
		});
		// 删除操作
		$("#menuConfirmBtn").click(function(){
			$.ajax({
				"url":"menu/to/remove"+window.currentId+".json",
				"data":{"random":Math.random()},
				"type":"post",
				"dataType":"json",
				"success":function(response){
					if(response.result == "SUCCESS"){
						layer.msg("删除成功");
						initMenu();
					}
					if(response.result == "FAILED"){
						layer.msg(response.message);
					}
				},
				"error":function(response){
					layer.msg(response.message);
				}
			});
			$("#menuConfirmModal").modal("hide");
		});
		// 更新操作
		$("#menuEditBtn").click(function(){
			
		var name = $.trim($("#menuEditModal [name='name']").val());
		var url = $.trim($("#menuEditModal [name='url']").val());
		var icon = $.trim($("#menuEditModal [name='icon']:checked").val());	
		$.ajax({
			"url":"menu/to/update.json",
			"data":{
				"id":window.currentId,
				"name":name,
				"url":url,
				"icon":icon,
				"random":Math.random()
			},
			"type":"post",
			"dataType":"json",
			"success":function(response){
				if(response.result == "SUCCESS"){
					layer.msg("修改成功!");
					initMenu();
				}
				if(response.result == "FAILED"){
					layer.msg(response.message);
				}
			},
			"error":function(response){
				layer.msg(response.message);
			}
		});
		$("#menuEditModal").modal("hide");
		$("#menuEditResetBtn").click();
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
				<!-- 各个页面具体内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>

		</div>
	</div>
<%@include file="/WEB-INF/modal/menu_add.jsp" %>
<%@include file="/WEB-INF/modal/menuConfirm.jsp" %>
<%@include file="/WEB-INF/modal/menu_edit.jsp" %>
</body>
</html>