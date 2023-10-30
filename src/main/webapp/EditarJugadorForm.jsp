<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	errorPage="Error.jsp"
	import = "entities.Jugador"
    import = "entities.Posicion"
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
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
		EntityManager em = factory.createEntityManager();
		
		Jugador j = em.find(Jugador.class, request.getParameter("id"));
		%>
		
		<article>
			<form action="EditarJugador.html?id=<%= request.getParameter("id") %>" method="post">
				<p>DNI: <%= request.getParameter("id") %></p><br/>
				<label for="f_nombre"> Nombre:</label><br/>
				  <input type="text" name="nombre"  value=<%= j.getNombre() %>><br/>
				<label for="f_apellidos"> Apellidos:</label><br/>
				  <input type="text" name="apellidos"  value=<%= j.getApellidos() %>><br/>
				<label for="f_apodo"> Apodo:</label><br/>
				  <%
				  if (j.getAlias() == null) {
				  %>
				  	<input type="text" name="apodo" /><br/>
			  	  <%
				  } else {
				  %>
				    <input type="text" name="apodo"  value=<%= j.getAlias() %>><br/>
				  <%
				  }
				  %>
				<label for="f_posicion"> Posicion:</label><br/>
				    <select name="posicion">
						<%
						// get posiciones
						
						Query query = em.createNamedQuery("Posicion.getPosiciones");
						
						List<Posicion> posiciones = query.getResultList();
						
						
						for (Posicion p : posiciones) {
							
							// leave player's position as the selected one
							if (p.getNombre() == j.getPosicion()) {
							%>
								<option value="<%= p.getNombre() %>" selected><%= p.getNombre() %></option>
							<%
							}
							else if (p.isMax()) continue;
							else {
							%>
								<option value="<%= p.getNombre() %>"><%= p.getNombre() %></option>
							<%
							}
						}
						em.close();
						%>
					</select>

        		<input type="submit" value="Submit" />
			</form>
		</article>


	<%@ include file="Footer.jsp" %>

	</body>
</html>