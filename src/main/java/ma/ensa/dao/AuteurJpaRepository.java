package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.entities.Auteur;

@Transactional
public interface AuteurJpaRepository extends JpaRepository<Auteur, Long>{

	public Auteur findByUsername(String username);
}
