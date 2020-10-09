package ma.ensa;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import ma.ensa.dao.RoleJpaRepository;
import ma.ensa.entities.Article;
import ma.ensa.entities.Comite;
import ma.ensa.entities.Referee;
import ma.ensa.entities.Role;
import ma.ensa.entities.Utilisateur;
import ma.ensa.services.ArticleService;
import ma.ensa.services.AuteurService;
import ma.ensa.services.ComiteService;
import ma.ensa.services.RefereeService;
import ma.ensa.services.RoleService;
import ma.ensa.services.UtilisateurService;
import ma.ensa.web.ArticleRestService;

@SpringBootApplication
public class GestionRevueApplication implements CommandLineRunner{
	
	@Autowired AuteurService auteurService;
	@Autowired ArticleService articleService;
	@Autowired RefereeService refereeService;
	@Autowired RoleService roleService;
	@Autowired UtilisateurService utilisateurService;
	@Autowired RoleJpaRepository roleDao;
	@Autowired ComiteService comiteEditorialeService;
	
	public static void main(String[] args) {
		new File(ArticleRestService.articlesDirectory).mkdir();
		SpringApplication.run(GestionRevueApplication.class, args);
	}
	
	@Bean
    public BCryptPasswordEncoder getBCryptPassword(){
        return new BCryptPasswordEncoder();
    }
	
	
	@Override
	public void run(String... args) throws Exception {
		
//		roleService.ajouterRole(new Role("UTILISATEUR"));
//		roleService.ajouterRole(new Role("AUTEUR"));
//		roleService.ajouterRole(new Role("COMITE"));
//		
//		Utilisateur user1=new Utilisateur("TAIKOKp", "meryemp", "meryem1@gmail.com", Long.valueOf("0615246255"), "Agadir", "meryem1", "taikok1");
//		user1.getRoles().add(roleDao.findByNomRole("UTILISATEUR"));
//		utilisateurService.ajouterUtilisateur(user1);
//		Utilisateur user2=new Utilisateur("AHMADIp", "ridap", "rida@gmail.com", Long.valueOf("01245398"), "Casa", "ahmadi1", "rida1");
//		user2.getRoles().add(roleDao.findByNomRole("UTILISATEUR"));
//		utilisateurService.ajouterUtilisateur(user2);
//		Utilisateur user3=new Utilisateur("HACHIMl", "khalidi", "khalid@gmail.com", Long.valueOf("04896513745"), "Ifni1", "hachim1", "khalid1");
//		user3.getRoles().add(roleDao.findByNomRole("UTILISATEUR"));
//		utilisateurService.ajouterUtilisateur(user3);
//		
//	
//
//		Referee aut1=new Referee("TAIKOK", "meryem", "meryem1@gmail.com", Long.valueOf("0615246255"), "Agadir", "meryem", "taikok");
//		aut1.getRoles().add(roleDao.findByNomRole("AUTEUR"));
//		refereeService.ajouterReferee(aut1);
//		Referee aut2=new Referee("AHMADI", "rida", "rida@gmail.com", Long.valueOf("01245398"), "Casa", "ahmadi", "rida");
//		aut2.getRoles().add(roleDao.findByNomRole("AUTEUR"));
//		refereeService.ajouterReferee(aut2);
//		Referee aut3=new Referee("HACHIM", "khalid", "khalid@gmail.com", Long.valueOf("04896513745"), "Ifni", "hachim", "khalid");
//		aut3.getRoles().add(roleDao.findByNomRole("AUTEUR"));
//		refereeService.ajouterReferee(aut3);
//		
//
//		Utilisateur mery= new Utilisateur("taik", "mery", "mery@mail.com", Long.valueOf("0600000001"), "Agadir", "Meryeem1", "mery");
//		mery.getRoles().add(roleDao.findByNomRole("COMITE"));
//		mery.getRoles().remove(roleDao.findByNomRole("UTILISATEUR"));
//		utilisateurService.ajouterUtilisateur(mery);
//		
//		articleService.ajouterArticle(new Article(Long.valueOf("1"), "titre1", "aff1", "res1", "motCle1", "contenu1", "recent"));
//		articleService.ajouterArticle(new Article(Long.valueOf("2"), "titre2", "aff2", "res2", "motCle2", "contenu2", "recent"));
//		articleService.ajouterArticle(new Article(Long.valueOf("3"), "titre3", "aff3", "res3", "motCle3", "contenu3", "recent"));
//
//		comiteEditorialeService.ajouterComite(new Comite("ComiteEditorialMeryem","Comiteeee"));
//	
	

	
	}
}
