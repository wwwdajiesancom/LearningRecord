/**
 * 静态类
 * 
 * 封装了字符串，json,date格式化等常用的方法
 */
var Extra = {
		/**
		 * 产生随机guid
		 */
		guid :function () {
			function S4() {
				return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
			}
			return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
		},
		/**
		 * 日志记录
		 */
		log:function(data){
			window.console.log(data);
		},
		/**
		 * 是否为字符串
		 */
		isString:function(str){
			if(typeof (str) == 'string'){
				return true;
			}
			return false;
		},
		/**
		 * 获取href中的参数
		 * 例如：http://www.loujie.com/test/{id}?name={name}&age={age}
		 * 获取到的是[id,name,age]
		 * 
		 * _reg:这是一个正则,默认匹配{xxx},但是也可以匹配其它的,这时就需要自己写了
		 */
		getHrefParam:function(href,_reg){
			var result = [];
			if(!this.isEmpty(href)){
				var reg = /\{([\w\d_]+)\}/ig;
				if(!this.isEmpty(_reg)){
					reg = _reg;
				}
				var r = "";
				while(r=reg.exec(href)){
					result.push(r[1]);
				}
			}
			return result;
		},
		/**
		 * 转换成int类型
		 */
		int:function(str){
			var result = parseInt(str);
			return isNaN(result)?0:result;
		},
		/**
		 * 转换成float类型
		 */
		float:function(str){
			var result = parseFloat(str); 
			return isNaN(result)?0:result;
		},
		boolean :function(str){
			try{
				if(str=="true"||str==true){
					return true;
				}else{
					return false;
				}
			}catch(e){
				return false;
			}
		},
		/**
		 * 完全替换一个字符串中的字符
		 */
		replaceAll:function(str,key,value){//
			if(value==undefined)value='';
			do{
				str = str.replace(key,value);
			}while(str.indexOf(key)!=-1);
			return str;
		},
		/**
		 * 去除两边的seg 默认是去除空格
		 */
		trim : function(str,seg){
			if(this.isEmpty(str)){
				return str;
			}
			if(this.isEmpty(seg)){
				seg = " ";
			}
			return this.replaceAll(str,seg,'');
		},
		trimLR:function(str){
			if(this.isEmpty(str)){
				return str;
			}
			try{
				return str.trim();
			}catch(e){
				return str;
			}			
		},
		/**
		 * 判断是否为空 分为两种判断： 第一种是传递一个参数,为string,boolean,number
		 * 第二种是传递二个参数,第一个参数为{},第二个为string(其实是一个键值)
		 */
		isEmpty : function(isEmpty_options,isEmpty_jsonkey){
			// 1.先判断第一个参数
			if(isEmpty_options==undefined||isEmpty_options==null||(isEmpty_options+'')==''){
				return true;
			}
			// 2.如果类型分为object,就需要用到第二个参数了
			if(typeof (isEmpty_options) == "object"){
				if(this.isEmpty(isEmpty_jsonkey)){
					return false;
				}
				if(this.containStr(isEmpty_jsonkey,".")){
					var keys = isEmpty_jsonkey.split(".");
					var _value = this.getVal(isEmpty_options,keys);
					if(this.isEmpty(_value)){
						return true;
					}
				}else{
					var _value = isEmpty_options[isEmpty_jsonkey];
					if(this.isEmpty(_value)){
						return true;
					}					
				}
				// 不为空
				return false;
			}
			// 3.可能是string,number,boolean
			return false;
		},
		/**
		 * 判断是否包含指定字符串
		 */
		containStr:function(str,seg){
			if(!this.isEmpty(str)){
				if(str.indexOf(seg)!=-1){
					return true;
				}
			}
			return false;
		},
		/**
		 * 分割开一个具体的属性(分开具体的键值对) 因为会出现其它的:(冒号);例如:href:'http://www.baidu.com'
		 * result,返回结果{}
		 * itemAttr,一个最简单的用:(冒号)分开的键值对
		 */
		splitAttr:function(result,itemAttr){
			var itemAttrs = itemAttr.split(":");
			if(itemAttrs.length==2){
				var key = this.trim(itemAttrs[0]);
				var value = this.trim(itemAttrs[1]);
				if(!this.isEmpty(key)&&!this.isEmpty(value)){
					result[key]=value;
				}
			}else if(itemAttrs.length>2){
				// 当前仅仅只判断一方存在:(冒号)
				// 键中包含:(冒号)
				if(itemAttr.charAt(0)=="'"){
					var mhIndex = itemAttr.lastIndexOf(":");
					var key = this.trim(this.trim(itemAttr.substring(0,mhIndex)),"'");
					var value = this.trim(itemAttr.substring(mhIndex+1));
					result[key]=value;
				}else{
					// 值中包含:(冒号)
					var mhIndex = itemAttr.indexOf(":");
					var key = this.trim(itemAttr.substring(0,mhIndex));
					var value = this.trim(this.trim(itemAttr.substring(mhIndex+1)),"'");
					result[key]=value;
				}
			}
		},
		/**
		 * 拆分name将value放入到json对象中
		 * result:{},返回结果
		 * key:键值,但可能包含了其它的分割字符,例如person.name
		 * value:值
		 * seg:分隔符,默认为.
		 * 
		 * 处理复杂的name;目前可以处理以下几种,
		 * 
		 * 1.直接名称,例如age,page等;
		 * 
		 * 2.数组,id=1,id=2,id=3会转换成id:[1,2,3];
		 * 
		 * 3.携带.的字段;例如persion.name=2;转换成persion:{name:2};
		 * 
		 * 4.带有下标的例如list[0].name=jiege,list[0].age=12,list[1].name=weiwei,list[1].age=23,这些会转化为list:[{name:jiege,age:12},{name:weiwei,age:23}]
		 */
		keyValueToJson:function(result,key,value,seg){
			//1.设置默认值
			if(this.isEmpty(seg)){
				seg = ".";
			}
			//2.拆分
			var keys = key.split(seg);
			this.setKeyItor(keys,value,result);
		},
		/**
		 * 将一个[]放入到一个{}中,
		 * keys,[],["person","child","name"]
		 * value,值
		 * result:{},要填充的json对象
		 */
		setKeyItor:function (keys,value,result,i){
			if(this.isEmpty(i)){
				i = 0;
			}
			//最后一个,肯定不会带下标
			if(i==(keys.length-1)){
				if(!this.isEmpty(result[keys[i]])){
					if(typeof (result[keys[i]]) == "object"){
						//理论上应该是一个[]
						try{
							result[keys[i]].push(value);
						}catch(e){}
					}else{
						var itemArr = [];
						itemArr.push(result[keys[i]]);
						itemArr.push(value);
						result[keys[i]] = itemArr;
					}
				}else{
					result[keys[i]]=value;					
				}
			}else{
				//更加复杂了,主要是有重复的值
				var keyIndex = Extra.getHrefParam(keys[i],/\[([\d]+)\]/ig)[0];
				var newKey = this.replaceAll(keys[i],"["+keyIndex+"]");
				if(!this.isEmpty(result[newKey])){
					//存在下标,abc[0].name
					if(!this.isEmpty(keyIndex)){
						var indexResult = result[newKey][keyIndex];
						if(this.isEmpty(indexResult)){
							indexResult = {};
						}
						this.setKeyItor(keys,value,indexResult,i+1);
						result[newKey][keyIndex] = indexResult;
					}else{
						this.setKeyItor(keys,value,result[newKey],i+1);						
					}
				}else{					
					var _result = {};
					this.setKeyItor(keys,value,_result,i+1);
					if(!this.isEmpty(keyIndex)){
						var values_ = [];values_[keyIndex]=_result;
						result[newKey] = values_;
					}else{						
						result[newKey] = _result;
					}
				}
			}
		},
		/**
		 * 从json对象中获取值
		 */
		getJsonValue:function(json,key){
			//1.如果有一个为空,返回''
			if(this.isEmpty(json)||this.isEmpty(key))return "";
			//2.判断key是否包含.
			if(this.containStr(key,".")){
				return this.getVal(json,key.split("."));
			}else{
				var value = json[key];
				if(this.isEmpty(value)){
					return "";
				}else{
					return value;
				}
			}
		},
		/**
		 * 获取一个value
		 * json:{person:{name:"jiie"}}
		 * keys,可以是一个数组["person","name"],也可以是一个键(但里面不可以有.)
		 */
		getVal:function getVal(json,keys,i){
			if(this.isEmpty(i))i=0;
			if(typeof (keys) == "string"){
				var aa = [];
				aa.push(keys);
				keys = aa;
			}
			if(i==(keys.length-1)){
				return json[keys[i]];
			}else{
				if(this.isEmpty(json[keys[i]])){return "";}
				return getVal(json[keys[i]],keys,i+1);
			}
		},formatDate:function(nowDate,format){
			if(this.isEmpty(nowDate)){
				return nowDate;
			}
			var value = "";
			try{
				var l_nowDate = nowDate;
				if(l_nowDate==undefined){
					l_nowDate = new Date();
				}
				l_nowDate = new Date(l_nowDate);
				var l_year = l_nowDate.getFullYear();		
				var l_month = l_nowDate.getMonth()+1;
				if(l_month<10)l_month = "0" + l_month;		
				var l_day = l_nowDate.getDate();
				if(l_day<10)l_day = "0"+l_day;		
				var l_hours = l_nowDate.getHours();
				if(l_hours<10)l_hours = "0"+l_hours;		
				var l_minutes = l_nowDate.getMinutes();
				if(l_minutes<10)l_minutes = "0"+l_minutes;		
				var l_seconds = l_nowDate.getSeconds();
				if(l_seconds<10)l_seconds = "0"+l_seconds;		
				var _format = format;
				
				if(_format==undefined){
					_format = 'yyyy-MM-dd';
				}
				var _formats = _format.split(' ');
				var _f1;
				var _f2;
				if(_formats.length==2){
					_f1 = _formats[0].split('-');
					_f2 = _formats[1].split(':');
				}
				if(_formats.length==1){
					if(_formats[0].split('-').length>1){
						_f1 = _formats[0].split('-');	
					}else{
						_f2 = _formats[0].split(':');	
					}
				}
				function l_switch(key){
					switch(key){
						case 'yyyy':return l_year;
						case 'MM':return l_month;
						case 'dd':return l_day;
						case 'HH':return l_hours;
						case 'mm':return l_minutes;
						case 'ss':return l_seconds;
					};
				}
				if(_f1!=undefined&&_f1.length>0){
					for(var i in _f1){
						value += l_switch(_f1[i]);
						if(parseInt(i)!=(_f1.length-1)){
							value += '-';
						}
					}
				}
				if(_f2!=undefined&&_f2.length>0){
					if(value!=''){value += ' ';}
					for(var j in _f2){
						value += l_switch(_f2[j]);
						if(parseInt(j)!=(_f2.length-1)){
							value += ':';
						}
					}
				}
			}catch(e){}
			return value;
		},
};


/**
 * ajax封装
 */
var ExtraAjax = {
		/**
		 * 验证form表单是否可以提交了
		 * options:
		 * 	validType:easyui,验证方式
		 */
		validForm : function($form,options){
			//1.看是否存在验证函数
			if(!Extra.isEmpty($form.attr(CallbackOptions.callbackValid))){
				var callbackValid = $form.attr(CallbackOptions.callbackValid);
				callbackValid = eval(callbackValid);
				return callbackValid();
			}
			//2.验证分为不同的方式,可以是easyui的验证,也可以是其它插件的,当前的情况我们只写一种就是easyui的验证
			//1.设置验证方式
			var validType = "easyui";
			if(!Extra.isEmpty(options,"validType")){
				validType = options['validType'];
			}
			//2.easyui验证
			if(validType=="easyui"){
				$form.form("enableValidation");
				var result = $form.form("validate");
				if(result&&!Extra.isEmpty($form.attr(CallbackOptions.callbackSubValid))){
					var callbackSubValid = $form.attr(CallbackOptions.callbackSubValid);
					callbackSubValid = eval(callbackSubValid);
					result = callbackSubValid();
				}
				return result;
			}
			return false;
		},
		/**
		 * ajax的错误提示
		 */
		ajaxError : function(){
			ExtraAjax.ajaxTip('服务器异常', '服务器链接异常！', 'warning');		
		},
		
		/**
		 * 获取Ajax调用时传递的参数对象
		 * 
		 * form表单可有属性：
		 * callbackParam：可选,ajax的传递的data对象,由该函数提供,它是无参函数,function(){return {};}
		 * callbackSubParam：可选,ajax的传递的data对象,部分由该函数提供,它也是无参函数,function(){return ();}
		 * 
		 */
		ajaxData : function($form){
			try{
				//ajax所需要的参数
				if(!Extra.isEmpty($form.attr(CallbackOptions.callbackParam))){
					var callbackParam = $form.attr(CallbackOptions.callbackParam);
					callbackParam = eval(callbackParam);
					return callbackParam();
				}else{
					var data = {};
					//自定义子处理
					if(!Extra.isEmpty($form.attr(CallbackOptions.callbackSubParam))){
						var callbackSubParam = $form.attr(CallbackOptions.callbackSubParam);
						callbackSubParam = eval(callbackSubParam);
						data = callbackSubParam();
					}
					//form表单中的
					var params = $form.find(FormOptions.form_input_selector).serializeArray();
					if(params.length>0){
						for(var i in params){
							var item = params[i];
							var key = item["name"];
							var value = Extra.trimLR(item["value"]);
							if(!Extra.isEmpty(value)&&!Extra.isEmpty(key)){
								//处理复杂的name;目前可以处理以下几种,1.直接名称,例如age,page等;2.数组,id=1,id=2,id=3会转换成id:[1,2,3];3.携带.的字段;例如persion.name=2;转换成persion:{name:2};带有下标的例如list[0].name=jiege,list[0].age=12,list[1].name=weiwei,list[1].age=23,这些会转化为list:[{name:jiege,age:12},{name:weiwei,age:23}]
								Extra.keyValueToJson(data,key,value);
							}
						}
					}					
					return data;
				}
			}catch(e){return {};}
		},
		
		/**
		 * 获取ajax调用需要的参数
		 * 
		 * form表单中的属性：
		 * 		action:url地址
		 * 		method:get/post
		 * 		contentType:可选,提供了form表单提交参数的形式,可以有json一种形式,默认是正常的
		 * 		函数：
		 * 		callbackSuccess：ajax调用成功后的函数,function(result){}
		 * 		callbackSubSuccess：ajax调用成功后,有一个默认处理方法,但如果是后台执行成功,需要例外的执行一些事件,就需要它了,function(){}
		 * 		callbackParam：可选,ajax的传递的data对象,由该函数提供,它是无参函数,function(){return {};}
		 * 		callbackSubParam：可选,ajax的传递的data对象,部分由该函数提供,它也是无参函数,function(){return ();}
		 * 
		 */
		ajaxOptions : function($form,eoptions){
			var options = {};
			
			//1.额外参数
			if(!Extra.isEmpty(eoptions,AjaxOptions.progressText)){
				options[AjaxOptions.progressText] = eoptions[AjaxOptions.progressText];
			}
			
			//2.必须的参数
			//url
			options["url"] = $form.attr("action");
			//get/post
			options["type"] = $form.attr("method");
			//可选参数
			if($form.attr("contentType")=="json"){
				options["contentType"] = "application/json";
			}else{
				options["contentType"] = "application/x-www-form-urlencoded";
			}
			
			//3.获取Ajax的data数据
			options["data"] = this.ajaxData($form);
			
			//4.一些方法的处理
			if(!Extra.isEmpty($form.attr(CallbackOptions.callbackSuccess))){
				options[CallbackOptions.callbackSuccess]=$form.attr(CallbackOptions.callbackSuccess);
			}
			if(!Extra.isEmpty($form.attr(CallbackOptions.callbackSubSuccess))){
				options[CallbackOptions.callbackSubSuccess]=$form.attr(CallbackOptions.callbackSubSuccess);
			}
			return options;
		},
		/**
		 * ajax提交
		 * 
		 * options,ajax的参数,但同时也有一些必要例外的参数：
		 * 
		 * 		defaultCallbackSuccess：ajax成功后默认调用函数,function(result,options){},其中result是ajax的返回结果,options中可能有callbackSubSuccess函数
		 * 
		 */
		ajax : function(options){
			var url = options["url"];
			var type = "post";
			if(!Extra.isEmpty(options,"type")){
				type = options["type"];
			}
			//数据
			var data = {};
			if(!Extra.isEmpty(options,"data")){
				data = options["data"];
			}
			//提交参数形式
			var contentType = "application/x-www-form-urlencoded";
			if(!Extra.isEmpty(options,"contentType")){
				contentType = options["contentType"];
			}
			//如果contentType=application/json,转换参数
			if(Extra.containStr(contentType,"json")){
				data = JSON.stringify(data);
			}
			var dataType = 'json';
			var cache = false;
			var progressText = "";
			if(!Extra.isEmpty(options,AjaxOptions.progressText)){
				progressText = options[AjaxOptions.progressText];
			}
			$.ajax({
				url:url,
				type:type,
				data:data,
				contentType:contentType,
				dataType:dataType,
				cache:cache,
				beforeSend:function(){
					//发送之前
					if(!Extra.isEmpty(progressText)){
						$.messager.progress({
							text : progressText
						});					
					}
				},
				complete : function() {
					//发送完成之后
					if(!Extra.isEmpty(progressText)){
						$.messager.progress('close');
					}
				},
				success:function(result){
					//成功后执行的方法
					try{
						if(!Extra.isEmpty(options,CallbackOptions.callbackSuccess)){
							eval(options[CallbackOptions.callbackSuccess])(result,options);
						}else{						
							if(!Extra.isEmpty(options,CallbackOptions.defaultCallbackSuccess)){
								options[CallbackOptions.defaultCallbackSuccess](result,options);
							}else{
								ExtraAjax.ajaxSuccess(result,options);
							}
						}
					}catch(e){
						Extra.log("Extra.ajax:"+e);
					}
				},
				error : function(xhr, textStatus, errorThrown) {
					ExtraAjax.ajaxError();
				},
			});
		},
		/**
		 * ajax调用成功后执行的方法 result:ajax成功返回的值 options,可能携带了一些东西
		 */
		ajaxSuccess : function(result, options) {
			try {
				// 1.后台是否执行成功
				if (ExtraAjax.ajaxSuccessStatus(result)) {
					//获取提示信息
					var msg = ExtraAjax.ajaxSuccessMsg(result,options);
					// 提示
					ExtraAjax.ajaxTip("成功提示", msg, "info");
					// 函数调用外部子函数
					ExtraAjax.ajaxSuccessSubCallback(options);
				} else {
					//获取失败提示信息
					var msg = ExtraAjax.ajaxFailMsg(result,options);					
					// 提示
					ExtraAjax.ajaxTip("失败提示", msg, "error");			
				}
			} catch (e) {
				Extra.log("Extra.ajaxSucess:"+e);
			}
		},	
		/**
		 * 判断ajax调用的后台执行是否成功
		 */
		ajaxSuccessStatus:function(result){
			if(Extra.getJsonValue(result,AjaxOptions.success)){
				return true;
			}
			return false;
		},
		/**
		 * ajax调用后台成功执行过程中,执行的一个外部提供的子函数
		 */
		ajaxSuccessSubCallback:function(options){
			if (!Extra.isEmpty(options, CallbackOptions.callbackSubSuccess)) {
				var callbackSubSuccess = eval(options[CallbackOptions.callbackSubSuccess]);
				callbackSubSuccess();
			}
		},
		/**
		 * ajax调用后台成功执行过程中,执行的一个后置成功函数
		 */
		ajaxSuccessPostCallback:function(options){
			if (!Extra.isEmpty(options, CallbackOptions.callbackPostSuccess)) {
				var callbackPostSuccess = eval(options[CallbackOptions.callbackPostSuccess]);
				callbackPostSuccess();
			}
		},
		/**
		 * ajax调用后台成功执行过程中,执行的一个后置失败函数
		 */
		ajaxFailPostCallback:function(options){
			if (!Extra.isEmpty(options, CallbackOptions.callbackPostFail)) {
				var callbackPostFail = eval(options[CallbackOptions.callbackPostFail]);
				callbackPostFail();
			}
		},
		/**
		 * 获取ajax调用后台成功的返回信息,这个是惊醒提示的
		 */
		ajaxSuccessMsg:function(result,options){
			var msg = "成功";
			if(!Extra.isEmpty(options,AjaxOptions.default_success_msg))msg=options[AjaxOptions.default_success_msg];
			if (!Extra.isEmpty(result, AjaxOptions.result_success_msg)){msg = result[AjaxOptions.result_success_msg];}
			return msg;
		},
		/**
		 * 获取ajax调用后台失败的返回信息,这个是惊醒提示的
		 */
		ajaxFailMsg:function(result,options){
			var msg = "失败";
			if(!Extra.isEmpty(options,AjaxOptions.default_fail_msg))msg=options[AjaxOptions.default_fail_msg];
			if (!Extra.isEmpty(result, AjaxOptions.result_fail_msg)){msg = result[AjaxOptions.result_fail_msg];}
			return msg;
		},
		/**
		 * ajax的提示
		 * 
		 */
		ajaxTip:function(title,msg,icon){
			$.messager.show(
				{
					title : title,
					msg : msg,
					timeout : 2000,
					showType : 'slide'
				}		
			);
		}
};

var ExtraObject = function(extraObject){
	this.extraObject = extraObject;
	this.id = this.extraObject.id;
}

ExtraObject.prototype["equals"]=function(id){
	if(!id.startsWith("#")){
		id = "#"+id;
	}
	if(this.id==id){
		return true;
	}else{
		return false;
	}
}

/**
 * 当前正在使用的dialog历史记录
 */
var ExtraHistory = {
	arr:[],//存放dialog
	add:function(extraObject){
		this.arr.push(new ExtraObject(extraObject));
	},
	//可以获取
	get:function(index){
		if(Extra.isEmpty(index)){
			index = 0;
		}
		return this.arr[index].extraObject;
	},
	//删除
	del:function(idOrIndex){
		try{
			if(typeof (idOrIndex)=="string"){
				for(var i in this.arr){
					if(this.arr[i].equals(idOrIndex)){
						return this.del(parseInt(i));
					}
				}
			}else if(typeof (idOrIndex)=="number"){
				return this.arr.splice(idOrIndex,1);
			}
		}catch(e){}
	}
}

var DatagridOptions = {
	search:"search",
	add:"add",
	view:"view",
	update:"update",
	deletes:"deletes",
	
	dialog:"dialog",
};

var FormOptions = {
	form_input_selector:":input[name][znone!='true']:enabled",
};

var AjaxOptions = {
	success:"success",//判断ajax调用是否成功所需要的字段,返回的是一个boolean值最好,如果不是需要修改相关的引用方法
	
	result_success_msg:"msg",//ajax调用成功后,执行成功的提示信息获取用到的key
	result_fail_msg:"msg",//ajax调用成功后,执行失败的提示信息获取用到的key
	
	default_success_msg:"success_msg",//ajax调用成功后,执行成功提示信息获取用到的默认key
	default_fail_msg:"fail_msg",//ajax调用成功后,执行失败提示信息获取用到的默认key
	
	progressText:"progressText",
};

var CallbackOptions = {
	callbackPostSuccess:"callbackPostSuccess",
	callbackPostFail:"callbackPostFail",
	
	callbackParam:"callbackParam",
	callbackSubParam:"callbackSubParam",
	
	callbackValid:"callbackValid",
	callbackSubValid:"callbackSubValid",
	
	defaultCallbackSuccess:"defaultCallbackSuccess",
	callbackSuccess:"callbackSuccess",
	callbackSubSuccess:"callbackSubSuccess",
};
