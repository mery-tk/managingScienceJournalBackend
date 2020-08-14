package ma.ensa;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.ensa.controllers.ArticleControlleur;
import ma.ensa.dao.IArticleDao;
import ma.ensa.entities.Article;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Referee;
import ma.ensa.entities.Role;
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
		auteurService.ajouterAuteur(new Auteur("ach1", "sara1", "sara1@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara1", "sara"));
		auteurService.ajouterAuteur(new Auteur("ach2", "sara2", "sara2@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara2", "sara"));
		auteurService.ajouterAuteur(new Auteur("ach3", "sara3", "sara3@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara3", "sara"));
		auteurService.ajouterAuteur(new Auteur("ach4", "sara4", "sara4@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara4", "sara"));
		auteurService.ajouterAuteur(new Auteur("ach5", "sara5", "sara5@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara5", "sara"));
		articleService.ajouterArticle(new Article(Long.valueOf("1"), "titre1", "aff1", "res1", "motCle1", "contenu1", "recent"));
		articleService.ajouterArticle(new Article(Long.valueOf("2"), "titre2", "aff2", "res2", "motCle2", "contenu2", "recent"));
		articleService.ajouterArticle(new Article(Long.valueOf("3"), "titre3", "aff3", "res3", "motCle3", "contenu3", "recent"));
		refereeService.ajouterReferee(new Referee("nom1", "prenom1", "email1@mail.com", Long.valueOf("0600000001"), "adresse1", "username1", "sara"));
		refereeService.ajouterReferee(new Referee("nom2", "prenom2", "email2@mail.com", Long.valueOf("0600000001"), "adresse2", "username2", "sara"));
	
	
	
	roleService.ajouterRole(new Role("UTILISATEUR"));
	roleService.ajouterRole(new Role("AUTEUR"));
	
	utilisateurService.addRoleToUser("sara1", "AUTEUR");
	utilisateurService.addRoleToUser("sara2", "AUTEUR");
	utilisateurService.addRoleToUser("sara3", "AUTEUR");
	utilisateurService.addRoleToUser("sara4", "AUTEUR");
	utilisateurService.addRoleToUser("sara5", "AUTEUR");
	utilisateurService.addRoleToUser("username1", "AUTEUR");
	utilisateurService.addRoleToUser("username2", "AUTEUR");
	
	}
}
