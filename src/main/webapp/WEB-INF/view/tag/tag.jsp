<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>


<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

Tags: ${tags}
<div class=backgroundTitle>
    <c:forEach var="article" items="${articles}">
        <a href="/article/${article}"><h1>${article}</h1></a>
    </c:forEach>
</div>
