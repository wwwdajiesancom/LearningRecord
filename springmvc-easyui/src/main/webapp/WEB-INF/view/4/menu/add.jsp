<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>
<script type="text/javascript">
<!--
	function test_gatagrid_valid() {
		alert("abc");
		return false;
	}
//-->
</script>
<form action="${contextPath}/ajax/datagrid/add" method="post"
	callbackValid="" callbackSuccess="" callbackSubSuccess=""
	callbackParam="" callbackSubParam="">

	<table>
		<tr>
			<td>城市名称：</td>
			<td> <input
				type="text" name="name" class="easyui-validatebox" zauto="true" required="true" /></td>
		</tr>
		<tr>
			<td>城市缩写：</td>
			<td><input type="text" name="abbr" class="easyui-validatebox"
				zauto="true"  required="true" /></td>
		</tr>
	</table>

</form>
