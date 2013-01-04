<%@ page import="net.prcins.wara.ui.Version"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Pragma" content="no-cache" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/bootstrap-responsive.min.css" />"
	rel="stylesheet" type="text/css" />

<title><%=Version.PROJECT_NAME%> - <%=Version.PROJECT_VERSION%></title>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
      <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
  
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</a>
				<a class="brand" href="#">Eclaims Profiles</a>
				<div class="nav-collapse collapse">  
				  <ul class="nav">
				    <li class="active"><a href="<c:url value="/" />">Home</a></li>
				    <li><a href="<c:url value="/getstarted" />">Get started</a></li>
				    <li><a href="<c:url value="/errors-validations" />">Errors &amp; Validations</a></li>
				  </ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
					  <li class="nav-header">FAQ</li>
					  <li><a href="<c:url value="/faq" />"> Why another fork?</a></li>
					 
					  <li class="nav-header">Reference</li>
					  <li><a href="https://github.com/SpringSource/spring-mvc-showcase/blob/master/MasteringSpringMVC3.pdf" target="_blank">Mastering Spring MVC</a></li>
					  <li><a href="http://twitter.github.com/bootstrap/getting-started.html" target="_blank">Mastering Bootstrap</a></li>
					  <li><a href="http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/spring-web.html" target="_blank">Reference Guide</a></li>
					  <li><a href="https://src.springframework.org/svn/spring-samples/" target="_blank">Spring Samples</a></li>   
					  <li><a href="http://wrapbootstrap.com/" target="_blank">Bootstrap Themes</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span10">
				<div class="hero-unit">
					<h1>Eclaims Profile</h1>
					<p>With <a href="http://twitter.github.com/bootstrap/" target="_blank">Twitter Bootstrap</a> 2.1</p>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="container" align="center">
							<p>
							
							</p>
						</div>

						<hr class="soften">

						<%=Version.PROJECT_NAME%>
						<%=Version.PROJECT_VERSION%>

					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
		</div>
		<!--/span-->
	</div>
	<!--/row-->

	<hr>

	<footer class="footer">
	    <div class="container">
	        <p class="pull-right"><a href="#">Back to top</a></p>
	    </div>
	</footer>
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/bootstrap.js" />"></script>	
</html>