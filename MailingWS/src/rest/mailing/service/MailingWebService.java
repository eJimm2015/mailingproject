package rest.mailing.service;


import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ejb.mailing.entity.Message;
import ejb.mailing.entity.User;
import ejb.mailing.exceptions.MailInexistantException;
import ejb.mailing.exceptions.UtilisateurExistantException;
import ejb.mailing.exceptions.UtilisateurInexistantException;
import ejb.mailing.service.MailingPersistenceInterface;

@Path("/mailing")
public class MailingWebService {





@GET
@Path("/users/{id}")
@Produces(MediaType.APPLICATION_XML)
public Response trouverUser(@PathParam("id") String mail) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface  mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	User user = null;
	
	try {
	user = mailingPersistence.trouverUser(mail);
	return Response.status(200).entity(user).build();
	} catch (UtilisateurInexistantException e) {
	return Response.status(404).build();	
	}
	
}

@POST
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public Response ajouterUser(User user) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface  mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	
	try {
		mailingPersistence.ajouterUser(user);
	} catch (UtilisateurExistantException e) {
		return Response.status(409).build();
	}
	return Response.status(201).build();
}

@POST
@Path("/mails")
@Consumes(MediaType.APPLICATION_JSON)
public Response envoyerMail(Message message) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	try {
		mailingPersistence.envoyerMail(message);
	} catch (UtilisateurInexistantException e) {
		return Response.status(404).build();
	}
	return Response.status(201).build();
}

@GET
@Path("/users/{id}/receivedMails")
@Produces(MediaType.APPLICATION_XML)
public Response boiteReception(@PathParam("id") String email) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	List<Message> messages = null;
	try {
		messages =  mailingPersistence.boiteReception(email);

	} catch (UtilisateurInexistantException e) {
		return Response.status(404).entity(null).build();
	}
	CacheControl cacheControl = new CacheControl();
	cacheControl.setNoCache(true);
	GenericEntity<List<Message>> list = new GenericEntity<List<Message>>(messages){};
	return Response.status(200).entity(list).cacheControl(cacheControl).build();
}

@GET
@Path("/users/{id}/sentMails")
@Produces(MediaType.APPLICATION_XML)
public Response boiteEnvoi(@PathParam("id") String email) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	List<Message> messages = null;
	try {
		messages = mailingPersistence.boiteEnvoi(email);
	} catch (UtilisateurInexistantException e) {
		return Response.status(404).build();
	}
	CacheControl cacheControl = new CacheControl();
	cacheControl.setNoCache(true);
	GenericEntity<List<Message>> list = new GenericEntity<List<Message>>(messages){};
	return Response.status(200).entity(list).cacheControl(cacheControl).build();
}
@DELETE
@Path("/mails/{id}")
public Response supprimerMail(@PathParam("id") int messageId) throws NamingException {
Context context = new InitialContext();
MailingPersistenceInterface mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	try {
		mailingPersistence.supprimerMail(messageId);
	} catch (MailInexistantException e) {
		return Response.status(404).build();
	}
	return Response.status(200).build();
}

@GET
@Path("/mails/read/{id}")
@Produces(MediaType.APPLICATION_XML)
public Response trouverEtLireMail(@PathParam("id") int messageId) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	Message message = null;
	try {
		message = mailingPersistence.trouverEtLireMail(messageId);
	} catch (MailInexistantException e) {
		return Response.status(404).build();
	}
	return Response.status(200).entity(message).build();
	
}

@GET
@Path("/mails/{id}")
@Produces(MediaType.APPLICATION_XML)
public Response trouverMail(@PathParam("id") int messageId) throws NamingException {
	Context context = new InitialContext();
	MailingPersistenceInterface mailingPersistence = (MailingPersistenceInterface)context.lookup("MailingServiceEJB");
	Message message = null;
	try {
		message = mailingPersistence.trouverMail(messageId);
	} catch (MailInexistantException e) {
		return Response.status(404).build();
	}
	return Response.status(200).entity(message).build();
	
}

}
