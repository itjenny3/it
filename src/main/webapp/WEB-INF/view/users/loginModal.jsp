<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<%-- Login Modal --%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
    aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/users/login"></div>

<%-- Top Menu --%>
<div id="word" class="top_fixed">
    <a id="play" class="menu"><span class="glyphicon glyphicon-play"></span></a>

    <div class="btn-group">
        <button class="btn dropdown-toggle" data-toggle="dropdown">
            <span class="glyphicon glyphicon-cog"></span>
        </button>
        <ul class="dropdown-menu pull-right">
            <li>
                <div class="make-switch switch-small">
                    <input type="checkbox" checked>
                </div>
            </li>
            <li>Pagination <a id="settingPagination">${setting.pagination}</a></li>
            <li>Oneline <a id="settingOneline">${setting.oneline}</a></li>
        </ul>
    </div>

    <%-- Login --%>
    <sec:authorize access="!hasRole('ROLE_USER')">
        <a data-target="#myModal" role="button" id="login" class="menu btn"
            data-toggle="modal"><span class="glyphicon glyphicon-off"></span></a>
    </sec:authorize>

    <%-- Logout --%>
    <sec:authorize access="hasRole('ROLE_USER')">
		${loginUser.userId}
		<a href="/users/logout" class="menu" title="logout"><span
            class="glyphicon glyphicon-off"></span></a>
    </sec:authorize>
</div>

<div id="keynote" class="top_fixed" style="display: none">
    <a id="stop" class="menu"><span class="glyphicon glyphicon-stop"></i></a>
</div>

<sec:authorize access="hasRole('ROLE_USER')">
    <script>
		setCookie('pagination', ${setting.pagination});
		setCookie('oneline', ${setting.oneline});
	</script>
</sec:authorize>

<script>
	$('#play').click(function() {
		$('#word').hide();
		$('#keynote').show();
		$('#container').attr('class', 'keynote');
		setCookie('mode', 'keynote');
		moveCurrentSection();
	});

	$('#stop').click(function() {
		$('#word').show();
		$('#keynote').hide();
		$('#container').attr('class', 'word');
		setCookie('mode', 'word');
	});
	
	$('#settingPagination').click(function() {
	    if (getCookie('mode') == 'keynote') {
    		$('.page').toggle();
	    }
	});
	
	function sendSetting(option, value) {
		$.ajax({
			type : "POST",
			url : '/setting/' + option + '/' + value,
			beforeSend : function() {
			}
		});
	}

	$('#settingPagination').click(function() {
		if ($('#settingPagination').text() == 'true') {
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
		if ($('#settingOneline').text() == 'true') {
			$('#settingOneline').text("false");
			setCookie('oneline', false);
			sendSetting('oneline', 0);
		} else {
			$('#settingOneline').text('true');
			setCookie('oneline', true);
			sendSetting('oneline', 1);
		}
	});
	
	if (getCookie('pagination') == null) {
		$('#settingPagination').text(getCookie('pagination'));
		$('#settingOneline').text(getCookie('oneline'));
	}
	
	$(document).ready(function() {
	    if (getCookie('mode') == 'keynote') {
    		if (getCookie('pagination') == 'false') {
    			$('.page').hide();
    		}
    		if (getCookie('mode') == 'keynote') {
    			$('#play').click();
    		}
	    } else {
			$('.page').hide();
	    }
	});
</script>