package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "idEvaluationReferee")
public class EvaluationReferee extends Evaluation {

	private String qualificationReferee;

	
	
	 @OneToOne(fetch = FetchType.EAGER)
	 @JsonIgnore
	 @JoinColumn(name = "idReferee")
	 private Referee referee;
	
	
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
	
	
	
	
	
}