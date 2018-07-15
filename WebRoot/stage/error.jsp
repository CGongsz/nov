<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>error page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron" style="text-align: center;">
  <h1>未知错误</h1>
  <script type="text/javascript">
  	function redirectPage(){
  		window.location.href = "${pageContext.request.contextPath}/stage/index.jsp";
  	}
  </script>
  <p><a class="btn btn-primary btn-lg" href="javascript:void(0);" onclick="redirectPage()" role="button">前往前台页面</a></p>
</div>
</body>
</html>