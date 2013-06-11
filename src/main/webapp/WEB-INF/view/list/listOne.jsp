<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="container">
	<%-- alert --%>
	<div id="alertPosition">
		<div id="alert"></div>
	</div>
	
	<div>
		<table class="table table-striped">
			<tbody>
				<%-- waiting icon --%>
				<tr id="waitingDocument" style="display: none;"><td>
					<img src="/img/wait24trans.gif" />
				</td></tr>
		
				<%-- document one --%>
				<%@include file="document.jsp"%>
			</tbody>
		</table>
	</div>
</div>