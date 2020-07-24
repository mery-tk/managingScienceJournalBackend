package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.dao.IEvaluationRefereeDao;
import ma.ensa.entities.EvaluationReferee;
import ma.ensa.services.IEvaluationRefereeService;

@Service
public class EvaluationRefereeService implements IEvaluationRefereeService{

	@Autowired
	private IEvaluationRefereeDao evaluationRefereeDao; 
	
	@Override
	public List<EvaluationReferee> afficherEvaluationReferees() {
		return evaluationRefereeDao.findAll();
	}
	

	@Override
	public EvaluationReferee afficherEvaluationRefereeParId(Long idEvaluationReferee) {
		return evaluationRefereeDao.findById(idEvaluationReferee).get();
		}

	@Override
	public EvaluationReferee ajouterEvaluationReferee(EvaluationReferee evaluationReferee) {
		return evaluationRefereeDao.save(evaluationReferee);
	}

	@Override
	public EvaluationReferee modifierEvaluationReferee(Long id, EvaluationReferee evaluationReferee) {
		EvaluationReferee evaluationRefer=this.afficherEvaluationRefereeParId(id);
		evaluationRefer.setCommentaire(evaluationReferee.getCommentaire());
		evaluationRefer.setQualificationReferee(evaluationReferee.getQualificationReferee());
		evaluationRefer.setRapport(evaluationReferee.getRapport());
		
		
		return evaluationRefer;
	}

	@Override
	public boolean supprimerEvaluationReferee(Long idEvaluationReferee) {
		if(evaluationRefereeDao.findById(idEvaluationReferee).isPresent()) {
			evaluationRefereeDao.deleteById(idEvaluationReferee);
			return true;
		}
		return false;
	}
	
	
	
	

}