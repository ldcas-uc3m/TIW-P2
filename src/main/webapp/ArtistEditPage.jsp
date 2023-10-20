
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="Error.jsp"%>
<%@page import="entities.Artist"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" type="text/css"
	href="styles/css/themeColours.css" />




</head>
<body>

	<%@include file="Header.jsp"%>


	<%
	try {
		String msg = (String) request.getAttribute("message");
		if (msg != null) {
	%>
	<pre>
	<%=msg%>
	
	</pre>

	<%
	}
	} catch (Exception e) {

	}
	%>


	<%
	Artist artist = (Artist) request.getAttribute("artist");

	if (artist != null) {
	%>




	<article>
		<h3>Modify an existing artist:</h3>

		<form action="ArtistUpdate.html" method="post">

			<!--  Campo oculto que usaremos para la actualización/eliminación -->
			<input name="artistId" value="<%=artist.getIdartist()%>" hidden=true />
			<label for="f_name"> Name:</label> <br /> <input type="text"
				name="name" value="<%=artist.getName()%>" /> <br /> <label
				for="f_description"> Description:</label><br />
			<textarea id="f_description" name="description"> <%=artist.getDescription()%></textarea>
			<label for="f_followers"> <br /> Follower count:
			</label><br /> <input id="f_followers" type="number" name="followers"
				value="<%=artist.getFollowers()%>" /> <br /> <input type="submit"
				value="Update" />

		</form>

		<form action="ArtistDelete.html" method="post">
			<!--  Campo oculto que usaremos para la actualización/eliminación -->
			<input name="artistId" value="<%=artist.getIdartist()%>" hidden=true />
			<input type="submit" value="Delete" />
		</form>

	</article>


	<%
	}
	%>
<%@include file="Footer.jsp"%>

</body>
</html>