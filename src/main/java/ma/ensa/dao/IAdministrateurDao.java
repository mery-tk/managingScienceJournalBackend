package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.Administrateur;

@Transactional
public interface IAdministrateurDao extends JpaRepository<Administrateur, Long>{
	
}
