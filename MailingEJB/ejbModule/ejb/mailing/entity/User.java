package ejb.mailing.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.trueFalseType;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Serializable {
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String mail = "";
	
	
	private String pass = "";
	private String nom = "";
	private String prenom = "";
	

	private String inscription = new Date().toString();
	
	@XmlTransient
	@OneToMany(mappedBy="expediteur", cascade= CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Message> boiteEnvoi = new ArrayList<>();

	@XmlTransient
	@OneToMany(mappedBy="destinataire", cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Message> boiteReception = new ArrayList<>();

	public User() {
			
		}
	
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getInscription() {
		return inscription;
	}

	public void setInscription(String inscription) {
		this.inscription = inscription;
	}

	public List<Message> getBoiteEnvoi() {
		return boiteEnvoi;
	}

	public void setBoiteEnvoi(List<Message> boiteEnvoi) {
		this.boiteEnvoi = boiteEnvoi;
	}
	
	public List<Message> getBoiteReception() {
		return boiteReception;
	}

	public void setBoiteReception(List<Message> boiteReception) {
		this.boiteReception = boiteReception;
	}
	
	
	
}
