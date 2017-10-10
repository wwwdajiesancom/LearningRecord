<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui布局组件(layout)</title>
<script type="text/javascript">
<!--
	//layout,它依赖了panel,resizable
	$(function(){
				
	});
	
//-->
</script>
</head>
<body class="easyui-layout" fit="true">
	
	<div region="north" style="height: 60px;" title="上北(north)" noheader="true" >上北(north)</div>
	
	<div region="west" style="width: 200px;" title="左西(west)">左西(west)</div>
	
	<div region="center" title="中间(center)" fit="true" noheader="true" >中间(center)</div>
	
	<!-- <div region="east" style="width: 150px;" title="右东(east)">右东(east)</div> -->
	
	<div region="south" style="height: 40px;" title="下南(south)" noheader="true">下南(south)</div>
	
</body>
</html>