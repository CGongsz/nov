<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>文章列表</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/style/css/article_list.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/style/css/nav.css">
<script
	src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
<script
	src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>

<script type="text/javascript">
		$.post(
			"${pageContext.request.contextPath}/stageArticleServlet?method=getArticleByArticleType",
			{aid:'${param.aid}',tid:'${param.tid}' ,currentPage:'${param.currentPage}', pageSize:'${param.pageSize}'},
			function(data){
				for(var i = 0; i < data.rows.length; i++){
					$("#articleList").append("<a href='${pageContext.request.contextPath}/stage/article.jsp?aid=${param.aid}&id="+data.rows[i].id+"&tid="+data.rows[i].article_type_id+"'><div class='bs-callout bs-callout-danger' id='callout-glyphicons-empty-only' style='border-left-color: #8E44AD;'><h4>"+data.rows[i].title+"</h4><p>"+data.rows[i].keyword+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最后修改时间"+data.rows[i].createTime+"</p><div class='view'><span> 阅读量 </span><span class='glyphicon glyphicon-eye-open'></span><span>"+data.rows[i].clickRate+"</span> </div> </div></a>");
				}
				$("#name").append(data.rows[0].articleType.typeName);
				$("#info").append(data.rows[0].articleType.info);
				$("#total").append(data.total)
				$("#currentPage").val(data.currentPage);
				$("#pageSize").val(data.pageSize);
				
			},
			"json"
		);
		
		function previous(){
			var currentPage = parseInt($("#currentPage").val()) - 1;
			if(currentPage < 1){
				currentPage = 1;
			}
			
			var pageSize = $("#pageSize").val();
			window.location.href = "${pageContext.request.contextPath}/stage/articleList.jsp?aid=${param.aid}&tid=${param.tid}&currentPage="+currentPage+"&pageSize="+pageSize;
		}
		
		function next(){
			var totalPage = $("#totalPage").val();
			var currentPage = $("#currentPage").val();
			currentPage = parseInt(currentPage) + 1;
			
			var pageSize = $("#pageSize").val();
			window.location.href = "${pageContext.request.contextPath}/stage/articleList.jsp?aid=${param.aid}&tid=${param.tid}&currentPage="+currentPage+"&pageSize="+pageSize;
		}
	</script>
</head>
<body>

	<input type="hidden" id="currentPage" name="currentPage">
	<input type="hidden" id="pageSize" name="pageSize">
	<input type="hidden" id="totalPage" name="totalPage">
	<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="${pageContext.request.contextPath }/stage/index.jsp">博客入口</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li ><a
					href="${pageContext.request.contextPath }/stage/home.jsp?id=${param.aid}">博客首页</a></li>
				<li class="active"><a href="#">文章列表</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/stage/searchArticle.jsp? method="post">
          		<div class="input-group" style="width: 300px; float: right; margin-top: 8px;">
          			<input name="aid" value="${param.aid}" type="hidden">
          			<input name="keyword" type="text" class="form-control" aria-label="Text input with segmented button dropdown">
                	<div class="input-group-btn">
	               	 <button id="search" type="submit" class="btn btn-default">搜索</button>
                	</div>
              	</div>
         	</form>
		</div>
	</div>
	</nav>
	<div class="container">
		<h2 id="name"></h2>
		<h4 id="info">&nbsp;&nbsp;&nbsp;&nbsp;类型介绍：</h4>
		<div id="articleList" class="">
		</div>
		<!-- 分页 -->
		<nav aria-label="..." style="margin: 40px 0;">
			<ul class="pager">
				<li style="float:left;"><a href="javascript:void(0);" onclick="previous()">Previous</a></li>
				<li>
					<button class="btn btn-primary" type="button">
						类别总量 <span id="total" class="badge"></span>
					</button>
				</li>
				<li style="float:right;"><a href="javascript:void(0);" onclick="next()">Next</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>