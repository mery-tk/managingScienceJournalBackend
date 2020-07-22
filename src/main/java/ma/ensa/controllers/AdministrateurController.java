package ma.ensa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.Administrateur;
import ma.ensa.services.IAdministrateurService;

@RestController
public class AdministrateurController {

	 @Autowired private IAdministrateurService administrateurService;
	 
	 @GetMapping(value = "/administrateurs")
	 public List<Administrateur> getAdmins(){
		 return administrateurService.afficherAdministrateurs();
	 }
	 
	 @GetMapping(value = "/administrateurs/{idAdmin}")
	 public Administrateur getAdminByID(@PathVariable Long idAdmin) {
		 return administrateurService.afficherAdminParId(idAdmin);
	 }
	 
	 @PostMapping(value = "/administrateurs")
	 public Administrateur addAdmin(@RequestBody Administrateur administrateur) {
		 return administrateurService.ajouterAdmin(administrateur);
	 }
	 
	 @PutMapping(value = "/administrateurs/{idAdmin}")
	 public Administrateur editAdmin(@PathVariable Long idAdmin, @RequestBody Administrateur administrateur) {
		 return administrateurService.modifierAdmin(idAdmin, administrateur);
	 }
	 
	 @DeleteMapping(value = "/administrateurs/{idAdmin}")
	 public void deleteAdmin(@PathVariable Long idAdmin) {
		 administrateurService.supprimerAdministrateur(idAdmin);
	 }
	
}
