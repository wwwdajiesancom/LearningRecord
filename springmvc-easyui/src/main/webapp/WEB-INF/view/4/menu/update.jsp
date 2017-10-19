<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/os-taglib.jsp"%>

<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>

<form action="${contextPath}/ajax/datagrid/update" method="post"
	callbackSuccess="" callbackSubSuccess="" callbackParam=""
	callbackSubParam="">
	<table>
		<tr>
			<td>城市名称：</td>
			<td>
			<input type="hidden" name="id" value="${city.id}" />
			<input type="text" name="name" class="easyui-validatebox"
				zauto="true" value="${city.name}" required="true" /></td>
		</tr>
		<tr>
			<td>城市缩写：</td>
			<td><input type="text" name="abbr" class="easyui-validatebox"
				zauto="true" value="${city.abbr}" required="true" /></td>
		</tr>
		<tr>
			<td>创建事件：<fmt:formatDate value="${city.createdAt}"
					pattern="yyyy-MM-dd HH:mm:ss" var="fmtCreatedAt" /></td>
			<td><input type="text" name="createdAt" disabled="disabled"
				readonly="readonly" zauto="true" value="${fmtCreatedAt}" /></td>
		</tr>
	</table>
</form>
