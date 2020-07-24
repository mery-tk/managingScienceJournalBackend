package ma.ensa.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@PrimaryKeyJoinColumn(name = "idAuteur")
public class Auteur extends Utilisateur{
	
	private int nbrArticlesEcrites;
	private boolean corresponsance;
	
	
	@ManyToMany(mappedBy = "auteurs", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Article> articles = new ArrayList<Article>();
	
	public Auteur() {
		super();
	}

	public Auteur(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password) {
		super(nom, prenom, email, telephone, adresse, username, password);
	}

	public Auteur(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password, int nbrArticlesEcrites, boolean corresponsance) {
		super(nom, prenom, email, telephone, adresse, username, password);
		this.nbrArticlesEcrites = nbrArticlesEcrites;
		this.corresponsance = corresponsance;
	}

	public int getNbrArticlesEcrites() {
		return nbrArticlesEcrites;
	}

	public void setNbrArticlesEcrites(int nbrArticlesEcrites) {
		this.nbrArticlesEcrites = nbrArticlesEcrites;
	}

	public boolean isCorresponsance() {
		return corresponsance;
	}

	public void setCorresponsance(boolean corresponsance) {
		this.corresponsance = corresponsance;
	}
	
	
	

}
