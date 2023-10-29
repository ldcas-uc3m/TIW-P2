<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
    import = "entities.Posicion"
    import = "entities.Jugador"
    import = "java.util.List"
    import = "javax.persistence.PersistenceContext"
	import = "javax.persistence.Query"
	import = "javax.persistence.EntityManager"
	import = "javax.persistence.EntityManagerFactory"
	import = "javax.persistence.Persistence"
	
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
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
		EntityManager em = factory.createEntityManager();
    	%>
    	
    	<p>
    		| 
	    	<%
	    	// get posiciones
			Query query_pos = em.createNamedQuery("Posicion.getPosiciones");
			List<Posicion> posiciones = query_pos.getResultList();
			
			for (Posicion pos : posiciones) {
			%>
				<%= pos.getNombre() %>s: <%= pos.getNumJugadores() %>/<%= pos.getMaxJugadores() %> | 
			<%
			}
			%>
		</p>
		
		
		<!-- Jugadores -->
	  	<ol start="0">
	    <%
	
	    Query query_jug = em.createNamedQuery("Jugador.findAll");
	    
		List<Jugador> jugadores = query_jug.getResultList();
		
	    
		int i = 0;
	    for (Jugador j : jugadores) {
	   	%>
	   		<li>
	   			[<%= j.getPosicion() %>] <%= j.getNombre() %> 
		   		<%
		   		if (j.getAlias() != null) {
	   			%>
	   				"<%= j.getAlias() %>"
		   		<%
		   		}
		   		%>
	       		<%= j.getApellidos() %> - <%= j.getDni() %>
	       		
	       		|
	     
	       		<a href="EditarJugadorForm.jsp?id=<%= j.getDni() %>">Editar</a>
	       		<a href="EliminarJugadorForm.jsp?id=<%= j.getDni() %>">Eliminar</a>
	      	</li>
	    <%
	    }
		em.close();
	    %>
	  	</ol>
	
	
	    <a href="InsertarJugadorForm.jsp">Insertar Jugador</a>


		<!--  Incluimos footer global -->
		<%@include file="Footer.jsp" %>
		
		
	</body>
</html>