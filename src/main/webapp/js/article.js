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

function moveLastChapter() {
	$('#html,body').animate({
		scrollTop : $('.lastChapter').offset().top
	}, 'slow');
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
				if (content.search("wrong") == -1) {
					$(".lastChapter").removeClass("lastChapter");
					$("#answer").replaceWith("<blockquote><p>" + $("#answer").val() + "</p></blockquote>");
					$("#nextChapter").before(content);
					moveLastChapter();
				} else {
					alert("wrong");
				}
			}
		});
	}
}

$(window).scroll(
		function() {
			if ($(window).scrollTop() == $(document).height() - $(window).height()) {
				if ($("#myModal").attr('class') == "modal fade hide") {
					$("#login").click();
				}
			}

			$.each($(".section"), function() {
				$(".current").removeClass("current");
				if ($(this).offset().top <= $(window).scrollTop()
						&& $(window).scrollTop() < $(this).offset().top + $(this).height()) {
					$(this).addClass("current");
					return false;
				}
			});
		});

$(document).ready(function() {
	$(document).keydown(function(e) {
		switch (e.which) {
		case 37: // left arrow
		case 38: // up arrow
			e.preventDefault();
			$('#html,body').animate({
				scrollTop : $(".current").prev().offset().top
			}, 'slow');
			break;

		case 13: // enter
		case 32: // space
		case 39: // right arrow
		case 40: // down arrow
			e.preventDefault();
			$('#html,body').animate({
				scrollTop : $(".current").next().offset().top
			}, 'slow');
			break;
		}
	});
});