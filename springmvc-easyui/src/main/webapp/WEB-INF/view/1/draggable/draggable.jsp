<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui拖拽(Draggable)</title>
<script type="text/javascript">
<!--
	//1.使元素具有拖动的操作draggable
	//1.1有两种添加方法,一种是添加class=easyui-draggable
	//1.2另一种是使用js加载
	$(function(){
		$('#box').draggable();
		//2.draggable的属性及方法,事件
		//2.1属性
		//2.2方法
		//2.3事件
	});
	
	//3.放置组件droppable
	$(function(){
		$('big-box').droppable({
			accept:'#box',//接收的元素
			onDrop:function(){//事件
				alert('ox');
			}
		});
		
	});
	
//-->
</script>
</head>
<body>
	<div>
		<div id="box"  style="width: 100px; height: 100px; background: blue;">
			大家好
		</div>
	</div>
	<div id="big-box" style="width: 500px;height: 600px;background: black;">
		
	</div>
</body>
</html>