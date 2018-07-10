<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>博客社区</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	 <script src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/style/js/nav.js"></script>
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/nav.css">
    
    <script type="text/javascript">
    	$.post(
    		"${pageContext.request.contextPath}/homeServlet?method=getArticleType",
    		{id:'${param.id}'},
    		function(data){
    			$.each( data, function(i, n){
    				$("#article_type").append("<div class='col-xs-6 col-lg-4'><h2>"+n.typeName+"</h2><p>"+n.info+"</p><p><a class='btn btn-default' href='${pageContext.request.contextPath}/stage/articleList.jsp?aid="+n.author_id+"&tid="+n.typeId+"' role='button'>View details &raquo;</a></p></div>");
				});
    		},
    		"json"
    	);
    	
    	$.post(
    		"${pageContext.request.contextPath}/homeServlet?method=getTop10Article",
    		{id:'${param.id}'},
    		function(data){
    			$.each( data, function(i, n){
    				$("#click_rank").append("<a href='${pageContext.request.contextPath}/stage/article.jsp?aid=${param.id}&id="+n.id+"' class='list-group-item'>"+(i+1)+n.title+"("+n.articleType.typeName+")&nbsp&nbsp"+n.clickRate +"访问量</a>");
				});
    		},
    		"json"
    	);
    	
    	$.post(
    		"${pageContext.request.contextPath}/homeServlet?method=getAuthor",
    		{id:'${param.id}'},
    		function(data){
    			$("#name").append(data.username +"の博客世界");
    			$("#info").append(data.introduction);
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
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
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

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div  class="jumbotron">
            <h2 id="name"></h2>
            <p id="info"></p>
          </div>
          <div id="article_type" class="row">
            
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div id="click_rank" class="list-group">
            <a href="#" class="list-group-item active">阅读排行</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->
      <hr>
      <footer>
        <p>&copy; Company 2018</p>
      </footer>
    </div>
   
  </body>
</html>