function toGreen(target){
	target.style.background="green";
}

function toRed(target){
	target.style.background="red";
}

function getValue(target){
	console.log(target.value);
}


window.onload = function(){
	
	var oText = document.getElementById("text1");
	
	oText.onkeydown = function(){
		return false;
	}
	
	
	
}