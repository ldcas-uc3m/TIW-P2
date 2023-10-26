<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
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
    @PersistenceContext(unitName = "PU")
    EntityManager em;
    
    Query query = em.createdNamedQuery("Jugador.findAll");
    
    List<Jugador> jugadores = query.getResultList();
    
    for (Jugador j : jugadores) {
    %>
    <li>
      [<%= jugador.getPosicion() %>] <%= jugador.getNombre() %> "<%= jugador.getAlias() %>" <%= jugador.getApellidos() %> - <%= jugador.getDNI() %>
       <a href="EditarJugadorPage.html?index=<%=i%>">Editar</a>
       <a href="EliminarJugador.html?index=<%=i%>">Eliminar</a>
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