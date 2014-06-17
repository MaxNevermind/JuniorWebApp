<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link
		href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
		rel="stylesheet">
		<link
		href="${pageContext.request.contextPath}/resources/css/my.css"
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
						<li><a href="/postMessage">Post a message</a></li>
						<li class="active"><a href="/showMessages">Find a message</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Filters</h3>
			</div>
			<br>

			<form class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">User
						name:</label>
					<div class="col-sm-10">
						<input type="text" id="userName" class="filterField">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Period from:</label>
					<div class="col-sm-10">
						<input type="text"  id="date1" class="filterField"> <span class="error" id="errorDate1"> Invalid Date.(yyyy/mm/dd hh:mm:ss)</span>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Period to:</label>
					<div class="col-sm-10">
						<input type="text" id="date2" class="filterField"><span class="error" id="errorDate2"> Invalid Date.(yyyy/mm/dd hh:mm:ss)</span>
					</div>
				</div>
			</form>

		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Messages</h3>
			</div>
			<!-- <div class="container-fluid"> -->
			<table class="table">
				<tbody id="tableBody">
					<tr>
						<th class="col-sm-1">Date</th>
						<th class="col-sm-2">User</th>
						<th class="col-sm-9">Message</th>
					</tr>
					<c:forEach var="message" items="${messages}">
						<tr class="tableRow">
							<td class="col-sm-1"><fmt:formatDate value="${message.creationDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td class="col-sm-2">${message.userName}</td>
							<td class="col-sm-9">${message.message}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- </div> -->
		</div>
		<ul class="pagination" id="pagination">
		</ul>
	</div>
	<!-- /container -->


	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/my/findMessagePage.js"></script>

</body>
</html>