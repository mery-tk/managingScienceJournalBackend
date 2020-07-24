package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@PrimaryKeyJoinColumn(name = "idReferee")
public class Referee extends Utilisateur{

	private int nbrArticlesEvaluees;
	
	@OneToOne(mappedBy = "referee", fetch = FetchType.LAZY)
	@JsonIgnore
	private EvaluationReferee evaluation;

	public Referee() {
		super();
	}

	public Referee(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password) {
		super(nom, prenom, email, telephone, adresse, username, password);
	}

	public Referee(String nom, String prenom, String email, Long telephone, String adresse, String username,
			String password, int nbrArticlesEvaluees) {
		super(nom, prenom, email, telephone, adresse, username, password);
		this.nbrArticlesEvaluees = nbrArticlesEvaluees;
	}

	public int getNbrArticlesEvaluees() {
		return nbrArticlesEvaluees;
	}

	public void setNbrArticlesEvaluees(int nbrArticlesEvaluees) {
		this.nbrArticlesEvaluees = nbrArticlesEvaluees;
	}
	
	
	
	
}
