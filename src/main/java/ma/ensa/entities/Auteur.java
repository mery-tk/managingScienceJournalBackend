package ma.ensa.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@PrimaryKeyJoinColumn(name = "idAuteur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Auteur extends Utilisateur{
	
	private int nbrArticlesEcrites=0;
	
	
//	@ManyToMany(mappedBy = "auteurs", fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Article> articles = new ArrayList<Article>();
	
	
	@OneToMany(mappedBy ="correspondance_PK.auteur")
	@JsonIgnore
	private List<Correspondance> corres=new ArrayList<Correspondance>();
	
	public Auteur() {
		super();
	}

	public Auteur(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password) {
		super(nom, prenom, email, telephone, adresse, username, password);
	}

	public Auteur(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password, int nbrArticlesEcrites, boolean correspondance) {
		super(nom, prenom, email, telephone, adresse, username, password);
		this.nbrArticlesEcrites = nbrArticlesEcrites;
	}

	public int getNbrArticlesEcrites() {
		return nbrArticlesEcrites;
	}

	public void setNbrArticlesEcrites(int nbrArticlesEcrites) {
		this.nbrArticlesEcrites = nbrArticlesEcrites;
	}

	public List<Correspondance> getCorres() {
		return corres;
	}

	public void setCorres(List<Correspondance> corres) {
		this.corres = corres;
	}




	
	
	

}
