<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>文章</title>
	<link href="${pageContext.request.contextPath }/style/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/nav.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/article.css">
    <script src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
     <script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
     
     <script type="text/javascript">
     	$.post(
    		"${pageContext.request.contextPath}/stageArticleServlet?method=showArticle",
    		{aid:'${param.aid}',id:'${param.id}'},
    		function(data){
    				$("#article").append("<h4>"+data.title+"</h4><div class='row line'></div><div class='row'>"+data.content+"</div><div class='row line'></div>");
    		},
    		"json"
    	);
     	
     	$.post(
     		'${pageContext.request.contextPath}/stageCommentServlet?method=showComment',
     		{id:'${param.id}'},
     		function(data){
     			// 评论格式
     		},
     		'json'
     	);
     	
     	function validateEamil(){
     		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
     		var email = $("#email").val().trim();
     		if(reg.test(email)){
     			$("#eamilMes").html("");
     			$("#errorMes").html("");
     			$.post(
     				'${pageContext.request.contextPath}/stageCommentServlet?method=appearComment',
     				{id:'${parma.id}',email:email},
     				function(data){
     					if(data == "用户存在" || data == "邮件地址合法"){
     						$("#eamilMes").html(data);
     					} else {
     						$("#errorMes").html(data);
     					}
     				}
     			);
     		}else{
     			$("#errorMes").html("邮箱格式不正确");
     		}
     		
     	}
     	
     	
     	function submitVisitor(){
     		var email = $("#email").val().trim();
     		var content = $("#content").val();
     		var canSubmit = $("#eamilMes").html();
     		
     		// 用户存在提交评论的处理
     		if(canSubmit == "用户存在"){
     			var conf = confirm("确定提交评论吗？");
     			if(conf){
     				$.post(
     					'${pageContext.request.contextPath}/stageCommentServlet?method=existVisitorSubmitComment',
     					{aid:'${param.aid}',id:'${param.id}',email:email,content:content},
     					function(data){
     						$.each( data, function(i, n){
     							// 评论格式
							});
     					},
     					'json'
     				);
     			}
     		}
     		
     		if(canSubmit == "邮件地址合法"){
     			var conf = confirm("确定提交评论吗？");
     			if(conf){
     				$("#hide").attr("style","display:block;");
     			}
     		}
     	}
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
            <li><a href="${pageContext.request.contextPath }/stage/home.jsp?id=${param.aid}">Home</a></li>
            <li class="active"><a href="#about">Article</a></li>
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
		<div id="article">
		</div>
		<div id="comment">
		</div>
		<div class="discuss">
			<form action="#" style="margin-top: 100px;">
				<label for="">请用邮箱评论</label>
				<input onblur="validateEamil()" id="email" type="text" name="email">
				<span id="eamilMes" style="color: green;"></span>
				<span id="errorMes" style="color: red;"></span>
				<textarea id="content" class="form-control" rows="10" cols="15" style="border: 1px solid; width: 40%; text-align: left;">
				</textarea>
				<!-- <input type="submit" value="Submit"> -->
				<a href="javascript:void(0);" onclick="submitVisitor()" class="btn btn-lg btn-primary btn-block">提交</a>
			</form>
		</div>
		<div id="hide" class="sign" style="display: none;">
			<input type="text" name="sign" placeholder="请输入评论昵称">
			<input type="submit" name="" value="Submit">
			
		</div>
	</div>

</body>
</html>