<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<blockquote id="commentBox${comment.commentId}">
	<p>${comment.content}
		<c:if test="${providerUserId eq comment.providerUserId}">
			<a href="#deleteCommentModal${comment.commentId}" class="pull-right" data-toggle="modal"><i class="icon-remove"></i></a>
		</c:if>
	</p>
	<small>${comment.name}</small>
	
	<%-- Modal --%>
	<div class="modal hide fade" id="deleteCommentModal${comment.commentId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">DELETE</h3>
		</div>
		<div class="modal-body">
			<p>Do you want to delete?</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
			<button type="submit" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="deleteComment(${comment.documentId}, ${comment.commentId})">Yes</button>
		</div>
	</div>
</blockquote>