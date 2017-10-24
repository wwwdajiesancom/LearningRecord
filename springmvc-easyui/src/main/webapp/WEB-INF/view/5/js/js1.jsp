<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<script type="text/javascript"
	src="${contextPath}/static/viewjs/js/js1.js"></script>

<title>js1学习</title>
</head>
<body>
	<div style="height: 200px;width: 200px;background: red;" onmouseover="toGreen(this);" onmouseout="toRed(this);">
		dajiahao
	</div>
	
	<select onclick="getValue(this);" style="width: 150px;height: 25px;">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
	</select>
	
</body>
</html>