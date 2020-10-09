package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.EvaluationComite;

@Transactional
public interface EvaluationComiteJpaRepository extends JpaRepository<EvaluationComite, Long>{

}