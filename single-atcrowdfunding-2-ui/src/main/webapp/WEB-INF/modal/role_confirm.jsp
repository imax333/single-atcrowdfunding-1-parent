<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="roleConfirmModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">尚筹网系统信息</h4>
			</div>
			<div class="modal-body">
				<p>你真的要删除下面的数据吗？</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>RoleID</th>
							<th>RoleName</th>
						</tr>
					</thead>
					<tbody id="roleConfirmBody"></tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button id="roleConfirmBtn" type="button" class="btn btn-primary">确认删除</button>
			</div>
		</div>
	</div>
</div>