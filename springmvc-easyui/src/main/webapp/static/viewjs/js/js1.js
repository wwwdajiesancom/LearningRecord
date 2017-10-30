function toGreen(target){
	target.style.background="green";
}

function toRed(target){
	target.style.background="red";
}

function getValue(target){
	console.log(target.value);
}

function GetTarget(id){
	return document.getElementById(id);	
}

function VMove(id){
	var _this = this;
	this.id = id;
	
	//获取目标
	this.__proto__.target = function(){
		return GetTarget(this.id);
	}
	
	//移动
	this.__proto__.vmove = function(){
		
	}
	
	//鼠标按下，鼠标移动，鼠标松开
	this.__proto__.mousedown = function(){
		
		var x;
		var y;
		
		//鼠标按下
		this.target().onmousedown=function(ev){
			ev = ev||event;
			//记录鼠标的位置
			x = ev.clientX;
			y = ev.clientY;			
		}
		
		//鼠标移动
		this.target().onmousemove=function(ev){
			ev = ev||event;
			
			//修改div的位置
			
		}
		
		//鼠标松开
		this.target().onmouseup=function(ev){
			ev = ev||event;
			
			//
			
		}
		
	}
	
	this.__proto__.init = function(){
		this.mousedown();
	}
	
	this.init();
}

function HMove(id){
	
	
}

window.onload = function(){
	
	var div1 = new VMove("div1");
	
}