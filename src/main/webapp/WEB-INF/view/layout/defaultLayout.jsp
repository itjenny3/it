<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<title id="browserTitleArea"><tiles:insertAttribute name="title" ignore="true" defaultValue="메뉴 :: 브랜드명 서비스" /></title>

<tiles:insertAttribute name="header" />
<script type="text/javascript" src="/js/jquery-1.8.0.js?20120904"></script>
</head>

<body>
	<tiles:insertAttribute name="top" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
	<tiles:useAttribute id="lazyloadingScripts" name="lazyloadingScripts" classname="java.util.List" />
	<c:forEach var="script" items="${lazyloadingScripts}">
		<script type="text/javascript" src="<c:url value='${script}'/>"></script>
	</c:forEach>
</body>
</html>