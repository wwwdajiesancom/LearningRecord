<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>


<form action="${contextPath}/ajax/dialog/view.json" id="from_id" method="post" callbackSuccess="" callbackSubSuccess="" callbackParam="" callbackSubParam="">
	<table>
		<tr>
			<td>姓名：</td>
			<td><input type="text" name="name" class="easyui-validatebox" zauto="true"
				required="true" /></td>
		</tr>
		<tr>
			<td>Email：</td>
			<td><input type="text" name="email" class="easyui-validatebox" zauto="true"
				validType="email" /></td>
		</tr>
	</table>
</form>
