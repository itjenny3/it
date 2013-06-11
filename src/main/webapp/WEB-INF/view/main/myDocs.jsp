<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<%-- document list --%>
<c:forEach var="myDoc" items="${myDocs}">
	<%@include file="myDoc.jsp"%>
</c:forEach>