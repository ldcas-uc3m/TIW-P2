<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a new artist</title>

<link rel="stylesheet" type="text/css" href="styles/css/themeColours.css"/>

</head>
<body>

<%@include file="Header.jsp"%>


	<h1>Create a new artist</h1>


	<article>
		<form action="InsertArtistPage.html" method="post">
			<label for="f_name"> Name:</label><br/> <input type="text" name="name" /><br/>
			<label for="f_description"> Description:</label><br/>
			<textarea id="f_description" name="description"> </textarea><br/>
			<label for="f_followers"> Follower count:</label><br/>
			 <input
				id="f_followers" type="number" name="followers" /> <br/> <input
				type="submit" value="Submit" />
		</form>
	</article>

<%@include file="Footer.jsp"%>

</body>
</html>