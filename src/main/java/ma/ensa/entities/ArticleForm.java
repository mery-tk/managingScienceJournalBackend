package ma.ensa.entities;

import java.util.List;

public class ArticleForm {
	
	private String titre;
	private String affiliations;
	private String resume;
	private String motCle;
	private String contenu;
	private String etat;
	private Auteur auteurCorrespondant;
	private List<Auteur> co_auters;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAffiliations() {
		return affiliations;
	}
	public void setAffiliations(String affiliations) {
		this.affiliations = affiliations;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getMotCle() {
		return motCle;
	}
	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Auteur getAuteurCorrespondant() {
		return auteurCorrespondant;
	}
	public void setAuteurCorrespondant(Auteur auteurCorrespondant) {
		this.auteurCorrespondant = auteurCorrespondant;
	}
	public List<Auteur> getCo_auters() {
		return co_auters;
	}
	public void setCo_auters(List<Auteur> co_auters) {
		this.co_auters = co_auters;
	}
	public ArticleForm() {
		super();
	}
	
	

}
