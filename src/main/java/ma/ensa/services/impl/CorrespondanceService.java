package ma.ensa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.dao.ICorrespodanceDAO;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Correspondance_PK;
import ma.ensa.services.ICorrespondanceService;

@Service 
public class CorrespondanceService implements ICorrespondanceService{

	@Autowired private ICorrespodanceDAO correspodanceDAO;

	@Override
	public List<Correspondance> afficherCorrespondanceistrateurs() {
		return correspodanceDAO.findAll();
	}

	@Override
	public Correspondance afficherCorrespondanceParId(Correspondance_PK idCorrespondance) {
		return correspodanceDAO.findById(idCorrespondance).get();
	}

	@Override
	public Correspondance ajouterCorrespondance(Correspondance correspondance) {
		return correspodanceDAO.save(correspondance);
	}

	@Override
	public Correspondance modifierCorrespondance(Long idCorrespondance, Correspondance correspondance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
