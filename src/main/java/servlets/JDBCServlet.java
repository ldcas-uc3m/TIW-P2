package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.exceptions.DatabaseException;



@WebServlet("/JDBCServlet")
public class JDBCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
    public JDBCServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		// Después de guardar en la base de datos con JDBC
		response.sendRedirect("Home.jsp");

	}

	
}
