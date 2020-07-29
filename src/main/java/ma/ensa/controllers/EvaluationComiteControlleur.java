package ma.ensa.controllers;

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
import ma.ensa.entities.EvaluationComite;
import ma.ensa.services.IEvaluationComiteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationComiteControlleur {
 @Autowired private IEvaluationComiteService evaluationComiteService;
	 
	 @GetMapping(value = "/evaluationComites")
	 public List<EvaluationComite> getEvaluationComites(){
		 return evaluationComiteService.afficherEvaluationComites();
	 }
	 
	 @GetMapping(value = "/evaluationComites/{idEvaluationComite}")
	 public EvaluationComite getEvaluationComiteByID(@PathVariable Long idEvaluationComite) {
		 return evaluationComiteService.afficherEvaluationComiteParId(idEvaluationComite);
	 }
	 
	 @PostMapping(value = "/evaluationComites")
	 public EvaluationComite addEvaluationComite(@RequestBody EvaluationComite EvaluationComite) {
		 return evaluationComiteService.ajouterEvaluationComite(EvaluationComite);
	 }
	 
	 @PutMapping(value = "/evaluationComites/{idEvaluationComite}")
	 public EvaluationComite editEvaluationComite(@PathVariable Long idEvaluationComite, @RequestBody EvaluationComite EvaluationComite) {
		 return evaluationComiteService.modifierEvaluationComite(idEvaluationComite, EvaluationComite);
	 }
	 
	 @DeleteMapping(value = "/evaluationComites/{idEvaluationComite}")
	 public void deleteEvaluationComite(@PathVariable Long idEvaluationComite) {
		 evaluationComiteService.supprimerEvaluationComite(idEvaluationComite);
	 }
	
}