<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui面板(panel)</title>
<script type="text/javascript">
<!--
	//1.一个面板的组成部分
	//header[title,tool两部分]
	//body,内容部分
	
	//2.属性
	//需要注意的:
	//fit:true;自适应
	//tools,自定义工具菜单,有例子:#tt
	
	//3.加载数据
	//href,填写url地址
	//extractor,处理上面返回的结果
	//method:get,post
	//queryParams,href查询时向后台传递的参数
	
	$(function(){
		$("#pox").panel({
			title:'My Panel',
			width:600,
			height:400,
			//tools:'#tt',
			href:'${context}/test/get/3',
		});
		
	});
//-->
</script>
</head>
<body>
	<!-- 
	<div class="easyui-panel" title="My Panel" style="width: 400px;height: 300px;" tools="#tt"></div>
	 -->
	<div id="pox"></div>	
	<div id="tt">
		<a href="#" class="icon-add" onclick="javascript:alert('add')"></a>
		<a href="#" class="icon-edit" onclick="javascript:alert('edit')"></a>
	</div>
	
	
</body>
</html>