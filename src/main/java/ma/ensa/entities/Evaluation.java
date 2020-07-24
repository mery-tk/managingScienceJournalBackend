package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Evaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvaluation;
	private String commentaire;
	private String rapport;
	
	//////////////////////////////
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "idArticle")
	private Article article;
	
	/////////////////////////
	public Long getIdEvaluation() {
		return idEvaluation;
	}
	public void setIdEvaluation(Long idEvaluation) {
		this.idEvaluation = idEvaluation;
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
	public Evaluation(Long idEvaluation, String commentaire, String rapport) {
		super();
		this.idEvaluation = idEvaluation;
		this.commentaire = commentaire;
		this.rapport = rapport;
	}
	public Evaluation() {
		super();
	}
	
	
	

}