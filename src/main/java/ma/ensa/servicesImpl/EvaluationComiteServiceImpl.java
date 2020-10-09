package ma.ensa.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import ma.ensa.dao.EvaluationComiteJpaRepository;
import ma.ensa.entities.EvaluationComite;
import ma.ensa.services.EvaluationComiteService;

@Service
public class EvaluationComiteServiceImpl implements EvaluationComiteService {
	
	@Autowired
	private EvaluationComiteJpaRepository evaluationComiteJpaRepository;
	

	@Override
	public List<EvaluationComite> afficherEvaluationComites() {
		return evaluationComiteJpaRepository.findAll();
	}

	@Override
	public EvaluationComite afficherEvaluationComiteParId(Long idEvaluationComite) {
Optional<EvaluationComite> Eval=evaluationComiteJpaRepository.findById(idEvaluationComite);
		
		if(!Eval.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'EvaluationComite dont le id("+idEvaluationComite+") est introuvable");
		}
		return evaluationComiteJpaRepository.findById(idEvaluationComite).get();
	}

	@Override
	public EvaluationComite ajouterEvaluationComite(EvaluationComite evaluationComite) {
		
		return evaluationComiteJpaRepository.save(evaluationComite);
	}

	@Override
	public EvaluationComite modifierEvaluationComite(Long idEvaluationComite, EvaluationComite evaluationComite) {
		if(!idEvaluationComite.equals(evaluationComite.getIdEvaluation())) {
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "le 'id' dans l'IRI ne correspond pas à celui de l'EvaluationComite à mettre à jour");
		}
		
		Optional<EvaluationComite> ad=evaluationComiteJpaRepository.findById(idEvaluationComite);
		
		if(!ad.isPresent()) {			
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "l'EvaluationComite dont le id est ' "+idEvaluationComite+" ' est introuvable");	
		}
		
		
		EvaluationComite evaluationComit=this.afficherEvaluationComiteParId(idEvaluationComite);
		evaluationComit.setQualificationComite(evaluationComite.getQualificationComite());
		evaluationComit.setQualificationComite(evaluationComite.getQualificationComite());		
		return evaluationComit;
	}

	@Override
	public boolean supprimerEvaluationComite(Long idEvaluationComite) {
		if(evaluationComiteJpaRepository.findById(idEvaluationComite).isPresent()) {
			evaluationComiteJpaRepository.deleteById(idEvaluationComite);
			return true;
		}
		return false;
	}
	
	
	
	

}