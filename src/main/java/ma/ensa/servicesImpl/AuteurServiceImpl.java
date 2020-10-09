package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.AuteurJpaRepository;
import ma.ensa.entities.Auteur;
import ma.ensa.services.AuteurService;

@Service
public class AuteurServiceImpl implements AuteurService{

	@Autowired private AuteurJpaRepository auteurJpaRepository;
	
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<Auteur> afficherAuteurs() {
		return auteurJpaRepository.findAll();
	}

	@Override
	public Auteur afficherAuteurParId(Long idAuteur) {
Optional<Auteur> Auteur=auteurJpaRepository.findById(idAuteur);
		
		if(!Auteur.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'Auteur dont le id("+idAuteur+") est introuvable");
		}
		return auteurJpaRepository.findById(idAuteur).get();
	}

	@Override
	public Auteur ajouterAuteur(Auteur auteur) {
		
		String passHash=bCryptPasswordEncoder.encode(auteur.getPassword());
        auteur.setPassword(passHash);
		return auteurJpaRepository.save(auteur);
		
		
		
		
	}

	@Override
	public Auteur modifierAuteur(Long idAuteur, Auteur auteur) {
		if(!idAuteur.equals(auteur.getIdUtilisateur())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'auteur à mettre à jour");
		}
		
		Optional<Auteur> ad=auteurJpaRepository.findById(idAuteur);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'auteur dont le id est ' "+idAuteur+" ' est introuvable");	
		}
		Auteur aut = this.afficherAuteurParId(idAuteur);
		aut.setAdresse(auteur.getAdresse());
		aut.setEmail(auteur.getEmail());
		aut.setNom(auteur.getNom());
		aut.setPassword(auteur.getPassword());
		aut.setPrenom(auteur.getPrenom());
		aut.setTelephone(auteur.getTelephone());
		aut.setUsername(auteur.getUsername());
		aut.setNbrArticlesEcrites(auteur.getNbrArticlesEcrites());
		return auteurJpaRepository.save(aut);
	}

	@Override
	public boolean supprimerAuteur(Long idAuteur) {
		if(auteurJpaRepository.findById(idAuteur).isPresent()) {
			auteurJpaRepository.deleteById(idAuteur);
			return true;
		}
		return false;	
	}
	

	
	
}
