package ma.ensa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idAdmin")
public class Administrateur extends Utilisateur {

	private Date debutfonction;
	private Date finFonction;
	
	
	public Administrateur() {
		super();
	}


	public Administrateur(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password, Date debutfonction, Date finFonction) {
		super( nom, prenom, email, telephone, adresse, username, password);
		this.debutfonction = debutfonction;
		this.finFonction = finFonction;
	}


	public Date getDebutfonction() {
		return debutfonction;
	}


	public void setDebutfonction(Date debutfonction) {
		this.debutfonction = debutfonction;
	}


	public Date getFinFonction() {
		return finFonction;
	}


	public void setFinFonction(Date finFonction) {
		this.finFonction = finFonction;
	}
	
	
	
	
}
