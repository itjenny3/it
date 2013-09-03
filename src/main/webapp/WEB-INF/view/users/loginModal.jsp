<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!-- Modal -->
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-remote="/users/login">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">Login</h3>
	</div>
	<div class="modal-body"></div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
		<button class="btn btn-primary">Login</button>
	</div>
</div>
<sec:authorize access="!hasRole('ROLE_USER')">
	<a data-target="#myModal" role="button" id="login" class="btn" data-toggle="modal">Login</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
	${loginUser.userId}
	<div class=backgroundTitle>
		<a href="/users/logout" title="logout">Logout</a>
	</div>
</sec:authorize>
