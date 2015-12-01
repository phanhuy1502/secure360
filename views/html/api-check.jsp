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
			<div class="row">
				<form action="loading" method="post" class="col s12" id="injection-detection-form">
					<div class="row">
						<div class="input-field col s1">
							<i class="material-icons prefix right">file_upload</i>
						</div>
						<div class="input-field col s5">
							<select id="input-file-name" class="validate" name="file-name" required >
								<c:forEach items="${files}" var="file">
									<c:choose>
									    <c:when test="${file==fileName}">
									        <option value="${file}" selected><c:out value="${file}" default="Not Available" escapeXml="false"></c:out></option>
									    </c:when>    
									    <c:otherwise>
									        <option value="${file}"><c:out value="${file}" default="Not Available" escapeXml="false"></c:out></option>
									    </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<label>Select type of API security check</label>
							<div class="chip right">Use <span class ="blue-text text-darken-2">less</span> for smaller files</div>
							<c:if test="${not empty error}">
								<div id="error" class = "chip white-text red darken-4 right"> <c:out value="${error}"> </c:out> </div>	
							</c:if>
						</div>
						<div class="input-field col s1">
							<i class="material-icons prefix right">email</i>
						</div>
						<div class="input-field col s5">
							<input id="input-email-address" type="email" class="validate" name="email" value = "${email}">
							<label for="input-email-address" data-error="Wrong format">Your email address</label>
						</div>
					</div>

					<span class="input-group-btn right">
						<button class="btn btn-default" type="submit">Run Engine!</button>
					</span>
				</form>
			</div>
		<script>
    	$('select').material_select();
    	$("#error").fadeIn(1000).delay(3000).fadeOut(3000);
    </script>
		</div>
		
		<c:if test="${not empty result}">
		<div class="component-wrapper">
			 <blockquote>
		     		<h5> The file has <span class="btn-floating btn-large waves-effect waves-light red"><i><c:out value="${result.size()}" default="Not Available" escapeXml="false"></c:out></i></span> injections. </h5>
		    </blockquote>
			<table class ="highlight responsive-table z-depth-2">
				<thead>
					<tr>
						<th data-field="id"></th>
						<th data-field="line">Line</th>
						<th data-field="url">URL</th>
						<th data-field="header">Header</th>
						<th data-field="value">Value</th>
						<th data-field="type">Injection Type</th>
					</tr>
				</thead>
				
				<c:set var="injectionCount" value="0" scope="page" />
				<tbody>
					<c:forEach items="${result}" var="injectionList" varStatus="status">
						<c:set var="injectionCount" value="${injectionCount + 1}" scope="page"/>
						<tr>
							<td><c:out value="${injectionCount}" default="Not Available" escapeXml="false"></c:out></td>
							<td><c:out value="${injectionList.getLineNumber()}" default="Not Available" escapeXml="false"></c:out></td>
							<td class="break-word"><div><c:out value="${injectionList.getURL()}" default="Not Available" escapeXml="false"></c:out></div></td>
							<td class="break-word"><div><c:out value="${injectionList.getHeader()}" default="Not Available" escapeXml="false"></c:out></div></td>
							<td class="break-word"><div>
								<c:out value="${injectionList.getValue()[0]}" default="Not Available" escapeXml="false"></c:out>
								<span class="red-text"><c:out value="${injectionList.getValue()[1]}" default="Not Available" escapeXml="false"></c:out></span>
								<c:out value="${injectionList.getValue()[2]}" default="Not Available" escapeXml="false"></c:out>
							</div></td>	
							<td><c:out value="${injectionList.getType()}" default="Not Available" escapeXml="false"></c:out></td>
						</tr>		    		
			  		</c:forEach>
				</tbody>
			</table>        
	    </div>
	    </c:if>
    </div>
 </body>
</html>                                                       