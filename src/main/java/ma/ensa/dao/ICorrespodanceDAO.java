package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.Correspondance;
import ma.ensa.entities.Correspondance_PK;

@Transactional
public interface ICorrespodanceDAO extends JpaRepository<Correspondance, Correspondance_PK>{

}
