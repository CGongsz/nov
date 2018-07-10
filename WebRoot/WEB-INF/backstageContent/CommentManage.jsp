<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<style>
		body {
			padding-left: 40px;
			background: #eee;
		}
	</style>
	
</head>
<body>
	<h2 class="sub-header">留言列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>评论编号</th>
                  <th>昵称</th>
                  <th>时间</th>
                  <th>内容</th>
                  <th>所属文章</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${pageBean.rows }" var="comment" varStatus="s">
                <tr>
                  <td>${(pageBean.currentPage-1)*pageBean.pageSize + s.count }</td>
                  <td>${comment.visitor_username }</td>
                  <td><fmt:formatDate value="${comment.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                  <td>${comment.content }</td>
                  <td>${comment.article.title }</td>
                  <td>
                  	<button type="button" class="btn btn-danger" onclick="deleteComment('${comment.id}','${comment.visitor_username }')">删除</button>
                  	<script type="text/javascript">
						function deleteComment(id, username) {
							var del = confirm("您确定要删除"+username+"的评论吗?");
							if(confirm) {
								window.location.href = "${pageContext.request.contextPath}/commentServlet?method=delete&id="+id+"&currentPage=${pageBean.currentPage}"
							}
						}
					</script>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
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
	            <a href="${pageContext.request.contextPath }/commentServlet?method=list&currentPage=${pageBean.currentPage-1}" aria-label="Previous" >
	              <span aria-hidden="true">&laquo;</span>
	            </a>
	          </li>
          </c:if>
          
          
          <c:forEach begin="1" end="${pageBean.totalPage }" varStatus="s">
          	<c:if test="${pageBean.currentPage == s.count }">
          		<li class="active"><a href="javascript:void(0);">${s.count }</a></li>
          	</c:if>
          	<c:if test="${pageBean.currentPage != s.count }">
          		<li><a href="${pageContext.request.contextPath }/commentServlet?method=list&currentPage=${s.count}">${s.count }</a></li>
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
            <a href="${pageContext.request.contextPath }/commentServlet?method=list&currentPage=${pageBean.currentPage+1}" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
          </c:if>
          
        </ul>
      <span class="label label-info" style="margin-left: 10%;">共${pageBean.total }条数据</span>
      </nav>   
      
</body>
</html>