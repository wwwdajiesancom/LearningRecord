<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<script type="text/javascript" src="${contextPath}/static/js/bind-extra.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/easyui-extra/dialog-extra.js"></script>

<title>easyui对话框(dialog)</title>
<script type="text/javascript">
<!--
	//dialog,它依赖了window,linkbutton
	//
	$(function(){
		var test_dialog = new DialogExtra("test_dialog");
		
		$("#open_dialog").bind("click",function(){
			test_dialog.open();
		});
	});
//-->
</script>
</head>
<body>
	
	<a href="#" class="easyui-linkbutton" tag="dialog" buttons="save,close" params="modal:true;width:350px;height: 250px;href:${contextPath}/jsp/datagrid/add;">动态创建一个dialog</a>
	
	<a href="#" class="easyui-linkbutton" id="open_dialog" >打开本页面的dialog</a>
	
 	<div id="test_dialog" href="${contextPath}/jsp/datagrid/add" class="easyui-dialog" title="对话框2" modal="true" buttons="#bt" style="width: 350px;height: 250px;">
		<div id="bt">
			<a href="#" class="easyui-linkbutton" tag="save">保存</a>
			<a href="#" class="easyui-linkbutton" tag="close" >关闭</a>
		</div>
	</div>
	
</body>
</html>