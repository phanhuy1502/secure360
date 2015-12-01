<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Check files for anormally</title>
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
      <div class ="left" style="margin-left: 20px"> <a href="/secure360/index"> Back </div>
			<div class="container">
				<div class="nav-wrapper">
					<a class="page-title">SECURITY 360 @ AUTODESK</a>
				</div>
			</div>
      	</nav>
    </header>
	<div class="container" role="main">	
    <h3 class="center">File checking result</h3>
    <ul class="collapsible popout" data-collapsible="accordion">
      <li>
        <div class="collapsible-header"><i class="material-icons">description</i>List of files with anomaly</div>
        <div class="collapsible-body">
          <table class ="highlight responsive-table z-depth-2">
            <thead>
              <tr>
                <th data-field="id"></th>
                <th data-field="file">File</th>
                <th data-field="header">Header No.</th>
                <th data-field="distance">Distance</th>
                <th data-field="ratio">Ratio</th>
              </tr>
            </thead>
            
            <c:set var="anormallyCount" value="0" scope="page" />
            <tbody>
              <c:forEach items="${malicious}" var="anormallyFile" varStatus="status">
                <c:set var="anormallyCount" value="${anormallyCount + 1}" scope="page"/>
                <c:forEach items="${anormallyFile.getAnormallyList()}" var="anormally" varStatus="status">
                  <tr>
                    <td><c:out value="${anormallyCount}" default="Not Available" escapeXml="false"></c:out></td>
                    <td><c:out value="${anormallyFile.getFileName()}" default="Not Available" escapeXml="false"></c:out></td>
                    <td><c:out value="${anormally.getPosition()}" default="Not Available" escapeXml="false"></c:out></td>
                    <td><c:out value="${anormally.getDistance()}" default="Not Available" escapeXml="false"></c:out></td>
                    <td ><c:out value="${anormally.getExceedingRatio()}" default="Not Available" escapeXml="false"></c:out></td> 
                  </tr>   
                </c:forEach>        
              </c:forEach>
            </tbody>
          </table>         
        </div>
      </li>
      <li>
        <div class="collapsible-header"><i class="material-icons">business</i>List of files</div>
        <div class="collapsible-body"><p>Total number of files: </p></div>
      </li>
    </ul>

    <form action="checkfile" method="get">
      <span class="input-group-btn">
        <button class="btn btn-default right" url="checkfile">Rerun engine</button>
      </span>
    </form>

  </div>
 </body>
</html>                                                       