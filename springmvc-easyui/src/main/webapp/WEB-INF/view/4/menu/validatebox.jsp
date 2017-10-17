<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<script type="text/javascript"
	src="${contextPath}/static/js/bind-extra.js"></script>

<title>easyui-验证框(validatebox)</title>
<script type="text/javascript">
<!--
	$(function() {
		$("#cc").combo({
			required : true,
			multiple : true,
		});
	
		$("#fd").textbox({
			 buttonText:'Search',    
		    iconCls:'icon-man', 
		    iconAlign:'left'    
		});
	});
	
	
//-->
</script>
</head>
<body>


	<input type="text" class="easyui-validatebox" zauto="true"
		required="true" />

	<select id="cc" zauto="true">
		<option>java</option>
		<option>hphp</option>
		<option>python</option>
		<option>c#</option>
	</select>
	
	
	<input id="fd" />


</body>
</html>