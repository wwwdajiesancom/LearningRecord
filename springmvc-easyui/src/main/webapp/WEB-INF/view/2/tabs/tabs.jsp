<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui选项卡(tabs)</title>
<script type="text/javascript">
<!--
	//tabs,它依赖了panel,linkbutton
	$(function(){
		$('#tbox').tabs({
			plain:false,
			border:true,
			tools:'#tab-tools',
		});
		
	});
	
	function tab_tools_add(){
			
		//2.得出tab名称
		var newTabName = 'tab';
		for(var i = 1;;i++){
			var tmpTab = $('#tbox').tabs('getTab',newTabName + i);
			if(tmpTab==null){
				newTabName = newTabName + i;
				break;
			}
		}
		//3.添加tab
		//可以通过href及content获取内容信息
		//content : ('<iframe  src="'
		//			+ node['url']
		//			+ '" title="'
		//			+ node['text'] + '" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'),
		$('#tbox').tabs('add',{
			title:newTabName,
			content:newTabName,
			closable:true,
			
		});
		
		return false;
	}
//-->
</script>
</head>
<body>

	
	
	<div id="tab-tools" style="display: none;">
		<a href="#" class="icon-add" onclick="return tab_tools_add();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</div>
	<div id="tbox" title="My Tabs" style="width: 800px;height: 450px;">
		<div title="tab1">tab1</div>
		<div title="tab2">tab2</div>
		<div title="tab3">tab3</div>
	</div>
	
	
	
</body>
</html>