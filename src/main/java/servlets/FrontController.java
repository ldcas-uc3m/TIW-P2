package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import entities.Artist;

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
		System.out.print("[@alreylz] Front Controller --> PATH REQUESTED: " + pathAskedFor + " REQUEST METHOD :"
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

		case "/InsertArtistPage.html":
			forwardToJSP = InsertArtistPageHandler(request, response);
			break;

		case "/SearchArtistPage.html":
			forwardToJSP = findArtistById(request, response);
			break;

		case "/ArtistEditPage.html":
			forwardToJSP = showArtistEditPage(request, response);
			break;

		// UPDATE Y DELETE

		case "/ArtistUpdate.html":

			forwardToJSP = updateArtist(request, response);
			break;

		case "/ArtistDelete.html":
			forwardToJSP = deleteArtist(request, response);
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

		Artist a = em.find(Artist.class, Integer.parseInt(id));

		if (a == null)
			return null;
		
		
		request.setAttribute("artist", a);

		return "/ArtistEditPage.jsp";

	}

	private String deleteArtist(HttpServletRequest request, HttpServletResponse response) {

		int idArtist = Integer.parseInt(request.getParameter("artistId"));

		System.out.print("ID ARTIST:" + idArtist);

		
		try {
			ut.begin();
			Artist toDeleteArtist = em.find(Artist.class, idArtist);
			request.setAttribute("message", "Successfully removed record "+toDeleteArtist.getName());
			System.out.print("ELIMINANDO..." + toDeleteArtist.getName());
			
			
			
			em.remove(toDeleteArtist);
			ut.commit();


			
			
			
			return "ArtistEditPage.jsp";

		} catch (Exception e) {

			
			request.setAttribute("message", "Error en deleteArtist()" + e.getMessage() + e.getCause());
			
			return "Error.jsp";
		}
		
		
		
	}

	private String findArtistById(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {

			try {

				Query q = em.createQuery("SELECT a FROM Artist a");
				@SuppressWarnings("unchecked")
				List<Artist> artistList = (List<Artist>) q.getResultList();
				request.setAttribute("artistList", artistList);
			} catch (Exception e) {
				System.out.print(e);

			}

			return "SearchArtistPage.jsp";
		}

		try {

			ut.begin();
			List<Artist> artistList = new ArrayList<Artist>();

			Artist foundArtist = em.find(Artist.class, Integer.parseInt(request.getParameter("search")));

			if (foundArtist != null) {
				request.setAttribute("found", true);
			}

			artistList.add(foundArtist);

			ut.commit();

			request.setAttribute("artistList", artistList);
			return "SearchArtistPage.jsp";

		} catch (Exception e) {

			response.getWriter().append("Shit, something went wrong" + e.getMessage());
			System.out.print("Shit, something went wrong" + e.getMessage());
			return null;
		}

	}

	private String updateArtist(HttpServletRequest request, HttpServletResponse response) {

		// En la request me debería venir la información del artista

		int idArtist = Integer.parseInt(request.getParameter("artistId"));

		System.out.print("ID ARTIST:" + idArtist);

		Artist toUpdateArtist = em.find(Artist.class, idArtist);

		String nuName = request.getParameter("name");
		String nuDescription = request.getParameter("description");

		BigInteger nuFollowers = new BigInteger(request.getParameter("followers"));

		try {
			ut.begin();

			toUpdateArtist.setName(nuName);
			toUpdateArtist.setDescription(nuDescription);
			toUpdateArtist.setFollowers(nuFollowers);
			em.merge(toUpdateArtist);
			ut.commit();

			request.setAttribute("artist", toUpdateArtist);

			request.setAttribute("message", "Successfully updated record.");
			
			
			return "ArtistEditPage.html";

		} catch (Exception e) {

			return "Error.jsp";
		}

	}

	private String InsertArtistPageHandler(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String requestMethod = request.getMethod();

		if (requestMethod == "GET") {
			return "ArtistInsertForm.jsp";
		}

		Artist nuArtist = new Artist();
		nuArtist.setName(request.getParameter("name"));
		nuArtist.setDescription(request.getParameter("description"));
		nuArtist.setFollowers(new BigInteger(request.getParameter("followers")));

		try {
			ut.begin();
			em.persist(nuArtist);
			ut.commit();

			request.setAttribute("artist", nuArtist);
			return "ArtistEditPage.jsp";

		} catch (Exception e) {

			response.getWriter().append("Shit, something went wrong" + e.getMessage());
			System.out.print("Shit, something went wrong" + e.getMessage());
			return null;
		}

	}

}
