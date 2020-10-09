package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.EvaluationJpaRepository;
import ma.ensa.entities.Evaluation;
import ma.ensa.services.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService{

	@Autowired 
	private EvaluationJpaRepository evaluationJpaRepository;

	@Override
	public List<Evaluation> afficherEvaluations() {
		return evaluationJpaRepository.findAll();
	}

	@Override
	public Evaluation afficherEvaluationParId(Long idAdmin) {
		  Optional<Evaluation> Evaluation=evaluationJpaRepository.findById(idAdmin);
			
			if(!Evaluation.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'Evaluation dont le id("+idAdmin+") est introuvable");
			}
		return evaluationJpaRepository.findById(idAdmin).get();
	}

	@Override
	public Evaluation ajouterEvaluation(Evaluation Evaluation) {
		
		return evaluationJpaRepository.save(Evaluation);
	}

	@Override
	public Evaluation modifierEvaluation(Long id, Evaluation evaluation) {
		
		if(!id.equals(evaluation.getIdEvaluation())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'evaluation à mettre à jour");
		}
		
		Optional<Evaluation> ad=evaluationJpaRepository.findById(id);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'evaluation dont le id est ' "+id+" ' est introuvable");	
		}
		
		
		Evaluation eval = this.afficherEvaluationParId(id);
		return evaluationJpaRepository.save(eval);
	}

	@Override
	public boolean supprimerEvaluation(Long idAdmin) {
		if(evaluationJpaRepository.findById(idAdmin).isPresent()) {
			evaluationJpaRepository.deleteById(idAdmin);
			return true;
		}
		return false;
	}
	
	
	
}