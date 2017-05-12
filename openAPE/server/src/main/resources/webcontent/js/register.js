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

function getToken(){
	 $.ajax({
	        type: 'POST',
	        async: false,
	        contentType: 'json',
	        url: "http://localhost:4567/token?grant_type=password&username=admin&password=12345",
	        dataType: "json",
	        /*data: JSON.stringify(user),*/
	        success: function(data, textStatus, jqXHR){
	        	console.log(data);
				/*window.location = "http://localhost:4567/resource-manager.html";
	        */},
	        error: function(jqXHR, textStatus, errorThrown){
	           console.log(jqXHR, textStatus, errorThrown);
	        }
	  });
}