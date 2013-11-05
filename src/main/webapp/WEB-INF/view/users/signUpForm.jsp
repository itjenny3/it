<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<head>
<title>로그인 :: itjenny</title>
</head>

<section class="login-content">
	<div class="content-main">
		<p>welcome</p>
		<form:form action="" method="post" cssClass="sign-in-with-sns"
			modelAttribute="signUpForm">
			<p>nickname</p>
			<form:input path="userId" cssClass="inp_nickname focused" />
			<br />
			<form:errors path="*" />
			<button class="btn btn-primary" type="submit">회원가입</button>
		</form:form>
	</div>
	<div class="content-sub"></div>
	</sction>