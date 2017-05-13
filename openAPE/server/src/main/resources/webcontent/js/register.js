$(document).ready(function(){
	$('#register').click(function(){
		setUserData();
	})
})


function setUserData(){
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	
	if(openape.setUser(username, email, password)==true){
		window.location = "http://localhost:4567/restTests.html";
	}
}