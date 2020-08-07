package ma.ensa.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.ensa.entities.Article;
import ma.ensa.entities.ArticleForm;
import ma.ensa.entities.ArticleRefereeForm;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Referee;
import ma.ensa.services.IArticleService;
import ma.ensa.services.IAuteurService;
import ma.ensa.services.ICorrespondanceService;
import ma.ensa.services.IRefereeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleControlleur {

	/////////////////////////////////////////
	public static String articlesDirectory = System.getProperty("user.dir") + "/articles/";
	/////////////////////////////////////////
	
	@Autowired private IArticleService articleService;
	@Autowired private ICorrespondanceService corresService; 
	@Autowired private IAuteurService auteurService;
	@Autowired private IRefereeService refereeService; 
	
	
	
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
	 

	 
	 @GetMapping(value = "articles/{idArticle}/auteurs")
	 public List<Auteur> getAuteursPourArticle(@PathVariable Long idArticle) {
		 Article article = articleService.afficherArticleParId(idArticle);
		 List<Correspondance> correspondances = article.getCorres();
		 List<Auteur> auteurs = new ArrayList<Auteur>();
		 for (Correspondance correspondance : correspondances) {
			auteurs.add(correspondance.getCorrespondance_PK().getAuteur());
		}
		 return auteurs;
	 }
	
	 @GetMapping(value ="/article/{idArticle}",produces = MediaType.APPLICATION_PDF_VALUE)
	    public byte[] getFichier(@PathVariable("idArticle") Long idArticle) throws Exception{
			Article article = articleService.afficherArticleParId(idArticle);
			return Files.readAllBytes(Paths.get(articlesDirectory+article.getContenu()));
	    }
	
	 @PostMapping(value ="/articles")
	 public Article ajouterArticle(@RequestParam("file") MultipartFile file, @RequestParam("article")String article) throws JsonMappingException, JsonProcessingException, IOException {
			ArticleForm monArticle = new ObjectMapper().readValue(article, ArticleForm.class);
			System.out.println("article : "+article);
			System.out.println(monArticle);
			Article art = new Article();
			List<Auteur> auteurs = monArticle.getCo_auters();
			Auteur auteur = monArticle.getAuteurCorrespondant();
			art.setTitre(monArticle.getTitre());
			art.setAffiliations(monArticle.getAffiliations());
			art.setContenu(file.getOriginalFilename());
			art.setEtat(monArticle.getEtat());
		    art.setMotCle(monArticle.getMotCle());
		    art.setResume(monArticle.getResume());
		    Article articleAjoutee = articleService.ajouterArticle(art);
		    corresService.ajouterCorrespondance(new Correspondance(articleAjoutee, auteur, true));
		    for (Auteur auteur2 : auteurs) {
		    	corresService.ajouterCorrespondance(new Correspondance(articleAjoutee, auteur2, false));
			}
			Files.write(Paths.get(articlesDirectory+file.getOriginalFilename()),file.getBytes());
			return articleAjoutee;
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
	 
	 
	 @RequestMapping(value = "/chercherArticles",method=RequestMethod.GET)
		public Page<Article> chercherArticleParMotCle(@RequestParam(name="mc",defaultValue = "")String mc,
				                      @RequestParam(name="page",defaultValue = "0")int page,
				                      @RequestParam(name = "size",defaultValue = "5")int size){
	      return articleService.chercherArticles(mc, page, size);
		
		}
 
	 


	 @GetMapping(value = "/chercherArticlesParAuteur")
		public List<Article> chercherArticleParAuteur(@RequestParam(name="aut",defaultValue = "")String aut)
	 { 
		 List<Auteur> auteurs = auteurService.afficherAuteurs();
		 List<Auteur> listAut=new ArrayList<Auteur>(); 
		 List<Article> articlesChoisi=new ArrayList<Article>();
		 for(Auteur a :auteurs) {
			 if(a.getNom().contains(aut) || a.getPrenom().contains(aut)) {
				 listAut.add(a);
			 }		 
		 } 
		 for(Auteur au:listAut) {
			 List<Correspondance> correspondances = au.getCorres();	 
			 for(Correspondance corr:correspondances) {
				 articlesChoisi.add(corr.getCorrespondance_PK().getArticle());
				 System.out.println(corr.getCorrespondance_PK().getArticle().getMotCle());	 
			 }
		 }
	      return articlesChoisi;
		}
	 
	 
	 
	 
	 @RequestMapping(value = "/ajouterRefereeArticle/{id}",method=RequestMethod.PUT)
		public Article  affecterRefereeArticle(@PathVariable Long id, @RequestBody ArticleRefereeForm articleRefereeForm) { 
			Article art=articleService.afficherArticleParId(id);
			Referee referee=refereeService.afficherRefereeParId(articleRefereeForm.getIdReferee());
			List<Article> lista=referee.getArticles();
			List<Referee> listReferee=art.getReferees();
			listReferee.add(referee);
			art.setReferees(listReferee);		
			Article a=articleService.modifierArticle(id, art);
			lista.add(a);
			referee.setArticles(lista);
			refereeService.modifierReferee(articleRefereeForm.getIdReferee(), referee);
			return a;
		}
	 
	
	 @GetMapping(value = "/auteursNonReferee/{idArticle}")
	 public List<Auteur> trouverRefereePourArticle(@PathVariable Long idArticle){
		 Article article=articleService.afficherArticleParId(idArticle);
		 List<Correspondance> correspondances = article.getCorres();
		 List<Auteur> listeAuteurs=new ArrayList<Auteur>();
		 List<Auteur> listeAuteurs2=new ArrayList<Auteur>();
		 List<Auteur> list=auteurService.afficherAuteurs();
		 for(Correspondance corr:correspondances) {
			 listeAuteurs.add(corr.getCorrespondance_PK().getAuteur());	 	 
		 }
		 for(Auteur a:list) {
			 for(Auteur aut:listeAuteurs)
			 { if(a!=aut) {
					 listeAuteurs2.add(a);  } 
			 }	 
		 }
		 
		 return listeAuteurs2;
	 }
	 

	 
}