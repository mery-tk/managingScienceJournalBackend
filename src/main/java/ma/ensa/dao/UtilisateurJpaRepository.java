package ma.ensa.dao;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.ensa.entities.Utilisateur;

@Transactional
public interface UtilisateurJpaRepository extends JpaRepository<Utilisateur, Long>{
	
	@Query("select u from Utilisateur u where nom like :mc or prenom like :mc")
	public Page<Utilisateur> getPage(@Param("mc") String mc, Pageable pageable);


	public Utilisateur findByUsername(String username);

}
