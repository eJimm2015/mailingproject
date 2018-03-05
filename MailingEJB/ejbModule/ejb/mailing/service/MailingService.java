package ejb.mailing.service;



import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ejb.mailing.entity.Message;
import ejb.mailing.entity.User;
import ejb.mailing.exceptions.MailInexistantException;
import ejb.mailing.exceptions.UtilisateurExistantException;
import ejb.mailing.exceptions.UtilisateurInexistantException;


	
@Stateless(mappedName="MailingServiceEJB")
public class MailingService implements MailingPersistenceInterface {

	@PersistenceContext(unitName="uniteMail")
	EntityManager manager;
	
	
		
	// TESTEE
	@Override
	public User trouverUser(String email) throws UtilisateurInexistantException {
		User user = manager.find(User.class, email);
		if(user == null) throw new UtilisateurInexistantException("L'utilisateur dont l'email est : "+email+" n'existe pas !");
		return user;
	}
	
	// TESTEE
	// OBLIGATOIRE
	@Override
	public void ajouterUser(User user) throws UtilisateurExistantException {
		try {
			manager.persist(user);
		} catch (EntityExistsException e) {
			throw new UtilisateurExistantException("L'utilisateur existe deja !");
		}
		
	}
	// TESTEE
	// OBLIGATOIRE
	@Override
	public void envoyerMail(Message message) throws UtilisateurInexistantException {
		User destinataire = trouverUser(message.getDestinataire().getMail());
		message.setDestinataire(destinataire);
		manager.persist(message);
	}

	
	// OBLIGATOIRE
	@Override
	public List<Message> boiteReception(String email)  throws UtilisateurInexistantException{
		User user = trouverUser(email);
		Query query = manager.createQuery("SELECT m FROM Message m WHERE m.destinataire = :email");
		query.setParameter("email", user);
		return query.getResultList();
		
	}

	//OBLIGATOIRE
	@Override
	public List<Message> boiteEnvoi(String email)  throws UtilisateurInexistantException{
		User user = trouverUser(email);
		Query query = manager.createQuery("SELECT m FROM Message m WHERE m.expediteur = :email");
		query.setParameter("email", user);
		return query.getResultList();
		
	}

	//OBLIGATOIRE
	@Override
	public void supprimerMail(int messageId) throws MailInexistantException {
		Message message = trouverMail(messageId);
		manager.remove(message);
	}


	@Override
	public Message trouverMail(int messageId) throws MailInexistantException {
		Message message = manager.find(Message.class, messageId);
		if(message == null) throw new MailInexistantException("Le message n'existe pas");
		
		return message;
	}
	@Override
	public Message trouverEtLireMail(int messageId) throws MailInexistantException {
		Message message = manager.find(Message.class, messageId);
		if(message == null) throw new MailInexistantException("Le message n'existe pas");
		message.setLu(true);
		manager.flush();
		return message;
	}
	
}
