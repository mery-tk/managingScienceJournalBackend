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

import ma.ensa.entities.ComiteEditoriale;
import ma.ensa.services.IComiteEditorialeService;


@RestController
public class ComiteEditorialeController {
	
	@Autowired private IComiteEditorialeService comiteEditorialeService;

	@GetMapping(value = "/comiteEditoriales")
	public List<ComiteEditoriale> getComites(){
		return comiteEditorialeService.afficherComiteEditoriales();
	}
	
	@GetMapping(value = "/comiteEditoriales/{idComite}")
	public ComiteEditoriale getComite(@PathVariable Long idComite) {
		return comiteEditorialeService.afficherComiteEditorialeParId(idComite);
	}
	
	@PostMapping(value = "/comiteEditoriales")
	public ComiteEditoriale addComite(@RequestBody ComiteEditoriale comiteEditoriale) {
		return comiteEditorialeService.ajouterComiteEditoriale(comiteEditoriale);
	}
	
	@PutMapping(value = "/comiteEditoriales/{idComite}")
	public ComiteEditoriale editComite(@PathVariable Long idComite, @RequestBody ComiteEditoriale comiteEditoriale) {
		return comiteEditorialeService.modifierComiteEditoriale(idComite, comiteEditoriale);
	}
	
	@DeleteMapping(value = "/comiteEditoriales/{idComite}")
	public void deleteComite(@PathVariable Long idComite) {
		comiteEditorialeService.supprimerComiteEditoriale(idComite);
	}
}
