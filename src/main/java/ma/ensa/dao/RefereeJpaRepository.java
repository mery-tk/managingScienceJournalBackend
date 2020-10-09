package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.Referee;

@Transactional
public interface RefereeJpaRepository  extends JpaRepository<Referee, Long>{

}
