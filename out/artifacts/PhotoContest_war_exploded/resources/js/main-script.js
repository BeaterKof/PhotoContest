//js file

/* SignIn Form */

$(document).ready(function(){

   $("#login-link").click(function(){
    showpopup();
   });
   $("#form-close-button").click(function(){
    hidepopup();
   });

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