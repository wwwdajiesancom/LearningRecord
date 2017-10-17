<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<title>easyui菜单按钮(menu)</title>
<script type="text/javascript">
<!--
	$(function() {
		//绑定右键事件
		$(document).bind("contextmenu", function(e) {
			//1.去掉原来的右键事件
			e.preventDefault();
			//2.显示菜单
			$("#box").menu("show",{//显示在邮件的右下方
				left:e.pageX,
				top:e.pageY,
			});
		});

	});
//-->
</script>
</head>
<body>

	<div id="box" class="easyui-menu" style="width: 120px;">
		<div data-options="name:'new'">新建</div>
		<div data-options="name:'save',iconCls:'icon-save'">保存</div>
		<div data-options="name:'print',iconCls:'icon-print'">打印</div>
		<div class="menu-sep"></div>
		<div data-options="name:'exit'">退出</div>
	</div>


</body>
</html>