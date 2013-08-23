$(document).ready(function() {
	$("a.anchorLink").anchorAnimate()
});

jQuery.fn.anchorAnimate = function(settings) {
	settings = jQuery.extend({
		speed : 1100
	}, settings);

	return this.each(function() {
		var caller = this
		$(caller).click(function(event) {
			event.preventDefault()
			var locationHref = window.location.href
			var elementClick = $(caller).attr("href")

			var destination = $(elementClick).offset().top;
			$("html:not(:animated),body:not(:animated)").animate({
				scrollTop : destination
			}, settings.speed, function() {
				window.location.hash = elementClick
			});
			return false;
		})
	})
}

function sendAnswer(id) {
	if (event.keyCode == 13) {
		if ($("#answer").val() === "") {
			return;
		}

		$.ajax({
			type : "POST",
			url : $("#title").text() + "/" + id,
			dataType : "text",
			data : {
				answer : $("#answer").val()
			},
			success : function(content) {
				$("#answer").replaceWith(
						"<blockquote><p>" + $("#answer").val()
								+ "</p></blockquote>");
				$("#nextChapter").before(content);
			}
		});
	}
}