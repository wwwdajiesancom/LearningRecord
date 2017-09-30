<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui按钮(linkbutton)</title>
<script type="text/javascript">
<!--
	$(function(){
		$('#box').linkbutton();
		$('#box').click(function(){alert('ok');return false;});
	});
//-->
</script>
</head>
<body>
	<!-- 
	<a class="easyui-linkbutton" href="###">按钮</a>
	 -->
	 	<a id="box" href="###">按钮</a>
</body>
</html>