package ma.ensa.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ma.ensa.entities.EvaluationComite;

@Service
public interface IEvaluationComiteService {
	
	public List<EvaluationComite> afficherEvaluationComites();
	public EvaluationComite afficherEvaluationComiteParId(Long iEvaluationComite);
	public EvaluationComite ajouterEvaluationComite(EvaluationComite evaluationComite);
	public EvaluationComite modifierEvaluationComite(Long idEvaluationComite, EvaluationComite evaluationComite);
	public boolean supprimerEvaluationComite(Long idEvaluationComite);

}
