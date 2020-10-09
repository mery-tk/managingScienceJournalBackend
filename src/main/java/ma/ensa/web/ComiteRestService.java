package ma.ensa.web;

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

import ma.ensa.entities.Comite;
import ma.ensa.services.ComiteService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ComiteRestService {
	
	@Autowired private ComiteService comiteEditorialeService;

	@GetMapping(value = "/comiteEditoriales")
	public List<Comite> getComites(){
		return comiteEditorialeService.afficherComites();
	}
	
	@GetMapping(value = "/comiteEditoriales/{idComite}")
	public Comite getComite(@PathVariable Long idComite) {
		return comiteEditorialeService.afficherComiteParId(idComite);
	}
	
	@PostMapping(value = "/comiteEditoriales")
	public Comite addComite(@RequestBody Comite comiteEditoriale) {
		return comiteEditorialeService.ajouterComite(comiteEditoriale);
	}
	
	@PutMapping(value = "/comiteEditoriales/{idComite}")
	public Comite editComite(@PathVariable Long idComite, @RequestBody Comite comiteEditoriale) {
		return comiteEditorialeService.modifierComite(idComite, comiteEditoriale);
	}
	
	@DeleteMapping(value = "/comiteEditoriales/{idComite}")
	public void deleteComite(@PathVariable Long idComite) {
		comiteEditorialeService.supprimerComite(idComite);
	}
}
