;(function ($) {
	var SY = window.SY = window.SY || {};
	
	/**
	 * 网格grid分页控件
	 */
	SY.Page = SY.core.defineComp({
		defaults : {
			version : 'SY-WG-Page-1.0',
			idPrefix : 'SY-WG-Page-',
			appendTo : null,
			firstBtn : '首页',
			prevBtn : '上一页',
			nextBtn : '下一页',
			lastBtn : '尾页',
			pageNumber : 0,	//当前页数
			pageSize : 10,	//每页显示条数
			totalRow : 0,		//记录总数
			totalPage : 0	//总页数
		},
		Template : {
			box : ['<div class="sy-wg-pagination" id="{id}">',
			'每页<input data-page-size type="number" value="{pageSize}">条，',
			'共<span data-total-row>{totalRow}</span>条，',
			'当前<input data-page-number type="number" value="{pageNumber}">/<span data-total-page>{totalPage}</span>页',
			'&nbsp;&nbsp;',
			'<a data-firstBtn href="javascript:void(0);">{firstBtn}</a>',
			'<a data-prevBtn href="javascript:void(0);">{prevBtn}</a>',
			'<a data-nextBtn href="javascript:void(0);">{nextBtn}</a>',
			'<a data-lastBtn href="javascript:void(0);">{lastBtn}</a>',
			'</div>'].join('')
		},
		_init : function(){
			
			var _this = this, conf = this.conf;
			if(!conf.appendTo){
				throw new Error('分页控件初始化失败');
			}
			_this.box = $(conf.appendTo);
			
			_this._drawBox();
			_this._bindEvent();
			_this.setTotalRow(conf.totalRow);
		},
		/**
		 * 构建DOM
		 */
		_drawBox : function(){
			var _this = this, conf = this.conf;
			_this.box.html(_this.Template.box.format({
				id : _this.getId(),
				pageSize : conf.pageSize,
				totalRow : conf.totalRow,
				pageNumber : conf.pageNumber,
				totalPage : conf.totalPage,
				firstBtn : conf.firstBtn,
				prevBtn : conf.prevBtn,
				nextBtn : conf.nextBtn,
				lastBtn : conf.lastBtn
			}));
			
			//初始化话操作句柄
			var domList = ['data-page-size', 'data-total-row', 'data-page-number', 'data-total-page', 
			               'data-firstBtn', 'data-prevBtn', 'data-nextBtn', 'data-lastBtn'];
			
			var reg = new RegExp('-(\\w)', 'g'), key = null, text = null, rs = null;
			for(var i in domList){
				text = domList[i];
				key = text;
				while(rs = reg.exec(key)){
					key = key.replace(rs[0], rs[1].toUpperCase());
				}
				_this[key] = _this.box.find('[' + text + ']');
			}
		},
		/**
		 * 改变分页的值
		 */
		_changePage : function(){
			var conf = this.conf, _this = this;
			_this.dataPageNumber.val(conf.pageNumber);
			_this.dataPageSize.val(conf.pageSize);
			_this.dataTotalRow.text(conf.totalRow);
			_this.dataTotalPage.text(conf.totalPage);
			
			if(conf.pageNumber <= 1){
				_this.dataFirstBtn.addClass('disabled');
				_this.dataPrevBtn.addClass('disabled');
			}else{
				_this.dataFirstBtn.removeClass('disabled');
				_this.dataPrevBtn.removeClass('disabled');
			}
			
			if(conf.pageNumber >= conf.totalPage){
				_this.dataLastBtn.addClass('disabled');
				_this.dataNextBtn.addClass('disabled');
			}else{
				_this.dataLastBtn.removeClass('disabled');
				_this.dataNextBtn.removeClass('disabled');
			}
			_this._fireEvent('onPageChange', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 绑定事件
		 */
		_bindEvent : function(){
			var _this = this, conf = this.conf;
			_this.dataPrevBtn.click(function(){
				if(conf.pageNumber > 1){
					_this.prevPage();
				}
			});
			_this.dataNextBtn.click(function(){
				if(conf.pageNumber < conf.totalPage){
					_this.nextPage();
				}
			});
			_this.dataFirstBtn.click(function(){
				if(conf.pageNumber > 1){
					_this.firstPage();
				}
			});
			_this.dataLastBtn.click(function(){
				if(conf.pageNumber < conf.totalPage){
					_this.lastPage();
				}
			});
			_this.dataPageSize.on({
				keyup : function(event){
					//回车触发
					if(event.keyCode == 13){
						_this.setPageSize(this.value);
					}
				},
				blur : function(){
					_this._changePage();
				}
			});
			_this.dataPageNumber.on({
				keyup : function(event){
					//回车改变
					if(event.keyCode == 13){
						_this.setPageNumber(this.value);
					}
				},
				blur : function(){
					_this._changePage();
				}
			});
		},
		/**
		 * 上一页
		 */
		prevPage : function(){
			var conf = this.conf;
			conf.pageNumber--;
			if(conf.pageNumber < 0){
				conf.pageNumber = 1;
			}
			this._changePage();
			this._fireEvent('onPrevPage', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 下一页
		 */
		nextPage : function(){
			var conf = this.conf;
			conf.pageNumber++;
			if(conf.pageNumber > conf.totalPage){
				conf.pageNumber = conf.totalPage;
			}
			this._changePage();
			this._fireEvent('onNextPage', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 第一页
		 */
		firstPage : function(){
			var conf = this.conf;
			conf.pageNumber = 1;
			this._changePage();
			this._fireEvent('onFirstPage', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 最后一页
		 */
		lastPage : function(){
			var conf = this.conf;
			conf.pageNumber = conf.totalPage;
			this._changePage();
			this._fireEvent('onLastPage', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 跳到指定页数
		 */
		gotoPage : function(pageNum){
			var conf = this.conf;
			conf.pageNumber = pageNum;
			this._changePage();
			this._fireEvent('onGotoPage', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 设定每页显示条数
		 */
		setPageSize : function(pageSize){
			var conf = this.conf;
			conf.pageSize = parseInt(pageSize);
			if(conf.pageSize < 1){
				conf.pageSize = 1;
			}
			this._rebuildPage();
			this._fireEvent('onSetPageSize', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 设置当前页数
		 */
		setPageNumber : function(pageNumber){
			var conf = this.conf;
			conf.pageNumber = parseInt(pageNumber);
			if(conf.pageNumber < 1){
				conf.pageNumber = 1;
			}
			if(conf.pageNumber > conf.totalPage){
				conf.pageNumber = conf.totalPage;
			}
			this._changePage();
			this._fireEvent('onSetPageNumber', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 设置总记录数
		 */
		setTotalRow : function(totalRow){
			var conf = this.conf;
			conf.totalRow = totalRow;
			this._rebuildPage();
			this._fireEvent('onSetTotalRow', {
				pageNumber : conf.pageNumber,
				pageSize : conf.pageSize
			});
		},
		/**
		 * 获取当前页数
		 */
		getPageNumber : function(){
			return this.conf.pageNumber;
		},
		getPageSize : function(){
			return this.conf.pageSize;
		},
		/**
		 * 重建页数
		 */
		_rebuildPage : function(){
			var conf = this.conf;
			conf.pageNumber = 1;
			conf.totalPage = Math.ceil(conf.totalRow / conf.pageSize);		//计算总页数
			this._changePage();
		}
	});
	
	/**
	 * 网格grid控件
	 */
	SY.Grid = SY.core.defineComp({
		defaults : {
			version : 'SY-WG-Grid-1.0',
			idPrefix : 'SY-WG-Grid-',
			defaultText : '暂无数据显示',
			appendTo : null,	//挂载DOM
			columns : [],	//配置表格列显示
			url : '',		//请求后台的url
			data : [],		//数据体
			mask : false,	//表格加载时，是否显示遮罩
			load : false,	//表格是否在new 出来后马上请求数据
			selectType : 'radio', 	//支持模式：多选checkBox、单选radio
			pageSize : 10,
			showPage : true
		},
		Template : {
			box : [
				'<div class="sy-wg-grid" id="{id}">',
					'<div data-hbar></div>',
					'<div data-content class="sy-wg-grid-content">',
						'<div data-head></div>',
						'<div data-body></div>',
					'</div>',
					'<div data-fbar class="widget-header"></div>',
				'</div>'
			].join(''),
			table : '<table class="table table-striped table-bordered table-hover sy-table">{table}</table>',
			thead : '<thead class="flip-content bordered-azure themeprimary">{headData}</thead>',
			tbody : '<tbody>{bodyData}</tbody>',
			col : '<col style="{style}" />'
		},
		_init : function(){
			// 初始参数
			this._initParam();
			
			// 渲染DOM
			this._drawBox();
		},
		/**
		 * 初始化参数
		 */
		_initParam : function(){
			var _conf = this.conf, errorMsg = [];
			// 验证必要配置项
			if(_conf.columns.length == 0){
				errorMsg.push('columns');
			}
			if(!_conf.appendTo){
				errorMsg.push('appendTo');
			}
			if(errorMsg.length > 0){
				throw new Error('初始化控件失败, 缺少以下配置项: ' + errorMsg.join(','));
			}
			
			this.box = $(_conf.appendTo);
			
			//选中的数据容器
			this._selectedMap = {};
		},
		/**
		 * 渲染DOM
		 */
		_drawBox : function(){
			var _this = this, box = this.box, conf = this.conf;
			box.html(_this.Template.box.format({
				id : _this.getId()
			}));
			
			
			_this.domHead = box.find('[data-head]');
			_this.domBody = box.find('[data-body]');
			_this.domContent = box.find('[data-content]');
			_this.domHbar = box.find('[data-hbar]');
			_this.domFbar = box.find('[data-fbar]');
			
			//设置自定义高度
			if(conf.height > 0){
				_this.domContent.height(conf.height);
			}
			
			_this.wgPage = SY.Page.getInstance({
				appendTo : _this.domFbar,
				pageSize : _this.conf.pageSize,
				listeners : {
					onFirstPage : function(obj){
						_this._request(obj);
					},
					onPrevPage : function(obj){
						_this._request(obj);
					},
					onNextPage : function(obj){
						_this._request(obj);
					},
					onLastPage : function(obj){
						_this._request(obj);
					},
					onSetPageSize : function(obj){
						_this._request(obj);
					},
					onSetPageNumber : function(obj){
						_this._request(obj);
					}
				}
			});
			if(!conf.showPage){
				_this.wgPage.box.hide();
			}
			
			//渲染表头
			_this._drawTableHead();
			
			//渲染数据体
			_this._drawTableBody();
			
			//绑定事件
			_this._bindEvent();
		},
		/**
		 * 渲染表格头
		 */
		_drawTableHead : function(){
			var _this = this;
			
			//计算列宽
			_this._calWidth();
			
			var colsHtml = ['<tr>'], columnList = this.conf.columns, column = null;
			for(var i=0; i<columnList.length; i++){
				column = columnList[i];
				
				colsHtml.push('<th>');
				colsHtml.push(column.name ? column.name : '---');
				colsHtml.push('</th>');
			}
			colsHtml.push('</tr>');
			
			var colgroupHtml = _this._getColgroupHtml();
			var theadHtml = _this.Template.thead.format({
				headData : colsHtml.join('')
			});
			var tableHtml = _this.Template.table.format({
				table : colgroupHtml + theadHtml
			});
			this.domHead.html(tableHtml);
		},
		/**
		 * 渲染表格体
		 */
		_drawTableBody : function(){
			var _this = this, conf = this.conf;
			var colsHtml = [], data = this.conf.data || [], bean = null, columnList = this.conf.columns, column = null;
			
			//rowIndex偏移量
			var offset = (_this.wgPage.getPageNumber() - 1) * _this.wgPage.getPageSize();
			
			if(data.length > 0){	//有数据
				for(var i=0; i<data.length; i++){
					bean = data[i];
					bean.RowIndex = offset + i + 1;
					
					colsHtml.push('<tr data-row-index="' + bean.RowIndex + '">');
					for(var j=0; j<columnList.length; j++){
						column = columnList[j];
						colsHtml.push('<td>');
						colsHtml.push(_this._formatValue(bean, column));
						colsHtml.push('</td>');
					}
					
					colsHtml.push('</tr>');
				}
				
				this.domBody.html(_this.Template.table.format({
					table : _this._getColgroupHtml() + _this.Template.tbody.format({
						bodyData : colsHtml.join('')
					})
				}));
			}else{
				this.domBody.html('<div class="sy-wg-grid-defaultMsg">' + conf.defaultText + '</div>');
			}
			
		},
		_formatValue : function(bean, column){
			var value = '';
			if(column.type == SY.TYPE_SEQ){	//需要类型
				value = bean.RowIndex;
				
			}else if(typeof column.formatter == 'function'){
				value = column.formatter(bean[column.field], bean);
			}else{
				value = bean[column.field];
				if(typeof value == 'undefined'){
					value = '';
				}
			}
			return value;
		},
		/**
		 * 绑定事件
		 */
		_bindEvent : function(){
			var _this = this;
			_this.domBody.click(function(event){
				var target = event.target, rowIndex = null;
				while(target){
					rowIndex = target.getAttribute('data-row-index');
					if(rowIndex){
						_this._toggleSelected(rowIndex, target);
						break;
					}
					if(this == target){
						break;
					}
					target = target.parentNode;
				}
			});
			
		},
		/**
		 * 计算列宽
		 */
		_calWidth : function(){
			var conf = this.conf;
			
			conf.columnWidth = [];
			var totalWidth = this.domContent.width(), totalCusWidth = 0, noWidthCol = 0, width = null;
			
			var columnList = this.conf.columns, column = null;
			for(var i=0; i<columnList.length; i++){
				column = columnList[i];
				
				if(column.width > 0){	//设置里宽度的列
					totalWidth -= column.width;		//累减总宽度
					totalCusWidth += column.width;	//累加自定义总宽度
				}else{
					noWidthCol++;		//累加未设置宽度的列
				}
			}
			
			// 计算剩余需要自动设置列宽, 公式: (总宽度 - 已设置宽度) / 未设置宽度列数 = 剩余列平均宽度
			var average = parseInt(totalWidth / (noWidthCol == 0 ? 1 : noWidthCol)); 
			for(var i=0; i<columnList.length; i++){
				column = columnList[i];
				conf.columnWidth.push(column.width > 0 ? column.width : average);
			}
		},
		/**
		 * 获取每列的长度样式
		 */
		_getColgroupHtml : function(){
			var html = ['<colgroup>'], conf = this.conf, colTpl = this.Template.col, columnList = this.conf.columns, column = null;
			for(var i=0; i<columnList.length; i++){
				column = columnList[i];
				html.push(colTpl.format({
					style : 'width: ' + conf.columnWidth[i] + 'px'
				}));
			}
			html.push('</colgroup>');
			return html.join('');
		},
		/**
		 * 刷新内部数据
		 */
		_rebuildData : function(data, total){
			var conf = this.conf;
			conf.data = data;
			
			if(conf.params.pageNumber == 1){
				// 渲染分页信息
				this.wgPage.setTotalRow(total);
			}
			
			this._selectedMap = {};
		},
		/**
		 * 通过rowIndex查找数据
		 */
		findData : function(rowIndex){
			var data = this.conf.data, bean = null;
			for(var i=0; i<data.length; i++){
				bean = data[i];
				
				if(bean.RowIndex == rowIndex){
					return bean;
				}
			}
			return null;
		},
		size : function(){
			return this.conf.data.length || 0;
		},
		/**
		 * 选中/取消选中（单行）
		 */
		_toggleSelected : function(rowIndex, el){
			var _this = this, data = this.conf.data, bean = null, $el = $(el), selectType = this.conf.selectType;
			
			if(selectType == 'radio'){	//单选处理方式
				if(_this._selectedMap[rowIndex]){	//已选中
					delete _this._selectedMap[rowIndex];
					$el.removeClass('success');
				}else{		//未选中
					for(var i=0; i<data.length; i++){
						bean = data[i];
						
						if(bean.RowIndex == rowIndex){
							_this._selectedMap[bean.RowIndex] = bean;
							$el.addClass('success');
						}else{
							delete _this._selectedMap[bean.RowIndex];
							$el.siblings().removeClass('success');
						}
					}
				}
				
			}else{		//多选处理方式
				if(_this._selectedMap[rowIndex]){	//已选中
					delete _this._selectedMap[rowIndex];
					$el.removeClass('success');
				}else{		//未选中
					bean = this.findData(rowIndex);
					if(bean){
						_this._selectedMap[rowIndex] = bean;
						$el.addClass('success');
					}
				}
			}
		},
		/**
		 * 全选/取消全选（全部）
		 */
		toggleSelectedAll : function(){
			var _this = this, data = this.conf.data, bean = null, selectType = this.conf.selectType;
			
			if(selectType != 'checkbox'){	//只有checkbox类型才支持全选操作
				return ;
			}
			
			var map = _this._selectedMap = {};
			if(_this._selectAll){
				this.domBody.find('tr').removeClass('success');
				_this._selectAll = false;
			}else{		//选中全部
				for(var i=0; i<data.length; i++){
					bean = data[i];
					map[bean.RowIndex] = bean;
				}
				this.domBody.find('tr').addClass('success');
				_this._selectAll = true;
			}
		},
		/**
		 * 获取选中的数据
		 */
		getSelected : function(){
			var map = this._selectedMap, rs = [];
			for(var k in map){
				rs.push(map[k]);
			}
			return rs;
		},
		/**
		 * 请求数据
		 */
		_request : function(params){
			var conf = this.conf, _this = this, now = new Date().getTime();
			
			conf.params._r = now;
			if(params){
				$.extend(true, conf.params, params);
			}
			
			_this._fireEvent('beforeLoad');
//			NY.load.show('正在查询...');
			$.ajax({
				url : conf.url,
				type : 'post',
				dataType : 'json',
				data : conf.params,
				success : function(rs, status, xhr){
					if(!rs.success){
//						NY.Msg.error(rs.msg);
						return ;
					}
					conf._return = rs;
					
					//  保存响应结果信息, 刷新内部数据
					_this._rebuildData(rs.items, rs.total);
					// 渲染数据体
					_this._drawTableBody();
					
					// 回调监听数据加载事件
					_this._fireEvent('onLoad', rs);
				},
				error : function(request, status, error){
//					NY.Msg.error('访问出现异常！请求地址: ' + conf.url);
					_this._fireEvent('onError', request, error);
				},
				complete : function(xhr, status){
//					NY.load.hide();
					_this = null; params = null;
				}
			});
		},
		/**
		 * 刷新数据
		 */
		refresh : function(){
			var _this = this;
			_this._request({
				pageNumber : 1, 
				pageSize : _this.wgPage.getPageSize()
			});
		},
		getParam : function(){
			return $.extend(true, {}, this.conf.params);
		},
		/**
		 * 查询接口
		 */
		query : function(params){
			params = params || {};
			
			//重置到第一页、重置查询条件
			this.conf.params = $.extend(true, {
				pageNumber : 1, 
				pageSize : this.wgPage.getPageSize(),
			}, params);
			
			//请求数据
			this._request();
		}
	});
	
	
})(Zepto, window);