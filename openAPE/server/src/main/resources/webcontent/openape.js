$(document).ready(function(){
	$('#register').click(function(){
		setUserData();
	})
})




function setUserData(){
	var objUser = new Object();
	var arrRoles = [];
	arrRoles.push("admin");
	
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	
	var isUsernameCorrect = false;
	var isEmailCorrect = false;
	var isPasswordCorrect = false;
	
	//check if username is correct
	if(username!=""){
		objUser.username = $("#username").val();
		isUsernameCorrect = true;
	} else {
		isUsernameCorrect = false;
		alert("Username is needed");
	}
	
	//check if email is correct
	if(email!=""){
		objUser.email = $("#email").val();
		isEmailCorrect=true;
	} else {
		alert("Email is needed");
		isEmailCorrect=false;
	}
	
	//check if password is correct
	if(password!=""){
		objUser.password = $("#password").val();
		isPasswordCorrect=true;
	} else {
		alert("Password is needed");
		isPasswordCorrect=false;
	}
	
	
	if(isPasswordCorrect==true && isEmailCorrect==true && isUsernameCorrect==true){
		objUser.roles = arrRoles;
		sendUserData(objUser);
	}
}

function sendUserData(user){
	 $.ajax({
	        type: 'POST',
	        async: false,
	        contentType: 'json',
	        url: "http://localhost:4567/users",
	        dataType: "html",
	        data: JSON.stringify(user),
	        success: function(data, textStatus, jqXHR){
				localStorage.setItem("token", data.substring(17, 41));
				window.location = "http://localhost:4567/restTests.html";
	        },
	        error: function(jqXHR, textStatus, errorThrown){
	           console.log(jqXHR, textStatus, errorThrown);
	        }
	  });
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