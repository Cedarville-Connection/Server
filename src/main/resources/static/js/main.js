$(function() {
	$('.commentButton').click(function() {
        $(this).parent().parent().find(".commentBox").toggleClass('open');
    });
    
    $('.likeButton').click(function() {
    	var postid = $(this).attr("data-postId");
    	$.post( "/like", {"postId": postid}, function( data ) {
    		if(data == "ADDED") {
    			$(this).find(".fa").removeClass("fa-thumbs-o-up").addClass("fa-thumbs-up");
    		} else {
    			$(this).find(".fa").removeClass("fa-thumbs-up").addClass("fa-thumbs-o-up");
    		}
		  
		});
    });
});