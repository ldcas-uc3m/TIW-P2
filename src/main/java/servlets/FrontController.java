package servlets;

import java.io.IOException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.annotation.Resource;

import beans.Jugador;
import beans.Posicion;


@WebServlet(urlPatterns = { "*.html" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	InitialContext payaraContext;

	@Resource(mappedName = "jdbc/tiwp2")
	DataSource ds;
	Connection conn;
	
	private ServletContext context;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = config.getServletContext();
	}


	public FrontController() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			payaraContext = new InitialContext();
		} catch (NamingException e1) {

			e1.printStackTrace();
		}
	
		String forwardToJSP;

		// EXTRAEMOS EL FRAGMENTO DE URL DE LA PETICIÓN REALIZADA POR EL CLIENTE
		String pathAskedFor = request.getServletPath();
		System.out.print("Front Controller --> PATH REQUESTED: " + pathAskedFor + " REQUEST METHOD: "
				+ request.getMethod());


		// Realizamos diferentes acciones dependiendo de la url a la que se realiza la petición.
		// Cada "handler" devuelve un String que es el nombre de la jsp a redirigir.

		switch (pathAskedFor) {

			case "/index.html":
				forwardToJSP = "Home.jsp";
				break;

			case "/InsertarJugador.html":
				forwardToJSP = InsertJugador(request, response);
				break;

			case "/EditarJugador.html":
				forwardToJSP = EditJugador(request, response);
				break;

			case "/EliminarJugador.html":
				forwardToJSP = DeleteJugador(request, response);
				break;
			
			default:
				forwardToJSP = "Error.jsp";
				System.out.print(pathAskedFor + "not found");
				request.setAttribute("message", pathAskedFor + "not found");
		}

		// Forward a una jsp para mostrar el resultado de la operación
		context.getRequestDispatcher("/" + forwardToJSP).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	private String DeleteJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		try {
//			
//			ut.begin();
//
//			// search jugador
//			Jugador toDeleteJugador = em.find(Jugador.class, request.getParameter("id"));			
//
//			// delete jugador
//			em.remove(toDeleteJugador);
//
//			// update posicion
//			Posicion posicion = em.find(Posicion.class, toDeleteJugador.getPosicion());
//			em.persist(posicion);
//			posicion.removeJugador();
//			
//			ut.commit();
//
			return "Home.jsp";
//
//		} catch (IllegalArgumentException e) {
//
//			System.out.print("Error al eliminar jugador" + e.getMessage());
//			
//			request.setAttribute("error", e.getMessage());
//			
//			return "Home.jsp";
//
//		} catch (Exception e) {
//			System.out.print("Shit, something went wrong" + e.getMessage());
//			
//			throw (ServletException) e;
//		}
		
	}


	private String EditJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String requestMethod = request.getMethod();
//
//		if (requestMethod == "GET") {
//			return "EditarJugadorForm.jsp";
//		}
//		
//		System.out.print(request.getParameter("id"));
//		
//		// comprobar que existe el jugador
//		if (em.find(Jugador.class, request.getParameter("id")) == null) {
//			System.out.print("El jugador con DNI " + request.getParameter("id") + " no existe");
//			request.setAttribute("error", "El jugador con DNI " + request.getParameter("id") + " no existe");
//			
//			return "EditarJugadorForm.jsp";
//		}
//
//		try {
//			ut.begin();
//
//			Jugador upJugador = em.find(Jugador.class, request.getParameter("id"));
//
//			em.persist(upJugador);
//
//			upJugador.setNombre(request.getParameter("nombre"));
//			upJugador.setApellidos(request.getParameter("apellidos"));
//			
//			if (request.getParameter("apodo") != null) 
//				upJugador.setAlias(request.getParameter("apodo"));
//
//			// update posicion
//			if (upJugador.getPosicion() != request.getParameter("posicion")) {  // pos was changed
//				Posicion old_posicion = em.find(Posicion.class, upJugador.getPosicion());
//				em.persist(old_posicion);
//
//				Posicion new_posicion = em.find(Posicion.class, request.getParameter("posicion"));
//				em.persist(new_posicion);
//
//				old_posicion.removeJugador();
//				upJugador.setPosicion(new_posicion);
//			}
//
//			ut.commit();
//
			return "Home.jsp";
//
//		} catch (IllegalArgumentException e) {
//
//			System.out.print("Error al editar jugador" + e.getMessage());
//			
//			request.setAttribute("error", e.getMessage());
//			
//			return "EditarJugadorForm.jsp";
//
//		} catch (Exception e) {
//			System.out.print("Shit, something went wrong" + e.getMessage());
//			
//			throw (ServletException) e;
//		}
//
	}

	private String InsertJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "InsertarJugadorForm.jsp";
		}
		
		
		// TODO: comprobar que no existe ya el jugador
//		if (em.find(Jugador.class, request.getParameter("dni")) != null) {
//			System.out.print("El jugador con DNI " + request.getParameter("dni") + " ya existe");
//			request.setAttribute("error", "El jugador con DNI " + request.getParameter("dni") + " ya existe");
//			
//			return "InsertarJugadorForm.jsp";
//		}
		
		try {
			conn = ds.getConnection();

			Jugador nuJugador = new Jugador();
			nuJugador.setDni(request.getParameter("dni"));
			nuJugador.setNombre(request.getParameter("nombre"));
			nuJugador.setApellidos(request.getParameter("apellidos"));
			nuJugador.setAlias(request.getParameter("alias"));
			nuJugador.setPosicion(request.getParameter("posicion"));
			
			// get posiciones data
			Statement sqlStatement_pos = conn.createStatement();
			String query = "SELECT * FROM posiciones WHERE nombre = '" + nuJugador.getPosicion() + "'";
			System.out.print(query);
			ResultSet rs = sqlStatement_pos.executeQuery(query);
			
			rs.next();

			String nombre = rs.getString("nombre");
			int max_jugadores = rs.getInt("max_jugadores");
			int num_jugadores = rs.getInt("num_jugadores");

			
			if (num_jugadores >= max_jugadores) {
				throw new IllegalArgumentException("Número máximo de " + nombre + "s superado");
			}
			
			
			// insertar jugador
			Statement sqlStatement_ins = conn.createStatement();
			query = ""
					+ "INSERT INTO jugadores (dni, nombre, apellidos, alias, posicion_nombre) "
					+ "VALUES ('" + nuJugador.getDni() + "','" + nuJugador.getNombre()
					+ "','" + nuJugador.getApellidos() + "','" + nuJugador.getAlias() + "','" + nuJugador.getPosicion() + "')";
			System.out.print(query);
			sqlStatement_ins.execute(query);
			
			
			// aumentar posiciones
			num_jugadores++;
			Statement sqlStatement_aug = conn.createStatement();
			query = "UPDATE posiciones SET num_jugadores = '" + num_jugadores + "' WHERE (nombre = '" + nombre + "')";
			System.out.print(query);
			sqlStatement_aug.execute(query);
	
			return "Home.jsp";

		} catch (IllegalArgumentException e) {

			System.out.print("Error al insertar jugador" + e.getMessage());
			
			request.setAttribute("error", e.getMessage());
			
			return "InsertarJugadorForm.jsp";

		} catch (Exception e) {
			System.out.print("Shit, something went wrong" + e.getMessage());
			
			throw (ServletException) e;
		}
		

	}

}
