<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>
<form action="${contextPath}/ajax/datagrid/add" method="post"
	callbackValid="" callbackSuccess="" callbackSubSuccess=""
	callbackParam="" callbackSubParam="">

	<table>
		<tr>
			<td>用户名称：</td>
			<td><input type="text" name="name" class="easyui-validatebox"
				zauto="true" required="true" /></td>
		</tr>
		<tr>
			<td>用户登录帐号：</td>
			<td><input type="text" name="loginName" class="easyui-validatebox"
				zauto="true" required="true" /></td>
		</tr>
		<tr>
            <td>用户登录帐号2：</td>
            <td><input id="jiege" class="js-datetimebox" name="birthday"     
        data-options="required:true,showSeconds:false" value="3/4/2010 2:3" style="width:150px"> </td>
        </tr>		
	</table>

</form>

<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">   
    Dialog Content.    
</div>  

<script type="text/javascript">
$("#jiege").datetimebox({});
$("#jiege").next().find("input:eq(0)").css("background-color","#f0f0f0");
</script>