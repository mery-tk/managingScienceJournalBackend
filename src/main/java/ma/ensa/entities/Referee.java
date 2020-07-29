package ma.ensa.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@PrimaryKeyJoinColumn(name = "idReferee")
public class Referee extends Utilisateur{

	private int nbrArticlesEvaluees;
	
	@ManyToMany(mappedBy = "referees", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EvaluationReferee> evaluationReferees;

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

	public List<EvaluationReferee> getEvaluationReferees() {
		return evaluationReferees;
	}

	public void setEvaluationReferees(List<EvaluationReferee> evaluationReferees) {
		this.evaluationReferees = evaluationReferees;
	}

	
	
	
	
	
}
