package ejb.mailing.service;


import java.util.List;

import javax.ejb.Remote;

import ejb.mailing.entity.Message;
import ejb.mailing.entity.User;
import ejb.mailing.exceptions.MailInexistantException;
import ejb.mailing.exceptions.UtilisateurExistantException;
import ejb.mailing.exceptions.UtilisateurInexistantException;


@Remote
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
