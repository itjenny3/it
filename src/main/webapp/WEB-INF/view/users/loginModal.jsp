<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<%-- Login Modal --%>
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
	<a id="play" class="menu"><i class="icon-play"></i></a>

	<div class="btn-group">
		<button class="btn dropdown-toggle" data-toggle="dropdown">
			<i class="icon-cog"></i></span>
		</button>
		<ul class="dropdown-menu pull-right">
			<li>
				<div class="make-switch switch-small">
					<input type="checkbox" checked>
				</div>
			</li>
			<li>Pagination <a id="settingPagination">${setting.pagination}</a></li>
			<li>Oneline <a id="settingOneline">${setting.oneline}</a></li>
			<li>Fontsize <a id="settingFontsize">${setting.fontsize}</a></li>
		</ul>
	</div>
	<!-- 	<a id="setting" class="menu"><i class="icon-cog"></i></a> -->

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

<sec:authorize access="hasRole('ROLE_USER')">
	<script>
		setCookie("pagination", ${setting.pagination});
		setCookie("oneline", ${setting.oneline});
		setCookie("fontsize", ${setting.fontsize});
	</script>
</sec:authorize>

<script>
	$('#play').click(function() {
		$('#word').hide();
		$('#keynote').show();
		moveCurrentSection();
	});

	$('#stop').click(function() {
		$('#word').show();
		$('#keynote').hide();
	});

	$('#settingPagination').click(function() {
		$('.pagination').toggle();
	});
	
	function sendSetting(option, value) {
		$.ajax({
			type : "POST",
			url : "/setting/" + option + "/" + value,
			beforeSend : function() {
			}
		});
	}

	$('#settingPagination').click(function() {
		if ($('#settingPagination').text() == "true") {
			$('#settingPagination').text("false");
			setCookie('pagination', false);
			sendSetting('pagination', 0);
		} else {
			$('#settingPagination').text("true");
			setCookie('pagination', true);
			sendSetting('pagination', 1);
		}
	});

	$('#settingOneline').click(function() {
		if ($('#settingOneline').text() == "true") {
			$('#settingOneline').text("false");
			setCookie('oneline', false);
			sendSetting('oneline', 0);
		} else {
			$('#settingOneline').text('true');
			setCookie('oneline', true);
			sendSetting('oneline', 1);
		}
	});
	
	$('#settingPagination').text(getCookie('pagination'));
	$('#settingOneline').text(getCookie('oneline'));
	$('#settingFontsize').text(getCookie('fontsize'));
	
	$(document).ready(function() {
		if (getCookie('pagination') == 'false') {
			$('.pagination').hide();
		}
		
		var fontsize = getCookie('fontsize');
		if (0 < fontsize && fontsize < 6) {
			$('h1,h2,h3,h4,h5,h6,p,code,li').css({
				'font-size' : '+=' + (10 * (parseInt(getCookie('fontsize')) - 3))
			});
		}
	});
</script>