<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>后台系统登录</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
    <style>
        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        h2 {
            color: #76ac25;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="loginForm" class="form-signin" action="${pageContext.request.contextPath }/backstage/loginServlet" method="post">
        <h2 class="form-signin-heading">欢迎登录</h2>
        <label for="inputUserName" class="sr-only">账号</label> 
        <input id="username"  name="username" style="margin-bottom: 20px;" type="text" id="inputEmail" class="form-control" placeholder="账号" required="" autofocus="">
        <label for="inputPassword" class="sr-only">密码</label>
        <input id="password" name="password" type="password" id="inputPassword" class="form-control" placeholder="密码" required="">
        <span id="errorMes" style="color: red;">${error }</span>
       <!--  <div class="checkbox">
            <label>
                <input type="checkbox" name="remember" value="remember-me">记住密码
            </label>
        </div> -->
        <button id="btnSubmit" class="btn btn-lg btn-success btn-block" type="button">登录</button>
    </form>
</div>

<script src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>-->
<script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
<script>
 $(function(){
 	
 	 $("#btnSubmit").click(function(){
  	var username = $("#username").val();
  	var password = $("#password").val();
  	if(username != null && username.trim() != ""){
  		$("#errorMes").html("");
  	}else{
  		$("#errorMes").html("用户名不能为空");
  		return;
  	}
  	
  	if(password != null && password.trim() != ""){
  		$("#errorMes").html("");
  		
  	}else{
  		$("#errorMes").html("密码不能为空");
  		return;
  	}
  	$("#loginForm").submit();
  });
 });
 
    
</script>
</body>
</html>