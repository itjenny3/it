function computeMyDocIdFromHtml(content) {
	var regexp = /id=\"myDoc([0-9]+)/;
	var documentId = regexp.exec(content);
	return documentId[1];
}

function saveMyDoc() {
	if ($("#newMyDoc").val() === "") {
		return;
	}
	
	$.ajax({
		type : "POST",
		url : "/myDoc",
		dataType: "text",
		data: {"myDocId": $("#newest").text(), "title": $("#newMyDocTitle").val(), "rawContent": $("#newMyDoc").val()},
		beforeSend: function() {
			$("#saveRawContent").attr("onclick", "");
			$("#waitingMyDoc").css("display", "");
		},
		success : function(content) {
			$("#saveRawContent").attr("onclick", "saveMyDoc()");
			$("#waitingMyDoc").css("display", "none");
			$("#newMyDoc").attr("rows", 1).val('');
			if (content !== "") {
				$("#newest").text(computeMyDocIdFromHtml(content));
				$("#waitingMyDoc").after(content);
			}
		}
	});
}

function enableCloseEditContentOfMyDoc(myDocId) {
	$(document).bind("click", function(event) {
		var id = event.target.id;
		if(id != "rawContent" + myDocId && id != "updateRawContent" + myDocId && id != "deleteRawContent" + myDocId) {
			$("#divContent" + myDocId).css("display", "");
			$("#editContent" + myDocId).css("display", "none");
			$(document).unbind("click");
		}
	});
}

function clickMyDoc(myDocId) {
	var countLF = $("#rawContent" + myDocId).val().split(/[\n]/g).length;
	$("#rawContent" + myDocId).attr("rows", countLF);
	$("#divContent" + myDocId).css("display", "none");
	$("#editContent" + myDocId).css("display", "");
	$("#rawContent" + myDocId).focus();
	var timer = setInterval(function() {
		enableCloseEditContentOfMyDoc(myDocId);
		clearInterval(timer);
	}, 100);
}

function updateMyDoc(myDocId) {
	$.ajax({
		type : "POST",
		url : "/myDoc",
		dataType: "text",
		data: {"myDocId" : myDocId, "rawContent": $("#rawContent" + myDocId).val(), _method: "PUT"},
		beforeSend: function() {
		 	$("#updateRawContent" + myDocId).html("Updating...");
		},
		success : function(content) {
		 	$("#updateRawContent" + myDocId).html("Update");
		  	$("#editContent" + myDocId).css("display", "none");
			$("#divContent" + myDocId).css("display", "");
			$("#content" + myDocId).html(content);
			$(document).unbind("click");
		}
	});
}	

function deleteMyDoc(myDocId) {
	$.ajax({
		type : "POST",
		url : "/myDoc",
		dataType: "text",
		data: {"myDocId": myDocId, _method: "DELETE"},
		success : function() {
			$("#myDoc" + myDocId).remove();
			$(document).unbind("click");
		}
	});
}

function computeDocumentIdFromHtml(content) {
	var regexp = /id=\"document([0-9]+)/;
	var documentId = regexp.exec(content);
	return documentId[1];
}

function saveDocument() {
	if ($("#newDocument").val() === "") {
		return;
	}
	
	$.ajax({
		type : "POST",
		url : "/document",
		dataType: "text",
		data: {"documentId": $("#newest").text(), "rawContent": $("#newDocument").val()},
		beforeSend: function() {
			$("#saveRawContent").attr("onclick", "");
			$("#waitingDocument").css("display", "");
		},
		success : function(content) {
			$("#saveRawContent").attr("onclick", "saveDocument()");
			$("#waitingDocument").css("display", "none");
			$("#newDocument").attr("rows", 1).val('');
			if (content !== "") {
				$("#newest").text(computeDocumentIdFromHtml(content));
				$("#waitingDocument").after(content);
			}
		}
	});
}

function enableCloseEditContent(documentId) {
	$(document).bind("click", function(event) {
		var id = event.target.id;
		if(id != "rawContent" + documentId && id != "updateRawContent" + documentId && id != "deleteRawContent" + documentId) {
			$("#divContent" + documentId).css("display", "");
			$("#editContent" + documentId).css("display", "none");
			$(document).unbind("click");
		}
	});
}

function clickMyDocument(documentId) {
	var countLF = $("#rawContent" + documentId).val().split(/[\n]/g).length;
	$("#rawContent" + documentId).attr("rows", countLF);
	$("#divContent" + documentId).css("display", "none");
	$("#editContent" + documentId).css("display", "");
	$("#rawContent" + documentId).focus();
	var timer = setInterval(function() {
		enableCloseEditContent(documentId);
		clearInterval(timer);
	}, 100);
}

function updateDocument(documentId) {
	$.ajax({
		type : "POST",
		url : "/document",
		dataType: "text",
		data: {"documentId" : documentId, "rawContent": $("#rawContent" + documentId).val(), _method: "PUT"},
		beforeSend: function() {
		 	$("#updateRawContent" + documentId).html("Updating...");
		},
		success : function(content) {
		 	$("#updateRawContent" + documentId).html("Update");
		  	$("#editContent" + documentId).css("display", "none");
			$("#divContent" + documentId).css("display", "");
			$("#content" + documentId).html(content);
			$(document).unbind("click");
		}
	});
}	

function deleteDocument(documentId) {
	$.ajax({
		type : "POST",
		url : "/document",
		dataType: "text",
		data: {"documentId": documentId, _method: "DELETE"},
		success : function() {
			$("#document" + documentId).remove();
			$(document).unbind("click");
		}
	});
}

function saveComment(documentId) {
	var comment = parseInt($("#comment" + documentId).text());
	$("#comment" + documentId).text(comment + 1);
	$.ajax({
		type : "POST",
		url : "/comment",
		dataType: "text",
		data: {"documentId": documentId, "content": $("#newComment" + documentId).val()},
		beforeSend: function() {
			$("#waitingComment" + documentId).css("display", "inline");
		},
		success : function(content) {
			$("#waitingComment" + documentId).css("display", "none");
			$("#newComment" + documentId).attr("rows", 1).val('');
			$("blockquote#lastCommentPosition" + documentId).before(content);
		}
	});
}

function clickCommentForm(documentId) {
	$("#saveComment" + documentId).css("display", "inline");
}

function deleteComment(documentId, commentId) {
	var comment = parseInt($("#comment" + documentId).text());
	$("#comment" + documentId).text(comment - 1);
	$.ajax({
		type : "POST",
		url : "/comment",
		dataType: "text",
		data: {"documentId": documentId, "commentId": commentId, _method: "DELETE"},
		beforeSend: function() {
		},
		success : function() {
			$("#commentBox" + commentId).remove();
		}
	});
}

function post(documentId) {
	$.ajax({
		type : "POST",
		url : "/post",
		dataType: "text",
		data: {"documentId" : documentId, "content": $("#content" + documentId).text()},
		beforeSend: function() {
		},
		success : function(content) {
			$("#alert").replaceWith(content);
		}
	});
}
		
function like(documentId) {
	$.ajax({
		type : "GET",
		url : "/like",
		dataType: "text",
		data: {"documentId": documentId},
		success : function(isSuccess) {
			if (isSuccess === "true") {
				var like = parseInt($("#like" + documentId).text());
				$("#like" + documentId).text(like + 1);
				$("#cancelLike" + documentId).css("display", "");
				$("#alert").removeClass("alert alert-error").html("");
			}
			else {
				$("#alert").addClass("alert alert-error").html("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">x</button>Fail to like.");
			}
		}
	});
}

function cancelLike(documentId) {
	$.ajax({
		type : "GET",
		url : "/cancelLike",
		dataType: "text",
		data: {"documentId": documentId},
		success : function(isSuccess) {
			if (isSuccess === "true") {
				var like = parseInt($("#like" + documentId).text());
				$("#like" + documentId).text(like - 1);
				$("#cancelLike" + documentId).css("display", "none");
				$("#alert").removeClass("alert alert-error").html("");
			}
		}
	});
}

function dislike(documentId) {
	$.ajax({
		type : "GET",
		url : "/dislike",
		dataType: "text",
		data: {"documentId": documentId},
		success : function(isSuccess) {
			if (isSuccess === "true") {
				var dislike = parseInt($("#dislike" + documentId).text());
				$("#dislike" + documentId).text(dislike + 1);
				$("#cancelDislike" + documentId).css("display", "");
				$("#alert").removeClass("alert alert-error").html("");
			}
			else {
				$("#alert").addClass("alert alert-error").html("<button type=\"button\" class=\"close\" data-dismiss=\"alert\">x</button>Fail to dislike.");
			}
		}
	});
}

function cancelDislike(documentId) {
	$.ajax({
		type : "GET",
		url : "/cancelDislike",
		dataType: "text",
		data: {"documentId": documentId},
		success : function(isSuccess) {
			if (isSuccess === "true") {
				var dislike = parseInt($("#dislike" + documentId).text());
				$("#dislike" + documentId).text(dislike - 1);
				$("#cancelDislike" + documentId).css("display", "none");
				$("#alert").removeClass("alert alert-error").html("");
			}
		}
	});
}

function isRefreshPosition() {
	if ($(window).scrollTop() < 10) {
		$.ajax({
			type : "GET",
			url : "/list",
			dataType: "text",
			data: {"documentId": $("#newest").text(), "viewRecently": true},
			success : function(content) {
				$("#newest").text(computeDocumentIdFromHtml(content));
				$("#waitingDocument").after(content);
			}
		});
	}
}

var timerOfRefresh = setInterval(function() {
	isRefreshPosition();
}, 10000);

function isMorePosition() {
	if ($("#moreDocument").length > 0) {
		$(window).scroll(function() {
			if ($(window).scrollTop() + $(window).height() > $(document).height() - 10) {
				$(window).unbind('scroll');
				more();
			}
		});
	}
}

function more() {
	$.ajax({
		type : "GET",
		url : "/list",
		dataType: "text",
		data: {"documentId": $("#oldest").text()},
		success : function(content) {
			$("#moreDocument").replaceWith(content);
			isMorePosition();
		}
	});
}
isMorePosition();

$("#newDocument").keyup(function(event) {
	if (event.which == 13) {
		var row = parseInt($("#newDocument").attr("rows"));
		$("#newDocument").attr("rows", row + 1);
	}
})

$("#newMyDoc").keyup(function(event) {
	if (event.which == 13) {
		var row = parseInt($("#newMyDoc").attr("rows"));
		$("#newMyDoc").attr("rows", row + 1);
	}
})
