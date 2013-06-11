<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="container">
	<div>
		<table class="table table-striped">
			<tbody>
				<c:choose>
					<c:when test="${empty items}">
						<tr>
							<td><spring:message code="market.noItem" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<form:form method="post" action="${url}/buy" modelAttribute="checkbox">
							<c:forEach var="item" items="${items}" varStatus="i">
								<tr>
									<td><form:checkbox path="checkboxs[${i.index}]" value="false" /></td>
									<td>${item.name}</td>
									<td>${item.price}</td>
									<td>${item.desc}</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4">
									<button type="submit" id="buyItem" class="btn btn-info" onclick="buyItem()">Buy</button>
								</td>
							</tr>
						</form:form>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>