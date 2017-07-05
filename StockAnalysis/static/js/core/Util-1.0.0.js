;(function(win){
	Util = {
		jump : function(url){
			top.location.href = BuildPath(url);
		}
	};
})(window);