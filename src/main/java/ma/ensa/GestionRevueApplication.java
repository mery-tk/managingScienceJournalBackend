package ma.ensa;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ma.ensa.controllers.ArticleControlleur;
import ma.ensa.entities.Auteur;
import ma.ensa.services.IAuteurService;

@SpringBootApplication
public class GestionRevueApplication implements CommandLineRunner{
	
	@Autowired IAuteurService auteurService;

	public static void main(String[] args) {
		new File(ArticleControlleur.articlesDirectory).mkdir();
		SpringApplication.run(GestionRevueApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
//		auteurService.ajouterAuteur(new Auteur("ach1", "sara1", "sara1@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara1", "sara"));
//		auteurService.ajouterAuteur(new Auteur("ach2", "sara2", "sara2@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara1", "sara"));
//		auteurService.ajouterAuteur(new Auteur("ach3", "sara3", "sara3@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara1", "sara"));
//		auteurService.ajouterAuteur(new Auteur("ach4", "sara4", "sara4@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara1", "sara"));
//		auteurService.ajouterAuteur(new Auteur("ach5", "sara5", "sara5@gmail.com", Long.valueOf("0600000001"), "Agadir", "sara1", "sara"));
	}
}
