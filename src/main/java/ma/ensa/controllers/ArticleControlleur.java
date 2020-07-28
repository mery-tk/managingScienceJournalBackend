package ma.ensa.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.services.IArticleService;
import ma.ensa.services.IAuteurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleControlleur {

	/////////////////////////////////////////
	public static String articlesDirectory = System.getProperty("user.dir") + "/articles/";
	/////////////////////////////////////////
	
	@Autowired private IArticleService articleService;
	@Autowired private IAuteurService auteurService;
	 @GetMapping(value = "/articles")
	 public List<Article> getArticles(){
		 return articleService.afficherArticles();
	 }
	 
	 @GetMapping(value = "/articles/{idArticle}")
	 public Article getArticleByID(@PathVariable Long idArticle) {
		 return articleService.afficherArticleParId(idArticle);
	 }
	 
//	 @PostMapping(value = "/articles")
//	 public Article addArticle(@RequestBody Article Article) {
//		 return articleService.ajouterArticle(Article);
//	 }
	 
//	 @PutMapping(value = "/articles/{idArticle}")
//	 public Article editArticle(@PathVariable Long idArticle, @RequestBody Article Article) {
//		 return articleService.modifierArticle(idArticle, Article);
//	 }
	 
	 @DeleteMapping(value = "/articles/{idArticle}")
	 public void deleteArticle(@PathVariable Long idArticle) {
		 articleService.supprimerArticle(idArticle);
	 }
	 
	 @GetMapping(value ="/article/{idArticle}",produces = MediaType.APPLICATION_PDF_VALUE)
	    public byte[] getFichier(@PathVariable("idArticle") Long idArticle) throws Exception{
			Article article = articleService.afficherArticleParId(idArticle);
			return Files.readAllBytes(Paths.get(articlesDirectory+article.getContenu()));
	    }
	
	 @PostMapping(value ="/articles")
	 public Article ajouterArticle(@RequestParam("file") MultipartFile file, @RequestParam("article")String article) throws JsonMappingException, JsonProcessingException, IOException {
			Article monArticle = new ObjectMapper().readValue(article, Article.class);
			System.out.println("article : "+article);
			System.out.println(monArticle);
			Article art = new Article();
			List<Auteur> auteurs = monArticle.getAuteurs();
			art.setTitre(monArticle.getTitre());
			art.setAffiliations(monArticle.getAffiliations());
			art.setContenu(file.getOriginalFilename());
			art.setEtat(monArticle.getEtat());
		    art.setMotCle(monArticle.getMotCle());
		    art.setResume(monArticle.getResume());
		    art.setAuteurs(auteurs);
		    if(auteurs!=null) {
			    for (Auteur auteur : auteurs) {
			    	List<Article> articles = auteur.getArticles();
			    	articles.add(art);
					auteur.setArticles(articles);
					auteurService.modifierAuteur(auteur.getIdUtilisateur(), auteur);
				}
		    }
			Files.write(Paths.get(articlesDirectory+file.getOriginalFilename()),file.getBytes());
			return articleService.ajouterArticle(art);
	    }
	 
	 @PutMapping("/articles/{idArticle}")
		public Article update(@RequestParam("file") MultipartFile file, @RequestParam("article")String article, @PathVariable("idArticle") Long idArticle ) throws JsonMappingException, JsonProcessingException, IOException, Exception {
			Article monArticle = new ObjectMapper().readValue(article, Article.class);
			Article art = articleService.afficherArticleParId(idArticle);
			art.setTitre(monArticle.getTitre());
			art.setAffiliations(monArticle.getAffiliations());
			art.setContenu(file.getOriginalFilename());
			art.setEtat(monArticle.getEtat());
		    art.setMotCle(monArticle.getMotCle());
		    art.setResume(monArticle.getResume());
		    Files.write(Paths.get(articlesDirectory+file.getOriginalFilename()),file.getBytes());
			return articleService.modifierArticle(idArticle, art);
	    }
	 
}