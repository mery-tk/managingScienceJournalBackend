package ma.ensa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ma.ensa.entities.Administrateur;

@Service
public interface IAdministrateurService {

	public List<Administrateur> afficherAdministrateurs();
	public Administrateur afficherAdminParId(Long idAdmin);
	public Administrateur ajouterAdmin(Administrateur administrateur);
	public Administrateur modifierAdmin(Long idAdmin, Administrateur administrateur);
	public boolean supprimerAdministrateur(Long idAdmin);
	
	
}
