<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/component/os-taglib.jsp"%>

<script type="text/javascript"
	src="${contextPath}/static/js/exec-bind.js"></script> 

<table>
	<tr>
		<td>城市名称：</td>
		<td><input type="text" name="name" class="ztextbox"
			readonly="readonly" zauto="true" value="${city.name}" /></td>
	</tr>
	<tr>
		<td>城市缩写：</td>
		<td><input type="text" name="abbr" class="ztextbox"
			readonly="readonly" zauto="true" value="${city.abbr}" /></td>
	</tr>
	<tr>
		<td>创建事件：<fmt:formatDate value="${city.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" var="fmtCreatedAt"/></td>
		<td><input type="text" name="createdAt" class="ztextbox"
			readonly="readonly" zauto="true"
			value="${fmtCreatedAt}" /></td>
	</tr>
</table>
