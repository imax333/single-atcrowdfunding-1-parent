<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="script/admin_function.js"></script>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function() {
		// 初始化分页导航条
		function pageNavigatorInit() {
			
			// 总记录数
			var totalRecord = ${requestScope.PAGE.total};
			
			var pageSize = ${requestScope.PAGE.pageSize};
			
			var currentPage = ${requestScope.PAGE.pageNum - 1};
			
			// 使用JSON对象封装Pagination属性
			var properties = {
				num_edge_entries: 2,			//边缘页数
				num_display_entries: 4,			//主体页数
				callback: pageselectCallback,	//用户在浏览器上点击某一个页码时通过执行这个回调函数跳转到对应页码的页面
				items_per_page:pageSize,		//每页显示项
				current_page: currentPage,		//当前页的页索引，需要通过页码-1得到
				prev_text: "上一页",				//上一页按钮上显示的文本
				next_text: "下一页"			//下一页按钮上显示的文本
			};
			
			// #Pagination定位分页导航条所在位置
			// <div id="Pagination" class="pagination"><!-- 这里显示分页导航条 --></div>
			// 调用$("#Pagination")的pagination()方法，将总记录数和属性设置对象以参数形式传入
			$("#Pagination").pagination(totalRecord, properties);
		}
		
		pageNavigatorInit();
		 
		//回调函数在用户每次点击具体页码的时候执行
		//参数page_index{int整型}是用户点击页码页的索引，从0开始
		function pageselectCallback(page_index, jq){
			
			var keyword="${param.keyword}";
			// 页码需要通过页的索引+1得到
			var pageNo = page_index + 1;
			
			window.location.href = "query/with/search.html?pageNo="+pageNo+"&keyword="+keyword;
			
			return false;
		}
		
		// 全选全不选
			$("#summuryBtn").click(function(){
				//itemCheckbox
				$(".itemCheckbox").prop("checked",this.checked);
			});
			$(".itemCheckbox").click(function(){
				var pageSize = ${requestScope.PAGE.pageSize};
				var count=$(".itemCheckbox:checked").length;
				//itemCheckbox
				if(count == pageSize){
					$("#summuryBtn").prop("checked",this.checked);
				}else{
					$("#summuryBtn").prop("checked",false);
				}
			});
			var adminIdArray = new Array();
		// 批量删除	
		$("#batchBtn").click(function(){
			var count = $(".itemCheckbox:checked").length;
			if(count == 0){
				alert("请勾选要删除的选项");
				return;
			};
			$(".itemCheckbox:checked").each(function(){
				var adminId= this.id;
				adminIdArray.push(adminId);
			});
			var messageConfirm = confirm("您真的要删除"+adminIdArray+"这些用户吗？");
			if(messageConfirm){
				//删除
				resultMessage(adminIdArray,"${param.pageNo}");
			}
		});
		// 单独删除
		$(".singleBtn").click(function(){
			var adminId = this.id;
			var userName = $(this).parents("tr").children(":eq(3)").text();
			var confirmFlag=confirm("您真的要删除用户名为：【"+userName+"】 id为：【"+adminId+"】这条数据吗？");
			var adminIdArray = [adminId];
			if(confirmFlag){
				resultMessage(adminIdArray,"${param.pageNo}");
			}
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
						<form action="query/with/search.html" class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input name="keyword" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button  type="submit" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<a href="admin/to/add/page.html" class="btn btn-primary" style="float: right;" >
						<i class="glyphicon glyphicon-plus"></i> 新增</a>
						
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id ="summuryBtn" type="checkbox"></th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty requestScope.PAGE.list }">
									非常抱歉，没有查到数据
								</c:if>
									<c:if test="${ !empty requestScope.PAGE.list }">
										<c:forEach items="${requestScope.PAGE.list }" var="admin"
											varStatus="myStatus">

											<tr>
												<td>${myStatus.count}</td>
												<td><input id=${admin.id } class="itemCheckbox" type="checkbox"></td>
												<td>${admin.loginacct }</td>
												<td>${admin.username }</td>
												<td>${admin.email}</td>

												<td>
													<a href="asssign/role/page.html?adminId=${admin.id }" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></a>
													<a href="admin/to/edit/page.html?adminId=${admin.id }&pageNo=${param.pageNo}"  class="btn btn-success btn-xs" ><i class=" glyphicon glyphicon-pencil"></i></a>
													<button id=${admin.id } type="submit" class="btn btn-danger btn-xs singleBtn">
														<i class=" glyphicon glyphicon-remove"></i>
													</button>
												</td>
											</tr>
										</c:forEach>

									</c:if>
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

</body>
</html>