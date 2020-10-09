package ma.ensa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComite;
	private String titre;
	private String nom;


	@OneToMany(mappedBy = "comite", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<EvaluationComite> evaluation;
	
	
	@OneToMany(mappedBy = "comiteEditoriale", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	public Comite() {
		super();
	}

	public Comite(Long idComite, String nom) {
		super();
		this.idComite = idComite;
		this.nom = nom;
	}

	
	

	public Comite(String nom) {
		super();
		this.nom = nom;
	}

	public Long getIdComite() {
		return idComite;
	}

	public void setIdComite(Long idComite) {
		this.idComite = idComite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<EvaluationComite> getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(List<EvaluationComite> evaluation) {
		this.evaluation = evaluation;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public Comite(
			@Size(min = 4, max = 30, message = "Ce champ doit avoir entre 4 et 30  caractères") @NotBlank(message = "Ce champ ne doit pas etre vide") String titre,
			@Size(min = 4, max = 30, message = "Ce champ doit avoir entre 4 et 30  caractères") @NotBlank(message = "Ce champ ne doit pas etre vide") String nom) {
		super();
		this.titre = titre;
		this.nom = nom;
	}
	
	
	
	
	
	
}
