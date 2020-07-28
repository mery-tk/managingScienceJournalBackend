package ma.ensa.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.services.IAuteurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuteurController {
	
	@Autowired private IAuteurService auteurService;
	
	@GetMapping(value = "/auteurs")
	 public List<Auteur> getAuteurs(){
		 return auteurService.afficherAuteurs();
	 }
	 
	 @GetMapping(value = "/auteurs/{idAuteur}")
	 public Auteur getAuteurByID(@PathVariable Long idAuteur) {
		 return auteurService.afficherAuteurParId(idAuteur);
	 }
	 
	 @PostMapping(value = "/auteurs")
	 public Auteur addAuteur(@RequestBody Auteur auteur) {
		 return auteurService.ajouterAuteur(auteur);
	 }
	 
	 @PutMapping(value = "/auteurs/{idAuteur}")
	 public Auteur editAuteur(@PathVariable Long idAuteur, @RequestBody Auteur auteur) {
		 return auteurService.modifierAuteur(idAuteur, auteur);
	 }
	 
	 @DeleteMapping(value = "/auteurs/{idAuteur}")
	 public void deleteAuteur(@PathVariable Long idAuteur) {
		 auteurService.supprimerAuteur(idAuteur);
	 }
	 
	 @GetMapping(value = "/auteurs/{idAuteur}/articles")
	 public List<Article> getArticleAuteur(@PathVariable Long idAuteur) {
		 Auteur auteur =  auteurService.afficherAuteurParId(idAuteur);
		 return auteur.getArticles();
	 }

}
