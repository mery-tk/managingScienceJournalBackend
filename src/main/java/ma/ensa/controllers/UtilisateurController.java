package ma.ensa.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ma.ensa.entities.Utilisateur;
import ma.ensa.services.IUtilisateurService;

@RestController
public class UtilisateurController {

	@Autowired IUtilisateurService utilisateurService;
	
	@GetMapping(value = "/utilisateurs")
	public List<Utilisateur> getUsers(){
		return utilisateurService.afficherUtilisateurs();
	}
	
	@GetMapping(value = "/utilisateurs/{idUtilisateur}")
	public Utilisateur getUserById(@PathVariable Long idUtilisateur) {
		return utilisateurService.afficherUtilisateurParId(idUtilisateur);
	}
	
	@PostMapping(value = "/utilisateurs")
	public Utilisateur addUser(@RequestBody Utilisateur utilisateur) {
		return utilisateurService.ajouterUtilisateur(utilisateur);
	}
	
	@PutMapping(value = "/utilisateurs/{idUtilisateur}")
	public Utilisateur editUser(@PathVariable Long idUtilisateur, @RequestBody Utilisateur utilisateur) {
		return utilisateurService.modifierUtilisateur(idUtilisateur, utilisateur);
	}
	
	@DeleteMapping(value = "/utilisateurs/{idUtilisateur}")
	public void deleteUser(@PathVariable Long idUtilisateur) {
		utilisateurService.supprimerUtilisateur(idUtilisateur);
	}
	
	
	@GetMapping(value = "/utilisateurs")
	public Page<Utilisateur> chercher(@RequestParam(name = "mc", defaultValue = "")String mc,
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "4")int size){
		return utilisateurService.chercherUtilisateurs("%"+mc+"%", PageRequest.of(page, size));
	}
	
}
