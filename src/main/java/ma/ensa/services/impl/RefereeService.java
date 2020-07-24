package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.dao.IRefereeDao;
import ma.ensa.entities.Referee;
import ma.ensa.services.IRefereeService;

@Service
public class RefereeService implements IRefereeService{
	@Autowired private IRefereeDao refereeDao;

	@Override
	public List<Referee> afficherReferees() {
		return refereeDao.findAll();
	}

	@Override
	public Referee afficherRefereeParId(Long idReferee) {
		return refereeDao.findById(idReferee).get();
	}

	@Override
	public Referee ajouterReferee(Referee referee) {
		return refereeDao.save(referee);
	}

	@Override
	public Referee modifierReferee(Long idReferee, Referee referee) {
		Referee ref = this.afficherRefereeParId(idReferee);
		ref.setAdresse(referee.getAdresse());
		ref.setEmail(referee.getEmail());
		ref.setNom(referee.getNom());
		ref.setPassword(referee.getPassword());
		ref.setPrenom(referee.getPrenom());
		ref.setTelephone(referee.getTelephone());
		ref.setUsername(referee.getUsername());
		ref.setNbrArticlesEvaluees(referee.getNbrArticlesEvaluees());
		return refereeDao.save(ref);
	}

	@Override
	public boolean supprimerReferee(Long idReferee) {
		if(refereeDao.findById(idReferee).isPresent()) {
			refereeDao.deleteById(idReferee);
			return true;
		}
		return false;
	}
	
	

}
