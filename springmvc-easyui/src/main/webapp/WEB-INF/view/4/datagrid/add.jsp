<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>
<script type="text/javascript">
console.log(this);
</script>

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
            <td><select id="cc" class="easyui-combobox" panelHeigth="75">
		        <option>java</option>
		        <option>hphp</option>
		        <option>python</option>
		        <option>c#</option>
		    </select></td>
        </tr>		
	</table>

</form>

<div id="nxnxnxnx">
	<div id="dd"  class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"   
	        data-options="iconCls:'icon-save',resizable:true,modal:false,closed:true">   
	    Dialog Content.    
	</div>  
</div>