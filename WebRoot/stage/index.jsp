<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>博客首页</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/index.css">
	<script type="text/javascript">
		$.post(
			"${pageContext.request.contextPath}/homeServlet?method=getBlogAuthor",
			{},
			function(data){
				$.each( data, function(i, n){
  					$("#enterDoor").append("<a class='btn btn-success btn-lg' href='javascript:void(0);' onclick='enter("+n.id+")' role='button'>"+n.username+"のblog </a>&nbsp;&nbsp;&nbsp;&nbsp;");
				});
			},
			"json"
		);
		
		function enter(id){
			window.location.href = "${pageContext.request.contextPath}/stage/home.jsp?id="+id;
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<img src="${pageContext.request.contextPath }/style/img/002.svg" alt="">
		  	<h1>&nbsp;Hello, <br>&nbsp;&nbsp;&nbsp;&nbsp;welcome here!</h1>
		  	<p id="enterDoor" style="float: right; display: block;margin-top: 90px;margin-right: 230px;">
		  	<!-- <a class="btn btn-success btn-lg" href="javascript:void(0);" onclick="enter()" role="button">novのblog </a> -->
		  	</p>
		</div>
	</div>
	<video id="v1" autoplay muted loop style="width: 100%">
    	<source  src="${pageContext.request.contextPath }/style/video/home-bg.mp4">
	</video>
</body>
</html>