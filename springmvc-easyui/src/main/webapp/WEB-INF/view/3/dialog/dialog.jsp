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
		
	});
	
	function nxnxnxnxnxnxnxnx(){
		alert("nxnxnxnxnxnxnxnx");
	}
//-->
</script>
</head>
<body>

    <div abc="abc">
        <div a="a">
        
			<a href="#" class="easyui-linkbutton" tag="dialog" buttons="save,close" params="modal:true;width:350px;height: 250px;href:${contextPath}/jsp/datagrid/add;">动态创建一个dialog</a>
        </div>
    </div>
	
	
</body>
</html>