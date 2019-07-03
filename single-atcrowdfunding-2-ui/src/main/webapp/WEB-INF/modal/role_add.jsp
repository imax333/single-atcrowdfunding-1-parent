<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="saveModalBtn" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<form >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">尚筹网系统消息</h4>
			</div>
			<div class="modal-body">
				<input type="text" 
						   name="roleName"
						   class="form-control" 
						   placeholder="请输入角色名称"/>
			</div>
			<div class="modal-footer">
				<button id="closeBtn" type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="saveBtn" type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
		</form>
	</div>
</div>
