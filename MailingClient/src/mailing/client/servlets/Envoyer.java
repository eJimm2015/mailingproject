package mailing.client.servlets;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.mailing.entity.Message;
import ejb.mailing.entity.User;

/**
 * Servlet implementation class Envoyer
 */
@WebServlet("/Envoyer")
public class Envoyer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Envoyer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
		request.getRequestDispatcher("send.jsp").forward(request, response);
		} else {
			response.sendRedirect("./Connexion");
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user") != null) {
			Client client = ClientBuilder.newClient();
			WebTarget resource = client.target("http://localhost:8080/MailingWS/rest");
			resource = resource.path("/mailing/mails");
			Invocation.Builder query = resource.request();
				Message message = new Message();
				User rec = new User();
				rec.setMail(request.getParameter("destinataire"));
				message.setExpediteur((User)request.getSession().getAttribute("user"));
				message.setDestinataire(rec);
				message.setObjet(request.getParameter("objet"));
				message.setCorps(request.getParameter("corps"));
				Response reponse = query.post(Entity.entity(message, MediaType.APPLICATION_JSON));
				if(reponse.getStatus() == 201) response.sendRedirect("./Boite");
				else {
					request.setAttribute("error", "Le destinataire n'existe pas !");
					doGet(request, response);
				}
		}
		else {
			response.sendRedirect("./Connexion");
		}
	}

}
