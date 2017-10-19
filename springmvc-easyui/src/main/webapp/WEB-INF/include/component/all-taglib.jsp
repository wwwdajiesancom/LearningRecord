<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- 系统taglib --%>
<%@include file="/WEB-INF/include/component/os-taglib.jsp"%>

<%-- 自定义的taglib --%>
<%@include file="/WEB-INF/include/component/defined-taglib.jsp"%>

<%-- 设置全局页面变量 --%>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>