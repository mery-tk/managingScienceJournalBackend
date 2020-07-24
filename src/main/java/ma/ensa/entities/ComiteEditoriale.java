package ma.ensa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ComiteEditoriale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComite;
	private String nom;
	
	@OneToOne(mappedBy = "comite", fetch = FetchType.LAZY)
	@JsonIgnore
	private EvaluationComite evaluation;

	
	
	@OneToMany(mappedBy = "comiteEditoriale", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	public ComiteEditoriale() {
		super();
	}

	public ComiteEditoriale(Long idComite, String nom) {
		super();
		this.idComite = idComite;
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
	
	
	
	
	
}
