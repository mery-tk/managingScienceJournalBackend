package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.dao.IEvaluationComiteDao;
import ma.ensa.entities.EvaluationComite;
import ma.ensa.services.IEvaluationComiteService;

@Service
public class EvaluationComiteService implements IEvaluationComiteService {
	
	@Autowired
	private IEvaluationComiteDao evaluationComiteDao;
	

	@Override
	public List<EvaluationComite> afficherEvaluationComites() {
		return evaluationComiteDao.findAll();
	}

	@Override
	public EvaluationComite afficherEvaluationComiteParId(Long idEvaluationComite) {
		return evaluationComiteDao.findById(idEvaluationComite).get();
	}

	@Override
	public EvaluationComite ajouterEvaluationComite(EvaluationComite evaluationComite) {
		return evaluationComiteDao.save(evaluationComite);
	}

	@Override
	public EvaluationComite modifierEvaluationComite(Long idEvaluationComite, EvaluationComite evaluationComite) {
		EvaluationComite evaluationComit=this.afficherEvaluationComiteParId(idEvaluationComite);
		evaluationComit.setQualificationComite(evaluationComite.getQualificationComite());
		evaluationComit.setQualificationComite(evaluationComite.getQualificationComite());		
		return evaluationComit;
	}

	@Override
	public boolean supprimerEvaluationComite(Long idEvaluationComite) {
		if(evaluationComiteDao.findById(idEvaluationComite).isPresent()) {
			evaluationComiteDao.deleteById(idEvaluationComite);
			return true;
		}
		return false;
	}
	
	
	

}