<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	errorPage="Error.jsp"
	import = "beans.Jugador"
    import = "beans.Posicion"
    import = "java.util.List"
    import = "javax.persistence.PersistenceContext"
	import = "javax.persistence.Query"
	import = "javax.persistence.EntityManager"
	import = "javax.persistence.EntityManagerFactory"
	import = "javax.persistence.Persistence"
	import = "javax.naming.InitialContext"
	import = "javax.sql.DataSource"
	import = "java.sql.Connection"
	import = "java.sql.ResultSet"
	import = "java.sql.Statement"
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
    <title>Editar jugador</title>

    <link rel="stylesheet" type="text/css" href="styles/css/themeColours.css"/>

	</head>
	<body>

	<%@ include file="Header.jsp" %>


		<h2>Editar jugador</h2>
		
		<%
		if (request.getAttribute("error") != null) {
		%>
			<p>Error: <%= request.getAttribute("error").toString() %></p>
		<%
		}
		
		
		InitialContext ic = new InitialContext();

    	DataSource ds = (DataSource) ic.lookup("jdbc/tiwp2");
    	Connection conn = ds.getConnection();
		
		//Cogemos el jugador
		Statement sqlStatement = conn.createStatement();
		String query = "SELECT * FROM jugadores WHERE dni = '" + request.getParameter("id") + "'";
		System.out.print(query);
		ResultSet rs = sqlStatement.executeQuery(query);
		
		rs.next();
		String nombre = rs.getString("nombre");
		String apellidos = rs.getString("apellidos");
		String alias = rs.getString("alias");
		String posicion = rs.getString("posicion_nombre");
		
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
		//EntityManager em = factory.createEntityManager();
		
		//Jugador j = em.find(Jugador.class, request.getParameter("id"));
		%>
		
		<article>
			<form action="EditarJugador.html?id=<%= request.getParameter("id") %>" method="post">
				<p>DNI: <%= request.getParameter("id") %></p><br/>
				<label for="f_nombre"> Nombre:</label><br/>
				  <input type="text" name="nombre"  value=<%= nombre %>><br/>
				<label for="f_apellidos"> Apellidos:</label><br/>
				  <input type="text" name="apellidos"  value=<%= apellidos %>><br/>
				<label for="f_apodo"> Apodo:</label><br/>
				
				<%
				  if (alias == null || alias == "null") {
				  %>
				  	<input type="text" name="apodo" /><br/>
			  	  <%
				  } else {
				  %>
				    <input type="text" name="apodo"  value=<%= alias %>><br/>
				  <%
				  }
				  %>
				  
				<label for="f_posicion"> Posicion:</label><br/>
				    <select name="posicion">
						<%
						
						// get posiciones
				    	
				    	Statement sqlStatement_pos = conn.createStatement();
				    	System.out.print("SELECT * FROM posiciones");
						rs = sqlStatement_pos.executeQuery("SELECT * FROM posiciones");
						
						
						
						while(rs.next()) {
								
							String nombre_pos = rs.getString("nombre");
							int max_jugadores = rs.getInt("max_jugadores");
							int num_jugadores = rs.getInt("num_jugadores");
							
							if (nombre_pos == posicion) {
							%>
								<option value="<%= nombre_pos %>" selected><%= nombre_pos %></option>
							<%
							}
							else if (max_jugadores == num_jugadores) continue;
							else {
							%>
								<option value="<%= nombre_pos %>"><%= nombre_pos %></option>
							<%
							}
						}
						%>
					</select>

        		<input type="submit" value="Submit" />
			</form>
		</article>
		
		
		


	<%@ include file="Footer.jsp" %>

	</body>
</html>