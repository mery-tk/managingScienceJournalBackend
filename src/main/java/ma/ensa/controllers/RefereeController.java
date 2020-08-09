package ma.ensa.controllers;

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

import ma.ensa.entities.Article;
import ma.ensa.entities.Evaluation;
import ma.ensa.entities.EvaluationReferee;
import ma.ensa.entities.Referee;
import ma.ensa.services.IRefereeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RefereeController {

	@Autowired private IRefereeService refereeService;
	
	@GetMapping(value = "/referees")
	public List<Referee> getReferees(){
		return refereeService.afficherReferees();
	}
	
	@GetMapping(value = "/referees/{idReferee}")
	public Referee getRefereeByID(@PathVariable Long idReferee) {
		return refereeService.afficherRefereeParId(idReferee);
	}
	
	@PostMapping(value = "/referees")
	public Referee addReferee(@RequestBody Referee referee) {
		return refereeService.ajouterReferee(referee);
	}
	
	@PutMapping(value = "/referees/{idReferee}")
	public Referee editReferee(@PathVariable Long idReferee, @RequestBody Referee referee) {
		return refereeService.modifierReferee(idReferee, referee);
	}
	
	@DeleteMapping(value = "/referees/{idReferee}")
	public void deleteReferee(@PathVariable Long idReferee) {
		refereeService.supprimerReferee(idReferee);
	}
	
	@GetMapping(value = "/referees/{idReferee}/articles")
	public List<Article> getArticlesAEvaluer(@PathVariable Long idReferee){
		Referee referee = refereeService.afficherRefereeParId(idReferee);
		List<Article> articles = referee.getArticles();
		return articles;
	}
	
}
