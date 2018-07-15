<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>后台管理主界面</title>
</head>
<frameset frameborder="0" rows="8%, *">
	<frame frameborder="0" src="${pageContext.request.contextPath }/backstageDispatcherServlet?method=nav"></frame>
	<frameset frameborder="0" cols="15%, 85%">
		<frame noresize src="${pageContext.request.contextPath }/backstageDispatcherServlet?method=left"></frame>
		<frame noresize name="right" src="${pageContext.request.contextPath }/backstageDispatcherServlet?method=index"></frame>
	</frameset>
</frameset>
</html>