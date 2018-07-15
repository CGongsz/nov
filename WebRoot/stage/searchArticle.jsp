<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchArticle.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
		function GetRequest() {
  			var url = location.search; //获取url中"?"符后的字串
  			var theRequest = new Object();
  			if (url.indexOf("?") != -1) {
   				var str = url.substr(1);
    			strs = str.split("&");
    			for(var i = 0; i < strs.length; i ++) {
      				theRequest[strs[i].split("=")[0]] = unescape(decodeURI(strs[i].split("=")[1]));
    			}
  			}
  			return theRequest;
		}
		var Request = new Object();
		Request = GetRequest();// var id=Request["id"]; 
		var aid = Request["aid"];
		var keyword = Request["keyword"];
		$.post(
			"${pageContext.request.contextPath}/stageArticleServlet?method=getArticleByKeyWord",
			{"aid":aid,"keyword":keyword},
			function(data){
				console.log(data);
				for(var i = 0; i < data.rows.length; i++){
					$("#articleList").append("<a href='${pageContext.request.contextPath}/stage/article.jsp?aid=${aid}&id="+data.rows[i].id+"&tid="+data.rows[i].article_type_id+"'><div class='bs-callout bs-callout-danger' id='callout-glyphicons-empty-only' style='border-left-color: #8E44AD;'><h4>"+data.rows[i].title+"</h4><p>"+data.rows[i].keyword+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最后修改时间"+data.rows[i].createTime+"</p><div class='view'><span> 阅读量 </span><span class='glyphicon glyphicon-eye-open'></span><span>"+data.rows[i].clickRate+"</span> </div> </div></a>");
				}
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
			
			window.location.href = "${pageContext.request.contextPath}/stage/searchArticle.jsp?aid="+aid+"&keyword="+keyword+"&currentPage="+currentPage+"&pageSize="+pageSize;
		}
		
		function next(){
			var totalPage = $("#totalPage").val();
			var currentPage = $("#currentPage").val();
			currentPage = parseInt(currentPage) + 1;
			
			var pageSize = $("#pageSize").val();
			window.location.href = "${pageContext.request.contextPath}/stage/searchArticle.jsp?aid="+aid+"&keyword="+keyword+"&currentPage="+currentPage+"&pageSize="+pageSize;
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
		</div>
	</div>
	</nav>
	<div class="container">
		<h4 id="info">&nbsp;&nbsp;&nbsp;&nbsp;搜素结果：</h4>
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
