<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>Document</title>
	<jsp:include page="../base/head.jsp"></jsp:include>
	<script src="${ctx}/static/js/mobile/board/orgCount.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<form class="form-inline">
			  <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon">日期</div>
			      <div type="text" class="form-control queryDate" ></div>
			    </div>
			  </div>
			</form>
		</div>
		<div class="row">
			<div id="list"></div>
		</div>
	</div>
</body>
</html>