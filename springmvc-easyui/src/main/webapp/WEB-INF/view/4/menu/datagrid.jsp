<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<script type="text/javascript" src="${contextPath}/static/js/easyui-extra/datagrid-extra.js"></script>

<script type="text/javascript"
	src="${contextPath}/static/js/bind-extra.js"></script>

<title>easyui-分页table(datagrid)</title>
<script type="text/javascript">
<!--
	$(function() {
		
		
		
		//datagrid使用class加载,其中的tbody,thead,th中field都不可缺少,
		
		var columns = [[
			{
				title:"代码",
				field:"code",
				
			},{
				title:"名称",
				field:"name",
			},			
		]];
		
		var data = [
			{
				code:1,
				name:"jiege"
			},{
				code:2,
				name:"weiwei"
			}
		];
		
		//js加载
		$("#test_datagrid").datagrid({
			
			//列
			columns:columns,
			
			//数据
			data:data,
			
			//分页参数
			pagination:true,
			pagePosition: "bottom",//'top','bottom','both'
			pageNumber:1,
			pageSize:10,
			pageList:[10,20,30,40,50],
						
			//datagrid的一些属性
			
		});
		
	});
//-->
</script>
</head>
<body>
	
	<c:set value="test_datagrid" var="datagridId"></c:set>
	
	<table id="${datagridId}">
		
	</table>

	<%-- 
		<table class="easyui-datagrid">
			<!-- thead不可缺少 -->
			<thead>
				<tr>
					<th field="code">编码</th>
					<th field="name">名称</th>
					<th data-options="field:'price'">价格</th>
				</tr>
			</thead>
			<!-- tbody不可缺少 -->
			<tbody>
				<tr>
					<td>001</td>
					<td>name1</td>
					<td>2323</td>
				</tr>
				<tr>
					<td>002</td>
					<td>name2</td>
					<td>4612</td>
				</tr>
			</tbody>
		</table>
	--%>
</body>
</html>