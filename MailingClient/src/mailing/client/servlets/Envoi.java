package mailing.client.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.mailing.entity.Message;
import ejb.mailing.entity.User;

/**
 * Servlet implementation class Envoi
 */
@WebServlet("/Envoi")
public class Envoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Envoi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") != null) {
			Client client = ClientBuilder.newClient();
			WebTarget resource = client.target("http://localhost:8080/MailingWS/rest");
			User user = (User)request.getSession().getAttribute("user");
			resource = resource.path("/mailing/users/"+user.getMail()+"/sentMails");
			Invocation.Builder query = resource.request();
			Response reponse = query.accept(MediaType.APPLICATION_XML).get(Response.class);
			List<Message> messages = reponse.readEntity(new GenericType<List<Message>>(){});
			messages.sort(new Comparator<Message>() {
				@Override
				public int compare(Message m1, Message m2) {
					return m2.getId() - m1.getId();
				}
			});
			request.setAttribute("type", "Boite d'envoi");
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("box.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("./Connexion");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
