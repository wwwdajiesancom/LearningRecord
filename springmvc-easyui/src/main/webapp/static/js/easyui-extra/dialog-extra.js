function DailogSimpleExtra(dialogId_,options_){
	if(Extra.isEmpty(options_)){options_={};}
	options_["init"]=false;
	
	return new DialogExtra(dialogId_,options_);
}

/*
 * 该类封装了Dialog对象里面
 * 1.事件绑定
 * 2.事件处理（有一个Ajax提交）
 * 
 * dialogId_:dialog的Id
 * options_={
 * 
 * }
 * */
function DialogExtra(dialogId_, options_) {
	// 1.定义一些常用的变量
	this.id = "#" + dialogId_;// dialog的Id
	if(Extra.isEmpty(options_)){options_={};}
	if(Extra.isEmpty(options_,"init")){options_["init"]=true;}
	this.envOptions = {};
	this.envOptions[AjaxOptions.default_success_msg] = "成功";
	this.envOptions[AjaxOptions.default_fail_msg] = "失败";
	this.envOptions[CallbackOptions.callbackPostSuccess] = function(){};
	this.envOptions[CallbackOptions.callbackPostFail] = function(){};	
	this.envOptions[CallbackOptions.defaultCallbackSuccess] = this.ajaxSuccessDialog;
		
	// 2.定义一些常用的方法
	
	if(options_["init"]){
		this.init();
	}
}

/**
 * 获取dialog的属性信息
 */
DialogExtra.prototype["options"] = function() {
	return $(this.id).dialog("options");
};

/**
 * 打开dialog
 */
DialogExtra.prototype["open"] = function() {
	$(this.id).dialog('open');
	return false;
};

/**
 * 关闭dialog
 */
DialogExtra.prototype["close"] = function() {
	$(this.id).dialog("close");
	return false;
};

/**
 * 找到form表单
 */
DialogExtra.prototype["getForm"] = function(target) {
	// 1.找到form表单(怎么找呢？【可以分为2中,一种是直接找,另一种是根据Id查找】;Id从什么地方而来呢？【从选择器的属性中来，属性名字叫做form_id】)
	// 2.找到form表单,先选取第二种,如果没有form_id,再用第一中方法
	//这里面其实是可以通过target来查找form表单的,不过这里面我们为了简单处理,没有做,如果想做的话,请修改bind-extra.js中的getButton方法
	var $form;
	$form = $(this.id).find("form:eq(0)");
	return $form;
};


/**
 * 保存form表单信息 form的例子： <form action="${contextPath}/ajax/dialog/view.json"
 * id="from_id" contentType="" method="post" callbackValid=""
 * callbackSuccess="" callbackSubSuccess="" callbackParam=""
 * callbackSubParam="">
 * 
 * 
 * selector=a[tag='save'];选择器 可扩展, form_id:save标签的一个属性,标记是一个form表单的id
 * 
 * 对form表单的要求: action,method,必填
 * contentType=json;可选,如果是这种类型,提交的contentType:application/json,默认为"application/x-www-form-urlencoded"
 * callbackSuccess:可选,成功后的回调函数,有一个默认的（解释：因为ajax的返回值结构不一定，所以提供了一种自定义的处理函数，该函数的参数就是ajax的返回结果：function(result){}）
 * callbackSubSuccess:可选,使用默认的回调函数,但是在后台执行成功的时候,调用该回调函数（解释：默认的函数可以处理固定的结构，但是成功之后的操作就不知道了，所以这个函数是处理成功之后的操作的,没有参数,function(){}）
 * callbackParam:可选,参数的处理函数,返回一个{};因为ajax的参数有时候比较复杂,需要单独的处理,function(){return
 * {};}
 * callbackSubParam:可选,参数处理的部分函数,返回一个{},还使用默认的参数处理,但是因为一些特殊的参数不好处理,就交给自定一的参数函数处理,最终将她们合并了，function(){return
 * {};} callbackValid:验证函数,作为form表单的验证,function(){return false;}
 * 
 * eoptions={},额外的参数:
 * 
 * progress.text:可选,ajax调用前的提示信息,
 * 
 * 
 */
DialogExtra.prototype["save"] = function(target, eoptions) {
	if(Extra.isEmpty(eoptions)){eoptions={};}
	// 1.找到form表单
	var $form = this.getForm();
	if ($form == undefined) {
		return;
	}
	// 2.验证form表单
	if (!ExtraAjax.validForm($form))
		return;		
	
	this.getButtonText(target,eoptions);
	
	// 3.ajax参数收集
	var ajaxOptions = ExtraAjax.ajaxOptions($form, eoptions);

	// 设置默认的回调函数
	ajaxOptions[CallbackOptions.defaultCallbackSuccess] = this.ajaxSuccessDialog;
	ajaxOptions["oldTarget"]=this;
	
	// 4.ajax调用
	ExtraAjax.ajax(ajaxOptions);
};

/**
 * ajax调用成功后执行的方法 result:ajax成功返回的值 options,可能携带了一些东西
 */
DialogExtra.prototype["ajaxSuccessDialog"] = function(result, options) {
	try {
		// 1.后台是否执行成功
		if(ExtraAjax.ajaxSuccessStatus(result)){
			var msg = ExtraAjax.ajaxSuccessMsg(result,options["oldTarget"].envOptions);
			// 提示
			ExtraAjax.ajaxTip("成功提示", msg, "info");

			// 额外工作
			options["oldTarget"].close();

			// 额外函数调用
			ExtraAjax.ajaxSuccessSubCallback(options);
			//后置函数
			ExtraAjax.ajaxSuccessPostCallback(options["oldTarget"].envOptions);
		} else {
			//失败提示信息
			var msg = ExtraAjax.ajaxFailMsg(result,options["oldTarget"].envOptions);
			// 提示
			ExtraAjax.ajaxTip("失败提示", msg, "error");
			//后置函数
			ExtraAjax.ajaxFailPostCallback(options["oldTarget"].envOptions);
		}
	} catch (e) {
	}
};

/**
 * 获取button按钮的信息
 */
DialogExtra.prototype["getButtonText"] = function(target,eoptions){
	if(typeof (target) == "number"){
		eoptions[AjaxOptions.progressText] = this.options()["buttons"][target]["text"]+"中....";
		this.envOptions[AjaxOptions.default_success_msg]=this.options()["buttons"][target]["text"]+"成功";
		this.envOptions[AjaxOptions.default_fail_msg]=this.options()["buttons"][target]["text"]+"失败";
	}else{
		eoptions[AjaxOptions.progressText] = target.text()+"中....";
		this.envOptions[AjaxOptions.default_success_msg]=target.text()+"成功";
		this.envOptions[AjaxOptions.default_fail_msg]=target.text()+"失败";
	}
};

/**
 * 更新form表单 可扩展,
 */
DialogExtra.prototype["update"] = function(target,eoptions) {
	if(Extra.isEmpty(eoptions)){eoptions={};}
	// 1.找到form表单
	var $form = this.getForm();
	if ($form == undefined) {
		return;
	}
	
	// 2.验证form表单
	if (!ExtraAjax.validForm($form))
		return;

	this.getButtonText(target,eoptions);
			
	// 3.ajax参数收集
	var ajaxOptions = ExtraAjax.ajaxOptions($form, eoptions);

	// 设置默认的回调函数
	ajaxOptions[CallbackOptions.defaultCallbackSuccess] = this.ajaxSuccessDialog;
	ajaxOptions["oldTarget"]=this;
	
	// 4.ajax调用
	ExtraAjax.ajax(ajaxOptions);
}
;

/**
 * 关闭按钮事件绑定方法 selector=[a[tag='close'],a[tag='closeAndTip']]
 */
DialogExtra.prototype["btClose"] = function() {
	var _this = this;
	var options = this.options();
	if (!Extra.isEmpty(options, "buttons")&&Extra.isString(options["buttons"])) {
		// 绑定bt中的close事件,直接关闭dialog
		// 绑定bt中的closeAndTip事件,关闭dialog并提示
		$(options["buttons"]).undelegate("a[tag='close'],a[tag='closeAndTip']","click").delegate("a[tag='close'],a[tag='closeAndTip']","click",function(){
			_this.close();
		});
	} else {
	}
};

/**
 * 保存按钮事件绑定 selector=a[tag='save'] or a[tag='update']
 */
DialogExtra.prototype["btSave"] = function() {
	var _this = this;
	var options = this.options();
	if (!Extra.isEmpty(options, "buttons")&&Extra.isString(options["buttons"])) {
		// 绑定bt中的保存事件,
		$(options["buttons"]).undelegate("a[tag='save']","click").delegate("a[tag='save']","click",function(){
			_this.save($(this));
		});
		// 绑定bt中的更新事件,
		$(options["buttons"]).undelegate("a[tag='update']","click").delegate("a[tag='update']","click",function(){
			_this.update($(this));
		});
	}
};

/**
 * Id是否为guid
 */
DialogExtra.prototype["isIdGuid"] = function() {
	// #dcafb407-96c9-8131-f35c-4849564b5833
	if (this.id.length == 37) {
		var idArrs = this.id.split("-");
		if (idArrs.length == 5 && idArrs[0].length == 9
				&& idArrs[1].length == 4 && idArrs[2].length == 4
				&& idArrs[3].length == 4 && idArrs[4].length == 12) {
			return true;
		}
	}
	return false;
};

/**
 * 销毁dialog
 */
DialogExtra.prototype["destroy"] = function(){
	// 当是自己动态创建的dialog的时候就给他删除了
	if (this.isIdGuid()) {
		$(this.id).dialog("destroy");
	}
};

/**
 * 绑定事件方法 eventName事件名称,
 */
DialogExtra.prototype["bindEvent"] = function(eventName) {
	if (Extra.isEmpty(eventName)) {
		return false;
	}
	switch (eventName) {
	case "btClose":// 绑定bt中的关闭按钮事件
		this.btClose();
		break;
	case "btSave":// 绑定bt中的保存按钮事件
		this.btSave();
		break;
	}
	return false;
};


/**
 * 初始化绑定事件
 */
DialogExtra.prototype["init"] = function(){
	// 3.绑定一些事件
	// 3.1关闭标签绑定事件
	this.bindEvent("btClose");
	// 3.2保存/更新标签绑定事件
	this.bindEvent("btSave");
};
