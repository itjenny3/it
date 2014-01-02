<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<form:form modelAttribute="article" action="/form/new" method="POST"
    class="form-horizontal" role="form">
    <form:input type="text" class="form-control" path="title"
        placeholder="title" />
    <form:textarea class="form-control" rows="3" path="content" />
    <button type="submit" class="btn btn-default">Submit</button>
</form:form>