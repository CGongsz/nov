<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
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
        <table class="table table-striped" style="height:360px;">
          <thead>
            <tr>
              <th>类别编号</th>
              <th>文章类别</th>
              <th>类别描述</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageBean.rows }" var="articleType" varStatus="s">
            <tr>
              <td>${(pageBean.currentPage-1)*pageBean.pageSize + s.count }</td>
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
      <span class="label label-info" style="margin-left: 10%;">共${pageBean.total }条数据</span>
      </nav>
      <script type="text/javascript">
      		function saveArticleType(){
      			var typeName = $("#typeName").val();
      			var info = $("#info").val();
      			
      			if(typeName == null || typeName.trim() == ""){
      				$("#typeNameMes").html("请输入需要创建的文章类型");
    				return;
      			}else{
      				$("#typeNameMes").html("");
      			}
      			
      			if(info == null || info.trim() == ""){
      				$("#infoMes").html("请输入对该文章类型的一些描述");
      				return;
      			}else{
      				$("#infoMes").html("");
      			}
      			
      			var errorMes = $("#errorMes").html();
				if(errorMes == "已存在该类型名"){
					alert("请修改类型名");
					return;
				}
      			
      			$("#articleTypeForm").submit();
      		}
      		
      		function avoidRepeat(){
      			var typeName = $("#typeName").val();
	      		$.post(
	      			"${pageContext.request.contextPath}/articleTypeServlet?method=avoidRepeat",
	      			{'typeName':typeName},
	      			function(data){
	      				$("#errorMes").html(data);
	      			}
	      		);
	      		
	      		
      		}
      </script>
        <form id="articleTypeForm" action="${pageContext.request.contextPath }/articleTypeServlet?method=save&currentPage=${pageBean.currentPage}" method="post">
        	<div class="form-group has-warning has-feedback">
        	  <label>类别:</label>
        	  <input onblur="avoidRepeat()" name="typeName" style="width: 10%;margin-right: 0;" type="text" class="form-control" id="typeName" aria-describedby="inputWarning2Status">	
        	  <span id="typeNameMes" style="color: red;"></span>
        	  <span id="errorMes" style="color: red;"></span>
        	</div>
        	<div class="form-group has-warning has-feedback">
	        	<label>描述:</label>
	        	<textarea id="info" name="info" class="form-control" rows="3"></textarea>
	        	<span id="infoMes"  style="color: red;"></span>
	        	<button style="float: right; margin-right: 20px;margin-top: 20px;" onclick="saveArticleType()" type="button" class="btn btn-primary">&nbsp;&nbsp;添加&nbsp;&nbsp;</button>
        	</div>
        </form>

    </div>
</body>
</html>