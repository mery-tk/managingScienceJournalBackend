package ma.ensa.web;

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
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Evaluation;
import ma.ensa.entities.EvaluationComite;
import ma.ensa.entities.EvaluationReferee;
import ma.ensa.entities.Referee;
import ma.ensa.services.ArticleService;
import ma.ensa.services.AuteurService;
import ma.ensa.services.ComiteService;
import ma.ensa.services.CorrespondanceService;
import ma.ensa.services.EvaluationComiteService;
import ma.ensa.services.EvaluationRefereeService;
import ma.ensa.services.RefereeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleRestService {

	
	public static String articlesDirectory = System.getProperty("user.dir") + "/articles/";
	
	@Autowired private ArticleService articleService;
	@Autowired private CorrespondanceService corresService; 
	@Autowired private AuteurService auteurService;
	@Autowired private RefereeService refereeService; 
	@Autowired private EvaluationComiteService evaluationComiteService;
	@Autowired private ComiteService comiteEditorialeService;
	@Autowired private EvaluationRefereeService evaluationRefereeService;
	
	
	 @GetMapping(value = "/articles")
	 public List<Article> getArticles(){
		 return articleService.afficherArticles();
	 }
	 
	 @GetMapping(value = "/articles/{idArticle}")
	 public Article getArticleByID(@PathVariable Long idArticle) {
		 return articleService.afficherArticleParId(idArticle);
	 }
	 

	 
	 @DeleteMapping(value = "/articles/{idArticle}")
	 public void deleteArticle(@PathVariable Long idArticle) {
		 articleService.supprimerArticle(idArticle);
	 }
	 @GetMapping(value = "/chercherArticlesParAuteur")
		public List<Article> chercherArticleParAuteur(@RequestParam(name="aut",defaultValue = "")String aut) { 
		 List<Auteur> lesteAuteurs = auteurService.afficherAuteurs();
		 List<Auteur> listAut=new ArrayList<Auteur>(); 
		 List<Article> articlesChoisi=new ArrayList<Article>();
		 for(Auteur a :lesteAuteurs) {
			 if(a.getNom().contains(aut) || a.getPrenom().contains(aut)) {
				 listAut.add(a);
			 }		 
		 } 
		 for(Auteur au:listAut) {
			 List<Correspondance> listeCorrespondances = au.getCorres();	 
			 for(Correspondance corr:listeCorrespondances) {
				 articlesChoisi.add(corr.getCorrespondance_PK().getArticle());
				 System.out.println(corr.getCorrespondance_PK().getArticle().getMotCle());	 
			 }
		 }
	      return articlesChoisi;
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
			List<Referee> auteurs = monArticle.getCo_auters();
			Auteur auteur = monArticle.getAuteurCorrespondant();
			art.setTitre(monArticle.getTitre());
			art.setAffiliations(monArticle.getAffiliations());
			art.setContenu(file.getOriginalFilename());
			art.setEtat(monArticle.getEtat());
		    art.setMotCle(monArticle.getMotCle());
		    art.setResume(monArticle.getResume());
		    Article articleAjoutee = articleService.ajouterArticle(art);
		    corresService.ajouterCorrespondance(new Correspondance(articleAjoutee, auteur, true));
		    for (Referee auteur2 : auteurs) {
		    	corresService.ajouterCorrespondance(new Correspondance(articleAjoutee, auteur2, false));
			}
			Files.write(Paths.get(articlesDirectory+file.getOriginalFilename()),file.getBytes());
			return articleAjoutee;
	    }
	 
	 @PutMapping("/articles/{idArticle}")
		public Article update(@RequestParam("file") MultipartFile file, @RequestParam("article")String article, @PathVariable("idArticle") Long idArticle ) throws JsonMappingException, JsonProcessingException, IOException, Exception {
			Article monAr = new ObjectMapper().readValue(article, Article.class);
			Article art = articleService.afficherArticleParId(idArticle);
			art.setTitre(monAr.getTitre());
			art.setAffiliations(monAr.getAffiliations());
			art.setContenu(file.getOriginalFilename());
		    art.setMotCle(monAr.getMotCle());
		    art.setResume(monAr.getResume());
		    Files.write(Paths.get(articlesDirectory+file.getOriginalFilename()),file.getBytes());
			return articleService.modifierArticle(idArticle, art);
	    }
	 
	 @GetMapping(value = "articles/{idArticle}/auteurs")
	 public List<Auteur> getAuteursPourArticle(@PathVariable Long idArticle) {
		 Article article = articleService.afficherArticleParId(idArticle);
		 List<Correspondance> listeCorrespondances = article.getCorres();
		 List<Auteur> auteurs = new ArrayList<Auteur>();
		 for (Correspondance correspondance : listeCorrespondances) {
			auteurs.add(correspondance.getCorrespondance_PK().getAuteur());
		}
		 return auteurs;
	 }
	 
	 @PutMapping("/articless/{idArticle}")
		public Article updateArt(@RequestBody Article article, @PathVariable("idArticle") Long idArticle ) throws JsonMappingException, JsonProcessingException, IOException, Exception {
			Article art = articleService.afficherArticleParId(idArticle);
			art.setEtat("Refusé");
			
			return articleService.modifierArticle(idArticle, art);
	    }
	 
	 @RequestMapping(value = "/chercherArticles",method=RequestMethod.GET)
		public Page<Article> chercherArticleParMotCle(@RequestParam(name="mc",defaultValue = "")String mc,
				                      @RequestParam(name="page",defaultValue = "0")int page,
				                      @RequestParam(name = "size",defaultValue = "5")int size){
	      return articleService.chercherArticles(mc, page, size);
		
		}
 
	 

	 @PutMapping(value = "/articles/{idArticle}/evaluationComite")
	 public Article evaluerParComite(@PathVariable Long idArticle, @RequestBody String qualification) {
		 
		 Article article = articleService.afficherArticleParId(idArticle);
		 System.out.println(article);
		 EvaluationComite evaluationComite = new EvaluationComite(qualification);
		 System.out.println(evaluationComite);
		 evaluationComite.setArticle(article);
		 evaluationComite.setComite(comiteEditorialeService.afficherComiteParId(Long.valueOf("1")));
		 EvaluationComite comite = evaluationComiteService.ajouterEvaluationComite(evaluationComite);
		 List<Evaluation> evaluations = article.getListEvaluation();
		 evaluations.add(comite);
		 article.setListEvaluation(evaluations);
		 article.setEtat(qualification);
		 return articleService.modifierArticle(idArticle, article);
	 }
	 
	
	 
	 
	 
	 @RequestMapping(value = "/ajouterRefereeArticle/{id}",method=RequestMethod.PUT)
		public Article  affecterRefereeArticle(@PathVariable Long id, @RequestBody List<Referee> referees) { 
			Article art=articleService.afficherArticleParId(id);
			
			for (Referee referee2 : referees) {
				List<Article> articles= referee2.getArticles();
				articles.add(art);
				referee2.setArticles(articles);
				refereeService.modifierReferee(referee2.getIdUtilisateur(), referee2);
			}
			
			List<Referee> listReferee=art.getReferees();
			listReferee.addAll(referees);
			art.setReferees(listReferee);		
			art.setEtat("En cours d'evaluation par Referees");
			Article a = articleService.modifierArticle(id, art);
			
			return a;
		}
	 @PostMapping(value = "/articles/{idArticle}/evaluationReferee/{idReferee}")
	 public EvaluationReferee evaluerParReferee(@PathVariable Long idArticle, @RequestBody EvaluationReferee evaluationRefere, @PathVariable Long idReferee) {
		 Article article = articleService.afficherArticleParId(idArticle);
		 Referee referee = refereeService.afficherRefereeParId(idReferee);
		 evaluationRefere.setArticle(article);
		 List<Referee> listeReferee = evaluationRefere.getReferees();
		 listeReferee.add(referee);
		 evaluationRefere.setReferees(listeReferee);
		 return evaluationRefereeService.ajouterEvaluationReferee(evaluationRefere);
	 }
	
	
	 @GetMapping(value = "/auteursNonReferee/{idArticle}")
	 public List<Auteur> trouverRefereePourArticle(@PathVariable Long idArticle){
		 Article article=articleService.afficherArticleParId(idArticle);
		 List<Correspondance> listeCorrespondance = article.getCorres();
		 List<Auteur> listeAuteurs=new ArrayList<Auteur>();
		 List<Auteur> list=auteurService.afficherAuteurs();
		 List<Auteur> listt=auteurService.afficherAuteurs();
		 for(Correspondance corr:listeCorrespondance) {
			 listeAuteurs.add(corr.getCorrespondance_PK().getAuteur());		 
			 
		 }
		 for(Auteur a:list) {
			 for(Auteur aut:listeAuteurs)
			 { if(a.getIdUtilisateur()==aut.getIdUtilisateur()) {
				 listt.remove(a);
					 System.out.println(a.getIdUtilisateur());} 
			 
			 }	 
		 }
		 
		 return listt;
	 }
	 
	
	
	 
	 

	 
}