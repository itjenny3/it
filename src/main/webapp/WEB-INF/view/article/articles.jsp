<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<c:forEach var="article" items="${articles}">
	<a href="article/${article.title}"><h1>${article.title}</h1></a>
</c:forEach>