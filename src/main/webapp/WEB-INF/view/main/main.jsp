<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div id="container" class="container">
	<%-- alert --%>
	<div id="alertPosition">
		<div id="alert"></div>
	</div>
	
	<%-- new document form --%>
	<div class="row">
		<div class="span12">
			<input id="newMyDocTitle" type="text" class="span12" path="title" placeholder="title" /> &nbsp
			<textarea id="newMyDoc" class="span12" rows="1" placeholder="contents"></textarea>
			<button type="submit" id="saveRawContent" class="btn btn-info" onclick="saveMyDoc()">Save</button>
			<br /><br />
		</div>
	</div>

	<div>
		<c:choose>
			<c:when test="${empty myDocs}">
				<span id="newest" style="display: none;">0</span>
			</c:when>
			<c:otherwise>
				<span id="newest" style="display: none;">${myDocs[0].myDocId}</span>
			</c:otherwise>
		</c:choose>

		<table class="table table-striped">
			<tbody>
				<%-- waiting icon --%>
				<tr id="waitingMyDoc" style="display: none;"><td>
					<img src="img/wait24trans.gif" />
				</td></tr>
		
				<%-- document list --%>
				<%@include file="myDocs.jsp" %>
			</tbody>
		</table>
	</div>
</div>