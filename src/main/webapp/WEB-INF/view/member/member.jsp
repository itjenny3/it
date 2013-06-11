<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="container">
	<div class="row-fluid">
		<div class="thumbnail">
			<form:form method="post" action="${url}" modelAttribute="member">
				<div style="text-align: center;">
					<img src="http://graph.facebook.com/${member.providerUserId}/picture?type=large" />
				</div>
				
				<br />
				
				<table class="table table-striped">
					<thead>
						<tr><th colspan=2 style="text-align: center; "><spring:message code="member.information" /></th></tr>
					</thead>
					<tbody>
						<tr>
							<td><spring:message code="member.name" /></td>
							<td>
								<form:input type="text" path="name" value="${member.name}" /> &nbsp
								<button type="submit" class="btn btn-info" style="text-align: center;">Save</button>
							</td>
						</tr>
						<tr>
							<td><spring:message code="member.email" /></td>
							<td>${member.email}</td>
						</tr>
						<tr>
							<td><spring:message code="member.gender" /></td>
							<td>
								<c:choose>
									<c:when test="${member.gender}">
										<spring:message code="member.male" />
									</c:when>
									<c:otherwise>
										<spring:message code="member.female" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td><spring:message code="member.locale" /></td>
							<td>${member.locale}</td>
						</tr>
					</tbody>
				</table>
			</form:form>
				
			<table class="table table-striped">
				<thead>
					<tr><th colspan=2 style="text-align: center; "><spring:message code="member.item" /></th></tr>
				</thead>
				<tbody>
					<tr>
						<td><spring:message code="member.itemList" /></td>
						<td>
							<c:choose>
								<c:when test="${empty member.items}">
									<spring:message code="member.noItem" />
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${member.items}">
										${item} &nbsp
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table table-striped">
				<thead>
					<tr><th colspan=2 style="text-align: center; "><spring:message code="member.mission" /></th></tr>
				</thead>
				<tbody>
					<tr>
						<td><spring:message code="member.missionList" /></td>
						<td>
							<c:choose>
								<c:when test="${empty member.missions}">
									<spring:message code="member.noMission" />
								</c:when>
								<c:otherwise>
									<c:forEach var="mission" items="${member.missions}">
										${mission} &nbsp
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table table-striped">
				<thead>
					<tr><th colspan=2 style="text-align: center; "><spring:message code="member.titleChance" /></th></tr>
				</thead>
				<tbody>
					<tr>
						<td><spring:message code="member.chanceToDoc" /></td>
						<td>${member.chanceToDoc}</td>
					</tr>
					<tr>
						<td><spring:message code="member.chanceToComment" /></td>
						<td>${member.chanceToComment}</td>
					</tr>
					<tr>
						<td><spring:message code="member.chanceToLike" /></td>
						<td>${member.chanceToLike}</td>
					</tr>
					<tr>
						<td><spring:message code="member.chanceToDislike" /></td>
						<td>${member.chanceToDislike}</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table table-striped">
				<thead>
					<tr><th colspan=2 style="text-align: center; "><spring:message code="member.titleUsage" /></th></tr>
				</thead>
				<tbody>
					<tr>
						<td><spring:message code="member.usageOfDoc" /></td>
						<td>${member.usageOfDoc}</td>
					</tr>
					<tr>
						<td><spring:message code="member.usageOfComment" /></td>
						<td>${member.usageOfComment}</td>
					</tr>
					<tr>
						<td><spring:message code="member.usageOfLike" /></td>
						<td>${member.usageOfLike}</td>
					</tr>
					<tr>
						<td><spring:message code="member.usageOfDislike" /></td>
						<td>${member.usageOfDislike}</td>
					</tr>
				</tbody>
			</table>
			
			<table class="table table-striped">
				<thead>
					<tr><th colspan=2 style="text-align: center; "><spring:message code="member.titlePoint" /></th></tr>
				</thead>
				<tbody>
					<tr>
						<td><spring:message code="member.point" /></td>
						<td>${member.point}</td>
					</tr>
					<tr>
						<td><spring:message code="member.doc" /></td>
						<td>${member.doc}</td>
					</tr>
					<tr>
						<td><spring:message code="member.comment" /></td>
						<td>${member.comment}</td>
					</tr>
					<tr>
						<td><spring:message code="member.commentOnMyDoc" /></td>
						<td>${member.commentOnMyDoc}</td>
					</tr>
					<tr>
						<td><spring:message code="member.like" /></td>
						<td>${member.like}</td>
					</tr>
					<tr>
						<td><spring:message code="member.likeOnMyDoc" /></td>
						<td>${member.likeOnMyDoc}</td>
					</tr>
					<tr>
						<td><spring:message code="member.dislike" /></td>
						<td>${member.dislike}</td>
					</tr>
					<tr>
						<td><spring:message code="member.dislikeOnMyDoc" /></td>
						<td>${member.dislikeOnMyDoc}</td>
					</tr>
					<tr>
						<td><spring:message code="member.sharing" /></td>
						<td>${member.sharing}</td>
					</tr>
					<tr>
						<td><spring:message code="member.sharingOfMyDoc" /></td>
						<td>${member.sharingOfMyDoc}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

