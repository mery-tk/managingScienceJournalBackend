package ma.ensa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ma.ensa.entities.EvaluationReferee;

@Service
public interface EvaluationRefereeService {
	
	public List<EvaluationReferee> afficherEvaluationReferees();
	public EvaluationReferee afficherEvaluationRefereeParId(Long idEvaluationReferee);
	public EvaluationReferee ajouterEvaluationReferee(EvaluationReferee evaluationReferee);
	public EvaluationReferee modifierEvaluationReferee(Long id, EvaluationReferee evaluationReferee);
	public boolean supprimerEvaluationReferee(Long idEvaluationReferee);

}