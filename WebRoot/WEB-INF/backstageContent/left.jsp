<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<style type="text/css">
		h4 {
			color: #5e5e5e;
			background:#f8f8f8; 
			height: 40px;
			text-align: center;
			line-height: 40px;
			display: block;
			border: 2px;
			border-radius: 10px;
		}
		h4 a {
			color: #5e5e5e;
		} 
		.active {
			background: #5e5e5e;
			color: #f8f8f8;
		}
		.active a {
			color: #eee;
		}
		.current {
			color: #5e5e5e;
			padding-left: 50px;
			font-size: 14px;
		}
		body {
			margin-left: 10px;
			border: 2px solid #fff;
			border-radius: 20px;
			background: #eee;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript">
		$(function () {
			$('li').click(function() {
				//var hs = document.getElementsByTagName("h4");
				/*for(var i = 0; i < hs.length; i ++) {
					hs[i].removeClass(".active");
				}*/
				$("li>h4").removeClass("active");
				$(this).children("h4").addClass("active");
			});
			$('li').hover(function() {
				$(this).css("cursor","pointer");
			});
			
			
			
		});
	</script>
</head>
<body>
      <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li>
				<h4 class="active">文章管理</h4>
				<!-- 当前页数据 -->
				<input type="hidden" id="currentPage" name="currentPage" value="">
				<ul id="list" class="nav nav-sidebar" style="list-style:none;">
            		<li class="current"><a id="articleList" target="right" href="${pageContext.request.contextPath }/articleServlet?method=list">列表管理</a></li>
            		<li class="current"><a target="right" href="${pageContext.request.contextPath }/articleServlet?method=send">发布文章</a></li>
          		</ul>
          		<script type="text/javascript">
          			$('#list').hide();
          			$(".active").click(function () {
          				$('#list').toggle();
          			});
          		</script>
            </li>
            <li><h4><a target="right" href="${pageContext.request.contextPath }/commentServlet?method=list">留言管理</a></h4></li>
            <li><h4><a target="right" href="${pageContext.request.contextPath }/articleTypeServlet?method=list">分类管理</a></h4></li>
            <li><h4><a target="right" href="${pageContext.request.contextPath }/back/person_message.html">个人信息</a></h4></li>
          </ul>
      </div>
</body>
</html>