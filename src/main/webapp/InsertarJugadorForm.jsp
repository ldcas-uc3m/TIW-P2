<%@
	page language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	errorPage="Error.jsp"
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
    <title>Añadir jugador</title>

    <link rel="stylesheet" type="text/css" href="styles/css/themeColours.css"/>

	</head>
	<body>

	<%@ include file="Header.jsp" %>


		<h1>Añadir jugador</h1>


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
				<label for="f_posicion"> Posición:</label><br/>
          <%
          // get posiciones
          @PersistenceContext(unitName = "PU")
          EntityManager em;
          
          Query query = em.createdNamedQuery("Posicion.getPosiciones");
          
          List<Posicion> posiciones = query.getResultList();
          
          for (Posicion p : posiciones) {
          %>
          <option value="<%= p.getNombre() %>"><%= p.getNombre() %></option>
          <%
          }
          %>

        <input type="submit" value="Submit" />
			</form>
		</article>


  
	<%@ include file="Footer.jsp" %>

	</body>
</html>