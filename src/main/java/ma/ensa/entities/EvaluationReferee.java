package ma.ensa.entities;

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

	
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "evaluationReferee_referee",
	joinColumns = { @JoinColumn(name="idEvaluationReferee")},
	inverseJoinColumns ={ @JoinColumn(name="idReferee")} )
	 private List<Referee> referees;
	
	
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
	
	
	
	
	
}