package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "idEvaluationComite")
public class EvaluationComite extends Evaluation {
	
   private String qualificationComite;
   
   @OneToOne(fetch = FetchType.EAGER)
   @JsonIgnore
   @JoinColumn(name ="idComite" )
   private ComiteEditoriale comite;
   
   
   

public String getQualificationComite() {
	return qualificationComite;
}

public void setQualificationComite(String qualificationComite) {
	this.qualificationComite = qualificationComite;
}

public EvaluationComite(String qualificationComite) {
	super();
	this.qualificationComite = qualificationComite;
}

public EvaluationComite() {
	super();
}
   

}