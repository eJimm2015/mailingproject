package ejb.mailing.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String objet;
	private String corps;
	
	
	private String tempsEnvoi = new Date().toString();
	
	private boolean lu;
	
	@ManyToOne
	private User expediteur;
	
	@ManyToOne
	private User destinataire;

	public Message() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getCorps() {
		return corps;
	}

	public void setCorps(String corps) {
		this.corps = corps;
	}

	public String getTempsEnvoi() {
		return tempsEnvoi;
	}

	public void setTempsEnvoi(String tempsEnvoi) {
		this.tempsEnvoi = tempsEnvoi;
	}

	public boolean isLu() {
		return lu;
	}

	public void setLu(boolean lu) {
		this.lu = lu;
	}

	
	public User getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(User expediteur) {
		this.expediteur = expediteur;
	}
	
	
	public User getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(User destinataire) {
		this.destinataire = destinataire;
	}
	
	
}
