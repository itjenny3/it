<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<%@ include file="/WEB-INF/view/users/loginModal.jsp"%>

<script>
$(function() {
    var availableTags = [
        <c:forEach var="tag" items="${tags}">
            "${tag}",
        </c:forEach>
    ];
    function split(val) {
        return val.split(/,\s*/);
    }
    function extractLast(term) {
        return split(term).pop();
	}
   
    $("#search")
    // don't navigate away from the field on tab when selecting an item
    .bind("keydown", function(event) {
        if (event.keyCode === $.ui.keyCode.TAB && $(this).data("ui-autocomplete").menu.active) {
           event.preventDefault();
        } else if (event.keyCode === $.ui.keyCode.ENTER && !$(this).data("ui-autocomplete").menu.active) {
            var tags = this.value.replace(/[\s]+|[,\s]+$/g, '');
/*             var tags = this.value.replace(' ', '');
        	tags = tags.replace(/,+$/, ''); */
        	window.location = "./tag?tags=" + tags;
        }
    })
    .autocomplete({
        minLength: 0,
        source: function(request, response) {
            // delegate back to autocomplete, but extract the last term
            response($.ui.autocomplete.filter(availableTags, extractLast(request.term)));
        },
        focus: function() {
            // prevent value inserted on focus
            return false;
        },
        select: function(event, ui) {
            var terms = split(this.value);
            // remove the current input
            terms.pop();
            // add the selected item
            terms.push(ui.item.value);
            // add placeholder to get the comma-and-space at the end
            terms.push("");
            this.value = terms.join(", ");
            return false;
        }
    });
});
</script>

<div class="ui-widget">
    <label for="search">Search: </label> <input id="search">
</div>

<div class="btn-group" data-toggle="buttons-checked">
    <c:forEach var="tag" items="${tags}">
        <button type="button" id="tag${tag}" class="btn btn-primary">${tag}</button>
    </c:forEach>
</div>

<script>
    <c:forEach var="tag" items="${tags}">
    $('#tag${tag}').click(function() {
        // TODO :: find article with selected tag.
    });
    </c:forEach>
</script>

<div id="selectedArticles" class="backgroundTitle">
    <c:forEach var="article" items="${articles}">
        <a href="/article/${article.title}"><h1>${article.title}</h1></a>
    </c:forEach>
</div>