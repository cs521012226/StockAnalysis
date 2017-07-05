;(function ($) {
	String.prototype.format = function(args){
	    var result = this;
	    if (arguments.length > 0){    
	        if (arguments.length == 1 && typeof args == 'object'){
	            for(var key in args) {
	                if(args[key] != undefined){
	                    var reg = new RegExp('({' + key + '})', 'g');
	                    result = result.replace(reg, args[key]);
	                }
	            }
	        }else{
	        	for(var i = 0; i < arguments.length; i++){
	        		if (arguments[i] != undefined) {
	        			var reg= new RegExp('({)' + i + '(})', 'g');
	        			result = result.replace(reg, arguments[i]);
	                }
	            }
	        }
	    }
	    return result;
	};
	
	var SY = window.SY = window.SY || {
		/**
		 * 获取随机16进制字符串
		 * @param len	默认8位
		 * @returns
		 */
		uuid : function(prefix, len){
			if(typeof prefix != 'string'){
				prefix = '';
			}
			if(!len){
				len = 8;
			}
			var guid = [prefix];
			// 获取16进制随机数
			for(var i = 1 ; i <= len ; i++){
				var n = Math.floor(Math.random() * 16.0).toString(16);
				guid.push(n);
			}
			return guid.join('');
		},
		/**
		 * 获取该元素偏移量
		 */
		getOffset : function(e){
			var top = e.offsetTop; 
			var left = e.offsetLeft;
			var parent = e.offsetParent;
			while(parent){
				top += parent.offsetTop; 
				top += parent.clientTop;
				
				left += parent.offsetLeft;
				left += parent.clientLeft;
				parent = parent.offsetParent;
			}
			return {left : left , top : top};
		},
		/**
		 * 对象深复制
		 */
		clone : function(obj){
			if(typeof obj !== 'object'){
				return obj;
			}
			var clone = {}; 
			if(Object.prototype.toString.call(obj) == '[object Array]'){   
				clone = [];
			}
			for(var key in obj){  
				clone[key] = SY.clone(obj[key]);
			}
			return clone;
		},
		TYPE_SEQ : 'seq',
		TYPE_DATE : 'date',
		TYPE_NUMBER : 'number'
	};
	
	/**
	 * 所有用defineComp定义的组件，都默认继承该函数
	 */
	var BaseFn = function(){};
	BaseFn.prototype = {
		/**
		 * 默认设置
		 */
		defaults : {
			version : 'sy-component-1.0',
			idPrefix : 'sy-comp-base-',
		},
		/**
		 * 事件方法容器
		 */
		listeners : {},
		/**
		 * 初始化化方法，每次new组件时都会调用它
		 * @param param
		 */
		_init : function(param){
			param = param || {};
			// 初始化配置(深度合并defaults)
			this.conf = $.extend(true, {}, this.defaults, param);
			delete this.defaults;
			// 初始化监听事件
			this.listeners = $.extend({}, this.listeners, param.listeners);
			// 初始化id
			this.id = this.conf.id || SY.uuid(this.conf.idPrefix);
			
			//先移除原来已经加载的Dom
			var dom = document.getElementById(this.id);
			if(dom){
				dom.parentNode.removeChild(dom);
			}
			
			delete this.conf.idPrefix;
			// 注册组件
//			SY.regComp(this);
		},
		/**
		 * 获取组件ID
		 * @returns {Boolean}
		 */
		getId : function(){
			return this.id;
		},
		/**
		 * 调用事件
		 * @param method	事件名称
		 * @param args		参数
		 * @returns
		 */
		_fireEvent : function(method, args, args2){
			if(typeof this.listeners[method] == 'function'){
				return this.listeners[method].call(this, args, args2);
			}
			return null;
		},
		/**
		 * 关闭组件
		 */
		close : function(){
			//从容器移除组件
//			SY.removeComp(this);
		}
	};
	
	SY.core = {
		/**
		 * 自定义继承
		 * @param parentFn	类型为Function
		 * @param custom	类型为Object
		 * @returns {Function}
		 */
		extend : function(parentFn, custom){
			var temp = function(){};
			var parentPro = temp.prototype = parentFn.prototype;
			temp = new temp();
			temp._super = parentPro;
			
			var model = function(param){
				var stack = [], stackFn = null, _super = this._super;
				while(_super){
					if(typeof _super._init == 'function'){
						stack.push(_super._init);
					}
					_super = _super._super;
				}
				//先调用所有父类的_init方法
				while(stackFn = stack.pop()){
					stackFn.call(this, param);
				}
				//再调用自己的_init方法
				this._init(param);
			};
			model.prototype = $.extend(temp, custom);
			model.prototype.constructor = model;
			model.constructor = model;
			
			$.extend(model, {
				getInstance : function(conf){
					return new this.constructor(conf);
				}
			});
			return model;
		},
		/**
		 * 定义组件，默认继承BaseFn函数
		 */
		defineComp : function(obj){
			return this.extend(BaseFn, obj);
		}
	};
	
})(Zepto, window);