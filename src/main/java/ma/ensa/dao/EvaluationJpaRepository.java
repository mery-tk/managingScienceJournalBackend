package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.Evaluation;

@Transactional
public interface EvaluationJpaRepository extends JpaRepository<Evaluation, Long>{

}