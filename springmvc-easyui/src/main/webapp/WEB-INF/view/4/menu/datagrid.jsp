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
		},{
			title : "操作",
			field: "opt",
			formatter: function(value,row,index){
				var result = "";
				result += "<a href='#' class='easyui-linkbutton' action='${contextPath}/jsp/datagrid/view?id="+row['id']+"' onClick='return Row.view(this);' params='title:城市详情;width:350;height:250;' >View</a>|";
				result += "<a href='#' class='easyui-linkbutton' buttons='update,close' action='${contextPath}/jsp/datagrid/update?id="+row['id']+"' onClick='return Row.update(this);' params='title:更新;width:350;height:250;' >Update</a>|";
				result += "<a href='#' class='easyui-linkbutton' action='${contextPath}/ajax/datagrid/delete?id="+row['id']+"' onClick='return Row.del(this);' params='width:350;height:250;' >Delete</a>";
				return result;
			}
		}] ];
		
		var options_ = {
			title : "测试城市列表",
			columns : columns,
			fit : true,
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
			<a href="#" action="${contextPath}/jsp/datagrid/view?id={id}" params="title:城市详情;width:350;height:250;" buttons="close" class="easyui-linkbutton" tag="view" >视图</a>
			<a href="#" action="${contextPath}/jsp/datagrid/add" params="title:添加城市;width:350;height:250;" buttons="save[value:添加;],close" class="easyui-linkbutton" tag="add" >添加</a>
			<a href="#" action="${contextPath}/jsp/datagrid/update?id={id}" params="title:更新城市;width:350;height:250;" buttons="update[value:更新],close" class="easyui-linkbutton" tag="update" >修改</a>
			<a href="#" class="easyui-linkbutton" tag="deletes" action="${contextPath}/ajax/datagrid/delete?id={id}" >删除</a>		
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
	<table id="${datagridId}" toolbar="#${test_datagrid}_tb" height="100%" action="${contextPath}/ajax/findpage.json">
		
	</table>
</body>
</html>