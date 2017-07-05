;(function($){
	
	var Widget = {
		grid : SY.Grid.getInstance({
			appendTo : $('#list'),
			url : BuildPath('board/orgCountData'),
			showPage : false,
			columns : [
	           {name:'序号', field:"seq", width:50, type: SY.TYPE_SEQ},
	           {name:'代码', field:"stockCode", width:80},
	           {name:'名称', field:"stockName"},
	           {name:'买卖', field:"rankType", width:60},
	           {name:'专用机构数量', field:"cmpCount", width:80}
			]
		})
	};
	
	var Handle = {
	};
	
	Widget.grid.query();
	
})(Zepto);