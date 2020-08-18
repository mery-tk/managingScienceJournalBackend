package ma.ensa;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ma.ensa.controllers.ArticleControlleur;
import ma.ensa.services.IArticleService;
import ma.ensa.services.IAuteurService;
import ma.ensa.services.IRefereeService;
import ma.ensa.services.IRoleService;
import ma.ensa.services.IUtilisateurService;

@SpringBootApplication
public class GestionRevueApplication implements CommandLineRunner{
	
	@Autowired IAuteurService auteurService;
	@Autowired IArticleService articleService;
	@Autowired IRefereeService refereeService;
	@Autowired IRoleService roleService;
	@Autowired IUtilisateurService utilisateurService;
	
	public static void main(String[] args) {
		new File(ArticleControlleur.articlesDirectory).mkdir();
		SpringApplication.run(GestionRevueApplication.class, args);
	}
	
	@Bean
    public BCryptPasswordEncoder getBCryptPassword(){
        return new BCryptPasswordEncoder();
    }
	
	
	@Override
	public void run(String... args) throws Exception {

//		auteurService.ajouterAuteur(new Auteur("Taik1", "meryem1", "meryem1@gmail.com", Long.valueOf("0600000001"), "Agadir", "meryem1", "mery"));
//		auteurService.ajouterAuteur(new Auteur("Taik2", "meryem2", "meryem2@gmail.com", Long.valueOf("0600000001"), "Agadir", "meryem2", "mery"));
//		auteurService.ajouterAuteur(new Auteur("Taik3", "meryem3", "meryem3@gmail.com", Long.valueOf("0600000001"), "Agadir", "meryem3", "mery"));
//		auteurService.ajouterAuteur(new Auteur("Taik4", "meryem4", "meryem4@gmail.com", Long.valueOf("0600000001"), "Agadir", "meryem4", "mery"));
//		auteurService.ajouterAuteur(new Auteur("Taik5", "meryem5", "meryem5@gmail.com", Long.valueOf("0600000001"), "Agadir", "meryem5", "mery"));
//		articleService.ajouterArticle(new Article(Long.valueOf("1"), "titre1", "aff1", "res1", "motCle1", "contenu1", "recent"));
//		articleService.ajouterArticle(new Article(Long.valueOf("2"), "titre2", "aff2", "res2", "motCle2", "contenu2", "recent"));
//		articleService.ajouterArticle(new Article(Long.valueOf("3"), "titre3", "aff3", "res3", "motCle3", "contenu3", "recent"));
//		refereeService.ajouterReferee(new Referee("nom1", "prenom1", "email1@mail.com", Long.valueOf("0600000001"), "adresse1", "username1", "mery"));
//		refereeService.ajouterReferee(new Referee("nom2", "prenom2", "email2@mail.com", Long.valueOf("0600000001"), "adresse2", "username2", "mery"));
//		utilisateurService.ajouterUtilisateur(new Utilisateur("taik", "mery", "mery@mail.com", Long.valueOf("0600000001"), "Agadir", "Meryeem", "mery"));

//	roleService.ajouterRole(new Role("UTILISATEUR"));
//	roleService.ajouterRole(new Role("AUTEUR"));
//	roleService.ajouterRole(new Role("COMITE"));

//	utilisateurService.addRoleToUser("meryem1", "AUTEUR");
//	utilisateurService.addRoleToUser("meryem2", "AUTEUR");
//	utilisateurService.addRoleToUser("meryem3", "AUTEUR");
//	utilisateurService.addRoleToUser("meryem4", "AUTEUR");
//	utilisateurService.addRoleToUser("meryem5", "AUTEUR");
//	utilisateurService.addRoleToUser("username1", "AUTEUR");
//	utilisateurService.addRoleToUser("username2", "AUTEUR");
//		utilisateurService.addRoleToUser("Meryeem", "COMITE");

	
	}
}
