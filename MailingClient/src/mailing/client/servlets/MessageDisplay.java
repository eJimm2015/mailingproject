package mailing.client.servlets;

import java.io.IOException;
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

import ejb.mailing.entity.Message;
import ejb.mailing.entity.User;

/**
 * Servlet implementation class Message
 */
@WebServlet("/MessageDisplay")
public class MessageDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			String end = "";
			if(request.getParameter("type").equals("Boite de réception")) {
				end = "/mails/read/";
			}
			else {
				end = "/mails/";
			}
			Client client = ClientBuilder.newClient();
			WebTarget resource = client.target("http://localhost:8080/MailingWS/rest");
			resource = resource.path("/mailing"+end+request.getParameter("id"));
			Invocation.Builder query = resource.request();
			Response reponse = query.accept(MediaType.APPLICATION_XML).get(Response.class);
			Message message = reponse.readEntity(Message.class);
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("./Connexion");
		}
	}


}
