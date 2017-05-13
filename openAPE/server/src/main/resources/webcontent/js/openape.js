(function(window){
	
 function defineOpenape(){
    var objOpenape = {};
    //
    // FUNCTIONS FOR USER
    //
    
    //this function is used to set the user in the mongo database with the given username, email and password
    objOpenape.setUser = function (username, email, password) {
   		var objUser = new Object();
   		var arrRoles = [];
   		arrRoles.push("admin");

   		var isUsernameCorrect = false;
   		var isEmailCorrect = false;
   		var isPasswordCorrect = false;
    		
   		//check if username is correct
   		if(username!=""){
   			objUser.username = username;
   			isUsernameCorrect = true;
   		} else {
   			isUsernameCorrect = false;
   			alert("Username is needed");
   		}
    		
   		//check if email is correct
   		if(email!=""){
   			if((validateEmail(email))==true){
   	   			objUser.email = email; 			
   	   			isEmailCorrect=true;
   			} else {
   				alert("wrong email");
   			}
		}else {
   			alert("Email is needed");
   			isEmailCorrect=false;
  		}
    		
   		//check if password is correct
   		if(password!=""){
   			objUser.password = password;
   			isPasswordCorrect=true;
   		} else {
   			alert("Password is needed");
   			isPasswordCorrect=false;
   		}    		
    		
   		if(isPasswordCorrect==true && isEmailCorrect==true && isUsernameCorrect==true){
   			objUser.roles = arrRoles;
   			return sendUserData(objUser);
   		}
    }
    
    objOpenape.getUser = function (token) {
    	var objUserProfile = {};
    	$.ajax({
	        type: 'GET',
	        async: false,
	        contentType: 'json',
	        headers: {
	            "Authorization": token,
	        },
	        url: "http://localhost:4567/profile",
	        dataType: "html",
	        success: function(data, textStatus, jqXHR){
	        	objUserProfile.userProfile = data;
	        },
	        error: function(jqXHR, textStatus, errorThrown){
	          console.log(jqXHR, textStatus, errorThrown);
	        }
    	});
    	return objUserProfile;
    }
    
    /*
    * TOKEN FUNCTIONS
    */
  
    /*
    * This function is used to get the user token by the given grant_type, username and password 
    */
    objOpenape.getToken = function (grant_type, username, password) {
    	var objToken = {};
    	$.ajax({
    	        type: 'POST',
    	        async: false,
    	        contentType: 'json',
    	        url: "http://localhost:4567/token?grant_type="+grant_type+"&username="+username+"&password="+password,
    	        dataType: "json",
    	        success: function(data, textStatus, jqXHR){
    	        	objToken = data;
    	        },
    	        error: function(jqXHR, textStatus, errorThrown){
    	           console.log(jqXHR, textStatus, errorThrown);
    	      }
    	 });
    	
    	return objToken;
    }
    
    
    function sendUserData(user){
    	 var status = true;
    	 $.ajax({
    	        type: 'POST',
    	        async: false,
    	        contentType: 'json',
    	        url: "http://localhost:4567/users",
    	        dataType: "html",
    	        data: JSON.stringify(user),
    	        success: function(data, textStatus, jqXHR){
    				localStorage.setItem("token", data.substring(17, 41));
    				status =  true;
    	        },
    	        error: function(jqXHR, textStatus, errorThrown){
    	           status = false;
    	           if(jqXHR.responseText.includes("username_1 dup key")==true){
    		       	  alert("User with this username already exists");
    		       } else  if(jqXHR.responseText.includes("email_1 dup key")==true){
    		          alert("User with this email already exists");
    		       }
    	        }
    	  });
    	 return status;
    }

    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    
    // We will add functions to our library here !
    return objOpenape;
  }

  // We need that our library is globally accesible, then we save in the window
  if(typeof(window.openape) === 'undefined'){
    window.openape = defineOpenape();
  }
})(window); // We send the window variable withing our function
