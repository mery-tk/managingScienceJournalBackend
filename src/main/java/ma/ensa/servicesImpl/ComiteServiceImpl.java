package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.ComiteJpaRepository;
import ma.ensa.entities.Comite;
import ma.ensa.services.ComiteService;

@Service
public class ComiteServiceImpl implements ComiteService{
	
	@Autowired private ComiteJpaRepository comiteJpaRepository;

	@Override
	public List<Comite> afficherComites() {
		return comiteJpaRepository.findAll();
	}

	@Override
	public Comite afficherComiteParId(Long idComite) {
Optional<Comite> Comite=comiteJpaRepository.findById(idComite);
		
		if(!Comite.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "la Comite dont le id("+idComite+") est introuvable");
		}
		return comiteJpaRepository.findById(idComite).get();
	}

	@Override
	public Comite ajouterComite(Comite comite) {
		return comiteJpaRepository.save(comite);
	}

	@Override
	public Comite modifierComite(Long idComite, Comite comite) {
		if(!idComite.equals(comite.getIdComite())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de la comite'adresse à mettre à jour");
		}
		
		Optional<Comite> ad=comiteJpaRepository.findById(idComite);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "la comite dont le id est ' "+idComite+" ' est introuvable");	
		}
		Comite comit = this.afficherComiteParId(idComite);
		comit.setNom(comite.getNom());
		return comiteJpaRepository.save(comit);
	}

	@Override
	public boolean supprimerComite(Long idComite) {
		if(comiteJpaRepository.findById(idComite).isPresent()) {
			comiteJpaRepository.deleteById(idComite);
			return true;
		}
		return false;
	}
}
