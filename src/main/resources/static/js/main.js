$(function() {

	
	//$("#post").submit(function(e) {
	//	e.preventDefault();
	//	
	//	var content = $("#post").find("textarea").eq(0).val();
	//	$.post( "/post", {"postContents": content}, function( data ) {
    //		location.reload();
	//	});
	//});


	$('.commentButton').click(function() {
        $(this).parent().parent().find(".commentBox").toggleClass('open');
    });
    
    $('.likeButton').click(function() {
    	var postid = $(this).attr("data-postId");
    	var that = this;
    	var $amount = $(that).find(".amountOfLikes");
    	$.post( "/like", {"postId": postid}, function( data ) {
    		if(data == "ADDED") {
    			$(that).find(".fa").removeClass("fa-thumbs-o-up").addClass("fa-thumbs-up");
    			$amount.text(parseInt($amount.text()) + 1);
    		} else {
    			$(that).find(".fa").removeClass("fa-thumbs-up").addClass("fa-thumbs-o-up");
    			$amount.text(parseInt($amount.text()) - 1);
    		}
		});
    });

});