/**
 * 角色维护相关函数
 */

// 初始化角色分页页面
function initPage() {
	
	// window代表浏览器窗口
	// window对象的属性对于JavaScript代码来说，是全局范围的
	
	// 如果需要pageNo可以从全局变量范围获取：window.pageNo
	// 如果需要keyword可以从全局变量范围获取：window.keyword
	
	$.ajax({
		"url":"get/role/page.json",
		"data":{
			"pageNo":window.pageNo,
			"keyword":window.keyword,
			"random":Math.random()
		},
		"type":"post",
		"dataType":"json",
		"success":function(response) {
			
			if(response.result == "SUCCESS") {
				
				// 获取分页数据
				var pageInfo = response.data;
				
				// 初始化分页表格
				initPageTable(pageInfo);
				
				// 初始化Pagination
				initPagination(pageInfo);
			}
			
			if(response.result == "FAILED") {
				layer.msg(response.message);
			}
			
		},
		"error":function(response){
			
			layer.msg(response.message);
			
		}
	});
	
}

// 初始化分页表格
function initPageTable(pageInfo) {
	
	// 为了避免不断追加内容，在执行所有操作前先删除旧内容
	$("#rolePageListLocation").empty();
	
	// 获取用来显示分页列表数据的list
	var list = pageInfo.list;
	
	// 判断list是否有效
	if(list == null || list.length == 0) {
		$("#rolePageListLocation").append("<tr><td colspan='4' style='text-align:center'>未查询到任何数据！</td></tr>");
		return ;
	}
	
	// 遍历list
	for(var i = 0; i < list.length; i++) {
		var role = list[i];
		
		var roleId = role.id;
		var roleName = role.name;

		
		// 创建各个单元格的HTML标签
		var countTdHTML = "<td>"+(i+1)+"</td>";
		var checkBoxHTML = "<td><input roleId='"+roleId+"' class='itemCheckbox' type='checkbox'></td>";
		var roleNameTdHTML = "<td>"+roleName+"</td>";
		
		var checkBtn = "<button roleId='"+roleId+"' type='button' class='btn btn-success btn-xs authBtn'><i class=' glyphicon glyphicon-check'></i></button>";
		var pencilBtn = "<button roleId='"+roleId+"' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
		var removeBtn = "<button roleId='"+roleId+"' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
		
		var btnTdHTML = "<td>"+checkBtn+" "+pencilBtn+" "+removeBtn+"</td>";
		
		// 将单元格代码组装到tr中
		var trHTML = "<tr>"+countTdHTML+checkBoxHTML+roleNameTdHTML+btnTdHTML+"</tr>";
		
		// 在tbody中以追加方式组装tr
		$("#rolePageListLocation").append(trHTML);
	}
	
}

// 初始化分页导航条
function initPagination(pageInfo) {
	
	// 总记录数
	var totalRecord = pageInfo.total;
	
	var pageSize = pageInfo.pageSize;
	
	var currentPage = pageInfo.pageNum - 1;
	
	// 使用JSON对象封装Pagination属性
	var properties = {
		num_edge_entries: 2,			//边缘页数
		num_display_entries: 5,			//主体页数
		callback: pageNoClickCallBack,	//用户在浏览器上点击某一个页码时通过执行这个回调函数跳转到对应页码的页面
		items_per_page:pageSize,		//每页显示项
		current_page: currentPage,		//当前页的页索引，需要通过页码-1得到
		prev_text: "上一页",				//上一页按钮上显示的文本
		next_text: "下一页",				//下一页按钮上显示的文本
	};
	
	// #Pagination定位分页导航条所在位置
	// <div id="Pagination" class="pagination"><!-- 这里显示分页导航条 --></div>
	// 调用$("#Pagination")的pagination()方法，将总记录数和属性设置对象以参数形式传入
	$("#Pagination").pagination(totalRecord, properties);
}

// 点击页码时跳转页面回调函数
function pageNoClickCallBack(page_index) {
	// 将全局变量pageNo修改为pageIndex+1
	window.pageNo = page_index + 1;
	
	// 重新初始化当前页面
	initPage();
	
	// 阻止页面跳转
	return false;
}
/**
 * 获取要删除的用户集合
 * @returns
 */
function getRoleModalList(){
	
	var ajaxResult = $.ajax({
		"url":"role/get/modal/list.json",
		"data":JSON.stringify(window.roleIdList),
		"type":"post",
		"contentType":"application/json;charset=UTF-8",
		"dataType":"json",
		"async":false // 同步
	});
	return ajaxResult.responseJSON.data;
}
// 填充模态框
function fillRoleModal(roleList){
	// 清空以前的旧数据
	$("#roleConfirmBody").empty();
	for(var i = 0;i < roleList.length;i++){
		var role = roleList[i];
		var roleId = role.id;
		var roleName = role.name;
		$("#roleConfirmBody").append("<tr><td>"+roleId+"</td><td>"+roleName+"</td></tr>")
	}
}
// 批量删除
function batchRemove(){
	$.ajax({
		"url":"role/madal/batch/remove.json",
		"data":JSON.stringify(window.roleIdList),
		"type":"post",
		"dataType":"json",
		"contentType":"application/json;charset=UTF-8",
		"success":function(response){
			if(response.result == "SUCCESS"){
				layer.msg("操作成功!");
				// 删除成功初始化页面
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
	$("#roleConfirmModal").modal("hide");
	
}

