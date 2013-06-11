<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="container">
	<div>
		<table class="table table-striped">
			<tbody>
				<%-- friend list --%>
				<c:choose>
					<c:when test="${empty friends}">
						<tr>
							<td>
								<spring:message code="friend.noFriends" />
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="friend" items="${friends}">
							<tr>
								<td>
									<c:choose>
										<c:when test="${friend.isTemplateMember}">
											<i class="icon-ok"></i>
										</c:when>
										<c:otherwise>
											<i class="icon-remove"></i>
										</c:otherwise>
									</c:choose>
								</td>
								</td>
								<td>
									<img class="img-polaroid" src="http://graph.facebook.com/${friend.providerUserId}/picture" />
										${friend.name}
									<br /><br />
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>