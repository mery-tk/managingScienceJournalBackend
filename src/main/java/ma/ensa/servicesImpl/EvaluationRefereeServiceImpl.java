package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.EvaluationRefereeJpaRepository;
import ma.ensa.entities.EvaluationReferee;
import ma.ensa.services.EvaluationRefereeService;

@Service
public class EvaluationRefereeServiceImpl implements EvaluationRefereeService{

	@Autowired
	private EvaluationRefereeJpaRepository evaluationRefereeJpaRepository; 
	
	@Override
	public List<EvaluationReferee> afficherEvaluationReferees() {
		return evaluationRefereeJpaRepository.findAll();
	}
	

	@Override
	public EvaluationReferee afficherEvaluationRefereeParId(Long idEvaluationReferee) {
		  Optional<EvaluationReferee> EvaluationReferee=evaluationRefereeJpaRepository.findById(idEvaluationReferee);
			
			if(!EvaluationReferee.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'EvaluationReferee dont le id("+idEvaluationReferee+") est introuvable");
			}
		return evaluationRefereeJpaRepository.findById(idEvaluationReferee).get();
		}

	@Override
	public EvaluationReferee ajouterEvaluationReferee(EvaluationReferee evaluationReferee) {
		
		return evaluationRefereeJpaRepository.save(evaluationReferee);
	}

	@Override
	public EvaluationReferee modifierEvaluationReferee(Long id, EvaluationReferee evaluationReferee) {
		if(!id.equals(evaluationReferee.getIdEvaluation())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'evaluationReferee à mettre à jour");
		}
		
		Optional<EvaluationReferee> ad=evaluationRefereeJpaRepository.findById(id);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'evaluationReferee dont le id est ' "+id+" ' est introuvable");	
		}
		
		
		EvaluationReferee evaluationRefer=this.afficherEvaluationRefereeParId(id);
		evaluationRefer.setCommentaire(evaluationReferee.getCommentaire());
		evaluationRefer.setQualificationReferee(evaluationReferee.getQualificationReferee());
		evaluationRefer.setRapport(evaluationReferee.getRapport());
		
		
		return evaluationRefer;
	}

	@Override
	public boolean supprimerEvaluationReferee(Long idEvaluationReferee) {
		if(evaluationRefereeJpaRepository.findById(idEvaluationReferee).isPresent()) {
			evaluationRefereeJpaRepository.deleteById(idEvaluationReferee);
			return true;
		}
		return false;
	}
	
	

}