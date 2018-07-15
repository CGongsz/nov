<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
	<style>
		body {
			padding-left: 40px;
			background: #eee;
			padding-top: 2%;
		}
	</style>

	<script type="text/javascript">
		function updateAuthorMes(){
			var conf = confirm("确定修改信息吗？");
			if(conf){
				var username = $("#username").val().trim();
				var password = $("#password").val().trim();
				var regUsername = /^[a-zA-Z0-9]+$/;
				var regPassword = /^(?![^a-zA-Z]+$)(?!\D+$)/;
				
				if(!regUsername.test(username)){
					$("#usernameMes").html("账号必须是字母或数字");
					return;
				} else {
					$("#usernameMes").html("");
				}
				
				if(!regPassword.test(password)){
					$("#passwordMes").html("密码必须包含至少一位数字和一位字母");
					return;
				} else {
					$("#passwordMes").html("");
				}
				
				$("#authorForm").submit();
			}
		}
	</script>
</head>
<body>
	<form id="authorForm" action="${pageContext.request.contextPath}/authorServlet?method=update" method="post">
    	<div class="form-group has-warning has-feedback">
    		<div class="form-group has-warning has-feedback">
        	<label>用户名:</label>
        	<input style="width: 10%;margin-right: 0;" name="username" type="text" value="${writeBackAuthor.username }" class="form-control" id="username" aria-describedby="inputWarning2Status">
        	<span id="usernameMes" style="color: red;"></span>
        	<label>密码:</label>
        	<input style="width: 10%;margin-right: 0;" name="password" type="text" class="form-control" id="password" aria-describedby="inputWarning2Status">	
        	<span id="passwordMes" style="color: red;"></span><br>
        	<label>自我描述:</label>
        	<textarea name="introduction" class="form-control" rows="3">${writeBackAuthor.introduction }</textarea>
        	<span id="inputWarning2Status" class="sr-only">(warning)</span>
        	<button style="float: right; margin-right: 20px;margin-top: 20px;" onclick="updateAuthorMes()" type="button" class="btn btn-primary">&nbsp;&nbsp;更新&nbsp;&nbsp;</button>
    	</div>
     </form>
</body>
</html>