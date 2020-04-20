$(function() {
	$('.commentButton').click(function() {
        $(this).parent().parent().find(".commentBox").toggleClass('open');
    });
});