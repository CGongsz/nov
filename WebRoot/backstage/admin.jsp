<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Dashboard Template for Bootstrap</title>
    <link href="${pageContext.request.contextPath }/style/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/style/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/style/css/admin.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/style/js/ie-emulation-modes-warning.js"></script>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Blog</a>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">文章管理 <span class="sr-only">(current)</span></a></li>
            <li><a href="#">留言管理</a></li>
            <li><a href="#">文章管理</a></li>
            <li><a href="#">个人信息</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">文章列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>标题</th>
                  <th>时间</th>
                  <th>浏览量</th>
                  <th>所属分类</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td>sit</td>
                  <td>
                  	<button type="button" class="btn btn-primary">ç¼è¾</button>
                  	<button type="button" class="btn btn-danger">å é¤</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <script src="${pageContext.request.contextPath }/style/js/botstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/style/js/holder.min.js"></script>
    <script src="${pageContext.request.contextPath }/style/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
