<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<%-- document list --%>
<c:forEach var="document" items="${documents}">
	<%@include file="document.jsp"%>
</c:forEach>

<%-- waiting icon --%>
<c:if test="${more}">
	<tr id="moreDocument"><td>
		<img src="img/wait24trans.gif" />
		<span id="oldest" style="display: none;">${documentId}</span>
	</td></tr>
</c:if>