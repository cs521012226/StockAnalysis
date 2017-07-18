<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getServletContext().getContextPath()); %>
<!doctype html>
<html>
<head>
	<title>Document</title>
</head>
<body>
<script type="text/javascript">
window.location.href="${ctx}/mb/home/";
</script>
</body>
</html>