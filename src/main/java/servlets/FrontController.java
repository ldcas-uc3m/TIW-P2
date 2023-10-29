package servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import org.eclipse.persistence.exceptions.DatabaseException;


import entities.Jugador;
import entities.Posicion;


@WebServlet(urlPatterns = { "*.html" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "PU")
	private EntityManager em;
	@Resource
	private UserTransaction ut;

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

		String forwardToJSP = "Error.jsp";

		// EXTRAEMOS EL FRAGMENTO DE URL DE LA PETICIÓN REALIZADA POR EL CLIENTE
		String pathAskedFor = request.getServletPath();
		System.out.print("Front Controller --> PATH REQUESTED: " + pathAskedFor + " REQUEST METHOD :"
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

			case "/DeleteJugador.html":
				forwardToJSP = DeleteJugador(request, response);
				break;
		}

		// Forward a una jsp para mostrar el resultado de la operación
		context.getRequestDispatcher("/" + forwardToJSP).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	private String DeleteJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "Home.jsp";
		}
		
		try {
			
			// search jugador
			Jugador toDeleteJugador = em.find(Jugador.class, request.getParameter("id"));
			
			ut.begin();

			// delete jugador
			em.merge(toDeleteJugador);
			em.remove(toDeleteJugador);

			// update posicion
			Posicion posicion = em.find(Posicion.class, toDeleteJugador.getPosicion());
			em.persist(posicion);
			posicion.removeJugador();

			ut.commit();

			return "Home.jsp";

		} catch (IllegalArgumentException e) {

			System.out.print("Error al eliminar jugador" + e.getMessage());
			
			request.setAttribute("error", e.getMessage());
			
			return "Home.jsp";

		} catch (Exception e) {
			System.out.print("Shit, something went wrong" + e.getMessage());
			
			throw (ServletException) e;
		}
		
	}


	private String EditJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "EditarJugadorForm.jsp";
		}
		
		// comprobar que existe el jugador
		if (em.find(Jugador.class, request.getParameter("id")) == null) {
			System.out.print("El jugador con DNI " + request.getParameter("id") + " no existe");
			request.setAttribute("error", "El jugador con DNI " + request.getParameter("id") + " no existe");
			
			return "EditarJugadorForm.jsp";
		}

		try {
			Jugador upJugador = em.find(Jugador.class, request.getParameter("id"));

			ut.begin();

			em.persist(upJugador);

			upJugador.setNombre(request.getParameter("nombre"));
			upJugador.setApellidos(request.getParameter("apellidos"));
			upJugador.setAlias(request.getParameter("alias"));

			// update posicion
			if (upJugador.getPosicion() != request.getParameter("posicion")) {  // pos was changed
				Posicion old_posicion = em.find(Posicion.class, upJugador.getPosicion());
				em.persist(old_posicion);

				Posicion new_posicion = em.find(Posicion.class, request.getParameter("posicion"));
				em.persist(new_posicion);

				old_posicion.removeJugador();
				new_posicion.addJugador();
			}

			ut.commit();

			return "Home.jsp";

		} catch (IllegalArgumentException e) {

			System.out.print("Error al editar jugador" + e.getMessage());
			
			request.setAttribute("error", e.getMessage());
			
			return "EditarJugadorForm.jsp";

		} catch (Exception e) {
			System.out.print("Shit, something went wrong" + e.getMessage());
			
			throw (ServletException) e;
		}

	}

	private String InsertJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "InsertarJugadorForm.jsp";
		}
		
		// comprobar que no existe ya el jugador
		if (em.find(Jugador.class, request.getParameter("dni")) != null) {
			System.out.print("El jugador con DNI " + request.getParameter("dni") + " ya existe");
			request.setAttribute("error", "El jugador con DNI " + request.getParameter("dni") + " ya existe");
			
			return "InsertarJugadorForm.jsp";
		}
		
		try {
			Jugador nuJugador = new Jugador();
			nuJugador.setDni(request.getParameter("dni"));
			nuJugador.setNombre(request.getParameter("nombre"));
			nuJugador.setApellidos(request.getParameter("apellidos"));
			nuJugador.setAlias(request.getParameter("alias"));


			ut.begin();
			em.persist(nuJugador);
			// get posicion
			Posicion posicion = em.find(Posicion.class, request.getParameter("posicion"));
			em.persist(posicion);
			nuJugador.setPosicion(posicion);

			request.setAttribute("jugador", nuJugador);
			ut.commit();

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
