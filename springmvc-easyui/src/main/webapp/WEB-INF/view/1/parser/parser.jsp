<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui渲染器(parser)</title>
<script type="text/javascript">
<!--
	//1.关闭自动渲染
	$.parser.auto=false;
	
	$(function(){
		//2.渲染页面上的[所有]easyui组件
		//$.parser.parse();
		
		//3.渲染指定的easyui组件
		//如果想单独的渲染指定的easyui组件,需要有parent包含它
		$.parser.parse('#box_parent');
		
	});
	
	//4.有一个渲染完毕后的事件监听
	$.parser.onComplete = function(context){
		alert('ok');
	};
//-->
</script>
</head>
<body>
	<div id="box_parent">
		<div id="box" class="easyui-dialog" style="height: 400px;width: 600px;">
			大家好
		</div>
	</div>
</body>
</html>