<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="/">Project</a>

			<div class="nav-collapse">
				<ul class="nav">
					<c:choose>
						<c:when test="${url eq 'list'}">
							<li class="active"><a href="/list">List</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/list">List</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${url eq 'member'}">
							<li class="active"><a href="/member">Member</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/member">Member</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${url eq 'friend'}">
							<li class="active"><a href="/friend">Friend</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/friend">Friend</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${url eq 'market'}">
							<li class="active"><a href="/market">Market</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/market">Market</a></li>
						</c:otherwise>
					</c:choose>
					<li><form:form method="get" action="/search"
							class="navbar-search pull-left">
							<input type="text" class="search-query span2" name="q"
								placeholder="Search">
						</form:form></li>
				</ul>
				<ul class="nav pull-right">
					<li><a href="<c:url value="/signout" />" class="navbar-link">Sign
							Out</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>