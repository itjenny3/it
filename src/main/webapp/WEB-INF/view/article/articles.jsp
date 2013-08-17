<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

${loginUser.userId}
<form action="/signin/facebook" method="POST">
	<input type="hidden" name="scope" value="publish_stream" />
	<button type="submit" class="btn-login-facebook">
		<i class="foundicon-facebook"></i>facebook
	</button>
</form>
<a href="/users/logout" title="logout">logout</a>

<div class=backgroundTitle>
	<c:forEach var="article" items="${articles}">
		<a href="/article/${article.title}"><h1>${article.title}</h1></a>
	</c:forEach>
</div>