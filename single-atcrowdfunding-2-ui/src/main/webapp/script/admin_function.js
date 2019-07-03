/**
 * 
 */

function resultMessage(adminIdArray,pageNo){
	var jsonString = JSON.stringify(adminIdArray);
	$.ajax({
		"url":"admin/to/remove.json",
		"type":"post",
		"data":jsonString,
		"dataType":"json",
		"contentType":"application/json;charset=UTF-8",
		"success":function(response){
			var result = response.result;
			if(result == "SUCCESS" ){
				window.location.href = "query/with/search.html?pageNo="+pageNo;
			}
			if(result == "FAILED" ){
				alert(response.message);
			}
		},
		"error":function(response){
			alert(response.message);
		}
	});
}