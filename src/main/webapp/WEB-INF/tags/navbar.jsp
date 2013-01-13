<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a class="btn btn-navbar" data-toggle="collapse"
	data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="index.html">Your friendly Insurance Company</a>
<div class="nav-collapse collapse">
	<p class="navbar-text pull-right">
		<!-- Login -->
	<form class="form-inline">
		<input type="text" class="input-small" placeholder="Email"> <input
			type="password" class="input-small" placeholder="Password"> <label
			class="checkbox"> <input type="checkbox"> Remember me
		</label>
		<button type="submit" class="btn">Sign in</button>
	</form>
	
	<div id="loggedout" style="float: right; display: none">
		(<a href="#" style="color: #ffffff" id="logoutlink"> <strong>logout</strong></a>)
	</div>
	<div id="loggedin" style="float: right; display: none"></div>

	<ul class="nav">
		<li class="active"><a href="<c:url value="/" />"> Home</a></li>
		<li><a href="<c:url value="/about" />">About</a></li>
		<li><a href="<c:url value="/policies" />">Policies</a></li>
	</ul>
</div>