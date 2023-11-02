<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	errorPage="Error.jsp"
    import = "java.util.List"
	import = "javax.sql.DataSource"
	import = "javax.naming.InitialContext"
	import = "java.sql.Connection"
	import = "java.sql.ResultSet"
	import = "java.sql.Statement"
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
    <title>Insertar jugador</title>

    <link rel="stylesheet" type="text/css" href="styles/css/themeColours.css"/>

	</head>
	<body>

	<%@ include file="Header.jsp" %>

		<h2>Insertar jugador</h2>
		
		<%
		if (request.getAttribute("error") != null) {
		%>	
			<p>Error: <%= request.getAttribute("error").toString() %></p>
		<%	
		}
		%>

		<article>
			<form action="InsertarJugador.html" method="post">
				<label for="f_nombre"> Nombre:</label><br/>
				  <input type="text" name="nombre" /><br/>
				<label for="f_apellidos"> Apellidos:</label><br/>
				  <input type="text" name="apellidos" /><br/>
				<label for="f_apodo"> Apodo:</label><br/>
				  <input type="text" name="apodo" /><br/>
				<label for="f_dni"> DNI:</label><br/>
        			<input type="text" name="dni" /><br/>
				<label for="f_posicion"> Posicion:</label><br/>
				    <select name="posicion">
						<%
						// get posiciones
						InitialContext ic = new InitialContext();
    	
				    	DataSource ds = (DataSource) ic.lookup("jdbc/tiwp2");
				    	Connection conn = ds.getConnection();
				    	
				    	
				    	Statement sqlStatement_pos = conn.createStatement();
				    	System.out.print("SELECT * FROM posiciones");
						ResultSet rs = sqlStatement_pos.executeQuery("SELECT * FROM posiciones");

						while(rs.next()) {
								
							String nombre = rs.getString("nombre");
							int max_jugadores = rs.getInt("max_jugadores");
							int num_jugadores = rs.getInt("num_jugadores");
							
							if (max_jugadores == num_jugadores) continue;
						%>
							<option value="<%= nombre %>"><%= nombre %></option>
						<%
						}
						%>
					</select>

        		<input type="submit" value="Submit" />
			</form>
		</article>


  
	<%@ include file="Footer.jsp" %>

	</body>
</html>