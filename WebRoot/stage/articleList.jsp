<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>文章列表</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/article_list.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/nav.css">
	<script src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$.post(
			"${pageContext.request.contextPath}/stageArticleServlet?method=getArticleByArticleType",
			{'aid':${param.aid},'tid':${param.tid}},
			function(data){
				$.each( data, function(i, n){
					$("#articleList").append("<a href='${pageContext.request.contextPath}/stage/article.jsp?aid=${param.aid}&id="+n.id+"'><div class='bs-callout bs-callout-danger' id='callout-glyphicons-empty-only' style='border-left-color: #8E44AD;'><h4>"+n.title+"</h4><p>"+n.keyword+"</p><div class='view'><span> 阅读量 </span><span class='glyphicon glyphicon-eye-open'></span><span>"+n.clickRate+"</span> </div> </div></a>");
				});
				
				$("#name").append(data[0].author.username);
			},
			"json"
		);
	</script>
</head>
<body>
	<nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${pageContext.request.contextPath }/stage/index.jsp">Welcome</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath }/stage/home.jsp?id=${param.aid}">Home</a></li>
            <li><a href="#about">Article</a></li>
          </ul>
          	<div class="input-group" style="width: 300px; float: right; margin-top: 8px;">
                    <input type="text" class="form-control" aria-label="Text input with segmented button dropdown">
                    <div class="input-group-btn">
	                      <button type="button" class="btn btn-default">Action</button>
	                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        <span class="caret"></span>
	                        <span class="sr-only">Toggle Dropdown</span>
	                      </button>
	                      <ul class="dropdown-menu dropdown-menu-right">
	                        <li><a href="#">Action</a></li>
	                        <li><a href="#">Another action</a></li>
	                        <li><a href="#">Something else here</a></li>
	                        <li role="separator" class="divider"></li>
	                        <li><a href="#">Separated link</a></li>
	                      </ul>
                    </div>
              </div>
        </div><!-- /.nav-collapse -->
		
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
	<div class="container">
		<h2 id="name"></h2>
		<h4>&nbsp;&nbsp;&nbsp;&nbsp;this is cool</h4>
		<div id="articleList" class="">
			
		<!-- </div>
		
		<div class="bs-callout bs-callout-danger" id="callout-glyphicons-empty-only" style="border-left-color: #8E44AD;">
			<h4>文章标题</h4>
		    <p>摘要：.................................</p>
			<div class="view">
				<span> 阅读量 </span>
		    	<span class="glyphicon glyphicon-eye-open"></span>
		    	<span>1</span> 
			</div> 
  		</div>
  		<div class="bs-callout bs-callout-danger" id="callout-glyphicons-empty-only" style="border-left-color: #8E44AD;">
			<h4>文章标题</h4>
		    <p>摘要：.................................</p>
			<div class="view">
				<span> 阅读量 </span>
		    	<span class="glyphicon glyphicon-eye-open"></span>
		    	<span>1</span> 
			</div> 
  		</div>
  		<div class="bs-callout bs-callout-danger" id="callout-glyphicons-empty-only" style="border-left-color: #8E44AD;">
			<h4>文章标题</h4>
		    <p>摘要：.................................</p>
			<div class="view">
				<span> 阅读量 </span>
		    	<span class="glyphicon glyphicon-eye-open"></span>
		    	<span>1</span> 
			</div> 
  		</div>
  		<div class="bs-callout bs-callout-danger" id="callout-glyphicons-empty-only" style="border-left-color: #8E44AD;">
			<h4>文章标题</h4>
		    <p>摘要：.................................</p>
			<div class="view">
				<span> 阅读量 </span>
		    	<span class="glyphicon glyphicon-eye-open"></span>
		    	<span>1</span> 
			</div> 
  		</div> -->
	</div>
</body>
</html>