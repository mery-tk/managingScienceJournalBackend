package ma.ensa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Correspondance_PK;

@Service
public interface ICorrespondanceService {
	

	public List<Correspondance> afficherCorrespondanceistrateurs();
	public Correspondance afficherCorrespondanceParId(Correspondance_PK idCorrespondance);
	public Correspondance ajouterCorrespondance(Correspondance correspondance);
	public Correspondance modifierCorrespondance(Long idCorrespondance, Correspondance  correspondance);


}
