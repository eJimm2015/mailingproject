package mailing.client.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ejb.mailing.entity.*;
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



/**
 * Servlet implementation class Boite
 */
@WebServlet("/Boite")
public class Boite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Boite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getSession().getAttribute("user")==null) {
			response.sendRedirect("./Connexion");
		}
		else {
		
			Client client = ClientBuilder.newClient();
			WebTarget resource = client.target("http://localhost:8080/MailingWS/rest");
			User user = (User)request.getSession().getAttribute("user");
			resource = resource.path("/mailing/users/"+user.getMail()+"/receivedMails");
			Invocation.Builder query = resource.request();
			Response reponse = query.accept(MediaType.APPLICATION_XML).get(Response.class);
			List<Message> messages = reponse.readEntity(new GenericType<List<Message>>(){});
			messages.sort(new Comparator<Message>() {
				@Override
				public int compare(Message m1, Message m2) {
					return m2.getId() - m1.getId();
				}
			});
			request.setAttribute("type", "Boite de réception");
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("box.jsp").forward(request, response);
			
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
