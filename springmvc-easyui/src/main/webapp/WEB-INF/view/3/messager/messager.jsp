<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<title>easyui消息框(messager)</title>
<script type="text/javascript">
<!--
	//messager,它依赖了window,linkbutton,progressbar
	//由于它的特殊性,所以只有js的加载方式,并没有class的加载方式
	//这些方法都是异步的
	//一共分为5种,
	//alert:警告框
	//confirm:
	//prompt:
	//progress:
		
	//show
	$(function(){
		$("#alert_a").bind("click",function(){
			$.messager.alert("提示警告框","这个是警告框呀！！","error"//[error,question,info,warning,]
			);
		
			return false;
		});
		
		$("#confirm_a").bind("click",function(){
			$.messager.confirm("确认框","您确定吗？",function(flag){
				alert(flag);
			});
			alert('ok');
			return false;
		});
				
		$("#prompt_a").bind("click",function(){
			$.messager.prompt("提示输入框","请输入名称",function(r){
				alert(r);
			});	
			return false;
		});
		
		$("#progress_a").bind("click",function(){
			
			return false;
		});
		
		$("#show_a").bind("click",function(){
			$.messager.show({
				title:"提示框",
				msg:"您好吗?",
				
			});
			
		});
	});
//-->
</script>
</head>
<body>
	
	<a href="#" class="easyui-linkbutton" id="show_a">show</a>
	
	<a href="#" class="easyui-linkbutton" id="alert_a">警告框</a>
	
	<a href="#" class="easyui-linkbutton" id="confirm_a">确认框</a>
	
	<a href="#" class="easyui-linkbutton" id="prompt_a">输入框</a>
	
	<a href="#" class="easyui-linkbutton" id="progress_a">进度框</a>
	
</body>
</html>