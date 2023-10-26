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

		<!--  Incluimos cabecera global -->
		<%@ include file="Header.jsp" %>

		<h1>Plantilla del Atleti</h1>
    
  	<ol start="0">

    <%
    // get jugadores
    // @PersistenceContext(unitName = "PU")
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
    EntityManager em = factory.createEntityManager();
    
    Query query = em.createNamedQuery("Jugador.findAll");
    
	List<Jugador> jugadores = query.getResultList();
	
    
	int i = 0;
    for (Jugador j : jugadores) {
   	%>
   	<li>
      [<%= j.getPosicion() %>] <%= j.getNombre() %> "<%= j.getAlias() %>" <%= j.getApellidos() %> - <%= j.getDni() %>
       <a href="EditarJugadorPage.html?index=<%=i%>">Editar</a>
       <a href="EliminarJugador.html?index=<%=i%>">Eliminar</a>
      </li>
    <%
		i++;

    }
	em.close();
    %>
  	</ol>


    <a href="InsertarJugadorForm.jsp">Insertar Jugador</a>


		<!--  Incluimos footer global -->
		<%@include file="Footer.jsp" %>
		
		
	</body>
</html>