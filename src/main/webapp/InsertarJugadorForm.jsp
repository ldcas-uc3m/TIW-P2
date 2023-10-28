<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	errorPage="Error.jsp"
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
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
					EntityManager em = factory.createEntityManager();
					
					Query query = em.createNamedQuery("Posicion.getPosiciones");
					
					List<Posicion> posiciones = query.getResultList();
	
						// TODO: ver numero de posiciones y mostrar conforme a eso
					
					for (Posicion p : posiciones) {
						System.out.print(p.getNumJugadores());
						if (p.isMax()) continue;
						
					%>
						<option value="<%= p.getNombre() %>"><%= p.getNombre() %></option>
					<%
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