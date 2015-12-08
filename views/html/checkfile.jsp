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

    <div class="component-wrapper">
      <form action="checkfile" method="get">
        <span class="input-group-btn">
            <div class="row">
              <div class="input-field col s1">
                <i class="material-icons prefix right">file_upload</i>
              </div>

              <div class="input-field col s6">
                <input disabled id="input-path-folder" type="text" class="validate" name="path" value = "/home/phanhuy1502/Spark/Scala/files/original-header" >
                <label for="input-path-folder">Path to folder</label>
              </div>

              <div class="input-field col s1">
                <i class="material-icons prefix right">email</i>
              </div>
              <div class="input-field col s4">
                <input id="input-email-address" type="email" class="validate" name="email" value = "${email}">
                <label for="input-email-address" data-error="Wrong format">Your email address</label>
              </div>
            </div>
          <button class="btn btn-default right" url="checkfile">Rerun engine</button>
        </span>
      </form>
    </div>
    </br> </br>
    <div class="component-wrapper">
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
          <div class="collapsible-header"><i class="material-icons">business</i>List of files in the folder</div>
          <div class="collapsible-body">
            <ul class="collection">
              <li class="collection-item"> Total number of files: <c:out value="${files.size()}" default="Not Available" escapeXml="false"></c:out> </li>
              <li class="collection-item"> Total number of malicious files: <c:out value="${anormallyCount}" default="Not Available" escapeXml="false"></c:out> </li>
            </ul>
             <table class ="highlight responsive-table z-depth-2">
                <thead>
                  <tr>
                    <th></th>
                    <th>File</th>
                    <th>Check result</th>
                  </tr>
                </thead>
                
                <c:set var="fileCount" value="0" scope="page" />
                <tbody>
                  <c:forEach items="${files}" var="file" varStatus="status">
                    <c:set var="fileCount" value="${fileCount + 1}" scope="page"/>
                      <tr>
                        <td><c:out value="${fileCount}" default="Not Available" escapeXml="false"></c:out></td>
                        <td><c:out value="${file.getName()}" default="Not Available" escapeXml="false"></c:out></td>
                        <td>
                          <c:if test="${file.isMalicious()}">
                            <i class="material-icons">warnings</i>
                          </c:if>
                          <c:if test="${not file.isMalicious()}">
                            <i class="material-icons">done_all</i>
                          </c:if>
                        </td>
                      </tr>        
                  </c:forEach>
                </tbody>
              </table>  
            </div>       
        </li>
      </ul>
    </div>
  </div>
 </body>
</html>                                                       