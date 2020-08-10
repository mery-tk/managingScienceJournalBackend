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

import ma.ensa.dao.IEvaluationDao;
import ma.ensa.entities.Article;
import ma.ensa.entities.ArticleForm;
import ma.ensa.entities.ArticleRefereeForm;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Evaluation;
import ma.ensa.entities.EvaluationComite;
import ma.ensa.entities.EvaluationReferee;
import ma.ensa.entities.Referee;
import ma.ensa.services.IArticleService;
import ma.ensa.services.IAuteurService;
import ma.ensa.services.IComiteEditorialeService;
import ma.ensa.services.ICorrespondanceService;
import ma.ensa.services.IEvaluationComiteService;
import ma.ensa.services.IEvaluationRefereeService;
import ma.ensa.services.IEvaluationService;
import ma.ensa.services.IRefereeService;
import ma.ensa.services.impl.EvaluationRefereeService;
import ma.ensa.services.impl.EvaluationService;

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
	@Autowired private IEvaluationComiteService evaluationComiteService;
	@Autowired private IComiteEditorialeService comiteEditorialeService;
	@Autowired private IEvaluationRefereeService evaluationRefereeService;
	@Autowired private IEvaluationDao evaluationDao;
	@Autowired private IEvaluationService evaluationService;
	
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
		public Article  affecterRefereeArticle(@PathVariable Long id, @RequestBody List<Referee> referees) { 
			Article art=articleService.afficherArticleParId(id);
			for (Referee referee2 : referees) {
				List<Article> articles = referee2.getArticles();
				articles.add(art);
				referee2.setArticles(articles);
				refereeService.modifierReferee(referee2.getIdUtilisateur(), referee2);
			}
			List<Referee> listReferee=art.getReferees();
			listReferee.addAll(referees);
			art.setReferees(listReferee);		
			Article a=articleService.modifierArticle(id, art);
			
			return a;
		}
	 
	
	 @GetMapping(value = "/auteursNonReferee/{idArticle}")
	 public List<Auteur> trouverRefereePourArticle(@PathVariable Long idArticle){
		 Article article=articleService.afficherArticleParId(idArticle);//OK
		 List<Correspondance> correspondances = article.getCorres();//OK
		 List<Auteur> listeAuteurs=new ArrayList<Auteur>();//OK
		 List<Auteur> list=auteurService.afficherAuteurs();//OK
		 List<Auteur> listt=auteurService.afficherAuteurs();
		 for(Correspondance corr:correspondances) {
			 listeAuteurs.add(corr.getCorrespondance_PK().getAuteur());	 //OK	 
			 
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
	 
	 @PutMapping(value = "/articles/{idArticle}/evaluationComite")
	 public Article evaluerParComite(@PathVariable Long idArticle, @RequestBody String qualification) {
		 Article article = articleService.afficherArticleParId(idArticle);
		 EvaluationComite evaluationComite = new EvaluationComite(qualification);
		 evaluationComite.setArticle(article);
		 evaluationComite.setComite(comiteEditorialeService.afficherComiteEditorialeParId(Long.valueOf("1")));
		 EvaluationComite comite = evaluationComiteService.ajouterEvaluationComite(evaluationComite);
		 List<Evaluation> evaluations = article.getListEvaluation();
		 evaluations.add(comite);
		 article.setListEvaluation(evaluations);
		 article.setEtat(qualification);
		 return articleService.modifierArticle(idArticle, article);
	 }
	 
	 @PutMapping(value = "/articles/{idArticle}/evaluationReferee")
	 public void evaluerParReferee(@PathVariable Long idArticle, @RequestBody EvaluationReferee evaluationRefere) {
		 Article article = articleService.afficherArticleParId(idArticle);
		 Referee referee=refereeService.afficherRefereeParId(6L);
		Evaluation evaluation=evaluationDao.findAll().get(evaluationDao.findAll().size()-1);
		System.out.println(evaluation.getIdEvaluation());
		
		 //Article et evaluation
		 List<Evaluation> evaluations=article.getListEvaluation();
		 evaluations.add(evaluation);
		 article.setListEvaluation(evaluations);
		 Article ar=articleService.modifierArticle(idArticle, article);
		 evaluation.setArticle(ar);
		 evaluationService.modifierEvaluation(evaluation.getIdEvaluation(), evaluation);
		 
//		 //entre referee et evaluationReferee
		 EvaluationReferee evaluationReferee=evaluationRefereeService.afficherEvaluationRefereeParId(evaluation.getIdEvaluation());
		 System.out.println(evaluationReferee.getIdEvaluation());
		 System.out.println(referee);
		 List<Referee> listeReferee=evaluationReferee.getReferees();
		 List<EvaluationReferee> listeEvaluationReferees=referee.getEvaluationReferees();
		 
		 listeEvaluationReferees.add(evaluationReferee);
		 referee.setEvaluationReferees(listeEvaluationReferees);
		 Referee ref=refereeService.modifierReferee(6L, referee);
         System.out.println(ref);
         
		 listeReferee.add(ref);
		 
		 evaluationReferee.setReferees(listeReferee);
		
		evaluationRefereeService.modifierEvaluationReferee(evaluation.getIdEvaluation(), evaluationReferee);		
		System.out.println(evaluationReferee.getIdEvaluation());
	 }
	
	 
	 

	 
}