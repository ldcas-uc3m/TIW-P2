<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entities.Artist,java.util.List"%>
<%@ page errorPage="Error.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Artist Search by Id</title>
</head>
<body>

	<!--  Incluimos cabecera global -->
	<%@include file="Header.jsp"%>


	<form action="SearchArtistPage.html" method="post">

		<input type="text" name="search" placeholder="Enter the id" /> <input
			type="submit" value="SEARCH!" />

	</form>


	<%
	if (request.getAttribute("found") != null && (boolean) request.getAttribute("found")) {

		List<Artist> artistList = (List<Artist>) request.getAttribute("artistList");

		for (int i = 0; i < artistList.size(); i++) {
			Artist nthArtist = artistList.get(i);
	%>



	<div>
		<h4>
			Name:
			<%=nthArtist.getName()%>
			[ID=<%=nthArtist.getIdartist()%>]
		</h4>
		<p>
			Description:
			<%=nthArtist.getDescription()%></p>
		<p>
			Followers:
			<%=nthArtist.getFollowers()%></p>
		<br />

	</div>


	<%
	}
	}

	else {
	%>
	<h2>Full List of artists</h2>

	<%
	try {
		List<Artist> artistList = (List<Artist>) request.getAttribute("artistList");

		for (int i = 0; i < artistList.size(); i++) {
			Artist nthArtist = artistList.get(i);
	%>


	<hr>
	<div>
		<h4>
			Name:
			<%=nthArtist.getName()%>
			[ID=<%=nthArtist.getIdartist()%>]
		</h4>
		<p>
			Description:
			<%=nthArtist.getDescription()%></p>
		<p>
			Followers:
			<%=nthArtist.getFollowers()%></p>
		<br />

		<form action="ArtistEditPage.html" method="post">
			<input name="artistId" value="<%=nthArtist.getIdartist()%>"  hidden="true"/> <input
				type="submit" value="Modify me" />

		</form>


	</div>

	<%
	}
	}

	catch (Exception e) {
	%>
	<p>Full artist list could not be retrieved</p>

	<%
	}

	}
	%>







<%@include file="Footer.jsp"%>



</body>
</html>