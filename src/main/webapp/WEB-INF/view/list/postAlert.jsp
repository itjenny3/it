<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<c:choose>
	<c:when test="${isSuccess == true}">
		<div id="alert" class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">x</button>${alertMessage}
		</div>
	</c:when>
	<c:otherwise>
		<div id="alert" class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">x</button>${alertMessage}
		</div>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	$("#sharing${document.documentId}").text("${document.sharing}");
</script>