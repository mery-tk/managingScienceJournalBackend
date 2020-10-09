package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.CorrespodanceJpaRepository;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Correspondance_PK;
import ma.ensa.services.CorrespondanceService;

@Service 
public class CorrespondanceServiceImpl implements CorrespondanceService{

	@Autowired private CorrespodanceJpaRepository correspodanceJpaRepository;

	@Override
	public List<Correspondance> afficherCorrespondanceistrateurs() {
		return correspodanceJpaRepository.findAll();
	}

	@Override
	public Correspondance afficherCorrespondanceParId(Correspondance_PK idCorrespondance) {
Optional<Correspondance> Correspondance=correspodanceJpaRepository.findById(idCorrespondance);
		
		if(!Correspondance.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "la Correspondance dont le id("+idCorrespondance+") est introuvable");
		}
		return correspodanceJpaRepository.findById(idCorrespondance).get();
	}

	@Override
	public Correspondance ajouterCorrespondance(Correspondance correspondance) {

		return correspodanceJpaRepository.save(correspondance);
	}

	@Override
	public Correspondance modifierCorrespondance(Long idCorrespondance, Correspondance correspondance) {
		return null;
	}
	

	
}
