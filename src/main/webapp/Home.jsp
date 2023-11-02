<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
    import = "beans.Posicion"
    import = "beans.Jugador"
    import = "java.util.List"
    import = "javax.persistence.PersistenceContext"
	import = "javax.persistence.Query"
	import = "javax.persistence.EntityManager"
	import = "javax.persistence.EntityManagerFactory"
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
    <title>Plantilla del Atleti</title>
	</head>
	<body>

		<%@ include file="Header.jsp" %>
    	
    	<h2>Plantilla actual</h2>
    	
    	
    	<%
    	InitialContext ic = new InitialContext();
    	
    	DataSource ds = (DataSource) ic.lookup("jdbc/tiwp2");
    	Connection conn = ds.getConnection();
    	
    	
    	Statement sqlStatement_pos = conn.createStatement();
    	System.out.print("SELECT * FROM posiciones");
		ResultSet rs = sqlStatement_pos.executeQuery("SELECT * FROM posiciones");
    	%>
    	
    	
		<!-- Posiciones -->
    	<p>
    		| 
	    	<%
			while(rs.next()) {
				
				String nombre = rs.getString("nombre");
				int max_jugadores = rs.getInt("max_jugadores");
				int num_jugadores = rs.getInt("num_jugadores");
			%>
				<%= nombre %>s: <%= num_jugadores %>/<%= max_jugadores %> | 
			<%
			}
			%>
		</p>
		
		
		<!-- Jugadores -->
	  	<ol start="0">
	    <%
	    
	    Statement sqlStatement_jug = conn.createStatement();
	    System.out.print("SELECT * FROM jugadores");
		ResultSet rs_jug = sqlStatement_jug.executeQuery("SELECT * FROM jugadores");
		
		while(rs_jug.next()) {
			String dni = rs_jug.getString("dni");
			String nombre = rs_jug.getString("nombre");
			String apellidos = rs_jug.getString("apellidos");
			String alias = rs_jug.getString("alias");
			String posicion = rs_jug.getString("posicion_nombre");
	   	%>
	   		<li>
	   			[<%= posicion %>] <%= nombre %> 
		   		<%
		   		if (alias != null && alias != "null" && alias.length() > 0) {
	   			%>
	   				"<%= alias %>"
		   		<%
		   		}
		   		%>
	       		<%= apellidos %> - <%= dni %>
	       		
	       		|
	     
	       		<a href="EditarJugadorForm.jsp?id=<%= dni %>">Editar</a>
	       		<a href="EliminarJugador.html?id=<%= dni %>">Eliminar</a>
	      	</li>
	    <%
	    }
	    %>
	  	</ol>
	
	
	    <a href="InsertarJugadorForm.jsp">Insertar Jugador</a>


		<!--  Incluimos footer global -->
		<%@include file="Footer.jsp" %>
		
		
	</body>
</html>