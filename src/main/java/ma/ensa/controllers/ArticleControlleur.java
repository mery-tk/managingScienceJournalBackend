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
import ma.ensa.entities.InfoReduitesArticle;
import ma.ensa.services.IArticleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleControlleur {
 @Autowired private IArticleService articleService;
	 
	 @GetMapping(value = "/articles")
	 public List<Article> getArticles(){
		 return articleService.afficherArticles();
	 }
	 
	 @GetMapping(value = "/articles/{idArticle}")
	 public Article getArticleByID(@PathVariable Long idArticle) {
		 return articleService.afficherArticleParId(idArticle);
	 }
	 
	 @PostMapping(value = "/articles")
	 public Article addArticle(@RequestBody Article Article) {
		 return articleService.ajouterArticle(Article);
	 }
	 
	 @PutMapping(value = "/articles/{idArticle}")
	 public Article editArticle(@PathVariable Long idArticle, @RequestBody Article Article) {
		 return articleService.modifierArticle(idArticle, Article);
	 }
	 
	 @DeleteMapping(value = "/articles/{idArticle}")
	 public void deleteArticle(@PathVariable Long idArticle) {
		 articleService.supprimerArticle(idArticle);
	 }
	 
	 @GetMapping(value = "/InfoArticles/{idArticle}")
	 public InfoReduitesArticle getInfoReduitesArticle(@PathVariable Long idArticle) {
		 return articleService.afficheInfoReduitesArticle(idArticle);
	 }
	 
	 @GetMapping(value = "articles/{idArticle}/auteurs")
	 public List<Auteur> getAuteursPourArticle(@PathVariable Long idArticle) {
		 return articleService.afficherArticleParId(idArticle).getAuteurs();
	 }
	 
	 
	 
	
}