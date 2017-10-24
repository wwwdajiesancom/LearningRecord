<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<script type="text/javascript" src="${contextPath}/static/viewjs/datagrid/datagrid.js"></script>

<title>easyui-分页table(datagrid)</title>
</head>
<body>
	<div id="datagrid_table_tb">
		<div>
			<a href="javascript:;" class="easyui-linkbutton" tag="view" buttons="close" params="title:用户详情;width:350;height:250;modal:true;" action="${contextPath}/jsp/datagrid/view/{id}">详情</a>
			<a href="javascript:;" class="easyui-linkbutton" tag="add" buttons="close,save" params="title:添加用户;width:350;height:250;modal:true;" action="${contextPath}/jsp/datagrid/add">添加</a>
			<a href="javascript:;" class="easyui-linkbutton" tag="update" buttons="update,close" params="title:修改用户;width:350;height:250;modal:true;" action="${contextPath}/jsp/datagrid/update/{id}">修改</a>
			<a href="javascript:;" class="easyui-linkbutton" tag="deletes" action="${contextPath}/ajax/datagrid/delete/{id}">删除</a>
		</div>
		<div>
			<table id="datagrid_table_tb_search">
				<tr>
					<td>名称：</td>
					<td>
						<input type="text" name="name" zauto="true" />
					</td>
					<td>
						编号：
					</td>
					<td>
						<input type="text" name="id" zauto="true" />
					</td>
					<td>
						<a href="#" class="easyui-linkbutton" tag="search" >查询</a>
					</td>
				</tr>
			</table>
		</div>
	</div>

	<table id="datagrid_table" toolbar="#datagrid_table_tb" height="100%"
		action="${contextPath}/ajax/findpage">

	</table>
</body>
</html>