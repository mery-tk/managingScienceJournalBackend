package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.AdministrateurJpaRepository;
import ma.ensa.entities.Administrateur;
import ma.ensa.services.AdministrateurService;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{

	@Autowired private AdministrateurJpaRepository administrateurJpaRepository ;

	@Override
	public List<Administrateur> afficherAdministrateurs() {
		return administrateurJpaRepository.findAll();
	}

	@Override
	public Administrateur afficherAdminParId(Long idAdmin) {
		  Optional<Administrateur> administrateur=administrateurJpaRepository.findById(idAdmin);
			
			if(!administrateur.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'Administrateur dont le id("+idAdmin+") est introuvable");
			}
		return administrateurJpaRepository.findById(idAdmin).get();
	}

	@Override
	public Administrateur ajouterAdmin(Administrateur administrateur) {
		
		return administrateurJpaRepository.save(administrateur);
	}

	@Override
	public Administrateur modifierAdmin(Long idAdmin, Administrateur administrateur) {
		if(!idAdmin.equals(administrateur.getIdUtilisateur())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'administrateur à mettre à jour");
		}
		
		Optional<Administrateur> ad=administrateurJpaRepository.findById(idAdmin);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'administrateur dont le id est ' "+idAdmin+" ' est introuvable");	
		}
		
		
		
		Administrateur admin = this.afficherAdminParId(idAdmin);
		admin.setAdresse(administrateur.getAdresse());
		admin.setDebutfonction(administrateur.getDebutfonction());
		admin.setEmail(administrateur.getEmail());
		admin.setFinFonction(administrateur.getFinFonction());
		admin.setNom(administrateur.getNom());
		admin.setPassword(administrateur.getPassword());
		admin.setPrenom(administrateur.getPrenom());
		admin.setTelephone(administrateur.getTelephone());
		admin.setUsername(administrateur.getUsername());
		return administrateurJpaRepository.save(admin);
	}

	@Override
	public boolean supprimerAdministrateur(Long idAdmin) {
		
		if(administrateurJpaRepository.findById(idAdmin).isPresent()) {
			administrateurJpaRepository.deleteById(idAdmin);
			return true;
		}
		
		
		return false;
	}

	
	
	
}
