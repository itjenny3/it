<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<script src="/js/article.js?20130903"></script>

<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

<div class=backgroundTitle>
	<a href=#section0 class=anchorLink><h1 id=title>${title}</h1></a>
</div>

<%-- chapters --%>
<c:forEach var="chapter" varStatus="loop" items="${chapters}">
	<%-- sections --%>
	<c:forEach var="section" varStatus="innerLoop" items="${chapter.sections}">
		<c:choose>
			<%-- 
				1 .first section in last chapter.  
				2. not first section in first chapter 
				3. doesn't has license
			--%>
			<c:when test="${!loop.first and loop.last and innerLoop.first and !license}">
				<div id="${section.id}" class="lastChapter ${section.css}">
					<a href="#${section.nextid}" class="anchorLink"><h1>${section.subtitle}</h1></a> ${section.content}
				</div>
			</c:when>

			<%-- normal section --%>
			<c:otherwise>
				<div id="${section.id}" class="${section.css}">
					<a href="#${section.nextid}" class="anchorLink"><h1>${section.subtitle}</h1></a> ${section.content}
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<%-- ask answer when doesn't have license. --%>
		<c:when test="${loop.last and !license}">
			<c:if test="${not empty chapter.quiz}">
				<div id="${chapter.quiz.id}" class="${chapter.quiz.css}">
					<a href="#${chapter.quiz.nextid}" class="anchorLink"><h1>${chapter.quiz.subtitle}</h1></a> ${chapter.quiz.content}
					<input id="answer" type="text" onKeyDown="sendAnswer('${chapter.id}')">
				</div>
			</c:if>
		</c:when>

		<%-- display answer --%>
		<c:otherwise>
			<c:if test="${not empty chapter.quiz}">
				<div id="${chapter.quiz.id}" class="${chapter.quiz.css}">
					<a href="#${chapter.quiz.nextid}" class="anchorLink"><h1>${chapter.quiz.subtitle}</h1></a> ${chapter.quiz.content}
					<blockquote>
						<p>${chapter.quiz.answer}</p>
					</blockquote>
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
		moveLastChapter();
	});
</script>