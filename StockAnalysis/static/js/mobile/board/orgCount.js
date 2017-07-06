$(function($){
	
	var Widget = {
			grid : SY.Grid.getInstance({
				appendTo : $('#list'),
				url : BuildPath('board/orgCountData'),
				showPage : false,
				columns : [
		           {name:'序号', field:"seq", width:80, type: SY.TYPE_SEQ},
		           {name:'代码', field:"stockCode"},
		           {name:'名称', field:"stockName"},
		           {name:'买卖', field:"rankType"},
		           {name:'专用机构数量', field:"cmpCount"}
				],
				listeners : {
					onLoad : function(){
						$('.queryDate').text(this.conf._return.other.date);
					}
				}
			})
		};
		
	/*var Handle = {
	};*/
	
	Widget.grid.query();
	
});