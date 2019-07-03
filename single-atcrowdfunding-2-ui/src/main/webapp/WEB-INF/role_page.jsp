<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<link rel="stylesheet" href="ztree/zTreeStyle.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="script/role_page.js"></script>
<script type="text/javascript">
$(function(){
	
	// 初始化分页数据
	window.pageNo = 1;
	window.keyword = "";
	
	// 调用初始化函数
	initPage();
	// 查询
	$("#selectBtn").click(function(){
		window.keyword=$.trim($("input[name=keyword]").val());
		initPage();
	});
	// 全选全不选
	$("#summaryCheckBox").click(function(){
		$(".itemCheckbox").prop("checked",this.checked);
	});
	$("#rolePageListLocation").on("click",".itemCheckbox",function(){
		var count = $(".itemCheckbox:checked").length;
		if(count == 5){
			$("#summaryCheckBox").prop("checked",true);
		}else{
			$("#summaryCheckBox").prop("checked",false);
		}
		
	});
	
	
	// 批量删除 填充模态框
	$("#batchRemove").click(function(){
			window.roleIdList = new Array();
		 $(".itemCheckbox:checked").each(function(){
			 var roleId = $(this).attr("roleId");
			 window.roleIdList.push(roleId);
		 });
		if(window.roleIdList.length == 0){
			layer.msg("请至少选择一条用户信息");
			return ;
		}
		// 获取通过ID查找到的用户信息  封装为函数了
		var roleList = getRoleModalList();
		// 显示模态框
		$("#roleConfirmModal").modal("show");
		// 填充模态框  封装为函数了
		fillRoleModal(roleList);
	});
	// 批量删除
	$("#roleConfirmBtn").click(function(){
		batchRemove();
	});
	
	// 单条删除
	$("#rolePageListLocation").on("click",".removeBtn",function(){
		var roleId = $(this).attr("roleId");
		// 给全局数组变量重复赋值，避免互相影响
		window.roleIdList = new Array();
		window.roleIdList.push(roleId);
		// 获取通过ID查找到的用户信息  封装为函数了
		var roleList = getRoleModalList();
		// 显示模态框
		$("#roleConfirmModal").modal("show");
		// 填充模态框  封装为函数了
		fillRoleModal(roleList);
	});
	
	
	// 添加 role 函数
	$("#saveRoleBtn").click(function(){
		// 显示添加模态框
		$("#saveModalBtn").modal("show");
	});
	// 添加role模态框中的  保存按钮函数
	$("#saveBtn").click(function(){
		var roleName = $("#saveModalBtn [name=roleName]").val();
		$.ajax({
			"url":"role/to/save.json",
			"data":{"roleName":roleName,"random":Math.random()},
			"type":"post",
			"dataType":"json",
			"success":function(response){
				if(response.result == "SUCCESS"){
					layer.msg("添加成功");
					// 添加成功初始化页面
					// 把页面值设置为最大值  直接跳转到最后一页
					window.pageNo=9999999;
					initPage();
				}
				if(response.result == "FAILED"){
					layer.msg(response.message);
				}
			},
			"error":function(response){
				layer.msg(response.message);
			}
		});
		$("#saveModalBtn").modal("hide");
		// 系统去点击模态框的关闭按钮  清空之前添加的数据
		$("#closeBtn").click();
	});
	
	
	// 修改前显示模态框信息  函数 
	$("#rolePageListLocation").on("click",".pencilBtn",function(){
		// 显示修改的模态框
		$("#editModalBtn").modal("show");
		// 获取roleId  roleName  去填充模态框
		window.roleId = $(this).attr("roleId");
		var roleName = $(this).parents("tr").children(":eq(2)").text();
		
		// 将roleName设置到模态框中文本框的value属性即可
		$("#editModalBtn [name=roleName]").val(roleName);
		
	});
	
	 // 修改函数  给模态框的确认按钮绑定单击函数
	 $("#updateBtn").click(function(){
		 // 获取 前边方法设置的  value属性 
		var roleName= $("#editModalBtn [name=roleName]").val();
		 $.ajax({
			 "url":"role/to/update.json",
			 "data":{"id":window.roleId,"name":roleName,"random":Math.random()}, // random   避免浏览器使用缓存
			 "type":"post",
			 "dataType":"json",
			 "success":function(response){
				 if(response.result == "SUCCESS"){
					layer.msg("添加成功");
					// 初始化界面
					initPage();
				}
				if(response.result == "FAILED"){
					layer.msg(response.message);
				}
			 },
			 "error":function(response){
				 layer.msg(response.message);
			 }
		 });
		 // 关闭模态框
		 $("#editModalBtn").modal("hide");
	 });
	 // 显示模态框  authBtn
	 $("#rolePageListLocation").on("click",".authBtn",function(){
		 window.roleId = $(this).attr("roleId");
		 $("#roleAssignAuthModal").modal("show");
		// 1.创建setting对象
		 var setting = {
		 	"data": {
		 		"simpleData": {
		 			"enable": true,
		 			"pIdKey": "categoryId"
		 		}, 
		 		"key": {
		 			"name": "title"
		 		}
		 	},
		 	"check": {
		 		"enable": true
		 	}
		 };

		
		 
		 var ajaxResult = $.ajax({
			 "url":"get/all/auth.json",
			 "type":"post",
			 "dataType":"json",
			 "async":false
		 });
		 
		 if(ajaxResult.responseJSON.result == "FAILED"){
			 layer.msg(ajaxResult.responseJSON.message);
			 return ;
		 }
		 var zNodes = ajaxResult.responseJSON.data;
		 // 初始化zTree 
		 $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		 
		 // true 表示 展开 全部节点
		 $.fn.zTree.getZTreeObj("treeDemo").expandAll(true);
		 
		 // 回显信息
		ajaxResult= $.ajax({
			 "url":"query/assign/auth.json",
			 "data":{"roleId": window.roleId ,"random":Math.random()},
			 "type":"post",
			 "dataType":"json",
			 "async":false
		 });
		 if(ajaxResult.responseJSON.result == "FAILED"){
			 layer.msg(ajaxResult.responseJSON.message);
			 return ;
		 }
		 var authIdList = ajaxResult.responseJSON.data;
		 
		 for(var i = 0; i<authIdList.length;i++){
			 var authId = authIdList[i];
			 var key = "id";
			 var treeNode = $.fn.zTree.getZTreeObj("treeDemo").getNodeByParam(key,authId);
			 
			 $.fn.zTree.getZTreeObj("treeDemo").checkNode(treeNode, true, false);
		 }
		 
	 });
	 
	 $("#roleAssignAuthBtn").click(function(){
		 var authArray = new Array();
		 
		var nodes =  $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes();
		 
		for(var i=0;i<nodes.length;i++){
			var auth = nodes[i];
			var authId = auth.id;
			authArray.push(authId);
		}
		 
		var requestBody = {"roliIdList":[window.roleId],"authIdList":authArray};
		
		$.ajax({
			"url":"save/auth.json",
			"data":JSON.stringify(requestBody),
			"type":"post",
			"contentType":"application/json;charset=UTF-8",
			"dataType":"json",
			"success":function(response){
				if(response.result == "SUCCESS"){
					layer.msg("操作成功");
					
				}
				if(response.result == "FAILED"){
					layer.msg(response.message);
				}
			},
			"error":function(response){
				layer.msg(response.message);
			}
			
		});
			$("#roleAssignAuthModal").modal("hide");
		
		
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
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input  name="keyword" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button id="selectBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemove" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" 
								id="saveRoleBtn"
								class="btn btn-primary"
								style="float: right;" >
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="summaryCheckBox" type="checkbox"></th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody id="rolePageListLocation">
									<!-- js引入的代码  -->
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination">
												<!-- 这里显示分页导航条 -->
											</div>
										</td>
									</tr>

								</tfoot>
							</table>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
<%@include file="/WEB-INF/modal/role_confirm.jsp" %>
<%@include file="/WEB-INF/modal/role_add.jsp" %>
<%@include file="/WEB-INF/modal/role_edit.jsp" %>
<%@include file="/WEB-INF/modal/role_assign_auth.jsp" %>
</body>
</html>