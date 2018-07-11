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
      	total = 0;
     	$.post(
    		"${pageContext.request.contextPath}/stageArticleServlet?method=showArticle",
    		{aid:'${param.aid}',id:'${param.id}'},
    		function(data){
    				$("#article").append("<h4>"+data.title+"</h4><div class='row line'></div><div class='row'>"+data.content+"</div><div class='row line'></div>");
    				var src = $('img').attr("src");
    				$("img").attr("src", "${pageContext.request.contextPath}/" + src);
    		},
    		"json"
    	);
     	
     	$.post(
     		'${pageContext.request.contextPath}/stageCommentServlet?method=showComment',
     		{id:'${param.id}'},
     		function(data){
     			// 评论格式
     			$.each( data, function(i, n){
     				total = i + 1;
     				$("#comment").append("<div style='border-bottom:1px solid #ccc;margin-bottom:20px;' class='bs-callout bs-callout-warning' ><p><span style='font-size:16px; color: #8E44AD;'>#"+(i+1)+"楼&nbsp;&nbsp;</span><span>"+n.createTime+"&nbsp;&nbsp;</span><span>"+n.visitor_username+"</span></p><p>"+n.content+"</p></div>");
				});
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
     		
     		if(email == null || email == ""){
     			alert("请输入邮箱");
     			return;
     		}
     		
     		// 用户存在提交评论的处理
     		if(canSubmit == "用户存在" || canSubmit == ""){
     			var conf = confirm("确定提交评论吗？");
     			if(conf){
     				$.post(
     					'${pageContext.request.contextPath}/stageCommentServlet?method=existVisitorSubmitComment',
     					{aid:'${param.aid}',id:'${param.id}',email:email,content:content},
     					function(data){
     						window.location.href = "${pageContext.request.contextPath}/stage/article.jsp?aid=${param.aid}&id=${param.id}&email="+data.visitor_email;
     					},
     					'json'
     				);
     			}
     		}
     		
     		if(canSubmit == "邮件地址合法"){
     			var conf = confirm("确定提交评论吗？");
     			if(conf){
     				$('#art_content>div').css("display","none");
     				<!-- 弹出层效果js -->
					$('#art_content').css({
						"position":"absolute",
						"width":"100%",
	     				"height":"100%",
	     				"background":"#000",
	     				"opacity":"0.5",
	     				"filter":"alpha(opacity=50)"
					});
					$("#hide").css("display", "block");
     			}
     		}
     	}
     	
     	function existVisitorUsername(){
     		var email = $("#email").val();
     		var username = $("#sign").val().trim();
     		if(username != null & username != ""){
	     		$.post(
	     			'${pageContext.request.contextPath}/stageVisitorServlet?method=usernameAvoidRepeat',
	     			{username:username},
	     			function(data){
	     				if(data == "该用户名已存在"){
	     					$("#errorMes").html(data);
	     				}else{
	     					$("#usernameMes").html(data);
	     				}
	     			}
	     		);
     		}
     	}
     	
     	function newVisitorSubmit(){
     		var errorMes = $("#errorMes").html();
     		if(errorMes == "该用户名已存在"){
     			alert("请修改用户名");
     			return;
     		}
     		var content = $("#content").val()
     		var username = $("#sign").val();
     		var email = $("#email").val();
     		
     		var conf = confirm("您确定使用该用户名？");
     		if(conf){
     			$.post(
     				'${pageContext.request.contextPath}/stageVisitorServlet?method=newVisitorSubmitComment',
     				{email:email ,username:username ,content:content ,id:'${param.id}' ,aid:'${param.aid}'},
     				function(data){
     						window.location.href = "${pageContext.request.contextPath}/stage/article.jsp?aid=${param.aid}&id=${param.id}&email="+email;
     				}
     			);
     		}
     	}
     </script>
     <style>
     	#hide{
     		position:absolute;
     		top:50%;left:50%;
     		width:400px;
     		height:200px;
     		background:#fff;
     		border:4px solid #8E44AD;
     		margin:-102px 0 0 -202px;
     		display:none;
     	}
     </style>
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
          <a class="navbar-brand" href="${pageContext.request.contextPath }/stage/index.jsp">博客入口</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath }/stage/home.jsp?id=${param.aid}">博客首页</a></li>
            <li><a href="${pageContext.request.contextPath }/stage/articleList.jsp?aid=${param.aid}&tid=${param.tid}">文章</a></li>
            <li class="active"><a href="#">文章</a></li>
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
	<div class="container" id="art_content">
		<div id="article">
		</div>
		<div id="comment">
			<h4>评论列表：</h4>
		</div>
		<div class="discuss">
			<form action="#" style="margin-top: 100px;">
				<h3>我要发表看法</h3>
			
				<textarea id="content" class="form-control" rows="10" cols="15" style="border: 1px solid; width: 40%; text-align: left;"></textarea>
				<h4>电子邮件：(必填*)</h4>
				<input style="width:300px;" class="form-control" onblur="validateEamil()" id="email" type="text" name="email" value="${param.email }" placeholder="输入邮箱地址进行评论">
				<span id="eamilMes" style="color: green;"></span><br>
				<span id="errorMes" style="color: red;"></span><br>
				
				<a id="sub" onclick="submitVisitor()" href="javascript:void(0);"  class="btn btn-primary" style="margin-left:400px; position:absolute;">提交</a>
			</form>
		</div>
	</div>
	<!-- 弹出层 class="sign" -->
	<div id="hide" style="display: none; text-align:center;">
		<h3>请输入评论昵称：</h3>
		<input type="text" id="sign" name="sign" placeholder="" onblur="existVisitorUsername()" class="form-control" style="width:200px;margin-left:24%;">
		<span id="errorMes" style="color: red;"></span>
		<span id="usernameMes" style="color: green;"></span>
		<input class="btn btn-primary" type="button" onclick="newVisitorSubmit()" value="提交" style="position:absolute;top:60%;left:44%;">
	</div>
</body>
</html>