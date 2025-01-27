package ma.ensa.web;

import java.util.ArrayList;
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

import ma.ensa.dao.RoleJpaRepository;
import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Role;
import ma.ensa.entities.Utilisateur;
import ma.ensa.services.AuteurService;
import ma.ensa.services.UtilisateurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuteurRestService {
	
	@Autowired private AuteurService auteurService;
	@Autowired private UtilisateurService utilisateurService;
	@Autowired private RoleJpaRepository roleDao;
	@GetMapping(value = "/auteurs")
	 public List<Auteur> getAuteurs(){
		 return auteurService.afficherAuteurs();
	 }
	 
	 @GetMapping(value = "/auteurs/{idAuteur}")
	 public Auteur getAuteurByID(@PathVariable Long idAuteur) {
		 return auteurService.afficherAuteurParId(idAuteur);
	 }
	 @GetMapping(value = "/auteurs/{idAuteur}/articles")
	 public List<Article> getArticleAuteur(@PathVariable Long idAuteur) {
		 Auteur aut =  auteurService.afficherAuteurParId(idAuteur);
		 List<Correspondance> correspondances = aut.getCorres();
		 List<Article> articles = new ArrayList<Article>();
		 for (Correspondance correspondance : correspondances) {
				articles.add(correspondance.getCorrespondance_PK().getArticle());
			}
		 return articles;
	 }

	 @PostMapping(value = "/auteurs")
	 public Auteur addAuteur(@RequestBody Auteur auteur) {
		 String u=auteur.getUsername();
			Utilisateur User=utilisateurService.findUserByUsername(u);
			if(User!=null) {
				throw new RuntimeException("Essayez un autre Username");	
			}
			Role rol=roleDao.findByNomRole("AUTEUR");
			auteur.getRoles().add(rol);
			System.out.println(auteur.getRoles());
			System.out.println(rol.getId());
			
		 
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
	 
	 

}
