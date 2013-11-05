<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Login</h3>
		</div>
		<div class="modal-body">
			<div class="span4">
				<p>ITJENNY</p>
				<form id="authentication" action="/users/authenticate" method="POST">
					<input id="authenticationId" name="authenticationId" class="span3"
						placeholder="이메일" type="email" required value="" /> <input
						id="authenticationPassword" name="authenticationPassword"
						class="span3" placeholder="비밀번호" type="password" value="" />
					<c:if test="${not empty param.login_error}">
						<label class="error" style="">E-mail or password isn't
							corect.</label>
					</c:if>
					<p>
						<label><input name="_spring_security_remember_me"
							type="checkbox" value="true" />Remember</label>
						<button id="loginSubmitBtn" type="submit" class="btn btn-primary">Login</button>
					</p>
				</form>
			</div>
			<div class="span4">
				<p>SNS</p>
				<form action="/signin/facebook" method="POST">
					<input type="hidden" name="scope" value="publish_stream" />
					<button type="submit" class="btn btn-primary">Facebook</button>
				</form>
				<form action="/signin/twitter" method="POST">
					<button type="submit" class="btn btn-primary">Twitter</button>
				</form>
				<form
					action="/signin/google?scope=https://www.googleapis.com/auth/userinfo.profile"
					method="POST">
					<button type="submit" class="btn btn-primary">Google</button>
				</form>
			</div>
			<div class="span4">
				<p>Sign Up</p>
				<form:form modelAttribute="user" cssClass="form-write"
					action="/users" method="post">
					<form:input path="email" class="inp_email" type="email"
						placeholder="Email" />
					<form:input path="userId" class="inp_pw" placeholder="Nickname" />
					<p>- Send temporary password your e-mail.</p>
					<button type="submit" class="btn btn-primary">Signup</button>
				</form:form>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
			<button class="btn btn-primary">Login</button>
		</div>
	</div>
</div>