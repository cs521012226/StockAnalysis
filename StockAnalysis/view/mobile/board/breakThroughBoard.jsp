<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>Document</title>
	<jsp:include page="../base/head.jsp"></jsp:include>
	<script src="${ctx}/static/js/mobile/board/breakThroughBoard.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<form class="form-inline">
			  <div class="form-group" style="margin-right: 10px;">
			  	<strong>向上突破且上榜股票</strong>
			  </div>
			  <div class="form-group">
			  	<div><label style="margin-right: 10px;">日期: </label>
			  	<span class="queryDate"></span></div>
			  </div>
			</form>
		</div>
		<div class="row">
			<div id="list"></div>
		</div>
	</div>
</body>
</html>