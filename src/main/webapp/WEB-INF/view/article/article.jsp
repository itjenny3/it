<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class=backgroundTitle>
	<a href=#div0 class=anchorLink><h1>${htmlArticle.title}</h1></a>
</div>

<c:forEach var="chapter" items="${htmlArticle.chapters}">
	<c:forEach var="section" items="${chapter.sections}">
		<div id=${section.id } class=${section.css}>
			<a href=#${section.nextid} class=anchorLink><h1>${section.subtitle}</h1></a> ${section.content}
		</div>
	</c:forEach>
	<c:if test="${not empty chapter.quiz}">
		<div id=${chapter.quiz.id } class=${chapter.quiz.css}>
			<a href=#${chapter.quiz.nextid} class=anchorLink><h1>${chapter.quiz.subtitle}</h1></a> ${chapter.quiz.content}
		</div>
	</c:if>
</c:forEach>

<div id="license"></div>
