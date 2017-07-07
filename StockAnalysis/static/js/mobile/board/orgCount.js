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
		           {name:'专机构量（买）', field:"cmpCountB", formatter : function(value){
		        	   if(value >= 3){
		        		   return '<span class="st-red-bold">' + value + '</span>';
		        	   }
		        	   return value;
		           }},
		           {name:'专机构量（卖）', field:"cmpCountS", formatter : function(value){
		        	   if(value >= 3){
		        		   return '<span class="st-red-bold">' + value + '</span>';
		        	   }
		        	   return value;
		           }}
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