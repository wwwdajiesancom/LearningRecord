var extra = {
	/**
	 * 产生随机guid
	 */
	guid :function () {
		function S4() {
			return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		}
		return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
	},
	int:function(str){
		try{
			return parseInt(str);
		}catch(e){
			return str;
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
	 * 去除两边的seg
	 * 默认是去除空格
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
	/**
	 * 判断是否为空
	 * 分为两种判断：
	 * 第一种是传递一个参数,为string,boolean,number
	 * 第二种是传递二个参数,第一个参数为{},第二个为string(其实是一个键值)
	 */
	isEmpty : function(isEmpty_options,isEmpty_jsonkey){
		//1.先判断第一个参数
		if(isEmpty_options==undefined||isEmpty_options==null||isEmpty_options==''){
			return true;
		}
		//2.如果类型分为object,就需要用到第二个参数了
		if(typeof (isEmpty_options) == "object"){
			if(this.isEmpty(isEmpty_jsonkey)){
				return false;
			}
			var _value = isEmpty_options[isEmpty_jsonkey];
			if(this.isEmpty(_value)){
				return true;
			}
			//不为空
			return false;
		}
		//3.可能是string,number,boolean
		return false;
	},
	/**
	 * 获取属性,转变成{}
	 * 里面的属性都是用;隔开的,键与值之间是用:分开的
	 * 例子:width: 350px;height: 150px;
	 */
	getAttr:function(attr_str){
		var result = {};
		//1.处理属性字符串,目前按照默认,不进行处理
		//2.分割
		if(this.isEmpty(attr_str)){
			return result;
		}
		//2.1分割开用;(分号)隔开的字符串
		var attrs = attr_str.split(";");
		for(var i=0;i<attrs.length;i++){
			var itemAttr = attrs[i];
			if(!this.isEmpty(itemAttr)){
				this.splitAttr(result,itemAttr);
			}
		}
		//去掉px
		this.setEasyuiSize(result);
		//处理条件
		this.setEasyuiQueryParams(result);
		//content设置
		this.setEasyuiContent(result);
		//将boolean值转换过来
		this.setEasyuiBoolean(result);
		return result;
	},
	/**
	 * 获取按钮Html
	 */
	getButtonsHtml:function(parentId,buttons_){
		var start_html = "<div id='"+parentId+"_bt'>";
		var center_html = "";
		var end_html = "</div>";
		//1.默认有一个关闭
		if(this.isEmpty(buttons_)){
			buttons_="close";
		}
		//2.拆分
		buttons_ = buttons_.split(",");
		for(var i=0;i<buttons_.length;i++){
			var item = this.trim(buttons_[i]);
			//2.1是否包含属性,就是最后一个字符是]
			if(item.charAt(item.length-1)=="]"){
				var zkhIndex = item.indexOf("[");
				//button的tag类型
				var but_type = item.substring(0,zkhIndex);
				//button的属性
				var but_attr = item.substring(zkhIndex+1,item.length-1);
				//拆分属性,例子[value:确定;]
				var but_attrs = but_attr.split(";");
				//存放button的属性
				var options = {};
				for(var j = 0;j<but_attrs.length;j++){
					this.splitAttr(options,but_attrs[j]);
				}
				//获取button的html
				center_html += this.getButtonHtml(but_type,options);
			}else{
				center_html += this.getButtonHtml(item);
			}
		}
		return start_html + center_html + end_html;
	},
	getButtonHtml:function(buttonType,options_){
		var start_html = "<a href='#' class='easyui-linkbutton' tag='"+buttonType+"' ";
		var a_val = "";
		var a_attr = "";
		var end_html = "</a>";
		//1.验证按钮是否合法
		switch(buttonType){
			case "save":
				if(!this.isEmpty(options_,"value")){
					a_val = options_["value"];
					delete options_["value"];
				}else{					
					a_val = "保存";
				}
				break;
			case "close":
				if(!this.isEmpty(options_,"value")){
					a_val = options_["value"];
					delete options_["value"];
				}else{					
					a_val = "关闭";
				}
				break;
			case "cancel":
			default:return "";
		}
		//2.遍历属性
		if(!this.isEmpty(options_)){
			for(var key in options_){
				a_attr += " "+key+"='"+options_[key]+"' " 
			}
		}
		return start_html + a_attr + ">" + a_val + end_html;
	},
	/**
	 * 分割开一个具体的属性(分开具体的键值对)
	 * 因为会出现其它的:(冒号);例如:href:'http://www.baidu.com'
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
			//当前仅仅只判断一方存在:(冒号)
			//键中包含:(冒号)
			if(itemAttr.charAt(0)=="'"){
				var mhIndex = itemAttr.lastIndexOf(":");
				var key = this.trim(this.trim(itemAttr.substring(0,mhIndex)),"'");
				var value = this.trim(itemAttr.substring(mhIndex+1));
				result[key]=value;
			}else{
				//值中包含:(冒号)
				var mhIndex = itemAttr.indexOf(":");
				var key = this.trim(itemAttr.substring(0,mhIndex));
				var value = this.trim(this.trim(itemAttr.substring(mhIndex+1)),"'");
				result[key]=value;
			}
		}
	},
	setEasyuiSize:function(options_){
		if(!this.isEmpty(options_)){
			if(!this.isEmpty(options_["width"])){
				options_["width"]=this.int(this.trim(options_["width"],"px"));
			}
			if(!this.isEmpty(options_["height"])){
				options_["height"]=this.int(this.trim(options_["height"],"px"));
			}
		}
	},
	setEasyuiSizePx:function(options_){
		this.setEasyuiSize(options_);
		if(!this.isEmpty(options_)){
			if(!this.isEmpty(options_["width"])){
				options_["width"]=options_["width"]+"px";
			}
			if(!this.isEmpty(options_["height"])){
				options_["height"]=options_["height"]+"px";
			}
		}
	},
	setEasyuiQueryParams:function(options_){
		
	},
	setEasyuiBoolean:function(options_){
		if(!this.isEmpty(options_,"modal")){
			if(options_['modal']=="true"){
				options_['modal']=true;
			}else{
				options_['modal']=false;
			}
		}
		
	},
	setEasyuiContent:function(options_){
		if(!this.isEmpty(options_,'content')){
			options_["content"] = "<iframe src='"+options_["content"]+"' width='100%' height='100%' frameborder='0' scrolling='auto'></iframe>";
		}
	},
	createDialog :function(id,options_,buttons_html){
		var dialog_html = "<div id='"+id+"' >";
		dialog_html += buttons_html;
		dialog_html += "</div>";
		return dialog_html;
	},
}

/**
 * easyui事件绑定方法
 * 
 * tag:目标,标记当前按钮想干什么,可以打开dialog等.[dialog,]
 *
 * attr:存放属性;例子如下:attr="width:500px;height:250px;"
 * attr中的属性,最好别有（冒号，分号）,就是不要有多余的,除了必要的时候,不要带
 * 
 * buttons:存放dialog拥有的按钮,例子如下:buttons="save[value:保存],close[value:关闭;]"
 * buutons中不要带多余的东西,如果想修改a中的内容就按照close中的模版修改;value代表的是a中的内容,可以携带其它的属性,这些属性都会添加到a中
 * 
 * toolbar:存放dialog的最上面的按钮，例子:toolbar="add"
 * 这个功能暂时不实现了
 * 
 * @returns
 */
function easyui_dialog_event_bind(){
	//1.绑定dialog
	$("a[tag='dialog']").each(function(){
		if($(this).attr("bindclick")==undefined){
			$(this).attr("bindclick",true).bind("click",function(){
				//dialog的Id
				var bind_id = extra.guid();
				//1.找到相关的属性方法
				var attr = $(this).attr("attr");
				//格式化
				var attrOptions = extra.getAttr(attr);
				//弹出框的buttons属性
				var buttons = $(this).attr("buttons");
				var buttons_html = extra.getButtonsHtml(bind_id,buttons);
				attrOptions["buttons"] = "#"+bind_id+"_bt";
				
				//2.给当前的容器增加一个dialog代码
				//生成dialog的Html
				var dialog_html = extra.createDialog(bind_id,attrOptions,buttons_html);
				if($(this).closest("div").length==0){
					if($(this).closest("body").length==0){
						if($(this).closest("form").length==0){
							//不知道父元素是什么,不做处理了
							return false;
						}else{
							$(this).closest("form").append(dialog_html);
						}
					}else{
						$(this).closest("body").append(dialog_html);
					}
				}else{
					$(this).closest("div").append(dialog_html);
				}
				
				//3.dialog初始化
				$.parser.parse("#" + bind_id + "_bt");
				$("#"+bind_id).dialog(attrOptions);

				//绑定其它的事件
				var nowDialog = new DialogExtra(bind_id);				
				return false;
			});		
		}
	});
}

/**
 * 第一种执行绑定方式
 * @returns
 */
$(function(){
	easyui_dialog_event_bind();
});

/**
 * 第二种绑定执行方式
*/
/*$.parser.onComplete = function(context){
	easyui_dialog_event_bind();
};*/