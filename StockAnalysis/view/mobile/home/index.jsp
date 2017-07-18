<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>Document</title>
	<jsp:include page="../base/head.jsp"></jsp:include>
	<style type="text/css">
		.col-xs-4{
			padding: 0;
		}
		.sa-grid-box{
			height: 100px;
    		line-height: 100px;
    		text-align: center;
    		background: whitesmoke;
    		border: 1px solid #eaeaea;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row">
	  <div class="col-xs-4"><div class="sa-grid-box" data-url="board/orgCount">专用机构数量</div></div>
	  <div class="col-xs-4"><div class="sa-grid-box" data-url="board/newTopBoard">创新高上榜</div></div>
	  <div class="col-xs-4"><div class="sa-grid-box" data-url="board/breakThroughBoard">向上突破上榜</div></div>
	  <div class="col-xs-4"><div class="sa-grid-box" data-req="home/refresh">刷新数据</div></div>
	</div>
</div>
<script type="text/javascript">

$('[data-url]').click(function(){
	var url = this.dataset.url;
	Util.jump(url);
});
$('[data-req]').click(function(){
	var url = this.dataset.req;
	$.ajax({
		url : BuildPath(url),
		type : 'post',
		dataType : 'json',
		success : function(rs, status, xhr){
			if(rs.success){
				SY.Msg.success('刷新成功');
			}else{
				SY.Msg.error(rs.msg);
			}
		}
	});
});

</script>
</body>
</html>