<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

<div class="btn-group" data-toggle="buttons-checked">
	<c:forEach var="tag" items="${tags}">
		<button type="button" id="tag${tag}" class="btn btn-primary">${tag}</button>
	</c:forEach>
</div>

<script>
	<c:forEach var="tag" items="${tags}">
	$('#tag${tag}').click(function() {
		// TODO :: find article with selected tag.
	});
	</c:forEach>
</script>

<div id="selectedArticles" class="backgroundTitle">
	<c:forEach var="article" items="${articles}">
		<a href="/article/${article.title}"><h1>${article.title}</h1></a>
	</c:forEach>
</div>
