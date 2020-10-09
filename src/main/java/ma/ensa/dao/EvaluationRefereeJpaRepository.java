package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.EvaluationReferee;

@Transactional
public interface EvaluationRefereeJpaRepository extends JpaRepository<EvaluationReferee, Long> {

}