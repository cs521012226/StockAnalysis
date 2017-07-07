$(function($){
	
	var Widget = {
		grid : SY.Grid.getInstance({
			appendTo : $('#list'),
			url : BuildPath('board/breakThroughBoardData'),
			showPage : false,
			columns : [
	           {name:'序号', field:"seq", width:50, type: SY.TYPE_SEQ},
	           {name:'代码', field:"stockCode", width:80},
	           {name:'名称', field:"stockName", width:80},
	           {name:'买/卖', field:"rankType", width:80, formatter : function(value){
	        	   if(value == 'B'){
	        		   return '<span class="st-red-box">' + value + '</span>';
	        	   }else{
	        		   return '<span class="st-green-box">' + value + '</span>'; 
	        	   }
	           }},
	           {name:'买入额（万）', field:"buyMoney", width:120},
	           {name:'卖出额（万）', field:"saleMoney", width:120},
	           {name:'买卖额占比', field:"moneyPercent", width:120, formatter : function(value){
	        	   return value + "%";
	           }},
	           {name:'营业部', field:"companyName"},
	           {name:'风格', field:"style", width:120, formatter : function(value){
	        	   if(value && value.trim() != ''){
	        		   return '<span class="st-red-box">' + value + '</span>';
	        	   }
	        	   return '';
	           }},
	           {name:'日榜数', field:"rankCountD", width:80},
	           {name:'周榜数', field:"rankCountW", width:80},
	           {name:'上榜原因', field:"reason"}
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