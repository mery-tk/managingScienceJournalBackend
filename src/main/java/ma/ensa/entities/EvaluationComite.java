package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name = "idEvaluationComite")
public class EvaluationComite extends Evaluation {

   private String qualificationComite;
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JsonIgnore
   @JoinColumn(name ="idComite" )
   private Comite comite;
   
   
   

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

public Comite getComite() {
	return comite;
}

public void setComite(Comite comite) {
	this.comite = comite;
}
   

}