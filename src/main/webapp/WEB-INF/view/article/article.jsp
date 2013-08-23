<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class=backgroundTitle>
	<a href=#section0 class=anchorLink><h1 id=title>${title}</h1></a>
</div>

<c:forEach var="chapter" varStatus="loop" items="${chapters}">
	<c:forEach var="section" varStatus="innerLoop" items="${chapter.sections}">
		<c:choose>
			<c:when test="${loop.last and innerLoop.first}">
				<div id="${section.id}" class="lastChapter ${section.css}">
					<a href="#${section.nextid}" class="anchorLink"><h1>${section.subtitle}</h1></a> ${section.content}
				</div>
			</c:when>
			<c:otherwise>
				<div id="${section.id}" class="${section.css}">
					<a href="#${section.nextid}" class="anchorLink"><h1>${section.subtitle}</h1></a> ${section.content}
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${loop.last and not license}">
			<c:if test="${not empty chapter.quiz}">
				<div id="${chapter.quiz.id}" class="${chapter.quiz.css}">
					<a href="#${chapter.quiz.nextid}" class="anchorLink"><h1>${chapter.quiz.subtitle}</h1></a> ${chapter.quiz.content}
					<input id="answer" type="text" onKeyDown="sendAnswer('${chapter.id}')">
				</div>
			</c:if>
		</c:when>
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

<div id="nextChapter"></div>

<script>
	$(document).ready(function() {
		moveLastChapter();
	});
</script>