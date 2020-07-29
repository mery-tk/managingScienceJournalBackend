package ma.ensa.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArticle;
	private String titre;
	private String affiliations;
	private String resume;
	private String motCle;
	private String contenu;
	private String etat;
	
//	@ManyToMany
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JoinTable(name = "article_auteur",
//	joinColumns = { @JoinColumn(name="idArticle")},
//	inverseJoinColumns ={ @JoinColumn(name="idAuteur")} )
//	private List<Auteur> auteurs;
	
	////////////////////////////
	
	
	@OneToMany(mappedBy = "article")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private  List<Evaluation> listEvaluation;
	
	
	@OneToMany(mappedBy = "correspondance_PK.article")
	private List<Correspondance> corres=new ArrayList<Correspondance>();
	
	
	/////////////////////////////
	public Long getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}
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
	public Article(Long idArticle, String titre, String affiliations, String resume, String motCle,
			String contenu, String etat) {
		super();
		this.idArticle = idArticle;
		this.titre = titre;
		this.affiliations = affiliations;
		this.resume = resume;
		this.motCle = motCle;
		this.contenu = contenu;
		this.etat = etat;
	}
	public Article() {
		super();
	}
	
	public List<Evaluation> getListEvaluation() {
		return listEvaluation;
	}
	public void setListEvaluation(List<Evaluation> listEvaluation) {
		this.listEvaluation = listEvaluation;
	}
	public List<Correspondance> getCorres() {
		return corres;
	}
	public void setCorres(List<Correspondance> corres) {
		this.corres = corres;
	}

//	@Override
//	public String toString() {
//		return "Article [idArticle=" + idArticle + ", titre=" + titre + ", affiliations=" + affiliations + ", resume="
//				+ resume + ", motCle=" + motCle + ", contenu=" + contenu + ", etat=" + etat + ", auteurs=" + auteurs
//				+ ", listEvaluation=" + listEvaluation + "]";
//	}
	
	
	

}