<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<p id="previousAnswer" style="display: none">${answer}</p>

<c:forEach var="section" items="${chapter.sections}">
	<div class="section lastChapter background">
		<h1>${section.subtitle}</h1>
		${section.content}

		<%-- pagination --%>
		<p class="page">${section.index}/ ${totalSection}</p>
	</div>
</c:forEach>

<c:if test="${not empty chapter.quiz}">
	<div class="section background">
		<h1>${chapter.quiz.subtitle}</h1>
		${chapter.quiz.content} <input id="answer" type="text"
			onKeyDown="sendAnswer('${chapter.id}')">

		<%-- pagination --%>
		<p class="page">${chapter.quiz.index}/ ${totalSection}</p>
	</div>
</c:if>