<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<%-- documentId를 string으로 설정함. comment에서 Map key로 integer 사용 안 됨. --%>
<c:set var="documentId">${document.documentId}</c:set>

<tr id="document${documentId}">
	<td>
		<%-- Content --%>
		<c:choose>
			<c:when test="${providerUserId == document.providerUserId}">
				<div id="divContent${documentId}" style="cursor: pointer;" onclick="clickMyDocument(${documentId})">
					<p id="content${documentId}">${document.content}</p>
					<p class="muted"><small>— ${document.name}</small></p>
				</div>
				
				<div id="editContent${documentId}" style="display: none;">
					<textarea path="rawContent" id="rawContent${documentId}" class="span12" rows="1">${document.rawContent}</textarea>
					<button type="submit" id="updateRawContent${documentId}" class="btn btn-info" onclick="updateDocument(${documentId})">Update</button>
					<a href="#deleteModal${documentId}" role="button" id="deleteRawContent${documentId}" class="btn btn-danger" data-toggle="modal">Delete</a><br /><br />
					
					<%-- Modal (Delete) --%>
					<div id="deleteModal${documentId}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">DELETE</h3>
						</div>
						<div class="modal-body">
							<p>Do you want to delete?</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
							<button type="submit" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="deleteDocument(${documentId})">Yes</button>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div id="content${documentId}">
					${document.content}
				</div>
				<p class="muted"><small>— ${document.name}</small></p>
			</c:otherwise>
		</c:choose>
		
		<%-- LIKE, DISLIKE --%>
		<div>
			<c:choose>
				<c:when test="${providerUserId == document.providerUserId}">
					<i class="icon-thumbs-up"></i><span id="like${documentId}">${document.like}</span>&nbsp&nbsp
					<i class="icon-thumbs-down"></i><span id="dislike${documentId}">${document.dislike}</span>&nbsp&nbsp
				</c:when>
				<c:otherwise>
					<a href="#" onclick="like(${documentId})"><i class="icon-thumbs-up"></i><span id="like${documentId}">${document.like}</span></a>&nbsp
					<c:choose>
						<c:when test="${document.hasMyLike}">
							<a href="#" id="cancelLike${documentId}" onclick="cancelLike(${documentId})"><spring:message code="list.cancel" /></a>
						</c:when>
						<c:otherwise>
							<a href="#" id="cancelLike${documentId}" onclick="cancelLike(${documentId})" style="display:none;"><spring:message code="list.cancel" /></a>
						</c:otherwise>
					</c:choose>
					&nbsp
					<a href="#" onclick="dislike(${documentId})"><i class="icon-thumbs-down"></i><span id="dislike${documentId}">${document.dislike}</span></a>&nbsp
					<c:choose>
						<c:when test="${document.hasMyDislike}">
							<a href="#" id="cancelDislike${documentId}" onclick="cancelDislike(${documentId})"><spring:message code="list.cancel" /></a>
						</c:when>
						<c:otherwise>
							<a href="#" id="cancelDislike${documentId}" onclick="cancelDislike(${documentId})" style="display:none;"><spring:message code="list.cancel" /></a>
						</c:otherwise>
					</c:choose>
					&nbsp
				</c:otherwise>
			</c:choose>
			<i class="icon-comment"></i><span id="comment${documentId}">${document.comment}</span>&nbsp&nbsp
		
			<%-- POST --%>
			<c:if test="${not empty providerUserId}">
				<%-- icon --%>
				<a href="#postModal${documentId}" data-toggle="modal">
					<i class="icon-retweet"></i><span id="sharing${documentId}">${document.sharing}</span>&nbsp&nbsp
				</a>
				<%-- Modal (Post) --%>
				<div id="postModal${documentId}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">POST</h3>
					</div>
					<div class="modal-body">
						<p>Do you want to post?</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
						<button type="submit" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="post(${documentId})">Yes</button>
					</div>
				</div>
			</c:if>
		</div>
		
		<br />
		
		<%-- COMMENT LIST --%>
		<c:forEach var="comment" items="${comments[documentId]}">
			<%@include file="comment.jsp"%>
		</c:forEach>

		<%-- new comment after sending comment --%>
		<blockquote id="lastCommentPosition${documentId}" style="display:none;"></blockquote>
		
		<%-- waiting icon --%>
		<blockquote><p><img src="/img/wait24trans.gif" id="waitingComment${documentId}" style="display:none;" /></p></blockquote>
		
		<%-- COMMENT FORM --%>
		<c:if test="${not empty providerUserId}">
			<textarea id="newComment${documentId}" class="span12" onclick="clickCommentForm(${documentId})" rows="1" placeholder="comment"></textarea>
			<br />
			<button type="submit" id="saveComment${documentId}" class="btn btn-info" onclick="saveComment(${documentId})" style="display:none;">Save</button>
			<br /><br />
		</c:if>
		
		<script type="text/javascript">
			$(document).ready(function () {
				$("#newComment${documentId}").keydown(function(event) {
					if (event.which == 13) {
						var row = parseInt($("#newComment${documentId}").attr("rows"));
						$("#newComment${documentId}").attr("rows", row + 1);
					}
				});
			})
		</script>
	</td>
</tr>