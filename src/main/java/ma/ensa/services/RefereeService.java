package ma.ensa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.ensa.entities.Referee;

@Service
public interface RefereeService {
	
	public List<Referee> afficherReferees();
	public Referee afficherRefereeParId(Long idReferee);
	public Referee ajouterReferee(Referee referee);
	public Referee modifierReferee(Long idReferee, Referee referee);
	public boolean supprimerReferee(Long idReferee);


}
