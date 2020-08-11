package ma.ensa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@PrimaryKeyJoinColumn(name = "idEvaluationReferee")
public class EvaluationReferee extends Evaluation {

	private String qualificationReferee;
	public String commentaire;
	public String rapport;

	
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "evaluationReferee_referee",
	joinColumns = { @JoinColumn(name="idEvaluationReferee")},
	inverseJoinColumns ={ @JoinColumn(name="idReferee")} )
	 private List<Referee> referees = new ArrayList<Referee>();
	
	
	public String getQualificationReferee() {
		return qualificationReferee;
	}

	public void setQualificationReferee(String qualificationReferee) {
		this.qualificationReferee = qualificationReferee;
	}

	public EvaluationReferee(String qualificationReferee) {
		super();
		this.qualificationReferee = qualificationReferee;
	}

	public EvaluationReferee() {
		super();
	}

	public List<Referee> getReferees() {
		return referees;
	}

	public void setReferees(List<Referee> referees) {
		this.referees = referees;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getRapport() {
		return rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

	@Override
	public String toString() {
		return "EvaluationReferee [qualificationReferee=" + qualificationReferee + ", commentaire=" + commentaire
				+ ", rapport=" + rapport + ", referees=" + referees + "]";
	}
	
	
	
	
	
}