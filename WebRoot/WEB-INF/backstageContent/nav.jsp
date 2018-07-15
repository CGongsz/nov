<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<style>
		body {
			padding-left: 40px;
			background: #eee;
		}
	</style>
	
	<script type="text/javascript">
		function logout(){
			var conf = confirm("您确定要退出系统吗？");
			if(conf){
				window.top.location.href = "${pageContext.request.contextPath}/logoutServlet";
			}
		}
	</script>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">${author.username }のBlog</a> <a class="navbar-brand" href="javascript:void(0);" onclick="logout()">Logout</a>
        </div>
      </div>
    </nav>
</body>
</html>