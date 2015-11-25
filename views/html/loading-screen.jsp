<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Check API for Injection</title>
	<meta name="description" content="">
	<meta name="author" content="huy">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/secure360/views/css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/secure360/views/css/theme.css"  media="screen,projection"/>
</head>

<body role="document">
	<script type="text/javascript" src="/secure360/views/js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="/secure360/views/js/materialize.min.js"></script>
	<script type="text/javascript" src="/secure360/views/js/main.js"></script>
    <header>
    	<nav class="top-nav">
			<div class="container">
				<div class="nav-wrapper">
					<a class="page-title">SECURITY 360 @ AUTODESK</a>
				</div>
			</div>
      	</nav>
    </header>
	<div class="container" role="main" >
		<div class="component-wrapper">
		<h5 class="center">LOADING ...</h5>
		<div class="preloader-wrapper big active" id="preloader-circle">
	      <div class="spinner-layer spinner-blue">
	        <div class="circle-clipper left">
	          <div class="circle"></div>
	        </div><div class="gap-patch">
	          <div class="circle"></div>
	        </div><div class="circle-clipper right">
	          <div class="circle"></div>
	        </div>
	      </div>

	      <div class="spinner-layer spinner-red">
	        <div class="circle-clipper left">
	          <div class="circle"></div>
	        </div><div class="gap-patch">
	          <div class="circle"></div>
	        </div><div class="circle-clipper right">
	          <div class="circle"></div>
	        </div>
	      </div>

	      <div class="spinner-layer spinner-yellow">
	        <div class="circle-clipper left">
	          <div class="circle"></div>
	        </div><div class="gap-patch">
	          <div class="circle"></div>
	        </div><div class="circle-clipper right">
	          <div class="circle"></div>
	        </div>
	      </div>

	      <div class="spinner-layer spinner-green">
	        <div class="circle-clipper left">
	          <div class="circle"></div>
	        </div><div class="gap-patch">
	          <div class="circle"></div>
	        </div><div class="circle-clipper right">
	          <div class="circle"></div>
	        </div>
	      </div>
	    </div>
	  <div>
</body>
<form action="api" method="post" style="display: none">
    <input type="hidden" name="file-name" value = "${fileName}">
	<input type="hidden" name="email" value = "${email}">
	<button type="submit" id="submit"> </button>
</form>

<!-- this script submits the form AFTER it has been completely loaded -->
<script>
    $("#submit").click();
</script>
</html>                                                       

		