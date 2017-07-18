;(function ($) {
	var SY = window.SY = window.SY || {};
	
	/**
	 * Modal模态窗口控件
	 */
	SY.Modal = SY.core.defineComp({
		defaults : {
			version : 'SY-WG-Modal-1.0',
			idPrefix : 'SY-WG-Modal-',
			appendTo : null,
			targetBody : null,		//目标体
			title : '',
			body : '',
			modalStyle : {
				modal : 'modal-primary',
				success : 'modal-message modal-success',
				warn : 'modal-message modal-warning',
				error : 'modal-message modal-danger',
				confirm : 'modal-message modal-warning'
			},
			/*buttons : [{
				icon : '',
				label : '',
				fn : function(){}
			}]*/
			buttons : [],
			width : 0,
			hasCancel : true,		//是否默认有关闭按钮
			areaclose : false		//区域关闭（点击阴影部分是否关闭窗口）
		},
		Template : {
			box : ['<div id="{id}" class="sy-wg-modal">',
			       '<div class="modal {type}">',
			       	'<div data-backdrop class="sy-backdrop"></div>',
                    '<div class="modal-dialog">',
                        '<div data-content class="modal-content">',
                            '<div data-header class="modal-header">',
                                /*'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>',*/
                                '<h4 data-title class="modal-title">{title}</h4>',
                            '</div>',
                            '<div data-body class="modal-body">{body}</div>',
                            '<div data-footer class="modal-footer">{buttons}</div>',
                        '</div>',
                    '</div></div>',
                '</div>'].join(''),
            closeBtn : '<button data-btn-type="close" type="button" class="btn btn-warning" data-dismiss="modal">关闭</button>',
            btn : '<button data-btn-index="{index}" type="button" class="{icon}">{label}</button>'
		},
		_init : function(){
			this._draw();
		},
		_draw : function(data){
			var html = [], _this = this, conf = this.conf, box = null;
			
			if(conf.appendTo){
				box = (typeof conf.appendTo == 'string') ? $('#' + conf.appendTo) : $(conf.appendTo);
			}else{
				box = $(document.body);
			}
			
			html.push(_this.Template.box.format({
				id : _this.id,
				type : function(){
					var rs = conf.modalStyle[conf.type];
					return rs ? rs : conf.modalStyle.modal;
				},
				title : conf.title,
				body : conf.body || '',
				buttons : function(){
					var rs = [], btn = null;
					
					for(var i=0; i<conf.buttons.length; i++){
						btn = conf.buttons[i];
						rs.push(_this.Template.btn.format({
							index : i,
							icon : btn.icon || 'btn btn-primary',
							label : btn.label || '提交'
						}));
					}
					//是否需要显示默认关闭按钮
					if(conf.hasCancel){
						rs.push(_this.Template.closeBtn);
					}
					return rs.join('');
				}
			}));
			
			box.append(html.join(''));
			
			this.box = $('#' + _this.id);
			this.dataTitle = this.box.find('[data-title]');
			this.dataBody = this.box.find('[data-body]');
			this.dataFooter = this.box.find('[data-footer]');
			this.dataBackdrop = this.box.find('[data-backdrop]');
			
			if(conf.width > 0){
				this.box.find('.modal-dialog').width(conf.width);
			}
			
			//绑定事件
			this._bindEvent();
		},
		_bindEvent : function(){
			var _this = this, btnCfg = this.conf.buttons, conf = this.conf;
			
			//事件委托监听Footer里面的按钮事件
			_this.dataFooter.click(function(event){
				
				var target = event.target, btnIndex = null, btnType = null;
				while(target){
					btnIndex = target.getAttribute('data-btn-index');
					if(btnIndex && typeof btnCfg[btnIndex].fn == 'function'){
						btnCfg[btnIndex].fn();
						return ;
					}
					
					btnType = target.getAttribute('data-btn-type');
					if(btnType == 'close'){
						_this.hide();
						return ;
					}
					if(this == target){
						return ;
					}
					target = target.parentNode;
				}
			});
			
			//监听阴影事件
			_this.dataBackdrop.click(function(){
				if(conf.areaclose){
					_this.hide();
				}
			});
		},
		setBody : function(body){
			this.dataBody.html(body);
		},
		show : function(){
			var targetBody = this.conf.targetBody;
			if(targetBody){
				this.dataBody.append($(targetBody));
				this.conf.targetBody = null;
			}
			this.box.addClass('open');
			this._fireEvent('onShow');
		},
		hide : function(){
			this.box.removeClass('open');
			this._fireEvent('onHide');
		}
	});
	
	
	/**
	 * 提示消息框
	 */
	SY.Msg = {
		/**
		 * 警告框
		 */
		warn : function(msg, title){
			if(!this._warnModal){
				this._warnModal = SY.Modal.getInstance({
					title : title || '提示信息',
					type : 'warn',
					areaclose : true
				});
			}
			this._warnModal.setBody(msg);
			this._warnModal.show();
		},
		/**
		 * 成功提示框
		 */
		success : function(msg, title){
			var _this = this;
			if(!_this._successModal){
				_this._successModal = SY.Modal.getInstance({
					title : title || '提示信息',
					type : 'success',
					hasCancel : false,
					areaclose : true,
					buttons : [{
						icon : 'btn btn-success',
						label : '确定',
						fn : function(){
							_this._successModal.hide();
						}
					}]
				});
			}
			_this._successModal.setBody(msg);
			_this._successModal.show();
		},
		/**
		 * 错误提示框
		 */
		error : function(msg, title){
			if(!this._errorModal){
				this._errorModal = SY.Modal.getInstance({
					title : title || '错误提示',
					type : 'error',
					hasCancel : false,
					areaclose : true,
					buttons : [{
						icon : 'btn btn-danger',
						label : '关闭',
						fn : function(){
							_this._errorModal.hide();
						}
					}]
				});
			}
			this._errorModal.setBody(msg);
			this._errorModal.show();
		},
		/**
		 * 确认框
		 */
		confirm : function(msg, sureCallback){
			var _this = this;
			if(typeof sureCallback != 'function'){
				sureCallback = function(){};
			}
			if(!_this._confirmModal){
				_this._confirmModal = SY.Modal.getInstance({
					title : '确认提示',
					type : 'confirm',
					buttons : [{
						label : '确定',
						fn : function(){
							_this._confirmModal._callback();
							_this._confirmModal.hide();
						}
					}]
				});
			}
			_this._confirmModal._callback = sureCallback;
			_this._confirmModal.setBody(msg);
			_this._confirmModal.show();
		}
	};
	
})(Zepto, window);