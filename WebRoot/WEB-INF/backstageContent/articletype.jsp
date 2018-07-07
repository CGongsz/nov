<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <h2 class="sub-header">分类管理</h2>
    <div class="table-responsive">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>文章类别</th>
              <th>类别描述</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageBean.rows }" var="articleType">
            <tr>
              <td>${articleType.typeId }</td>
              <td>${articleType.typeName }</td>
              <td>${articleType.info }</td>
              <td>
              	<button type="button" class="btn btn-danger" onclick="delArticleType('${articleType.typeId }', '${articleType.typeName }')">删除</button>
              	<script type="text/javascript">
              		function delArticleType(id, typeName) {
              			var del = confirm("删除"+typeName+"类型将删除该类型的所有文章以及文章的所有评论，您确定要删除吗？")
              			if(del){
              				window.location.href = "${pageContext.request.contextPath}/articleTypeServlet?method=delete&currentPage=${pageBean.currentPage}&id="+id
              			}
              		}
              	</script>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
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
	            <a href="${pageContext.request.contextPath }/articleTypeServlet?method=list&currentPage=${pageBean.currentPage-1}" aria-label="Previous" >
	              <span aria-hidden="true">&laquo;</span>
	            </a>
	          </li>
          </c:if>
          
          
          <c:forEach begin="1" end="${pageBean.totalPage }" varStatus="s">
          	<c:if test="${pageBean.currentPage == s.count }">
          		<li class="active"><a href="javascript:void(0);">${s.count }</a></li>
          	</c:if>
          	<c:if test="${pageBean.currentPage != s.count }">
          		<li><a href="${pageContext.request.contextPath }/articleTypeServlet?method=list&currentPage=${s.count}">${s.count }</a></li>
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
            <a href="${pageContext.request.contextPath }/articleTypeServlet?method=list&currentPage=${pageBean.currentPage+1}" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
          </c:if>
        </ul>
      </nav>
        <form action="#">
        	<div class="form-group has-warning has-feedback">
        	  <label>类别:</label>
        	  <input style="width: 10%;margin-right: 0;" type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status">	
        	  
        	</div>
        	<div class="form-group has-warning has-feedback">
	        	<label>描述:</label>
	        	<textarea class="form-control" rows="3"></textarea>
	        	<span id="inputWarning2Status" class="sr-only">(warning)</span>
	        	<button style="float: right; margin-right: 20px;margin-top: 20px;" type="button" class="btn btn-primary">&nbsp;&nbsp;添加&nbsp;&nbsp;</button>
        	</div>
        </form>

    </div>
</body>
</html>