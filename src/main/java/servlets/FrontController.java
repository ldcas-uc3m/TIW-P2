package servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import org.eclipse.persistence.exceptions.DatabaseException;

//import entities.Artist;
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

		// Mostramos la página de Home por defecto al arrancar la aplicación (petición a
		// root del proyecto)

		// Realizamos diferentes acciones dependiendo de la url a la que se realiza la
		// petición.
		// Cada "handler" devuelve un String que es el nombre de la jsp a redirigir.

		switch (pathAskedFor) {

		// GET REQUESTS

		case "/index.html":
			forwardToJSP = "Home.jsp";
			break;

		case "/InsertarJugador.html":
			forwardToJSP = InsertJugador(request, response);
			break;

		case "/SearchArtistPage.html":
			forwardToJSP = findArtistById(request, response);
			break;

		case "/ArtistEditPage.html":
			forwardToJSP = showArtistEditPage(request, response);
			break;

		// UPDATE Y DELETE

		case "/EditarJugador.html":
			forwardToJSP = EditJugador(request, response);
			break;

		case "/DeleteJugador.html":
			forwardToJSP = DeleteJugador(request, response);
			break;
		}

		// Forward a una jsp para mostrar el resultado de la operación.
		context.getRequestDispatcher("/" + forwardToJSP).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String showArtistEditPage(HttpServletRequest request, HttpServletResponse response) {

		
		
		System.out.print("SHOW ARTIST EDIT PAGE EXECUTING");
		
		
		String id = request.getParameter("artistId");

		if (id == null ) {
			return "Error.jsp";
		}

		// Artist a = em.find(Artist.class, Integer.parseInt(id));

//		if (a == null)
//			return null;
//		
//		
//		request.setAttribute("artist", a);

		return "/ArtistEditPage.jsp";

	}

	private String DeleteJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "Home.jsp";
		}
		
		try {
			Jugador nuJugador = new Jugador();
			
			ut.begin();
			em.persist(nuJugador);
			
			Jugador toDeleteJugador = em.find(Jugador.class, request.getParameter("dni"));
			// get posicion
			Posicion posicion = em.find(Posicion.class, request.getParameter("posicion"));
			em.persist(posicion);
			nuJugador.setPosicion(posicion);

			request.setAttribute("jugador", nuJugador);
			
			em.remove(toDeleteJugador);
			ut.commit();
			
			
			return "Home.jsp";

		} catch (IllegalArgumentException e) {

			System.out.print("Error al insertar jugador" + e.getMessage());
			
			request.setAttribute("error", e.getMessage());
			
			return "Home.jsp";

		} catch (Exception e) {
			System.out.print("Shit, something went wrong" + e.getMessage());
			
			throw (ServletException) e;
		}
		
		
		
	}

	private String findArtistById(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {

			try {

				Query q = em.createQuery("SELECT a FROM Artist a");
//				@SuppressWarnings("unchecked")
//				// List<Artist> artistList = (List<Artist>) q.getResultList();
//				request.setAttribute("artistList", artistList);
			} catch (Exception e) {
				System.out.print(e);

			}

			return "SearchArtistPage.jsp";
		}

		try {

			ut.begin();
			// List<Artist> artistList = new ArrayList<Artist>();

			// Artist foundArtist = em.find(Artist.class, Integer.parseInt(request.getParameter("search")));

//			if (foundArtist != null) {
//				request.setAttribute("found", true);
//			}
//
//			artistList.add(foundArtist);

			ut.commit();

//			request.setAttribute("artistList", artistList);
			return "SearchArtistPage.jsp";

		} catch (Exception e) {

			response.getWriter().append("Shit, something went wrong" + e.getMessage());
			System.out.print("Shit, something went wrong" + e.getMessage());
			return null;
		}

	}

	private String EditJugador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "EditarJugadorForm.jsp";
		}
		
		// comprobar que no existe ya el jugador
		if (em.find(Jugador.class, request.getParameter("dni")) != null) {
			System.out.print("El jugador con DNI " + request.getParameter("dni") + " ya existe");
			request.setAttribute("error", "El jugador con DNI " + request.getParameter("dni") + " ya existe");
			
			return "EditarJugadorForm.jsp";
		}

		try {
			Jugador upJugador = new Jugador();
			upJugador.setDni(request.getParameter("dni"));
			upJugador.setNombre(request.getParameter("nombre"));
			upJugador.setApellidos(request.getParameter("apellidos"));
			upJugador.setAlias(request.getParameter("alias"));
			
			ut.begin();
			
			em.merge(upJugador);
			
			//get posicion
			Posicion posicion = em.find(Posicion.class, request.getParameter("posicion"));
			em.merge(posicion);
			upJugador.setPosicion(posicion);

			request.setAttribute("jugador", upJugador);
			ut.commit();
			return "Home.jsp";

		} catch (IllegalArgumentException e) {

			System.out.print("Error al insertar jugador" + e.getMessage());
			
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
