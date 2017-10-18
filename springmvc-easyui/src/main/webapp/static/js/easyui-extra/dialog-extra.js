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
	var _this = this;// 当前function
	this.id = "#" + dialogId_;// dialog的Id
	this.envOptions = {
		success_msg : "保存成功.",
		fail_msg : "保存失败.",
		callbackPostSuccess : function() {
		},
		callbackPostFail : function() {
		},
		defaultCallbackSuccess:_this.ajaxSuccessDialog
	};
	// 2.定义一些常用的方法

	/**
	 * 初始化方法 init_options={dialogId:"dialog的Id"}
	 */
	this.init = function(init_options) {
		if (!Extra.isEmpty(init_options, "dialogId")) {
			_this.id = _this.setId(init_options["dialogId"]);
		}
	}

	/**
	 * 设置dialog的Id
	 * 
	 */
	this.setId = function(setId_dialogId) {
		if (!Extra.isEmpty(setId_dialogId)) {
			_this.id = "#" + setId_dialogId;
		}
	}

	/**
	 * 获取dialog的属性信息
	 * 
	 */
	this.options = function() {
		return $(_this.id).dialog().dialog("options");
	}

	/**
	 * 打开dialog
	 */
	this.open = function() {
		$(_this.id).dialog('open');
		return false;
	}

	/**
	 * 关闭dialog
	 */
	this.close = function() {
		$(_this.id).dialog("close");
		return false;
	}

	/**
	 * 找到form表单
	 */
	this.getForm = function(a_) {
		// 1.找到form表单(怎么找呢？【可以分为2中,一种是直接找,另一种是根据Id查找】;Id从什么地方而来呢？【从选择器的属性中来，属性名字叫做form_id】)
		// 2.找到form表单,先选取第二种,如果没有form_id,再用第一中方法
		var $form;
		var form_id = $(a_).attr("form_id");
		if (Extra.isEmpty(form_id)) {
			$form = $(_this.id).closest("div").find("form:eq(0)");
		} else {
			$form = $("#" + form_id);
		}
		return $form;
	}

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
	this.save = function(save_a, eoptions) {
		if(Extra.isEmpty(eoptions)){eoptions={};}
		// 1.找到form表单
		var $form = _this.getForm(save_a);
		if ($form == undefined) {
			return;
		}
		// 2.验证form表单
		if (!ExtraAjax.validForm($form))
			return;

		eoptions["progress.text"] = "保存中....";
		_this.envOptions["success_msg"]="保存成功";
		_this.envOptions["fail_msg"]="保存失败";
		
		// 3.ajax参数收集
		var ajaxOptions = ExtraAjax.ajaxOptions($form, eoptions);

		// 设置默认的回调函数
		ajaxOptions["defaultCallbackSuccess"] = _this.ajaxSuccessDialog;

		
		
		// 4.ajax调用
		ExtraAjax.ajax(ajaxOptions);
	}

	/**
	 * ajax调用成功后执行的方法 result:ajax成功返回的值 options,可能携带了一些东西
	 */
	this.ajaxSuccessDialog = function(result, options) {
		try {
			// 1.后台是否执行成功
			if (result.success) {
				var msg = _this.envOptions["success_msg"];
				if (!Extra.isEmpty(result, "msg"))
					msg = result["msg"];

				// 提示
				$.messager.alert("成功提示", msg, "info");

				// 额外工作
				_this.close();

				// 额外函数调用
				if (!Extra.isEmpty(options, "callbackSubSuccess")) {
					var callbackSubSuccess = options["callbackSubSuccess"];
					callbackSubSuccess();
				}

				// 环境定义的函数
				if (!Extra.isEmpty(_this.envOptions, "callbackPostSuccess")) {
					_this.envOptions["callbackPostSuccess"]();
				}

			} else {
				var msg = _this.envOptions["fail_msg"];
				if (!Extra.isEmpty(result, "msg"))
					msg = result["msg"];

				// 提示
				$.messager.alert("失败提示", msg, "error");

				// 环境定义的函数
				if (!Extra.isEmpty(_this.envOptions, "callbackPostFail")) {
					_this.envOptions["callbackPostFail"]();
				}
			}
		} catch (e) {
		}
	}

	/**
	 * 更新form表单 可扩展,
	 */
	this.update = function(a_update) {
		var eoptions = {};
		eoptions["progress.text"] = "更新中....";
		_this.envOptions["success_msg"]="更新成功";
		_this.envOptions["fail_msg"]="更新失败";
		_this.save(a_update,eoptions);
	}

	/**
	 * 清空dialog中的内容
	 */
	this.clearForm = function() {

	}

	/**
	 * 关闭按钮事件绑定方法 selector=[a[tag='close'],a[tag='closeAndTip']]
	 */
	this.btClose = function() {
		var options = _this.options();
		if (!Extra.isEmpty(options, "buttons")) {
			// 绑定bt中的close事件,直接关闭dialog
			$(options["buttons"]).find("a[tag='close']").each(function() {
				if ($(this).attr("bindclick") == undefined) {
					$(this).attr("bindclick", true).bind('click', function() {
						_this.close();
					});
				}
			});
			// 绑定bt中的closeAndTip事件,关闭dialog并提示
			$(options["buttons"]).find("a[tag='closeAndTip']").each(function() {
				if ($(this).attr("bindclick") == undefined) {
					$(this).attr("bindclick", true).bind('click', function() {
						// TODO tip,提示
						// 关闭窗体
						_this.close();
					});
				}
			});

		} else {
		}
	}

	/**
	 * 保存按钮事件绑定 selector=a[tag='save'] or a[tag='update']
	 */
	this.btSave = function() {
		var options = _this.options();
		if (!Extra.isEmpty(options, "buttons")) {
			// 绑定bt中的保存事件,
			$(options["buttons"]).find("a[tag='save']").each(function() {
				if ($(this).attr("bindclick") == undefined) {
					$(this).attr("bindclick", true).bind('click', function() {
						_this.save($(this));
					});
				}
			});
			// 绑定bt中的更新事件,
			$(options["buttons"]).find("a[tag='update']").each(function() {
				if ($(this).attr("bindclick") == undefined) {
					$(this).attr("bindclick", true).bind('click', function() {
						_this.update($(this));
					});
				}
			});
		}
	}

	/**
	 * Id是否为guid
	 */
	this.isIdGuid = function() {
		// #dcafb407-96c9-8131-f35c-4849564b5833
		if (_this.id.length == 37) {
			var idArrs = _this.id.split("-");
			if (idArrs.length == 5 && idArrs[0].length == 9
					&& idArrs[1].length == 4 && idArrs[2].length == 4
					&& idArrs[3].length == 4 && idArrs[4].length == 12) {
				return true;
			}
		}
		return false;
	}

	/**
	 * dialog关闭时调用的方法
	 */
	this.onClose = function() {
		$(_this.id).dialog({
			onClose : function() {
				// 当是自己动态创建的dialog的时候就给他删除了
				if (_this.isIdGuid()) {
					$(_this.id).dialog("destroy");
				}
			}
		});
	}

	/**
	 * 绑定事件方法 eventName事件名称,
	 */
	this.bindEvent = function(eventName) {
		if (Extra.isEmpty(eventName)) {
			return false;
		}
		switch (eventName) {
		case "btClose":// 绑定bt中的关闭按钮事件
			_this.btClose();
			break;
		case "btSave":// 绑定bt中的保存按钮事件
			_this.btSave();
			break;
		case "onClose":// dialog关闭时触发的事件
			_this.onClose();
			break;
		}
		return false;
	}

	// 3.绑定一些事件
	// 3.1关闭标签绑定事件
	this.bindEvent("btClose");
	// 3.2保存/更新标签绑定事件
	this.bindEvent("btSave");
	// 绑定清空事件，在关闭dialog的时候
	this.bindEvent("onClose");
	//

}