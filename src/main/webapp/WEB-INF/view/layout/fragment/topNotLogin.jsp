<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(document).ready(function() {
        $('.dropdown-toggle').dropdown();
    });

    function facebookLogin() {
        $('#facebookForm').submit();
    }
</script>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse"
                data-target=".nav-collapse"> <span class="icon-bar"></span>
                <span class="icon-bar"></span> <span class="icon-bar"></span>
            </a> <a class="brand" href="#">Project</a>
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="#">List</a></li>
                    <li><a href="#">Member</a></li>
                    <li><a href="#">Friend</a></li>
                    <li><a href="#">Market</a></li>
                    <li><form:form method="get" action="/search"
                            class="navbar-search pull-left">
                            <input type="text" class="search-query span2"
                                name="q" placeholder="Search">
                        </form:form></li>
                </ul>
                <ul class="nav pull-right">
                    <li id="fat-menu" class="dropdown"><a href="#"
                        id="drop1" role="button" class="dropdown-toggle"
                        data-toggle="dropdown"> Login <b class="caret"></b>
                    </a>
                        <ul class="dropdown-menu" role="menu"
                            aria-labelledby="drop1">
                            <li><a tabindex="-1"
                                href="javascript:facebookLogin()">Facebook</a>
                                <div style="display: none">
                                    <form id="facebookForm"
                                        action="<c:url value="/signin/facebook" />"
                                        method="post">
                                        <input type="hidden" name="scope"
                                            value="email,publish_stream,offline_access" />
                                    </form>
                                </div></li>
                            <li><a tabindex="-1" href="#">Twitter</a></li>
                        </ul></li>
                </ul>
            </div>
        </div>
    </div>
</div>

