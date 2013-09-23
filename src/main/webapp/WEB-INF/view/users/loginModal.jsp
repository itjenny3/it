<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<%-- Modal --%>
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


<%-- Top Menu --%>
<div id="word" class="top_fixed">
	<a id="play" class="menu"><i class="icon-play"></i></a><a id="setting" class="menu"><i class="icon-cog"></i></a>

	<%-- Login --%>
	<sec:authorize access="!hasRole('ROLE_USER')">
		<a data-target="#myModal" role="button" id="login" class="menu btn" data-toggle="modal"><i class="icon-off"></i></a>
	</sec:authorize>

	<%-- Logout --%>
	<sec:authorize access="hasRole('ROLE_USER')">
		${loginUser.userId}
		<a href="/users/logout" class="menu" title="logout"><i class="icon-off"></i></a>
	</sec:authorize>
</div>

<div id="keynote" class="top_fixed" style="display: none">
	<a id="stop" class="menu"><i class="icon-pause"></i></a>
</div>

<script>
	$('#play').click(function() {
		$('#word').hide();
		$('#keynote').show();
	});
	
	$('#stop').click(function() {
		$('#word').show();
		$('#keynote').hide();
	});
</script>