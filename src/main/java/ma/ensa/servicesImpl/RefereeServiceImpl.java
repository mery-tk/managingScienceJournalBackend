package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.RefereeJpaRepository;
import ma.ensa.entities.Referee;
import ma.ensa.services.RefereeService;

@Service
public class RefereeServiceImpl implements RefereeService{
	
	@Autowired private RefereeJpaRepository refereeJpaRepository;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<Referee> afficherReferees() {
		return refereeJpaRepository.findAll();
	}

	@Override
	public Referee afficherRefereeParId(Long idReferee) {
		  Optional<Referee> Referee=refereeJpaRepository.findById(idReferee);
			
			if(!Referee.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "le Referee dont le id("+idReferee+") est introuvable");
			}
		return refereeJpaRepository.findById(idReferee).get();
	}

	@Override
	public Referee ajouterReferee(Referee referee) {
		
		String passHash=bCryptPasswordEncoder.encode(referee.getPassword());
        referee.setPassword(passHash);
		return refereeJpaRepository.save(referee);
	}

	@Override
	public Referee modifierReferee(Long idReferee, Referee referee) {
		if(!idReferee.equals(referee.getIdUtilisateur())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de le referee à mettre à jour");
		}
		
		Optional<Referee> ad=refereeJpaRepository.findById(idReferee);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "le referee dont le id est ' "+idReferee+" ' est introuvable");	
		}
		Referee ref = this.afficherRefereeParId(idReferee);
		ref.setAdresse(referee.getAdresse());
		ref.setEmail(referee.getEmail());
		ref.setNom(referee.getNom());
		ref.setPassword(referee.getPassword());
		ref.setPrenom(referee.getPrenom());
		ref.setTelephone(referee.getTelephone());
		ref.setUsername(referee.getUsername());
		ref.setNbrArticlesEvaluees(referee.getNbrArticlesEvaluees());
		return refereeJpaRepository.save(ref);
	}

	@Override
	public boolean supprimerReferee(Long idReferee) {
		if(refereeJpaRepository.findById(idReferee).isPresent()) {
			refereeJpaRepository.deleteById(idReferee);
			return true;
		}
		return false;
	}
	
}
