$(function($){
	
	var Widget = {
		grid : SY.Grid.getInstance({
			appendTo : $('#list'),
			url : BuildPath('board/newTopBoardData'),
			showPage : false,
			columns : [
	           {name:'序号', field:"seq", width:50, type: SY.TYPE_SEQ},
	           {name:'代码', field:"stockCode", width:80},
	           {name:'名称', field:"stockName", width:80},
	           {name:'买/卖', field:"rankType", width:80},
	           {name:'原因', field:"reason"},
	           {name:'营业部', field:"companyName"},
	           {name:'买入额（万）', field:"buyMoney"},
	           {name:'卖出额（万）', field:"saleMoney"}
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