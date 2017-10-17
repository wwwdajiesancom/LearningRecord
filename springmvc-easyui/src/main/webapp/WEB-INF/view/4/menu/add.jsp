<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>
<script type="text/javascript">
<!--
	function test_gatagrid_valid(){
		alert("abc");
		return false;
	}
//-->
</script>
<form action="${contextPath}/ajax/datagrid/add.json" id="from_id" method="post" callbackValid="" callbackSuccess="" callbackSubSuccess="" callbackParam="" callbackSubParam="">
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
<script type="text/javascript">
<!--
	
//-->
</script>
