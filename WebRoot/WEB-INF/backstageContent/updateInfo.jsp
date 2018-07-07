<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<style>
		body {
			padding-left: 40px;
			background: #eee;
			padding-top: 2%;
		}
	</style>

</head>
<body>
	<form action="#">
    	<div class="form-group has-warning has-feedback">
    		<div class="form-group has-warning has-feedback">
        	<label>用户名:</label>
        	<input style="width: 10%;margin-right: 0;" type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status">
        	<label>密码:</label>
        	<input style="width: 10%;margin-right: 0;" type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status">	
        	<label>自我描述:</label>
        	<textarea class="form-control" rows="3"></textarea>
        	<span id="inputWarning2Status" class="sr-only">(warning)</span>
        	<button style="float: right; margin-right: 20px;margin-top: 20px;" type="button" class="btn btn-primary">&nbsp;&nbsp;添加&nbsp;&nbsp;</button>
    	</div>
     </form>
</body>
</html>