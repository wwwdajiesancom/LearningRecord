<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>
<title>easyui-window窗体(window)</title>
<script type="text/javascript">
<!--
	//window,它依赖了panel,resizable,draggable
	//属性modal=true;后面添加一个墓碑,其它的就不可以操作了
//-->
</script>
</head>
<body>

	<div class="easyui-window" title="test-window" modal="true" style="width: 250px;height: 150px;" >
		
	</div>
	
		<div class="easyui-window" title="test-window2" modal="false" style="width: 250px;height: 150px;" >
		
	</div>
	
</body>
</html>