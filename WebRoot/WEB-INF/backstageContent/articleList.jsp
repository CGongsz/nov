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
</head>
<body>
      <h2 class="sub-header">文章列表</h2>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
            <tr>
              <!-- <th>ID</th> -->
              <th>标题</th>
              <th>最后修改时间</th>
              <th>浏览量</th>
              <th>所属分类</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageBean.rows }" var="article">
            <tr>
              <%-- <td>${article.id }</td> --%>
              <td>${article.title }</td>
              <td><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
              <td>${article.clickRate }</td>
              <td>${article.articleType.typeName }</td>
              <td>
              	<button type="button" class="btn btn-primary">编辑</button>
              	<button type="button" class="btn btn-danger" onclick="delArticle('${article.id}', '${article.title }')">删除</button>
              	<script type="text/javascript">
              		function delArticle(id, title) {
              			var del = confirm("删除文章"+title+"将同时删除该文章的评论，您确定要删除吗？");
              			if(del){
              				window.location.href = "${pageContext.request.contextPath}/articleServlet?method=delete&currentPage=${pageBean.currentPage}&id="+id;
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
        <c:if test="${pageBean.currentPage == 1}">
          <li class="disabled">
            <a href="javascript:void(0);" aria-label="Previous" >
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          </c:if>
          <c:if test="${pageBean.currentPage != 1}">
	          <li>
	            <a href="${pageContext.request.contextPath }/articleServlet?method=list&currentPage=${pageBean.currentPage-1}" aria-label="Previous" >
	              <span aria-hidden="true">&laquo;</span>
	            </a>
	          </li>
          </c:if>
          
          
          <c:forEach begin="1" end="${pageBean.totalPage }" varStatus="s">
          	<c:if test="${pageBean.currentPage == s.count }">
          		<li class="active"><a href="javascript:void(0);">${s.count }</a></li>
          	</c:if>
          	<c:if test="${pageBean.currentPage != s.count }">
          		<li><a href="${pageContext.request.contextPath }/articleServlet?method=list&currentPage=${s.count}">${s.count }</a></li>
          	</c:if>
          </c:forEach>
          
          <c:if test="${pageBean.currentPage == pageBean.totalPage}">
          <li class="disabled">
            <a href="javascript:void(0);" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
          </c:if>
          <c:if test="${pageBean.currentPage != pageBean.totalPage}">
          <li>
            <a href="${pageContext.request.contextPath }/articleServlet?method=list&currentPage=${pageBean.currentPage+1}" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
          </c:if>
        </ul>
      </nav>
</body>
</html>