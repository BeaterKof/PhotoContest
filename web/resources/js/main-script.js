//js file

/* SignIn Form */

$(document).ready(function(){

    $(".image-cell").mouseenter(function(){
        $(this).find(".image-toggle").fadeToggle();
    }).mouseleave(function(){
            $(this).find(".image-toggle").fadeToggle();
    });

    $(".fancybox").fancybox();
});


function showpopup()
{
   $("#loginModal").fadeIn();
   $("#loginModal").css({"visibility":"visible","display":"block"});
}

function hidepopup()
{
   $("#loginModal").fadeOut();
   $("#loginModal").css({"visibility":"hidden","display":"none"});
}