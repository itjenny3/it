<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<div class="progress">
    <div class="progress-bar" role="progressbar"
        aria-valuenow="${section.index * 100 / totalSection}" aria-valuemin="0"
        aria-valuemax="100" style="width: ${section.index * 100 / totalSection}%;">
        <span class="sr-only">Complete</span>
    </div>
</div>