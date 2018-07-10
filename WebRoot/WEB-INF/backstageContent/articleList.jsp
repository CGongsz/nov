<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style>
		body {
      border: 2px solid #fff;
      border-radius: 4px;
			background: #eee;
			padding-left: 40px;
		}
	</style>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		function searchArticle(condition){
		 	var search = $("#search").val();
			window.location.href="${pageContext.request.contextPath}/articleServlet?method=list&condition="+condition+"&search="+search;
		}
		
	</script>
</head>
<body>
      <h2 class="sub-header">文章列表</h2>
      <div class="table-responsive" style="height:360px;">
		<div class="input-group"
			style="width: 300px; float: left; margin-top: 8px;">
			<input id="search" type="text" class="form-control" value="${search }"
				aria-label="Text input with segmented button dropdown">
			<div class="input-group-btn">
				<button type="button" class="btn btn-default" onclick="searchArticle('title')">搜索</button>
				<!-- <button style="height:34px;" type="button"
					class="btn btn-default dropdown-toggle" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<span class="caret"></span> <span class="sr-only">Toggle
						Dropdown</span>
				</button> -->
				<!-- <ul class="dropdown-menu dropdown-menu-right">
					<li><a href="javascript:void(0);" onclick="searchArticle('title')">按标题搜索</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul> -->
			</div>
		</div>
		<table class="table table-striped">
          <thead>
            <tr>
              <th>文章编号</th>
              <th>标题</th>
              <th>最后修改时间</th>
              <th>浏览量</th>
              <th>所属分类</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageBean.rows }" var="article" varStatus="s">
            <tr>
              <td>${(pageBean.currentPage-1)*pageBean.pageSize + s.count }</td>
              <td>${article.title }</td>
              <td><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
              <td>${article.clickRate }</td>
              <td>${article.articleType.typeName }</td>
              <td>
              	<button type="button" class="btn btn-primary" onclick="updateArticle('${article.id}','${article.title }')">编辑</button>
              	<button type="button" class="btn btn-danger" onclick="delArticle('${article.id}', '${article.title }')">删除</button>
              	<script type="text/javascript">
              		function delArticle(id, title) {
              			var del = confirm("删除文章"+title+"将同时删除该文章的评论，您确定要删除吗？");
              			if(del){
              				window.location.href = "${pageContext.request.contextPath}/articleServlet?method=delete&currentPage=${pageBean.currentPage}&id="+id;
              			}
              		}
              		
              		function updateArticle(id, title) {
              			var updateArticle = confirm("您确定要修改文章"+title+"吗？");
              			if(updateArticle){
              				window.location.href = "${pageContext.request.contextPath}/articleServlet?method=backWrite&id="+id;
              			}
              		}
              	</script>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      <!-- 分页 -->
      <nav style="margin-left: 60%;" aria-label="Page navigation">
        <ul class="pagination">
        <c:if test="${pageBean.currentPage == 1 }">
          <li class="disabled">
            <a href="javascript:void(0);" aria-label="Previous" >
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          </c:if>
          <c:if test="${pageBean.currentPage != 1 }">
	          <li>
	            <a href="${pageContext.request.contextPath }/articleServlet?method=list&currentPage=${pageBean.currentPage-1}&search=${search}&condition=${condition}" aria-label="Previous" >
	              <span aria-hidden="true">&laquo;</span>
	            </a>
	          </li>
          </c:if>
          
          
          <c:forEach begin="1" end="${pageBean.totalPage }" varStatus="s">
          	<c:if test="${pageBean.currentPage == s.count }">
          		<li class="active"><a href="javascript:void(0);">${s.count }</a></li>
          	</c:if>
          	<c:if test="${pageBean.currentPage != s.count }">
          		<li><a href="${pageContext.request.contextPath }/articleServlet?method=list&currentPage=${s.count}&search=${search}&condition=${condition}">${s.count }</a></li>
          	</c:if>
          </c:forEach>
          
          <c:if test="${pageBean.currentPage == pageBean.totalPage }">
          <li class="disabled">
            <a href="javascript:void(0);" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
          </c:if>
          <c:if test="${pageBean.currentPage != pageBean.totalPage }">
          <li>
            <a href="${pageContext.request.contextPath }/articleServlet?method=list&currentPage=${pageBean.currentPage+1}&search=${search}&condition=${condition}" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
          </c:if>
        </ul>
      <span class="label label-info" style="margin-left: 10%;">共${pageBean.total }条数据</span>
      </nav>
      
</body>
</html>