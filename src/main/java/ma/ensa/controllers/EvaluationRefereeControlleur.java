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
import ma.ensa.entities.EvaluationReferee;
import ma.ensa.services.IEvaluationRefereeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationRefereeControlleur {
	
	@Autowired private IEvaluationRefereeService evaluationRefereeService;
	 
	 @GetMapping(value = "/evaluationReferees")
	 public List<EvaluationReferee> getEvaluationReferees(){
		 return evaluationRefereeService.afficherEvaluationReferees();
	 }
	 
	 @GetMapping(value = "/evaluationReferees/{idEvaluationReferee}")
	 public EvaluationReferee getEvaluationRefereeByID(@PathVariable Long idEvaluationReferee) {
		 return evaluationRefereeService.afficherEvaluationRefereeParId(idEvaluationReferee);
	 }
	 
	 @PostMapping(value = "/evaluationReferees")
	 public EvaluationReferee addEvaluationReferee(@RequestBody EvaluationReferee EvaluationReferee) {
		 return evaluationRefereeService.ajouterEvaluationReferee(EvaluationReferee);
	 }
	 
	 @PutMapping(value = "/evaluationReferees/{idEvaluationReferee}")
	 public EvaluationReferee editEvaluationReferee(@PathVariable Long idEvaluationReferee, @RequestBody EvaluationReferee EvaluationReferee) {
		 return evaluationRefereeService.modifierEvaluationReferee(idEvaluationReferee, EvaluationReferee);
	 }
	 
	 @DeleteMapping(value = "/evaluationReferees/{idEvaluationReferee}")
	 public void deleteEvaluationReferee(@PathVariable Long idEvaluationReferee) {
		 evaluationRefereeService.supprimerEvaluationReferee(idEvaluationReferee);
	 }
	
}