<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="easyuiDirPath" value="static/jquery-easyui-1.3.5"></c:set>
<c:set var="easyuiContextPath" value="${pageContext.request.contextPath}"></c:set>

<%-- jquery --%>
<script type="text/javascript"
	src="${easyuiContextPath}/${easyuiDirPath}/jquery.min.js"></script>

<%-- easyui-1.3.5-script --%>
<script type="text/javascript"
	src="${easyuiContextPath}/${easyuiDirPath}/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${easyuiContextPath}/${easyuiDirPath}/locale/easyui-lang-zh_CN.js"></script>
	
	
<%-- 自己定义的扩展js --%>
<script type="text/javascript" src="${easyuiContextPath}/static/js/extra.js"></script>
	
	
<%-- easyui-1.3.5-css --%>
<link type="text/css" rel="stylesheet" href="${easyuiContextPath}/${easyuiDirPath}/themes/default/easyui.css" />
<link type="text/css" rel="stylesheet" href="${easyuiContextPath}/${easyuiDirPath}/themes/icon.css" />
