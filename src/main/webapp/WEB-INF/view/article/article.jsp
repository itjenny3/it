<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<script src="/js/article.js?20130903"></script>

<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

<style>
	${css}
</style>

<c:choose>
	<%-- 1 chapter --%>
	<c:when test="${fn:length(chapters) == '1'}">
		<div class="section lastChapter backgroundTitle">
			<h1 id=title>${title}</h1>
		</div>
	</c:when>

	<%-- 2 more chapter --%>
	<c:otherwise>
		<div class="section backgroundTitle">
			<h1 id=title>${title}</h1>
		</div>
	</c:otherwise>
</c:choose>

<%-- chapters --%>
<c:forEach var="chapter" varStatus="loop" items="${chapters}">
	<%-- sections --%>
	<c:forEach var="section" varStatus="innerLoop" items="${chapter.sections}">
		<c:choose>
			<%-- 
				1. first section in last chapter.  
				2. not first section in first chapter 
				3. doesn't has license
			--%>
			<c:when test="${!loop.first and loop.last and innerLoop.first and !license}">
				<div class="section lastChapter background">
					<h1>${section.subtitle}</h1>
					${section.content}

					<%-- pagination --%>
					<p class="page">${section.index} / ${totalSection}</p>
				</div>
			</c:when>

			<%-- normal section --%>
			<c:otherwise>
				<div class="section background">
					<h1>${section.subtitle}</h1>
					${section.content}

					<%-- pagination --%>
					<p class="page">${section.index} / ${totalSection}</p>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<%-- ask answer when doesn't have license. --%>
		<c:when test="${loop.last and !license}">
			<c:if test="${not empty chapter.quiz}">
				<div class="section background">
					<h1>${chapter.quiz.subtitle}</h1>
					${chapter.quiz.content}

					<sec:authorize access="hasRole('ROLE_USER')">
						<input id="answer" type="text" onKeyDown="sendAnswer('${chapter.id}')">
					</sec:authorize>

					<sec:authorize access="!hasRole('ROLE_USER')">
						<input id="answer" type="text" onFocus="login()">
					</sec:authorize>

					<%-- pagination --%>
					<p class="page">${chapter.quiz.index} / ${totalSection}</p>
				</div>
			</c:if>
		</c:when>

		<%-- display answer --%>
		<c:otherwise>
			<c:if test="${not empty chapter.quiz}">
				<div class="section background">
					<h1>${chapter.quiz.subtitle}</h1>
					${chapter.quiz.content}
					<blockquote>
						<p>${chapter.quiz.answer}</p>
					</blockquote>

					<%-- pagination --%>
					<p class="page">${chapter.quiz.index} / ${totalSection}</p>
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:choose>
	<%-- already has license --%>
	<c:when test="${license}">
		<jsp:include page="license.jsp" />
	</c:when>

	<%-- doesn't has license --%>
	<c:otherwise>
		<div id="nextChapter"></div>
	</c:otherwise>
</c:choose>


<script>
	$(document).ready(function() {
		setCurrent();
		moveLastChapter();
	});
</script>