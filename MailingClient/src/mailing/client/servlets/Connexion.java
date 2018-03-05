package mailing.client.servlets;



import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.mailing.entity.User;




/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//if(request.getSession().getAttribute("user") == null) {
		request.getRequestDispatcher("connexion.jsp").forward(request, response);
		
//	}
//	else request.getRequestDispatcher("Boite?type=IN").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target("http://localhost:8080/MailingWS/rest");
		resource = resource.path("/mailing/users/"+request.getParameter("email"));
		Invocation.Builder query = resource.request();
		Response reponse = query.accept(MediaType.APPLICATION_XML).get(Response.class);

		if(reponse.getStatus() == 404) {
			request.setAttribute("error", "L'email n'existe pas !");
			doGet(request, response);
		}
		else {
			try {
				User user = reponse.readEntity(User.class);
				if(!Inscription.sha1(request.getParameter("password")).equals(user.getPass())) {
					request.setAttribute("error", "Mot de passe incorrect !");
					doGet(request, response);
				}
			else {
				  request.getSession().setMaxInactiveInterval(1000*300);
				  request.getSession().setAttribute("user", user);
				  response.sendRedirect("./Boite");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
