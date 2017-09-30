<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui消息提示框(tooltip)</title>
<script type="text/javascript">
<!--
	$(function(){
		$('#a_tip').tooltip({
			content:'this name',
			position:'right'
		});
	});	
//-->
</script>
</head>
<body>
	<!-- 
	<a href="###" title="name tip" class="easyui-tooltip" position="right">my name is loujie.</a>	
	 -->
	<a href="###" id="a_tip">my name is loujie.</a>
	
	
</body>
</html>