<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getServletContext().getContextPath()); %>

<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!-- <meta http-equiv="keywords" content="jz">
<meta http-equiv="description" content="jz"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>

<!-- 适应手机 -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black"> -->

<script type="text/javascript">
BuildPath = function(url){
	return "${ctx}/mb/" + url;
};
</script>

<!-- bootstrap -->
<link rel="stylesheet" href="${ctx}/static/js/plugins/bootstrap/css/bootstrap.min.css"></link>

<!-- 第三方资源 -->
<script src="${ctx}/static/js/plugins/zepto.js"></script>

<link rel="stylesheet" href="${ctx}/static/js/plugins/widget/SyWidget.css"></link>
<script src="${ctx}/static/js/plugins/widget/SyCore.js"></script>
<script src="${ctx}/static/js/plugins/widget/SyGrid.js"></script>
<script src="${ctx}/static/js/plugins/widget/SyModal.js"></script>

<!-- 系统工具 -->
<script src="${ctx}/static/js/core/Util-1.0.0.js"></script>

<link rel="stylesheet" href="${ctx}/static/css/mobile/base/common.css"></link>
