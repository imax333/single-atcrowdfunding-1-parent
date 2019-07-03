/**
 * 
 */

function initMenu(){
	// 声明settings对象用来存储树形结构相关设置
	setting = {
			view: {
				addDiyDom: addDiyDom,
				addHoverDom:addHoverDom,
				removeHoverDom:removeHoverDom
			},
			data:{
				key:{
					url:"noExists"
				}
			}
	}
	var ajaxData = $.ajax({
		"url":"menu/get/root/node.json",
		"type":"post",
		"dataType":"json",
		"async":false // 同步
	});
	var zTreeNodes = ajaxData.responseJSON.data;
	
	
	// #treeDemo对应页面上的一个HTML标签，这是zTree整体树形结构所依附的HTML标签
	$.fn.zTree.init($("#treeDemo"), setting, zTreeNodes);
}


// treeId   String 数据格式   对应 zTree 的 treeId，便于用户操控
// treeNode   JSON数据格式   需要显示自定义控件的节点 JSON 数据对象
// zTree在生成每一个树形节点时会调用这个函数
function addDiyDom(treeId, treeNode) {
	// 获取自定义图标对象属性
	var iconValue = treeNode.icon;

	// treeNode 节点的唯一标识 tId。
	// 初始化节点数据时，由 zTree 增加此属性，请勿提前赋值
	// 获取treeNode 对象 当前节点在页面上的id属性
	var nodeId = treeNode.tId;
	
	// 拼装图标元素的id
	var iconSpanId = nodeId+"_ico";
	// 获取图标元素的jQuery对象，移除旧的class值，添加新的class值
	$("#"+iconSpanId).removeClass("button ico_open ico_docu")
					.addClass(iconValue)
					.css("background","");
};

// 菜单栏鼠标移入  
function addHoverDom(treeId, treeNode){
	// 获取当前节点的id
	var nodeId = treeNode.tId;
	// 拼装字符串
	var spanId= nodeId+"_btnGrp";
	
	// 判断当前是否已经添加了按钮组
	if($("#"+spanId).length >0){
		return;
	}
	// 获取对象的id属性
	var id = treeNode.id;
	var addBtnHTML = "<a onclick='showAddModal(this)' id='"+id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg'></i></a>";
	var editBtnHTML = "<a onclick='showEditModal(this)' id='"+id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' title='修改子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg'></i></a>";
	var removeBtnHTML = "<a onclick='showRemoveModal(this)' id='"+id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' title='删除子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg'></i></a>";
	var assignBtnHTML = "<a onclick='showAssignModal(this)' id='"+id+"' class='btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' title='分配权限'>&nbsp;&nbsp;<i class='fa fa-fw fa-check rbg'></i></a>";
	// 拼装当前超链接中的id
	var anchorId  = nodeId + "_a";
	
	var level = treeNode.level;
	if(level == 0){
		$("#"+anchorId).after("<span id='"+spanId+"'>"+addBtnHTML+"</span>");
	}
	if(level == 1){
		var children = treeNode.children.length;
		if(children >0){
			$("#"+anchorId).after("<span id='"+spanId+"'>"+addBtnHTML+editBtnHTML+assignBtnHTML+"</span>");
		}
		if(children ==0){
			$("#"+anchorId).after("<span id='"+spanId+"'>"+addBtnHTML+removeBtnHTML+editBtnHTML+assignBtnHTML+"</span>");
		}
	}
	if(level == 2){
		$("#"+anchorId).after("<span id='"+spanId+"'>"+editBtnHTML+removeBtnHTML+assignBtnHTML+"</span>");
	}
}
// 鼠标移出事件
function removeHoverDom(treeId, treeNode){
	
	// 获取当前节点的id
	var nodeId = treeNode.tId;
	// 拼装字符串
	var spanId= nodeId+"_btnGrp";
	
	$("#"+spanId).remove();
}

function showAddModal(currentBtn){
	
	$("#menuAddModal").modal("show");
	// 获取数据
	window.currentId= currentBtn.id;
}
function showRemoveModal(currentBtn){
	
	window.currentId=currentBtn.id;
	
	// 通过id去查找要删除的menu
	var ajaxResult = $.ajax({
		"url":"menu/get/"+window.currentId+".json",
		"type":"post",
		"dataType":"json",
		"async":false
	});
	if(ajaxResult.responseJSON.result == "FAILED"){
		layer.msg(ajaxResult.responseJSON.message);
		return ;
	}
	
	$("#menuConfirmModal").modal("show");
	var menu = ajaxResult.responseJSON.data;
	
	var html ="您真的要删除【<i class='"+menu.icon+"'></i> "+menu.name+"】这个菜单选项吗？";
	$("#menuConfirmMessage").html(html);
}
function showEditModal(currentBtn){
	window.currentId = currentBtn.id;
	
	
	var ajaxResult = $.ajax({
		"url":"menu/get/"+window.currentId+".json",
		"type":"post",
		"dataType":"json",
		"async":false
	});
	if(ajaxResult.responseJSON.result == "FAILED"){
		layer.msg(ajaxResult.responseJSON.message);
		return ;
	}
	$("#menuEditModal").modal("show");
	var menu = ajaxResult.responseJSON.data;
	
	
	$("#menuEditModal [name='name']").val(menu.name);
	$("#menuEditModal [name='url']").val(menu.url);
	$("#menuEditModal [name='icon'][value='"+menu.icon+"']").prop("checked","checked");
	
	
	
}
