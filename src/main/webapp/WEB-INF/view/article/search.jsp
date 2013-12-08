<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>


<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

keyword: ${q}
<div class=backgroundTitle>
    tag searched
    <c:forEach var="article" items="${articlesInTag}">
        <a href="/article/${article}"><h1>${article}</h1></a>
    </c:forEach>
</div>

<div class=backgroundTitle>
    content searched
    <c:forEach var="article" items="${articlesWithKeyword}">
        <a href="/article/${article}"><h1>${article}</h1></a>
    </c:forEach>
</div>