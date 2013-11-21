<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<title id="browserTitleArea"><tiles:insertAttribute name="title"
        ignore="true" defaultValue="itJenny" /></title>
<tiles:insertAttribute name="header" />
</head>

<body>
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
</body>
</html>