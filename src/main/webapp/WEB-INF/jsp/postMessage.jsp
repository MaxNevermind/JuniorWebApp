<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link
		href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
		rel="stylesheet">
		

<title>Sample project</title>

</head>
<body>

	<div class="container">

		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">Sample project</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/postMessage">Post a message</a></li>
						<li><a href="/showMessages">Find a message</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>


		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">New message</h3>
			</div>
			
				<div class="container">
					<form action="postMessage" method="post">
						<br>User name: <br>
						<input class="span2" type="text" name="userName"><br>
						Message: <br>
						<textarea cols="40" rows="5" name="message"></textarea>
						<br>
						<button type="submit" class="btn btn-primary btn-large">Submit</button>
						<br><br>
					</form>
	
				</div>
		</div>

	</div> <!-- /container -->
    
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
</body>
</html>