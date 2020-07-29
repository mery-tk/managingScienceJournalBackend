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
import ma.ensa.entities.Evaluation;
import ma.ensa.services.IEvaluationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluationControlleur {

 @Autowired private IEvaluationService evaluationService;
	 
	 @GetMapping(value = "/evaluations")
	 public List<Evaluation> getEvaluations(){
		 return evaluationService.afficherEvaluations();
	 }
	 
	 @GetMapping(value = "/evaluations/{idEvaluation}")
	 public Evaluation getEvaluationByID(@PathVariable Long idEvaluation) {
		 return evaluationService.afficherEvaluationParId(idEvaluation);
	 }
	 
	 @PostMapping(value = "/evaluations")
	 public Evaluation addEvaluation(@RequestBody Evaluation Evaluation) {
		 return evaluationService.ajouterEvaluation(Evaluation);
	 }
	 
	 @PutMapping(value = "/evaluations/{idEvaluation}")
	 public Evaluation editEvaluation(@PathVariable Long idEvaluation, @RequestBody Evaluation Evaluation) {
		 return evaluationService.modifierEvaluation(idEvaluation, Evaluation);
	 }
	 
	 @DeleteMapping(value = "/evaluations/{idEvaluation}")
	 public void deleteEvaluation(@PathVariable Long idEvaluation) {
		 evaluationService.supprimerEvaluation(idEvaluation);
	 }
}