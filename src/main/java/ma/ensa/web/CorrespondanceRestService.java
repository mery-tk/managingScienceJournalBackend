package ma.ensa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Correspondance_PK;
import ma.ensa.services.ArticleService;
import ma.ensa.services.AuteurService;
import ma.ensa.services.CorrespondanceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CorrespondanceRestService {

	
	@Autowired private CorrespondanceService correspondanceService;
	@Autowired private ArticleService articleService;
	@Autowired private AuteurService auteurService;
	
	@GetMapping(value = "correspondance/{idArticle}/{idAuteur}")
public boolean getCorrespondance(@PathVariable Long idArticle, @PathVariable Long idAuteur) {
		Article article = articleService.afficherArticleParId(idArticle);
		Auteur auteur = auteurService.afficherAuteurParId(idAuteur);
		
		Correspondance correspondance = correspondanceService.afficherCorrespondanceParId(new Correspondance_PK(article, auteur));
		return correspondance.isCorrespondance();
		
	}
	
}
