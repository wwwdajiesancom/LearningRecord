<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/os-taglib.jsp"%>

<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script>

<form action="${contextPath}/ajax/datagrid/update" method="post"
	callbackValid="" callbackSuccess="" callbackSubSuccess=""
	callbackParam="" callbackSubParam="">
	<input type="hidden" name="id" value="${user.id}" />
	<table>
		<tr>
			<td>用户名称：</td>
			<td><input type="text" name="name" class="easyui-validatebox"
				required="true" zauto="true" value="${user.name}" /></td>
		</tr>
		<tr>
			<td>用户登录帐号：</td>
			<td><input type="text" name="loginName" class="easyui-validatebox"
				required="true" zauto="true" value="${user.loginName}" /></td>
		</tr>
		<tr>
			<td>创建时间：<fmt:formatDate value="${user.createTime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="fmtCreateTime" /></td>
			<td><input type="text" class="ztextbox" readonly="readonly"
				zauto="true" value="${fmtCreateTime}" /></td>
		</tr>
		<tr>
			<td>更新时间：<fmt:formatDate value="${user.updateTime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="fmtUpdateTime" /></td>
			<td><input type="text" class="ztextbox" readonly="readonly"
				zauto="true" value="${fmtUpdateTime}" /></td>
		</tr>
	</table>
</form>