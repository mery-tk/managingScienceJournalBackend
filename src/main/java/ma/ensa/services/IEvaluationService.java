package ma.ensa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ma.ensa.entities.Evaluation;

@Service
public interface IEvaluationService {

	public List<Evaluation> afficherEvaluations();
	public Evaluation afficherEvaluationParId(Long idEvaluation);
	public Evaluation ajouterEvaluation(Evaluation evaluation);
	public Evaluation modifierEvaluation(Long idEvaluation, Evaluation evaluation);
	public boolean supprimerEvaluation(Long id);
}