package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.dao.IAuteurDao;
import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.services.IAuteurService;

@Service
public class AuteurService implements IAuteurService{

	@Autowired private IAuteurDao auteurDao;

	@Override
	public List<Auteur> afficherAuteurs() {
		return auteurDao.findAll();
	}

	@Override
	public Auteur afficherAuteurParId(Long idAuteur) {
		return auteurDao.findById(idAuteur).get();
	}

	@Override
	public Auteur ajouterAuteur(Auteur auteur) {
		return auteurDao.save(auteur);
	}

	@Override
	public Auteur modifierAuteur(Long idAuteur, Auteur auteur) {
		Auteur aut = this.afficherAuteurParId(idAuteur);
		aut.setAdresse(auteur.getAdresse());
		aut.setEmail(auteur.getEmail());
		aut.setNom(auteur.getNom());
		aut.setPassword(auteur.getPassword());
		aut.setPrenom(auteur.getPrenom());
		aut.setTelephone(auteur.getTelephone());
		aut.setUsername(auteur.getUsername());
		//aut.setCorrespondance(auteur.isCorrespondance());
		aut.setNbrArticlesEcrites(auteur.getNbrArticlesEcrites());
		//List<Article> articles = aut.getArticles();
	//	articles.addAll(auteur.getArticles());
		//aut.setArticles(articles);
		return auteurDao.save(aut);
	}

	@Override
	public boolean supprimerAuteur(Long idAuteur) {
		if(auteurDao.findById(idAuteur).isPresent()) {
			auteurDao.deleteById(idAuteur);
			return true;
		}
		return false;	
	}
	
	
	
}
