function saveData(){
	 var token = localStorage.getItem("token");	
	 objSaveUserContextsResult = openape.setUserContexts(document.getElementById("dataInput").value, token);
	 $("#saveUserContextsStatus").empty();
	 $("#saveUserContextsStatus").append("<b>UserContextId: </b>"+ objSaveUserContextsResult.userContextId);
}

function loadData() {
	var userContextId =  $('#getUserContextIdInput').val();
	var token = localStorage.getItem("token");
	$('#getUserContextTextarea').val(JSON.stringify(openape.getUserContexts(token, userContextId)));
}

function deleteData() {
	var userContextId =  $('#deleteUserContextIdInput').val();
	var token = localStorage.getItem("token");
	
	var objStatus = openape.deleteUserContexts(token, userContextId);
	if(objStatus.status == 204){
		$('#deleteStatus').empty();
		$('#deleteStatus').append("<font style='color:green'>Successfully deleted</font>");
	} 
	if(objStatus.status == 404){
		$('#deleteStatus').empty();
		$('#deleteStatus').append("<font style='color:red'>Not Found</font>");
	}
}

function updateData() {
	var token = localStorage.getItem("token");
	var userContextId =  $('#updateUserContextIdInput').val();
	var userContexts = document.getElementById("updateUserContextTextarea").value;
	var objUpdateStatus = openape.updateUserContexts(token, userContextId, userContexts);
		
	if(objUpdateStatus.status == 200){
		$('#updateStatus').empty();
		$('#updateStatus').append("updated");
	}
	
	console.log()
	
	if(objUpdateStatus.status == 400){
		$('#updateStatus').empty();
		$('#updateStatus').append("update failed");
	}
}


function Logout() {
	window.location = "http://localhost:4567/start.html";
}

