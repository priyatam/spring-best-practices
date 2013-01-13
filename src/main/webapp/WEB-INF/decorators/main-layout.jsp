<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>WARA UI Demo</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />    
  <link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" />    

  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>

<body>

   	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
      	<div class="container-fluid">
      		<c:import url="/WEB-INF/tags/navbar.jsp"/> 	
        </div>			      		 
      </div>
   	</div>

   	<div class="container-fluid">
	  <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
          	<c:import url="/WEB-INF/tags/menu.jsp"/>   
          </div>
        </div>
        
        <div class="span10">         
            <div class="hero-unit">
              <c:import url="/WEB-INF/tags/banner.jsp"/>       
            </div>
            <div class="row-fluid">
              <div class="span12">
              
                <decorator:body />
                
              </div>
              </div><!--/span-->
            </div><!--/row-->                 
        </div><!--/span-->
      </div><!--/row-->
	   
      <hr>

  	  <footer>
    	 <c:import url="/WEB-INF/tags/footer.jsp"/>
  	  </footer>

    </div>

	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>
</body>
</html>