<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<%-- myDocId를 string으로 설정함. --%>
<c:set var="myDocId">${myDoc.myDocId}</c:set>

<tr id="myDoc${myDocId}">
	<td>
		<%-- Content --%>
		<c:choose>
			<c:when test="${providerUserId == myDoc.providerUserId}">
				<div id="divContent${myDocId}" style="cursor: pointer;" onclick="clickMyDoc(${myDocId})">
					<p id="content${myDocId}">${myDoc.content}</p>
				</div>
				
				<div id="editContent${myDocId}" style="display: none;">
					<textarea path="rawContent" id="rawContent${myDocId}" class="span12" rows="1">${myDoc.rawContent}</textarea>
					<button type="submit" id="updateRawContent${myDocId}" class="btn btn-info" onclick="updateMyDoc(${myDocId})">Update</button>
					<a href="#deleteModal${myDocId}" role="button" id="deleteRawContent${myDocId}" class="btn btn-danger" data-toggle="modal">Delete</a><br /><br />
					
					<%-- Modal (Delete) --%>
					<div id="deleteModal${myDocId}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h3 id="myModalLabel">DELETE</h3>
						</div>
						<div class="modal-body">
							<p>Do you want to delete?</p>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
							<button type="submit" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="deleteMyDoc(${myDocId})">Yes</button>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div id="content${myDocId}">
					${myDoc.content}
				</div>
			</c:otherwise>
		</c:choose>
		
		<div>
			<%-- POST --%>
			<c:if test="${not empty providerUserId}">
				<%-- icon --%>
				<a href="#postModal${myDocId}" data-toggle="modal">
					<i class="icon-retweet"></i>&nbsp&nbsp
				</a>
				<%-- Modal (Post) --%>
				<div id="postModal${myDocId}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">POST</h3>
					</div>
					<div class="modal-body">
						<p>Do you want to post?</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">No</button>
						<button type="submit" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="post(${myDocId})">Yes</button>
					</div>
				</div>
			</c:if>
		</div>
	</td>
</tr>