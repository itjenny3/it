$(document).ready(function() {
    $('a.anchorLink').anchorAnimate();
});

jQuery.fn.anchorAnimate = function(settings) {
    settings = jQuery.extend({
	speed : 1100
    }, settings);

    return this.each(function() {
	var caller = this;
	$(caller).click(function(event) {
	    event.preventDefault();
	    var locationHref = window.location.href;
	    var elementClick = $(caller).attr('href');

	    var destination = $(elementClick).offset().top;
	    $('html:not(:animated),body:not(:animated)').animate({
		scrollTop : destination
	    }, settings.speed, function() {
		window.location.hash = elementClick;
	    });
	    return false;
	});
    });
};

function moveLastChapter() {
    $('#html,body').animate({
	scrollTop : $('.lastChapter').offset().top
    }, 'slow');
}

function moveCurrentSection() {
    $('#html,body').animate({
	scrollTop : $('.current').offset().top
    }, 'slow');
}

function login() {
    $('#login').click();
}

function sendAnswer(id) {
    if (event.keyCode == 13) {
	$.ajax({
	    type : 'POST',
	    url : '/article/' + $('#title').text() + '/' + id,
	    dataType : 'text',
	    data : {
		answer : $('#answer').val()
	    },
	    success : function(content) {
		if (content.search('wrong') == -1) {
		    $('.lastChapter').removeClass('lastChapter');
		    $('#nextChapter').before(content);
		    $('#answer').replaceWith(
			    '<blockquote><p>' + $('#previousAnswer').text()
				    + '</p></blockquote>');
		    $('#previousAnswer').remove();
		    moveLastChapter();
		} else {
		    alert('wrong');
		}
	    }
	});
    }
}

$(window).scroll(function() {
    setCurrent();
});

function setCurrent() {
    var chapterIndex = -1;

    // reach last page
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	if ($('#myModal').attr('class') == 'modal fade') {
	    $('#answer').focus();
	}
    }

    $.each($('.section'), function() {
	chapterIndex++;
	$('.current').removeClass('current');
	if ($(this).offset().top <= $(window).scrollTop()
		&& $(window).scrollTop() < $(this).offset().top
			+ $(this).height()) {
	    $(this).addClass('current');
	    $('#page').text(chapterIndex);
	    return false;
	}
    });
}

function getCookie(key) {
    var allcookies = document.cookie;
    var cookies = allcookies.split('; ');
    for ( var i = 0; i < cookies.length; i++) {
	var keyValues = cookies[i].split('=');
	if (keyValues[0] == key) {
	    return unescape(keyValues[1]);
	}
    }
    if (key == 'mode') {
	return 'word';
    }
    return '';
}

function setCookie(key, value) {
    var date = new Date();
    var validity = 100;
    date.setDate(date.getDate() + validity);
    document.cookie = key + '=' + escape(value) + ';expires='
	    + date.toGMTString();
}

function removeCookie(key) {
    var date = new Date();
    var validity = -1;
    date.setDate(date.getDate() + validity);
    document.cookie = key + '=;expires=' + date.toGMTString();
}

$(document).ready(function() {
    $(document).keydown(function(e) {
	switch (e.which) {
	case 37: // left arrow
	case 38: // up arrow
	case 75: // k
	    e.preventDefault();
	    $('#html,body').animate({
		scrollTop : $('.current').prev().offset().top
	    }, 'slow');
	    break;

	case 13: // enter
	case 32: // space
	case 39: // right arrow
	case 40: // down arrow
	case 74: // j
	    e.preventDefault();
	    $('#html,body').animate({
		scrollTop : $('.current').next().offset().top
	    }, 'slow');
	    break;

	case 27: // esc
	    $('#stop').click();
	    break;
	}
    });
})