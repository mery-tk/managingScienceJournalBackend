package ma.ensa.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ensa.dao.IEvaluationDao;
import ma.ensa.entities.Evaluation;
import ma.ensa.services.IEvaluationService;

@Service
public class EvaluationService implements IEvaluationService{

	@Autowired 
	private IEvaluationDao evaluationDao;

	@Override
	public List<Evaluation> afficherEvaluations() {
		return evaluationDao.findAll();
	}

	@Override
	public Evaluation afficherEvaluationParId(Long idAdmin) {
		return evaluationDao.findById(idAdmin).get();
	}

	@Override
	public Evaluation ajouterEvaluation(Evaluation Evaluation) {
		return evaluationDao.save(Evaluation);
	}

	@Override
	public Evaluation modifierEvaluation(Long id, Evaluation evaluation) {
		Evaluation eval = this.afficherEvaluationParId(id);
		eval.setCommentaire(evaluation.getCommentaire());
		eval.setRapport(evaluation.getRapport());
		return evaluationDao.save(eval);
	}

	@Override
	public boolean supprimerEvaluation(Long idAdmin) {
		if(evaluationDao.findById(idAdmin).isPresent()) {
			evaluationDao.deleteById(idAdmin);
			return true;
		}
		return false;
	}
	
	
	
}