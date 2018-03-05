package mailing.client.servlets;

import java.io.IOException;
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

/**
 * Servlet implementation class Supprimer
 */
@WebServlet("/Supprimer")
public class Supprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supprimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			String[] msgs = request.getParameterValues("delete");
			if(msgs != null) {
			
				for(int i=0; i<msgs.length; i++) {
					if(msgs[i] != null) {
						Client client = ClientBuilder.newClient();
						WebTarget resource = client.target("http://localhost:8080/MailingWS/rest");
						resource = resource.path("/mailing/mails/"+msgs[i]);
						Invocation.Builder query = resource.request();
						query.delete();
					}
				}
			}
			System.out.println();
			if(request.getParameter("type").equals("Boite de réception")) {
				response.sendRedirect("./Boite");
			}
			else response.sendRedirect("./Envoi");
		}
		else {
			response.sendRedirect("./Connexion");
		}
		
	}

}
