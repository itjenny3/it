function isEndPosition() {
	$(window).scroll(function() {
		if ($(window).scrollTop() + $(window).height() == $(document).height()) {
			$(window).unbind('scroll');
			$.ajax({
				type : "GET",
				url : location.pathname + "/license",
				dataType: "text",
				data: {"id": "social_id"},
				success : function(content) {
					$("#license").text(content);
				}
			});
		}
	});
}

isEndPosition();