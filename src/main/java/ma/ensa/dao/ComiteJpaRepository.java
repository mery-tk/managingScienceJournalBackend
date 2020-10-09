package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.Comite;

@Transactional
public interface ComiteJpaRepository extends JpaRepository<Comite, Long>{

}
