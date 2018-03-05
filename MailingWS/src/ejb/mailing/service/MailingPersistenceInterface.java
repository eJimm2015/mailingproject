package ejb.mailing.service;
import ejb.mailing.entity.*;
import ejb.mailing.exceptions.*;


import java.util.List;





public interface MailingPersistenceInterface {
	
	
	

	User trouverUser(String mail) throws UtilisateurInexistantException;
	void ajouterUser(User user) throws UtilisateurExistantException;
	void envoyerMail(Message message) throws UtilisateurInexistantException;
	List<Message> boiteReception(String email) throws UtilisateurInexistantException;
	List<Message> boiteEnvoi(String email) throws UtilisateurInexistantException;
	void supprimerMail(int messageId) throws MailInexistantException;
	Message trouverMail(int messageId) throws MailInexistantException;
	public Message trouverEtLireMail(int messageId) throws MailInexistantException;
	
}
