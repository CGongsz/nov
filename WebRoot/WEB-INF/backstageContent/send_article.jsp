<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery-3.2.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery.tinymce.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/tinymce.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery.form.js"></script>
	<script>
		tinymce.init({
		  selector: "#mytextarea",
		  height: 450,
		  upload_image_url: '${pageContext.request.contextPath}/articleServlet?method=imgurl',//图片上传路由
		 /*  plugins: [
		    "advlist autolink autosave link image lists charmap print preview hr anchor pagebreak",
		    "searchreplace visualblocks code fullscreen textcolor colorpicker textpattern code uploadimage",
		    /* "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
		    'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc help uploadimage',
		    
		    "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"
		  ], */
		   plugins: [
                'advlist autolink autosave lists link image charmap print preview anchor',
                'searchreplace visualblocks code fullscreen textcolor colorpicker textpattern code uploadimage',
                'contextmenu paste'
            ],
		  /* toolbar1: "newdocument fullpage | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect",
		  toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | insertdatetime preview | forecolor backcolor",
		  toolbar3: "table | hr removeformat | subscript superscript | charmap emoticons | print fullscreen | ltr rtl | visualchars visualblocks nonbreaking template pagebreak restoredraft | codesample help uploadimage", */
		  content_css: [
		    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
		    '//www.tinymce.com/css/codepen.min.css'],
		  toolbar1: "undo redo | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | fontselect fontsizeselect",
            toolbar2: "forecolor backcolor | bullist numlist | outdent indent | removeformat | link unlink uploadimage | preview fullscreen",
		  menubar: false,
		  toolbar_items_size: 'small',

		  style_formats: [{
		    title: 'Bold text',
		    inline: 'b'
		  }, {
		    title: 'Red text',
		    inline: 'span',
		    styles: {
		      color: '#ff0000'
		    }
		  }, {
		    title: 'Red header',
		    block: 'h1',
		    styles: {
		      color: '#ff0000'
		    }
		  }, {
		    title: 'Example 1',
		    inline: 'span',
		    classes: 'example1'
		  }, {
		    title: 'Example 2',
		    inline: 'span',
		    classes: 'example2'
		  }, {
		    title: 'Table styles'
		  }, {
		    title: 'Table row 1',
		    selector: 'tr',
		    classes: 'tablerow1'
		  }],

		  templates: [{
		    title: 'Test template 1',
		    content: 'Test 1'
		  }, {
		    title: 'Test template 2',
		    content: 'Test 2'
		  }],
		  
		  init_instance_callback: function () {
		    window.setTimeout(function() {
		      $("#div").show();
		     }, 1000);
		  }
		}); 
	</script>
	<style>
		button {
			margin-right: 2%;
			float: right;
		}
		.form-control {
			width: 30%;
		}
		body {
			padding-left: 40px;
			background: #eee;
		}
	</style>
	<script>
		$.get(
			"${pageContext.request.contextPath}/articleServlet?method=art_type",
			function(data) {
				var list = data.list;
				for(var i = 0; i < list.length; i ++) {
					var option = $("<option value="+list[i].typeId+">"+list[i].typeName+"</option> ");
					console.log(option);
					$("#art_type").append(option);
				}
			},
			'json'
		);
		
		function articleSave(){
			var title = $("#title").val();
			var keyword = $("#keyword").val();
			
			if(title == null || title.trim() == ""){
				$("#titleMes").html("标题不能为空");
				return;
			}else{
				$("#titleMes").html("");
			}
			
			if(keyword == null || keyword.trim() == ""){
				$("#keywordMes").html("关键字不能为空");
				return;
			}else{
				$("#keywordMes").html("");
			}
			
			var errorMes = $("#errorMes").html();
			if(errorMes == "已存在该标题的文章"){
				alert("请修改文章标题")
				return;
			}
			$("#articleForm").submit();
		}
		
		function avoidRepeat(){
		
			var title = $("#title").val().trim();
			
			if(title == ""){
				return;
			}
			
			$.post(
				"${pageContext.request.contextPath}/articleServlet?method=avoidRepeat",
				{'title':title},
				function(data){
					$("#errorMes").html(data);
				}
			);
		}
	</script>
</head>
<body>
	<div>
          <h2 class="sub-header">编辑文章</h2>
          <div class="table-responsive">
            <form id="articleForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/articleServlet?method=save">
            	<label for="">所属分类</label>
            	<select id="art_type" name="article_type_id" class="form-control">          	  
            	</select>
            	<div>
            	<label for="">标题</label>
            	<input id="title" onblur="avoidRepeat()" name="title" type="text" class="form-control">  
            	<span id="titleMes" style="color: red;"></span>
				<span id="errorMes" style="color: red;"></span>
            	</div>
            	<div>
            	<label  for="">关键字</label>
            	<input id="keyword" name="keyword" type="text" class="form-control">  
            	<span id="keywordMes" style="color: red;"></span>
            	</div>
            	<br><br>
				<input name="clickRate" type="hidden" value='${art.clickRate }'>
               <textarea name="content" id="mytextarea"></textarea>
               <!-- <button type="submit" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;</button> -->
               <a class="btn btn-primary" href="javascript:void(0);" onclick="articleSave()">&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;</a>
            </form>
          </div>
        </div>
</body>
</html>