package ma.ensa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Correspondance_PK;
import ma.ensa.services.IArticleService;
import ma.ensa.services.IAuteurService;
import ma.ensa.services.ICorrespondanceService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CorrespondanceController {

	
	@Autowired private ICorrespondanceService correspondanceService;
	@Autowired private IArticleService articleService;
	@Autowired private IAuteurService auteurService;
	
	@GetMapping(value = "correspondance/{idArticle}/{idAuteur}")
public boolean getCorrespondance(@PathVariable Long idArticle, @PathVariable Long idAuteur) {
		Article article = articleService.afficherArticleParId(idArticle);
		Auteur auteur = auteurService.afficherAuteurParId(idAuteur);
		
		Correspondance correspondance = correspondanceService.afficherCorrespondanceParId(new Correspondance_PK(article, auteur));
		return correspondance.isCorrespondance();
		
	}
	
}
