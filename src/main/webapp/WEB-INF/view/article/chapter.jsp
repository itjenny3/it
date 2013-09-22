<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<c:forEach var="section" items="${chapter.sections}">
	<div class="section lastChapter ${section.css}">
		<h1>${section.subtitle}</h1>
		${section.content}
		
		<%-- pagination --%>
		<p class="pagination">${section.index} / ${totalSection}</p>
	</div>
</c:forEach>

<c:if test="${not empty chapter.quiz}">
	<div class="section ${chapter.quiz.css}">
		<h1>${chapter.quiz.subtitle}</h1>
		${chapter.quiz.content} <input id="answer" type="text" onKeyDown="sendAnswer('${chapter.id}')">
		
		<%-- pagination --%>
		<p class="pagination">${chapter.quiz.index} / ${totalSection}</p>
	</div>
</c:if>