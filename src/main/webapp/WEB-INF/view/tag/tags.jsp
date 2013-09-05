<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>


<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

<div class=backgroundTitle>
	<c:forEach var="tag" items="${tags}">
		<a href="/tag/${tag.tag}"><h1>${tag.tag}</h1></a>
	</c:forEach>
</div>
