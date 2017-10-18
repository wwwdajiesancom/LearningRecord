<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/all-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/include/component/easyui-script-css.jsp"%>

<title>easyui-分页table(datagrid)</title>
<script type="text/javascript">
<!--
	$(function() {

		//datagrid使用class加载,其中的tbody,thead,th中field都不可缺少,
		//js加载
		//datagrid的一些属性
		//autoRowHeight:true,//定义设置行的高度，根据该行的内容。设置为false可以提高负载性能。
		//striped:true,//显示斑马线
		//rownumbers:true,//显示行号
		
		//singleSelect:true,//true,只允许选择一行
		//ctrlSelect:false,//是否要启动Ctrl键,进行多选；不过在singleSelect=true时失效
		
		//checkOnSelect:false,//
		//selectOnCheck:false,//

		//1.列
		//columns : columns,
		//frozenColumns : [[]],//同列属性，但是这些列将会被冻结在左侧。格式与columns是一样的

		//2.数据
		//loadMsg : "加载中......",
		//2.1直接数据
		//data : data,
		//2.2间接数据,通过url来获取了
		//url:"${contextPath}/ajax/findpage.json",
		//loadFilter:undefined,//处理url返回的json数据{total:100,rows:[{},{},{},{}]};这里不需要处理,除非特殊的
		
		
		//3.分页参数
		//pagination : true,
		//pagePosition : "bottom",//'top','bottom','both'
		//pageNumber : 1,
		//pageSize : 10,
		//pageList : [ 10, 20, 30, 40, 50 ],

		
		//clomun中的字段
		//title,显示中的标题
		//field,字段
		//width,宽度
		
		//排序
		//sortable:true,//启动本字段排序
		

		var columns = [ [ {
			title : "代码",
			field : "id",
			checkbox:true,
		}, {
			title : "名称",
			field : "name",
			sortable:true,
		}, {
			title : "缩写",
			field : "abbr"
		}, {
			title : "创建时间",
			field : "createdAt",
			formatter: function(value,row,index){
				return Extra.formatDate(value,"yyyy-MM-dd HH:mm:ss");
			}
		}] ];
		
		var options_ = {
			title : "测试table",
			
			columns : columns,
			
		};
		
		var datagrid = new DatagridExtra("test_datagrid",options_);

	});
//-->
</script>
</head>
<body>
	<!-- 设置datagrid的Id -->
	<c:set value="test_datagrid" var="datagridId"></c:set>
	
	<!-- datagrid的toolbar部分代码 -->
	<div id="${test_datagrid}_tb"  style="display: none;">
		<div>
			<a href="#" fhref="${contextPath}/jsp/dialog/view.html?id={id}" attr="title:添加测试;width:350;height:250;" buttons="close" class="easyui-linkbutton" tag="view" >视图</a>
			<a href="#" fhref="${contextPath}/jsp/datagrid/add.html" attr="title:添加测试;width:350;height:250;" buttons="save,close" class="easyui-linkbutton" tag="add" >添加</a>
			<a href="#" fhref="${contextPath}/jsp/datagrid/update.html?id={id}" attr="width:350;height:250;" buttons="update[value:修改],close" class="easyui-linkbutton" tag="update" >修改</a>
			<a href="#" class="easyui-linkbutton" tag="deletes" action="${contextPath}/ajax/datagrid/delete.json?ids={id}" >删除</a>		
		</div>
		<div>
			<table id="${test_datagrid}_tb_search">
				<tr>
					<td>名称：</td>
					<td>
						<input type="text" name="name" zauto="true" />
					</td>
					<td>
						编号：
					</td>
					<td>
						<input type="text" name="id" zauto="true" />
					</td>
					<td>
						<a href="#" class="easyui-linkbutton" tag="search" >查询</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- datagrid代码 -->
	<table id="${datagridId}" toolbar="#${test_datagrid}_tb" action="${contextPath}/ajax/findpage.json">
		
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