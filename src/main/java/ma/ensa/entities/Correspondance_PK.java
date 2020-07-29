package ma.ensa.entities;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Correspondance_PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name = "idAuteur")
	private Auteur auteur;

	
	
	
	
	
	public Correspondance_PK() {
		super();
	}

	public Correspondance_PK(Article article, Auteur auteur) {
		super();
		this.article = article;
		this.auteur = auteur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	
	
	

	
	
	
	
}
